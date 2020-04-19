package com.spacegame.main;

public class ReduceHPEvent  extends AbstractEvent {

	private int r;
	
	public ReduceHPEvent(int r) {
		this.r = r;
	}
	
	void trigger() {
		Main.instance.hp -= r;
		if (Main.instance.hp < 0) Main.instance.hp = 0;
		System.out.println(r);
	}
	
	
	
}
