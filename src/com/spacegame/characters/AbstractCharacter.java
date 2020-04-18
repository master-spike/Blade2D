package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;

public abstract class AbstractCharacter {
	
	protected int mSize;
	
	protected boolean hidden;
	
	protected ArrayList<AbstractDrawElem> mShape; // Shape is a set of Draw Elements
	
	public ArrayList<AbstractDrawElem> getShape() {
		return mShape;
	}
	
	public void hide() {
		hidden = true;
	}
	public void show() {
		hidden = true;
	}
	public boolean isHidden() {
		return hidden;
	}

}