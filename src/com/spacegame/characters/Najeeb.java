package com.spacegame.characters;

import com.blade2d.drawelements.TriElem;
import com.blade2d.drawelements.Vertex;

public class Najeeb extends AbstractCharacter {
	
	private int mWrapWidth, mWrapHeight;


	public Najeeb(int x, int y, int size, int ww, int wh) {
		super(x, y, size);
		mWeight = 10;
		mEngineImpulse = 0.5f;
		mSpinImpulse = 0.0005f;
		mWrapWidth = ww;
		mWrapHeight = wh;
	}
	
	public void update() {
		super.update();
		
		// Making sure we're on screen
		
		mX %= mWrapWidth;
		mY %= mWrapHeight;
		while (mX < 0) mX += mWrapWidth;
		while (mY < 0) mY += mWrapHeight;
		
		// Drawing stuff
		mShapes.clear();
		TriElem e = new TriElem((int)mX, (int)mY, (int)(mX-mSize/3), (int)(mY-mSize), (int)(mX+mSize/3), (int)(mY-mSize), 1f, 1f, 1f, 1f, -1);
		e.rotate(new Vertex(mX, mY), mRotation);
		mShapes.add(e);
		
	}
	
}
