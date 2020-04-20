package com.spacegame.guielems;

import com.spacegame.main.Main;

public class HealthDecreaseIndicator extends AbstractGUIElem {

	
	public static final int DURATION = 120;
	private static final float SPEED = 0.2f;
	
	private float x, y;
	private int r;
	private float a;
	public HealthDecreaseIndicator(float x, float y, int r) {
		this.x = x; this.y = y; this.r = r;
		a = 1f;
	}
	
	
	public void update() {
		drawelems.clear();
		drawelems.addAll(Main.instance.font.getString(("-" + r), x, y, 1f, 1.0f, 0.0f, 0.0f, a, 10000));
		y += SPEED;
		a -= 1.0f/DURATION;
	}

}
