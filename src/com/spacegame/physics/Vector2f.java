package com.spacegame.physics;

public class Vector2f {

	float x;
	float y;
	
	public Vector2f(float x, float y) {
		this.x = x; this.y = y;
	}
	
	public static Vector2f add(Vector2f v1, Vector2f v2) {
		return new Vector2f(v1.x + v2.x, v1.y + v2.y);
		
	}
	public static Vector2f scale(Vector2f v, float s) {
		return new Vector2f(v.x*s, v.y*s);
	}
	
	// around origin clockwise
	public static Vector2f rotate(Vector2f v, double theta) {
		float x_ = (float) (v.x * Math.cos(theta) - v.y * Math.sin(theta));
		float y_ = (float) (v.x * Math.sin(theta) + v.y * Math.cos(theta));
		return new Vector2f(x_, y_);
	}
	
}
