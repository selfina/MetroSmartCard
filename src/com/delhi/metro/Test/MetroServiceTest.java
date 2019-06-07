package com.delhi.metro.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;

import com.delhi.metro.impl.WeekdayFareStrategy;
import com.delhi.metro.impl.WeekendFareStrategy;
import com.delhi.metro.model.CardTrx;
import com.delhi.metro.model.SmartCard;
import com.delhi.metro.model.Station;
import com.delhi.metro.model.Traveller;
import com.delhi.metro.services.InsufficientCardBalance;
import com.delhi.metro.services.MetroService;
import com.delhi.metro.services.MinimumCardBalanceException;

public class MetroServiceTest {
    private MetroService metroService = new MetroService();

    private SmartCard card;

    @Before
    public void setUp() throws Exception {
        card = new SmartCard();
        card.setId(1);
        card.setBalance(100);
        card.setTraveller(new Traveller(1L, "Munish"));
    }

    @Test
    public void testCalculateFootFallForStation() throws Exception {
        metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
        metroService.swipeOut(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 8, 18, 35));

        metroService.swipeIn(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 10, 19, 05));
        metroService.swipeOut(card, Station.A10, LocalDateTime.of(2016, Month.APRIL, 10, 19, 15));

        assertThat("FootFall for station A6 should be 2", metroService.calculateFootFall(Station.A6), equalTo(2));
        assertThat("FootFall for station A1 should be 1", metroService.calculateFootFall(Station.A1), equalTo(1));
        assertThat("FootFall for station A10 should be 1", metroService.calculateFootFall(Station.A10), equalTo(1));
    }

    @Test
    public void testCardReport() throws Exception {
        metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
        metroService.swipeOut(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 8, 18, 35));

        metroService.swipeIn(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 10, 19, 05));
        metroService.swipeOut(card, Station.A10, LocalDateTime.of(2016, Month.APRIL, 10, 19, 15));
        final List<CardTrx> trxs = metroService.cardReport(card);

        assertThat("There should be 2 trxs for this card", trxs.size(), equalTo(2));
        assertThat("One of the Trx should be charged 35", trxs.toArray(new CardTrx[0]), hasItemInArray(new CustomMatcher<CardTrx>("Fare shall be 35") {
            @Override
            public boolean matches(Object o) {
                CardTrx trx = (CardTrx) o;
                return trx.getFare() == 35.0 && trx.getFareStrategyUsed() instanceof WeekdayFareStrategy && trx.getDistance()== 5;
            }
        }));

        assertThat("Other Trx should be charged 35", trxs.toArray(new CardTrx[0]), hasItemInArray(new CustomMatcher<CardTrx>("Fare shall be 35") {
            @Override
            public boolean matches(Object o) {
                CardTrx trx = (CardTrx) o;
                return trx.getFare() == 22.0 && trx.getFareStrategyUsed() instanceof WeekendFareStrategy && trx.getDistance() == 4;
            }
        }));
    }

    @Test(expected = MinimumCardBalanceException.class)
    public void testMinimumBalanceAtSwipeIn() throws Exception {
        card.setBalance(1);
        metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
    }

    @Test(expected = InsufficientCardBalance.class)
    public void testSufficientBalanceAtSwipeOut() throws Exception {
        card.setBalance(10);
        metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
        metroService.swipeOut(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 8, 18, 35));
    }
}
