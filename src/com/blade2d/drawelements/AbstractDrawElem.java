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
	
	public abstract void rotate(Vertex c, double angle);
	public abstract void translate(Vertex c);
	public abstract void scale(Vertex c, float scale);
	public abstract void reflect(Vertex c, double angle);

}
