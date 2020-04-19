package com.spacegame.guielems;

import com.blade2d.drawelements.QuadElem;
import com.spacegame.main.Main;

public class Healthbar extends AbstractGUIElem {

	public Healthbar(float width, float height, float x, float y, float borderthickness, float borderR, float borderG,
			float borderB, float backR, float backG, float backB, int redThld, int ylwThld) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.borderthickness = borderthickness;
		this.borderR = borderR;
		this.borderG = borderG;
		this.borderB = borderB;
		this.backR = backR;
		this.backG = backG;
		this.backB = backB;
		this.redThld = redThld;
		this.ylwThld = ylwThld;
		this.max = Main.MAX_HP;
	}

	public void update() {
		drawelems.clear();
		this.hp = Main.instance.hp;
		drawelems.add(new QuadElem(x, y, x + width, y, x + width, y + height, x, y + height, borderR, borderG, borderB,
				1.0f, 10000000));
		drawelems.add(new QuadElem(x + borderthickness, y + borderthickness, x + width - borderthickness,
				y + borderthickness, x + width - borderthickness, y + height - borderthickness, x + borderthickness,
				y + height - borderthickness, backR, backG, backB, 1.0f, 10000001));
		float r,g,b;
		if (hp <= redThld) {
			r = 1.0f; g = 0.0f; b = 0.0f;
		}
		else if (hp <= ylwThld) {
			r = 0.8f; g = 0.8f; b = 0.0f;
		}
		else {
			r = 0.0f; g = 0.8f; b = 0.0f;
		}
		drawelems.add(new QuadElem(x + borderthickness, y + borderthickness,
				x + borderthickness + (float)hp/(float)max * (width - 2 * borderthickness), y + borderthickness,
				x + borderthickness + (float)hp/(float)max * (width - 2 * borderthickness), y + height - borderthickness,
				x + borderthickness, y + height - borderthickness, r, g, b, 1.0f, 10000002));

	}

	private float width;
	private float height;
	private float x;
	private float y;
	private float borderthickness;
	private float borderR;
	private float borderG;
	private float borderB;
	private float backR;
	private float backG;
	private float backB;

	int hp;

	private int redThld;
	private int ylwThld;
	private int max;

}
