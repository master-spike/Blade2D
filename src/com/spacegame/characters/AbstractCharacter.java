package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;

public abstract class AbstractCharacter {
	
	protected int mSize;
	
	protected int mMomentum;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mSize = size;
		mMomentum = 0;
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

}