package com.spacegame.main;

import com.spacegame.guielems.AbstractGUIElem;
import com.spacegame.guielems.ExplodingEarth;
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
		PlaySoundEvent end_explosion_sound = new PlaySoundEvent(4);
		end_explosion_sound.trigger();
		Main.instance.addGUIElem(graphic);
		Main.instance.killEarth();
		Main.instance.addGUIElem(new ExplodingEarth(384, 384, Main.EARTH_RADIUS));
	}

}
