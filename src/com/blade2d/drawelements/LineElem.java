package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class LineElem extends AbstractDrawElem {
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float r;
	private float g;
	private float b;
	private float a;
	public LineElem(float x1, float y1, float x2, float y2, float r, float g, float b, float a, int l) {
		super(l);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public void draw() {
		glColor4f(r, g, b, a);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}

	public void rotate(Vertex c, double angle) {
		Vertex v = Transformations.rotate(new Vertex(x1, y1), c, angle);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.rotate(new Vertex(x2, y2), c, angle);
		x2 = v.x;
		y2 = v.y;
		
	}

	@Override
	public void translate(Vertex c) {
		Vertex v = Transformations.translate(new Vertex(x1, y1), c);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.translate(new Vertex(x2, y2), c);
		x2 = v.x;
		y2 = v.y;
	}

	@Override
	public void scale(Vertex c, float scale) {
		Vertex v = Transformations.scale(new Vertex(x1, y1), c, scale);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.scale(new Vertex(x2, y2), c, scale);
		x2 = v.x;
		y2 = v.y;
		
	}

	@Override
	public void reflect(Vertex c, double angle) {
		Vertex v = Transformations.reflect(new Vertex(x1, y1), c, angle);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.reflect(new Vertex(x2, y2), c, angle);
		x2 = v.x;
		y2 = v.y;
		
	}

	
}
