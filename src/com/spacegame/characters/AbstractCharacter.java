package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;

public abstract class AbstractCharacter {
	
	protected int mSize;
	
	protected float mX, mY;
	
	protected float mMomentumX, mMomentumY;
	protected float mWeight;
	protected float mEngineImpulse;
	protected float mNewImpulseX, mNewImpulseY;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mX = x; mY = y;
		mNewImpulseX = mNewImpulseY = 0;
		mSize = size;
		mMomentumX = mMomentumY = 0;
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
	
	public void addEngineDirection(float x, float y) {
		mNewImpulseX += x;
		mNewImpulseY += y;
	}
	
	public void addWeight(int w) {
		mWeight += w;
	}
	
	public void update() {
		mX += mMomentumX / mWeight;
		mY += mMomentumY / mWeight;
		
		double newImpulseMagnitude = Math.sqrt(Math.pow(mNewImpulseX, 2) + Math.pow(mNewImpulseY, 2));
		double s = newImpulseMagnitude / mEngineImpulse;
		if (s != 0) {
			mMomentumX += mNewImpulseX / s;
			mMomentumY += mNewImpulseY / s;
		}
		

		mNewImpulseX = mNewImpulseY = 0;
	}

}