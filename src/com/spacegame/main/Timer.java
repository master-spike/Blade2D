package com.spacegame.main;

import java.util.ArrayList;

public class Timer {
	
	private int countdown;
	private ArrayList<AbstractEvent> events;
	public Timer(int c, ArrayList<AbstractEvent> es) {
		countdown = c;
		this.events = es;
	}
	public void decrement() {
		if (countdown >= 0) countdown--;
		if (countdown == 0) for (AbstractEvent e : events) e.trigger();
	}

}
