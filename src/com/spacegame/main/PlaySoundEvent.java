package com.spacegame.main;

public class PlaySoundEvent extends AbstractEvent {

	private int index;
	
	public PlaySoundEvent(int index) {
		this.index = index;
	}

	public void trigger() {
		
		Main.instance.soundbank.play(index);
		
	}
	
}
