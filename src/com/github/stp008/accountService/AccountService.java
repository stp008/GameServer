package com.github.stp008.accountService;

import java.util.HashMap;
import java.util.Map;

import com.github.stp008.messageSystem.Abonent;
import com.github.stp008.messageSystem.Address;
import com.github.stp008.messageSystem.MessageSystem;
import com.github.stp008.utils.ThreadSleep;

public class AccountService implements Runnable, Abonent {

	private Address address;
	private MessageSystem MS;
	private Map<String, Integer> namesById = new HashMap<String, Integer>();
	
	public AccountService(MessageSystem MS) {
		this.MS = MS;
		this.address = new Address();
		this.MS.addService(this);
		setId("Riddick", 2);
		setId("Hulk", 4);
		setId("Batman", 5);
		setId("SpiderMan", 3);
		setId("RS7", 1);
	}
	
	public Integer getId(String name) {
		return namesById.get(name);
	}
	
	public void setId(String name, Integer id){
		namesById.put(name, id);
	}
	
	@Override
	public Address getAddress() {
		return address;
	}
	
	public MessageSystem getMessageSystem(){
		return MS;
	}

	@Override
	public void run() {
		while (true) {
			MS.execForAbonent(this);
			ThreadSleep.sleep(5000);
		}
		
	}	
}
