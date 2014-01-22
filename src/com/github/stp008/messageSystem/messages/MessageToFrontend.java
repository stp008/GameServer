package com.github.stp008.messageSystem.messages;

import com.github.stp008.frontend.Frontend;
import com.github.stp008.messageSystem.Abonent;
import com.github.stp008.messageSystem.Address;

public abstract class MessageToFrontend extends Message {
	
	public MessageToFrontend(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {		
		if (abonent instanceof Frontend){
			exec((Frontend) abonent);
		} else {
			System.err.println("Сущность не является Frontend");
			throw new RuntimeException();			
		}
	}

	public abstract void exec(Frontend frontend);
}