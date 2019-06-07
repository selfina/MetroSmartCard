package com.delhi.metro.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.delhi.metro.model.CardTrx;
import com.delhi.metro.model.SmartCard;

public class InMemoryCardTrxRepository {

	
//	private ConcurrentMap<SmartCard, CardTrx> transientStore= new ConcurrentHashMap<>();
//	private ConcurrentMap<SmartCard, List<CardTrx>> completeTraxlist= new ConcurrentHashMap<>();
//
	
	private ConcurrentMap<SmartCard, CardTrx> transientTrxStore = new ConcurrentHashMap<>();
    private ConcurrentMap<SmartCard, List<CardTrx>> completedTrxStore = new ConcurrentHashMap<>();
	
	 public void addCompletedTrx(SmartCard card, CardTrx trx){
	        completedTrxStore.putIfAbsent(card, new ArrayList<>());
	        completedTrxStore.get(card).add(trx);
	    }

	    public List<CardTrx> getCompletedTrxs(SmartCard card) {
	        return completedTrxStore.getOrDefault(card, Collections.emptyList());
	    }
	    public void addTransientTrx(SmartCard card, CardTrx trx){
	        transientTrxStore.put(card, trx);
	    }

	    public CardTrx getTransientTrx(SmartCard card) {
	        return transientTrxStore.remove(card);
	    }
	
	
}
