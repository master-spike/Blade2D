package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;

public abstract class AbstractCharacter {
	
	public boolean mPlayer;
	
	protected int mSize;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mSize = size;
		mShapes = new ArrayList<AbstractDrawElem>();
		mShapes.add(new TriElem(x, y, x-size/3, y-size, x+size/3, y-size, 1f, 1f, 1f, 1f, 0));
		mPlayer = false;
	}
	
	public ArrayList<AbstractDrawElem> getShape() {
		return mShapes;
	}

}
