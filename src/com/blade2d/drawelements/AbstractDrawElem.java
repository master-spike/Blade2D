package com.blade2d.drawelements;

public abstract class AbstractDrawElem implements Comparable<AbstractDrawElem> {
	
	protected int layer;
	
	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public abstract void draw();
	
	public AbstractDrawElem(int l) {
		layer = l;
	}

	public int compareTo(AbstractDrawElem e) {
		return layer - e.layer;
	}

}
