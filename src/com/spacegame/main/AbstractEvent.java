package com.spacegame.main;

public abstract class AbstractEvent {
	
	Main main;
	
	public AbstractEvent(Main m) {
		main = m;
	}
	
	public AbstractEvent() {
		main = Main.instance;
	}

	abstract void trigger();

}
