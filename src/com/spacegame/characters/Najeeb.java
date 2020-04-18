package com.spacegame.characters;

import com.blade2d.drawelements.TriElem;

public class Najeeb extends AbstractCharacter {


	public Najeeb(int x, int y, int size) {
		super(x, y, size);
		mWeight = 10;
		mEngineImpulse = 0.01f;
	}
	
	public void update() {
		super.update();
		mShapes.clear();
		mShapes.add(new TriElem((int)mX, (int)mY, (int)(mX-mSize/3), (int)(mY-mSize), (int)(mX+mSize/3), (int)(mY-mSize), 1f, 1f, 1f, 1f, 0));
		
	}
	
}
