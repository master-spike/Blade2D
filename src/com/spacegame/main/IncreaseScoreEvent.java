package com.spacegame.main;

public class IncreaseScoreEvent extends AbstractEvent {

	private int increase;
	
	public IncreaseScoreEvent(int i) {
		increase = i;
	}

	@Override
	public void trigger() {
		
		Main.instance.increaseScore(increase);
		System.out.println(increase);
		
	}

}
