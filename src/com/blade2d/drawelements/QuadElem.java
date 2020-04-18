package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class QuadElem extends AbstractDrawElem {

	float x1, x2, x3, x4, y1, y2, y3, y4;
	float r, g, b, a;
	
	public QuadElem(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, float r, float g, float b, float a,
			int l) {
		super(l);
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.y4 = y4;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		
	}

	@Override
	public void draw() {
		
		glColor4f(r,g,b,a);
		glBegin(GL_QUADS);
		glVertex2f(x1,y1);
		glVertex2f(x2,y2);
		glVertex2f(x3,y3);
		glVertex2f(x4,y4);
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
		v = Transformations.rotate(new Vertex(x4, y4), c, angle);
		x4 = v.x;
		y4 = v.y;
		
	}

	@Override
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
		v = Transformations.translate(new Vertex(x4, y4), c);
		x4 = v.x;
		y4 = v.y;
	}

	@Override
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
		v = Transformations.scale(new Vertex(x4, y4), c, scale);
		x4 = v.x;
		y4 = v.y;
		
	}

	@Override
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
		v = Transformations.reflect(new Vertex(x4, y4), c, angle);
		x4 = v.x;
		y4 = v.y;
		
	}

}
