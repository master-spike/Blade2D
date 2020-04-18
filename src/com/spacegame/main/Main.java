package com.spacegame.main;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.LineElem;
import com.blade2d.engine.GameCore;
import com.spacegame.characters.AbstractCharacter;
import com.spacegame.characters.Najeeb;

public class Main extends GameCore {
	
	private ArrayList<AbstractCharacter> mCharacters;
	
	private ArrayList<Star> mStars;

	public Main(int w, int h, String t, int r) {
		super(w, h, t, r);
	}

	protected void init() {
		mCharacters = new ArrayList<AbstractCharacter>();
		mStars = new ArrayList<Star>();
		
		mCharacters.add(new Najeeb(super.resWidth/2, super.resHeight/2, 40));
		
		int numStars = 100;
		for (int i = 0; i != numStars; i++) {
			mStars.add(new Star((int) (Math.random() * super.resWidth), (int) (Math.random() * super.resHeight)));
		}
	}

	protected boolean toTerminate() {
		return super.window.shouldClose();
	}

	protected void update() {
		super.draw_elems.clear();
		
		for (AbstractCharacter character: mCharacters) {
			if(!character.isHidden()) super.draw_elems.addAll(character.getShape());
		}
		for (Star star: mStars) {
			super.draw_elems.add(star.getShape());
		}
		
	}
}

class Star {
	private static final double chanceVisible = 0.5;
	private static final int size = 5;
	
	public final int mX, mY;
	@SuppressWarnings("unused")
	private boolean mVisible;
	
	public Star (int x, int y) {
		mX = x;
		mY = y;
		mVisible = Math.random() < chanceVisible;
	}
	
	public AbstractDrawElem getShape() {
		double angle = Math.random() * 2 * Math.PI;
		int xDist = (int) (Math.cos(angle) * size);
		int yDist = (int) (Math.sin(angle) * size);
		
		float r = 1.0f, g = 0.0f, b = 1.0f, a = 1.0f;
		
		return new LineElem(mX, mY, mX+xDist, mY+yDist, r, g, b, a, 0);
	}
}

