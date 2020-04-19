package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class TexRectElem extends QuadElem {

	
	public TexRectElem(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int l, float r, float g, float b, float a, Texture tex) {
		super(x1, y1, x2, y2, x3, y3, x4, y4, r, g, b, a, l);
		this.tex = tex;

	}
	
	private final Texture tex;
	
	public void draw() {
		glColor4f(r, g, b, a);
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
