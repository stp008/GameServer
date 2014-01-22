package com.github.stp008.messageSystem.messages;

import com.github.stp008.accountService.AccountService;
import com.github.stp008.messageSystem.Abonent;
import com.github.stp008.messageSystem.Address;

public abstract class MessageToAS extends Message{
	
	public MessageToAS(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		// TODO Auto-generated method stub
		if (abonent instanceof AccountService) {
			exec((AccountService) abonent);
		} else {
			System.err.println("Сущность не является AccountService");
			throw new RuntimeException();
		}
		
	}
	
	public abstract void exec (AccountService accountService);
	
}
