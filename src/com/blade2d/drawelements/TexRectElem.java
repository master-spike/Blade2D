package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class TexRectElem extends QuadElem {

	private int w;
	private int h;
	
	public TexRectElem(int x1, int y1,int x2, int y2,int x3, int y3,int x4, int y4, int l, float a, Texture tex) {
		super(x1, y1, x2, y2, x3, y3, x4, y4, 1.0f, 1.0f, 1.0f, a, l);
		this.tex = tex;
		w = tex.getWidth();
		h = tex.getHeight();

	}
	
	private final Texture tex;
	
	public void draw() {
		glColor4f(1.0f, 1.0f, 1.0f, a);
		tex.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0,1);
		glVertex2f(x1,y1);
		glTexCoord2f(1,1);
		glVertex2f(x2, y2);
		glTexCoord2f(1,0);
		glVertex2f(x3,y3);
		glTexCoord2f(0,0);
		glVertex2f(x4,y4);
		glEnd();
		tex.unbind();
		
	}

}
