package com.spacegame.main;

import com.spacegame.guielems.AbstractGUIElem;
import com.spacegame.guielems.GameOver;

public class GameOverEvent extends AbstractEvent {
	
	int mScore;
	
	public GameOverEvent(int score) {
		mScore = score;
	}

	@Override
	public void trigger() {
		Main.instance.pauseStatus = Main.ENDED;
		
		AbstractGUIElem graphic = new GameOver(mScore);
		Main.instance.addGUIElem(graphic);
	}

}
