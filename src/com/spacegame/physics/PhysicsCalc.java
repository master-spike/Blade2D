package com.spacegame.physics;

public class PhysicsCalc {

	public static Vect2fPair getElasticCollision(CollidableCircleModel c1, CollidableCircleModel c2) {
		
		Vector2f t = Vector2f.scale(c2.position, -1f);
		
		c1.translate(t);
		c2.translate(t);
		
		double theta = Math.atan(c1.position.y / c2.position.x);
		
		c1.rotate(-theta);
		
		float impulseMag = 2*c1.velocity.x*c1.mass*c2.mass/(c1.mass + c2.mass);
		Vector2f i1 = new Vector2f(0, impulseMag);
		Vector2f i2 = new Vector2f(0, impulseMag);
		i1 = Vector2f.scale(i1, -1.0f);
		i1 = Vector2f.rotate(i1, theta);
		i2 = Vector2f.rotate(i2, theta);

		return new Vect2fPair(i1, i2);

	}

}
