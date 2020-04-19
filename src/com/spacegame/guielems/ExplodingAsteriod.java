package com.spacegame.guielems;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;
import com.blade2d.drawelements.Vertex;
import com.spacegame.main.AbstractEvent;
import com.spacegame.main.DeleteGUIElemEvent;
import com.spacegame.main.DeleteGUIElemTimer;
import com.spacegame.main.Main;
import com.spacegame.main.Timer;
import com.spacegame.physics.Vector2f;

public class ExplodingAsteriod extends AbstractGUIElem {
	
	private final int MAX_COUNT = 100;
	
	protected int mX, mY;
	protected ArrayList<Vertex> mVertices;
	
	protected int mCounter;

	public ExplodingAsteriod(int x, int y, ArrayList<Vertex> vertices) {
		mX = x;
		mY = y;
		mVertices = vertices;
		mCounter = 0;
		Timer t = new DeleteGUIElemTimer(MAX_COUNT, this);
		t.start();
	}
	
	@Override
	public void update() {
		mCounter++;
		
		drawelems.clear();
		float r = 0.9f, g = 0.8f, b = 0.4f;
		Vertex prev = mVertices.get(mVertices.size()-1);
		for (Vertex curr: mVertices) {
			Vector2f a = new Vector2f((prev.x + curr.x), (prev.y + curr.y));
			a = Vector2f.scale(Vector2f.normalise(a), mCounter*3);
			TriElem tri = new TriElem(mX+a.x, mY+a.y, mX+prev.x+a.x, mY+prev.y+a.y, mX+curr.x+a.x, mY+curr.y+a.y, r, g, b, 1.0f, 0);
			drawelems.add(tri);
		}
		
		if (mCounter > MAX_COUNT) {
			Main.instance.guielems.remove(this);
		}
	}

}
