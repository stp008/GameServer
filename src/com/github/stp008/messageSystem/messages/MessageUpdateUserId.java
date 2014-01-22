package com.github.stp008.messageSystem.messages;

import com.github.stp008.frontend.Frontend;
import com.github.stp008.messageSystem.Address;

public class MessageUpdateUserId extends MessageToFrontend{

	final private String name;
	final private int userId;
	
	public MessageUpdateUserId(Address from, Address to, String name,
			int userId) {
		super(from, to);
		this.name = name;
		this.userId = userId;
	}

	@Override
	public void exec(Frontend frontend) {
		frontend.updateUserId(name, userId);
	}

}
