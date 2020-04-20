package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.TriElem;
import com.blade2d.drawelements.Vertex;
import com.spacegame.main.AsteroidDespawnEvent;
import com.spacegame.main.IncreaseScoreEvent;
import com.spacegame.main.Main;

public class Asteroid extends AbstractCharacter {
	
	public static final float ASTEROID_DENSITY = 0.2f;
	
	protected ArrayList<Vertex> mVertices;
	public ArrayList<Vertex> getmVertices() {
		return mVertices;
	}

	protected Vertex centre;
	
	protected int mAimX, mAimY;
	
	public Asteroid (int x, int y, int size, int aimX, int aimY) {
		super(x, y, size);
		mWeight = size*size*ASTEROID_DENSITY;
		mEngineImpulse = 1f;
		
		mAimX = aimX; mAimY = aimY;
		
		float currAngle = 0;
		float currLength = size;
		mVertices = new ArrayList<Vertex>();
		int numSides = 12;
		for (int i = 0; i != numSides; i++) {
			double dx = Math.cos(currAngle);
			double dy = Math.sin(-currAngle);
			mVertices.add(new Vertex((float) (currLength * dx) , 
									 (float) (currLength * dy) ));
			
			double angleVariance = 1.0 / (numSides + 1);
			double minAngle = Math.PI * 2.0 / numSides * (1-angleVariance);
			double maxAngle = Math.PI * 2.0 / numSides * (1+angleVariance);
			currAngle += Math.random() * (maxAngle - minAngle) + minAngle;
			
			double sizeVariance = 0.2;
			double minLength = size * (1-sizeVariance);
			double maxLength = size * (1+sizeVariance);
			currLength = (float) (Math.random() * (maxLength - minLength) + minLength);
		}
		
		mMoveSlowFactor = 1f;
		
		centre = new Vertex(x, y);
	}
	
	public void update() {
		super.update();
		
		centre.x = mX;
		centre.y = mY;
		
		if (Math.abs(mAimX - mX) > mAimX || Math.abs(mAimY - mY) > mAimY) {
			AsteroidDespawnEvent despawn_this = new AsteroidDespawnEvent(this);
			IncreaseScoreEvent ise = new IncreaseScoreEvent(1);
			
			Main.instance.immediate_events.add(ise);
			Main.instance.immediate_events.add(despawn_this);
		}
		
		// Drawing stuff
		mShapes.clear();
		
		float r = 0.9f, g = 0.8f, b = 0.4f;
		Vertex prev = mVertices.get(mVertices.size()-1);
		for (Vertex curr: mVertices) {
			TriElem tri;
			tri = new TriElem(mX, mY, mX+prev.x, mY+prev.y, mX+curr.x, mY+curr.y, r, g, b, 1.0f, 10);
			mShapes.add(tri);
			prev = curr;
		}
		
		
		for (AbstractDrawElem shape : mShapes) {
			shape.rotate(centre, mRotation);
		}
	}

}
