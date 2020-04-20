package com.spacegame.physics;

public class Vector2f {

	public float x;
	public float y;
	
	public Vector2f(float x, float y) {
		this.x = x; this.y = y;
	}
	
	public static Vector2f add(Vector2f v1, Vector2f v2) {
		return new Vector2f(v1.x + v2.x, v1.y + v2.y);
		
	}
	public static Vector2f negative(Vector2f v) {
		return new Vector2f(-v.x, -v.y);
	}
	public static Vector2f subtract(Vector2f v1, Vector2f v2) {
		return add(v1, negative(v2));
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
	
	public static float magnitude(Vector2f v) {
		return (float) Math.sqrt(v.x*v.x + v.y*v.y);
	}
	
	public static Vector2f normalise(Vector2f v) {
		return Vector2f.scale(v, 1f/magnitude(v));
	}
	
}
