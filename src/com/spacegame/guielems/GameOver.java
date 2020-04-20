package com.spacegame.guielems;

import com.blade2d.drawelements.QuadElem;
import com.spacegame.main.Main;

public class GameOver extends AbstractGUIElem {
	
	private int mScore;
	
	public GameOver(int score) {
		mScore = score;
		
		int w = Main.instance.GameWidth;
		int h = Main.instance.GameHeight;
		drawelems.add(new QuadElem(0,0,w,0,w,h,0,h,0f,0f,0f,0.5f,20000-1));
		
		float x1 = 0.2f;
		float y1 = 0.6f;
		
		drawelems.addAll(Main.instance.font.getString(("GAME OVER!"), Main.instance.GameWidth * x1, Main.instance.GameHeight * y1, 
				5f, 1f, 0.2f, 0.2f, 1f, 20000));
		
		x1 = 0.25f;
		y1 = 0.4f;
		
		drawelems.addAll(Main.instance.font.getString(("Final Score: "), Main.instance.GameWidth * x1, Main.instance.GameHeight * y1, 
				4f, 1f, 0.2f, 0.2f, 1f, 20000));
		
		x1 = 0.4f;
		y1 = 0.25f;
		
		drawelems.addAll(Main.instance.font.getString((""+mScore), Main.instance.GameWidth * x1, Main.instance.GameHeight * y1, 
				7f, 1f, 1f, 1f, 1f, 20000));
		
		x1 = 0.25f;
		y1 = 0.1f;
		
		drawelems.addAll(Main.instance.font.getString(("(press 'r' to restart)"), Main.instance.GameWidth * x1, Main.instance.GameHeight * y1, 
				2f, 1f, 1f, 1f, 1f, 20000));
	}

	@Override
	public void update() {
	}

}
