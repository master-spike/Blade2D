package com.spacegame.main;

public abstract class AbstractEvent {
	
	Main main;
	
	public AbstractEvent(Main m) {
		main = m;
	}
	
	abstract void trigger();

}
