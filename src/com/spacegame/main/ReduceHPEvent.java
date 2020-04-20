package com.spacegame.main;

public class ReduceHPEvent  extends AbstractEvent {

	private int r;
	
	public ReduceHPEvent(int r) {
		this.r = r;
	}
	
	public void trigger() {
		Main.instance.hp -= r;
		if (Main.instance.hp < 0) {
			Main.instance.hp = 0;
			Main.instance.addEvent(new GameOverEvent(Main.instance.getScore()));
		}
	}
	
	
	
}
