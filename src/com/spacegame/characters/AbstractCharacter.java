package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;

public abstract class AbstractCharacter {
	
	protected float mSize;
	protected float mRotation;
	
	protected float mX, mY;
	
	protected float mMomentumX, mMomentumY, mSpinMomentum;
	protected float mWeight;
	protected float mEngineImpulse, mSpinImpulse;
	protected float mNewImpulseX, mNewImpulseY, mNewSpinImpulse;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mX = x; mY = y;
		mRotation = 0f;
		mNewImpulseX = mNewImpulseY = 0;
		mSize = size;
		mMomentumX = mMomentumY = 0;
		mSpinMomentum = 0;
		mShapes = new ArrayList<AbstractDrawElem>();
	}
	protected boolean mHidden;
	
	public AbstractCharacter() {
		mShapes = new ArrayList<AbstractDrawElem>();
	}
	
	public ArrayList<AbstractDrawElem> getShape() {
		if (!mHidden)
			return mShapes;
		else
			return new ArrayList<AbstractDrawElem>();
	}
	
	public void hide() {
		mHidden = true;
	}
	public void show() {
		mHidden = true;
	}
	public boolean isHidden() {
		return mHidden;
	}
	
	public void addImpulse(float x, float y) {
		if (mWeight == 0) {
			System.err.println("Tried to speed up when weightless!");
		}
		mMomentumX += x;
		mMomentumY += y;
	}
	
	public void addFowardImpulse(float mag) {
		float y = (float) Math.cos(mRotation) * mag;
		float x = -(float) Math.sin(mRotation) * mag;
		addImpulse(x, y);
	}
	
	public void addSpin(float spin) {
		mNewSpinImpulse += spin;
	}
	
	public void addEngineDirection(float x, float y) {
		mNewImpulseX += x;
		mNewImpulseY += y;
	}
	
	public void addWeight(int w) {
		mWeight += w;
	}
	
	public void update() {
		
		double newImpulseMagnitude = Math.sqrt(Math.pow(mNewImpulseX, 2) + Math.pow(mNewImpulseY, 2));
		double s = newImpulseMagnitude / mEngineImpulse;
		if (s != 0) {
			mMomentumX += mNewImpulseX / s;
			mMomentumY += mNewImpulseY / s;
		}
		if (mNewSpinImpulse != 0)
			mSpinMomentum += ((mNewSpinImpulse > 0) ? 1 : -1) * mSpinImpulse;
		
		float SPINSLOWFACTOR = 0.99f;
		float MOVESLOWFACTOR = 0.99f;
		
		mX += mMomentumX / mWeight;
		mY += mMomentumY / mWeight;
		mRotation += mSpinMomentum;
		mSpinMomentum *= SPINSLOWFACTOR;
		mMomentumX *= MOVESLOWFACTOR;
		mMomentumY *= MOVESLOWFACTOR;
		

		mNewImpulseX = mNewImpulseY = mNewSpinImpulse = 0;
	}

}