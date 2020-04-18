package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.*;

public class TexRectElem extends AbstractDrawElem {

	private int w;
	private int h;
	private int x;
	private int y;
	private float scale;
	
	public TexRectElem(int x, int y, int l, float scale, Texture tex) {
		super(l);
		this.tex = tex;
		w = tex.getWidth();
		h = tex.getHeight();
		this.scale = scale;
		this.x = x;
		this.y = y;
	}
	
	private final Texture tex;
	
	public void draw() {
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		tex.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(x,y);
		glTexCoord2f(0,1);
		glVertex2f(x, y - (h* scale));
		glTexCoord2f(1,1);
		glVertex2f(x + (w*scale), y - (h*scale));
		glTexCoord2f(1,0);
		glVertex2f(x + (w*scale), y);
		glEnd();
		tex.unbind();
		
	}
	
	
	

}
