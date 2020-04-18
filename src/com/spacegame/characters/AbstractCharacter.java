package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;

public abstract class AbstractCharacter {
	
	public boolean mPlayer;
	
	protected int mSize;
	
	protected int mMomentum;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mSize = size;
		mMomentum = 0;
		mShapes = new ArrayList<AbstractDrawElem>();
		mPlayer = false;
	}
	protected boolean mHidden;
	
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