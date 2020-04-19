package com.spacegame.characters;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.LineElem;
import com.blade2d.drawelements.QuadElem;
import com.blade2d.drawelements.TriElem;
import com.blade2d.drawelements.Vertex;

public class Najeeb extends AbstractCharacter {
	
	private int mWrapWidth, mWrapHeight;


	public Najeeb(int x, int y, int size, int ww, int wh) {
		super(x, y, size);
		mWeight = 10;
		mEngineImpulse = 1.5f;
		mSpinImpulse = 0.005f;
		mWrapWidth = ww;
		mWrapHeight = wh;
		mSpinSlowFactor = 0.95f;
		mMoveSlowFactor = 1f;
	}
	
	public void update() {
		super.update();
		
		// Making sure we're on screen
		
		mX %= mWrapWidth;
		mY %= mWrapHeight;
		while (mX < 0) mX += mWrapWidth;
		while (mY < 0) mY += mWrapHeight;
		
		Vertex centre = new Vertex(mX, mY);
		// Drawing stuff
		mShapes.clear();
		TriElem e = new TriElem(mX, mY+mSize,
				mX-mSize*2/3, mY-mSize,
				mX+mSize*2/3, mY-mSize,
				1f, 1f, 1f, 1f, 2);
		QuadElem q = new QuadElem(mX-mSize*2/2,mY,
				mX-mSize*2/3, mY-mSize,
				mX+mSize*2/3, mY-mSize,
				mX+mSize*2/2, mY,
				0.7f, 0.7f, 0.7f, 1f, 1);
		QuadElem q1 = new QuadElem(mX-mSize*2/8, mY-mSize/5,
				mX+mSize*2/8, mY-mSize/5,
				mX+mSize*2/3, mY-mSize,
				mX-mSize*2/3, mY-mSize,
				0.0f, 0.8f, 1f, 1f, 3);

		LineElem l1 = new LineElem(mX-mSize*2/8, mY-mSize/5,
				mX+mSize*2/8, mY-mSize/5,
				0f,0f,0f,1f, 4);
		LineElem l2 = new LineElem(mX+mSize*2/8, mY-mSize/5,
				mX+mSize*2/3, mY-mSize,
				0f,0f,0f,1f, 4);
		LineElem l3 = new LineElem(mX-mSize*2/8, mY-mSize/5,
				mX-mSize*2/3, mY-mSize,
				0f,0f,0f,1f, 4);
		
		mShapes.add(l1);
		mShapes.add(l2);
		mShapes.add(l3);
		mShapes.add(e);
		mShapes.add(q);
		mShapes.add(q1);
		
		
		for (AbstractDrawElem shape : mShapes) {
			shape.rotate(centre, mRotation);
		}
		
	}
	
}
