package com.spacegame.characters;

import java.util.ArrayList;

import com.blade2d.drawelements.AbstractDrawElem;
import com.spacegame.main.Main;
import com.spacegame.physics.Vector2f;

public abstract class AbstractCharacter {
	
	protected float mSpinSlowFactor;
	protected float mMoveSlowFactor;
	
	protected float mSize;
	protected float mRotation;
	
	protected float mX, mY;
	
	protected float mMomentumX, mMomentumY, mSpinMomentum;
	protected float mWeight;
	protected float mEngineImpulse, mSpinImpulse;
	protected float mNewImpulseX, mNewImpulseY, mNewSpinImpulse;
	
	protected float mContactCountdown;
	
	protected ArrayList<AbstractDrawElem> mShapes; // Shape is a set of Draw Elements
	
	public AbstractCharacter(int x, int y, int size) {
		mX = x; mY = y;
		mRotation = 0f;
		mNewImpulseX = mNewImpulseY = 0;
		mSize = size;
		mContactCountdown = 0;
		mMomentumX = mMomentumY = 0;
		mSpinMomentum = 0;
		mMoveSlowFactor = mSpinSlowFactor = 1f;
		mShapes = new ArrayList<AbstractDrawElem>();
	}
	protected boolean mHidden;
	
	public AbstractCharacter() {
		mShapes = new ArrayList<AbstractDrawElem>();
	}
	
	public ArrayList<AbstractDrawElem> getShape() {
		if (!mHidden)
			return mShapes;
		else
			return new ArrayList<AbstractDrawElem>();
	}
	
	public void hide() {
		mHidden = true;
	}
	public void show() {
		mHidden = true;
	}
	public boolean isHidden() {
		return mHidden;
	}

	public void addImpulse(float x, float y) {
		if (mWeight == 0)
			System.err.println("Tried to speed up when weightless!");
		mMomentumX += x;
		mMomentumY += y;
	}
	public void addScaledImpulse(float x, float y, float mag) {
		if (mag == 0) return;
		double nn = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double s = nn / mag;
		if (s != 0) {
			mMomentumX += x / s;
			mMomentumY += y / s;
		}
	}
	
	public void addSpin(float spin) {
		if (mWeight == 0)
			System.err.println("Tried to spin up when weightless!");
		mSpinMomentum += spin;
	}
	
	public void addFowardImpulse(float mag) {
		float y = (float) Math.cos(mRotation) * mag;
		float x = -(float) Math.sin(mRotation) * mag;
		addImpulse(x, y);
	}
	
	public void addEngineSpin(float spin) {
		mNewSpinImpulse += spin;
	}
	
	public void addEngineDirection(float x, float y) {
		mNewImpulseX += x;
		mNewImpulseY += y;
	}
	
	public void addWeight(int w) {
		mWeight += w;
	}
	
	public void update() {
		
		addScaledImpulse(mNewImpulseX, mNewImpulseY, mEngineImpulse);
		if (mNewSpinImpulse != 0)
			mSpinMomentum += ((mNewSpinImpulse > 0) ? 1 : -1) * mSpinImpulse;
		
		mX += mMomentumX / mWeight;
		mY += mMomentumY / mWeight;
		mRotation += mSpinMomentum;
		mSpinMomentum *= mSpinSlowFactor;
		mMomentumX *= mMoveSlowFactor;
		mMomentumY *= mMoveSlowFactor;
		

		mNewImpulseX = mNewImpulseY = mNewSpinImpulse = 0;
		
		mContactCountdown--;
	}
	
	public boolean hasCollided(AbstractCharacter other) {
		if (mContactCountdown > 0) return false;
		float dist = (float) (Math.pow(mX - other.mX, 2) + Math.pow(mY - other.mY, 2));
		float minDist = (mSize + other.mSize) * (mSize + other.mSize);
		
		if (dist <= minDist) {
		mContactCountdown = 0;
		return true;
		}
		return false;
	}
	
	public void applyGravity(float mAimX, float mAimY) {
		
		Vector2f grav = new Vector2f(mAimX - mX, mAimY - mY);
		grav = Vector2f.scale(grav, (float) (Main.GRAVITY_CONST/Math.pow(Vector2f.magnitude(grav),2)));
		addImpulse(grav.x*mWeight, grav.y*mWeight);
		
	}
	
	public void onCollide() {};
	
	public float getWeight() {return mWeight;}
	public Vector2f getVelocity() {return new Vector2f(mMomentumX / mWeight, mMomentumY / mWeight);}
	public Vector2f getPosition() {return new Vector2f(mX, mY);}

}