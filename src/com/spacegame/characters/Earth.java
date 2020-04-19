package com.spacegame.characters;

import com.blade2d.drawelements.LineElem;
import com.blade2d.drawelements.TexRectElem;
import com.blade2d.drawelements.Texture;

public class Earth extends AbstractCharacter {
	
	Texture tex;

	public Earth(int x, int y, int size) {
		super(x, y, size);
		
		mWeight = 100000;
		
		tex = new Texture("earth.png");
	}
	
	public void update() {
		super.update();
		
		// draw stuff
		
		mShapes.clear();
		int x1 = (int) (mX - mSize/2), x2 = (int) (mX + mSize/2), x3 = x2, x4 = x1;
		int y1 = (int) (mY - mSize/2), y2 = y1, y3 = (int) (mY + mSize/2), y4 = y3;
		TexRectElem elem = new TexRectElem(x1,y1,x2,y2,x3,y3,x4,y4, 0, 1f, tex);
		
		mShapes.add(elem);
	}
	
}
