package com.spacegame.physics;

public class CollidableCircleModel {
	
	float mass;
	Vector2f velocity;
	Vector2f position;
	
	public CollidableCircleModel(float mass, Vector2f velocity, Vector2f position) {
		this.mass = mass; this.velocity = velocity; this.position = position;
	}
	void translate(Vector2f t) {
		position = Vector2f.add(position, t);
	}
	void transVel(Vector2f t) {
		velocity = Vector2f.add(velocity, t);
	}
	
	// around origin
	void rotate(double theta) {
		velocity = Vector2f.rotate(velocity, theta);
		position = Vector2f.rotate(position, theta);
	}

}
