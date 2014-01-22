package com.github.stp008.utils;

public class ThreadSleep {
			public static void sleep() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public static void sleep(int millis){
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
