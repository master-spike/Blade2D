package com.spacegame.main;

import java.util.ArrayList;

public class Timer {
	
	private int countdown;
	protected ArrayList<AbstractEvent> events;
	public Timer(int c, ArrayList<AbstractEvent> es) {
		countdown = c;
		this.events = es;
	}
	public Timer(int c) {
		countdown = c;
		this.events = new ArrayList<AbstractEvent>();
	}
	public void decrement() {
		if (countdown >= 0) countdown--;
		if (countdown == 0) for (AbstractEvent e : events) e.trigger();
	}
	
	public void start() {
		Main.instance.timers.add(this);
	}
	public void addEvent(AbstractEvent e) {
		events.add(e);
	}

}
