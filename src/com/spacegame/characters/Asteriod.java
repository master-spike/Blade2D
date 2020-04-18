package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.LineElem;
import com.blade2d.drawelements.TriElem;
import com.blade2d.drawelements.Vertex;

public class Asteriod extends AbstractCharacter {
	
	protected ArrayList<Vertex> mVertices;
	protected Vertex centre;
	
	protected int mAimX, mAimY;
	
	public Asteriod (int x, int y, int size, int aimX, int aimY) {
		super(x, y, size);
		mWeight = 100;
		mEngineImpulse = 3f;
		
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
			
			double sizeVariance = 0.15;
			double minLength = size * (1-sizeVariance);
			double maxLength = size * (1+sizeVariance);
			currLength = (float) (Math.random() * (maxLength - minLength) + minLength);
		}
		
		centre = new Vertex(x, y);
	}
	
	public void update() {
		super.update();
		
		centre.x = mX;
		centre.y = mY;
		
		// Propel to the planet!
		
		addEngineDirection(mAimX - mX, mAimY - mY);
		
		// Drawing stuff
		mShapes.clear();
		
		float r = 0.9f, g = 0.8f, b = 0.4f;
		Vertex prev = mVertices.get(mVertices.size()-1);
		for (Vertex curr: mVertices) {
			TriElem tri = new TriElem(mX, mY, mX+prev.x, mY+prev.y, mX+curr.x, mY+curr.y, r, g, b, 1.0f, 10);
			mShapes.add(tri);
			prev = curr;
		}
		
		
		for (AbstractDrawElem shape : mShapes) {
			shape.rotate(centre, mRotation);
		}
	}

}
