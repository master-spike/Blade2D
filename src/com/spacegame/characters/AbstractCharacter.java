package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;

public abstract class AbstractCharacter {
	
	protected int mSize;
	
	protected ArrayList<AbstractDrawElem> mShape; // Shape is a set of Draw Elements
	
	public ArrayList<AbstractDrawElem> getShape() {
		return mShape;
	}

}
