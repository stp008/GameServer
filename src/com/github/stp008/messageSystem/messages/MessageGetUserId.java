package com.github.stp008.messageSystem.messages;

import com.github.stp008.accountService.AccountService;
import com.github.stp008.messageSystem.Address;

public class MessageGetUserId extends MessageToAS{
	final private String name;
	
	public MessageGetUserId(Address from, Address to, String name) {
		super(from, to);
		this.name = name;		
	}

	@Override
	public void exec(AccountService accountService) {		
		Integer id = accountService.getId(name);
		if (id == null) id = -1;
		accountService.getMessageSystem().sendMessage(new MessageUpdateUserId(this.getTo(),
				this.getFrom(), name, id));		
	}
	
	
	
}
