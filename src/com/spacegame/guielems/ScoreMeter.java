package com.spacegame.guielems;

import com.spacegame.main.Main;

public class ScoreMeter extends AbstractGUIElem {

	
	
	public void update() {
		drawelems.clear();
		drawelems.addAll(Main.instance.font.getString("SCORE: " + Main.instance.getScore(), 600, 700, 1.5f, 0.0f, 0.8f, 0f, 1.0f, 15000));
		
	}

}
