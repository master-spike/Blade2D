package com.spacegame.characters;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;

public abstract class AbstractCharacter {
	
	protected int mSize;
	
	protected TriElem mShape;
	
	public AbstractCharacter(int x, int y, int size) {
		mSize = size;
		mShape = new TriElem(x, y, x-size/2, y-size, x+size/2, y-size, 1f, 1f, 1f, 1f, 0);
	}
	
	public AbstractDrawElem getShape() {
		return mShape;
	}

}
