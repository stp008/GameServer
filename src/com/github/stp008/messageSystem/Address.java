package com.github.stp008.messageSystem;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
			private static AtomicInteger abonentIdCreator = new AtomicInteger();
			final private int abonentId;
			
			public Address(){
				this.abonentId = abonentIdCreator.incrementAndGet();
			}
			
			@Override
			public int hashCode() {
				return abonentId;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Address other = (Address) obj;
				if (this.abonentId == other.abonentId)
					return true;
				return false;
			}			
}
