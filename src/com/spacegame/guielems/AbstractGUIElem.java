package com.spacegame.guielems;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;

public abstract class AbstractGUIElem {
	
	
	protected ArrayList<AbstractDrawElem> drawelems;
	
	public AbstractGUIElem() {
		drawelems = new ArrayList<AbstractDrawElem>();
	}
	
	public abstract void update();
	
	public ArrayList<AbstractDrawElem> getShape() {
		return drawelems;
	}
	
}
