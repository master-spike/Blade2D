package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class TexturedTriangle extends TriElem {

	Texture tex;
	float t_x1;
	float t_y1;
	float t_x2;
	float t_y2;
	float t_x3;
	float t_y3;
	
	public TexturedTriangle(float x1, float y1, float x2, float y2, float x3, float y3, float t_x1, float t_y1,
			float t_x2, float t_y2, float t_x3, float t_y3, Texture tex, float r, float g, float b, float a, int l) {
		super(x1, y1, x2, y2, x3, y3, r, g, b, a, l);
		this.tex = tex;
		this.t_x1 = t_x1;
		this.t_y1 = t_y1;
		this.t_x2 = t_x2;
		this.t_y2 = t_y2;
		this.t_x3 = t_x3;
		this.t_y3 = t_y3;
	}
	
	public void draw() {
		glColor4f(r, g, b, a);
		tex.bind();
		glBegin(GL_TRIANGLES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
		tex.unbind();
	}

}
