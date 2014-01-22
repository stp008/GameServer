package com.github.stp008.messageSystem.messages;

import com.github.stp008.messageSystem.Abonent;
import com.github.stp008.messageSystem.Address;

public abstract class Message {
	final private Address from;
	final private Address to;
	
	public Message(Address from, Address to){
		this.from = from;
		this.to = to;
	}
	
	public Address getFrom() {
		return from;
	}

	public Address getTo() {
		return to;
	}
	
	public abstract void exec(Abonent abonent);	
}
