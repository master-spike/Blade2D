package com.spacegame.characters;

import com.blade2d.drawelements.TriElem;

public class Najeeb extends AbstractCharacter {

	public Najeeb(int x, int y, int size) {
		super(x, y, size);
		mShapes.add(new TriElem(x, y, x-size/3, y-size, x+size/3, y-size, 1f, 1f, 1f, 1f, 0));
		mPlayer = true;
	}
	
	

}
