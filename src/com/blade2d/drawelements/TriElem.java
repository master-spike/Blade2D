package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class TriElem extends AbstractDrawElem{
	
	
	protected float x1, y1, x2, y2, x3, y3;
	protected float r;
	protected float g;
	protected float b;
	protected float a;
	
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

	public float getX3() {
		return x3;
	}

	public void setX3(float x3) {
		this.x3 = x3;
	}

	public float getY3() {
		return y3;
	}

	public void setY3(float y3) {
		this.y3 = y3;
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

	public TriElem(float x1, float y1, float x2, float y2, float x3, float y3,  float r, float g, float b, float a, int l) {
		super(l);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void draw() {
		glColor4f(r, g, b, a);
		glBegin(GL_TRIANGLES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
	}

	public void rotate(Vertex c, double angle) {
		Vertex v = Transformations.rotate(new Vertex(x1, y1), c, angle);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.rotate(new Vertex(x2, y2), c, angle);
		x2 = v.x;
		y2 = v.y;
		v = Transformations.rotate(new Vertex(x3, y3), c, angle);
		x3 = v.x;
		y3 = v.y;
		
	}

	public void translate(Vertex c) {
		Vertex v = Transformations.translate(new Vertex(x1, y1), c);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.translate(new Vertex(x2, y2), c);
		x2 = v.x;
		y2 = v.y;
		v = Transformations.translate(new Vertex(x3, y3), c);
		x3 = v.x;
		y3 = v.y;
	}

	public void scale(Vertex c, float scale) {
		Vertex v = Transformations.scale(new Vertex(x1, y1), c, scale);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.scale(new Vertex(x2, y2), c, scale);
		x2 = v.x;
		y2 = v.y;
		v = Transformations.scale(new Vertex(x3, y3), c, scale);
		x3 = v.x;
		y3 = v.y;
		
	}

	public void reflect(Vertex c, double angle) {
		Vertex v = Transformations.reflect(new Vertex(x1, y1), c, angle);
		x1 = v.x;
		y1 = v.y;
		v = Transformations.reflect(new Vertex(x2, y2), c, angle);
		x2 = v.x;
		y2 = v.y;
		v = Transformations.reflect(new Vertex(x3, y3), c, angle);
		x3 = v.x;
		y3 = v.y;
		
	}
	
}
