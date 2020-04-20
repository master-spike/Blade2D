package com.spacegame.guielems;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.Texture;
import com.blade2d.drawelements.TexturedTriangle;
import com.blade2d.drawelements.Vertex;

public class ExplodingEarth extends AbstractGUIElem {
	float x;
	float y;
	float size;
	
	public ExplodingEarth(float x, float y, float size) {
		drawelems.add(new TexturedTriangle(x-size, y-size, x-size, y, x, y, 0f ,0f, 0f, 0.5f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x-size, y-size, x, y-size, x, y, 0f ,0f, 0.5f, 0f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x+size, y-size, x+size, y, x, y, 1f ,0f, 1f, 0.5f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x+size, y-size, x, y-size, x, y, 1f ,0f, 0.5f, 0f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x-size, y+size, x-size, y, x, y, 0f ,1f, 0f, 0.5f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x-size, y+size, x, y+size, x, y, 0f ,1f, 0.5f, 1f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x+size, y+size, x+size, y, x, y, 1f ,1f, 1f, 0.5f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		drawelems.add(new TexturedTriangle(x+size, y+size, x, y+size, x, y, 1f ,1f, 0.5f, 1f, 0.5f,0.5f, new Texture("res/earth.png"), 1.0f, 1.0f, 1.0f, 1.0f, 20000-2));
		this.x = x;
		this.y = y;
	}
	public void update() {
		
		for (AbstractDrawElem e : drawelems) {
			TexturedTriangle t = (TexturedTriangle) e;

			e.translate(new Vertex(((t.getX1()+t.getX2()+t.getX3())/3-x)/30, ((t.getY1()+t.getY2()+t.getY3())/3-x)/30));
		}
		
	}

}
