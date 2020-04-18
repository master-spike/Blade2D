package com.spacegame.main;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.QuadElem;

public class SideBar {
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	protected int mWidth, mHeight;

	public SideBar (int x, int y, int width, int height) {
		mWidth = width;
		mHeight = height;
		int x1 = x+width, y1 = y+height;
		mShapes = new ArrayList<AbstractDrawElem>();
		mShapes.add(new QuadElem(x, y, x, y1, x1, y1, x1, y, 0.9f, 0.9f, 0.9f, 1f, 1));
	}
	
	public ArrayList<AbstractDrawElem> getShapes() {
		return mShapes;
	}
	
}
