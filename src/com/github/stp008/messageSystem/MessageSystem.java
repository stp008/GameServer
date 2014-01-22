package com.github.stp008.messageSystem;

import com.github.stp008.messageSystem.messages.Message;
import com.github.stp008.messageSystem.AddressService;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;  

public class MessageSystem {
	
			private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<Address, ConcurrentLinkedQueue<Message>>();
			private AddressService service = new AddressService();
			
			public void addService(Abonent abonent){
				service.setAddress(abonent);
				messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Message>());
			}
			
			public void sendMessage(Message message){
				messages.get(message.getTo()).add(message);				
			}
			
			public void execForAbonent(Abonent abonent){
				Queue<Message> messageQueue = messages.get(abonent.getAddress());
				while (!messageQueue.isEmpty()){					
					messageQueue.poll().exec(abonent);
				}				
			}
			
			public AddressService getAddressService() {
				return service;
			}
			
}