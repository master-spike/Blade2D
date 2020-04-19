package com.spacegame.physics;

public class PhysicsCalc {

	public static Vect2fPair getElasticCollision(CollidableCircleModel c1, CollidableCircleModel c2) {
		
		Vector2f tp = Vector2f.scale(c2.position, -1f);
		Vector2f tv = Vector2f.scale(c2.velocity, -1f);
		
		c1.translate(tp);
		c2.translate(tp);
		
		c1.transVel(tv);
		c2.transVel(tv);
		
		
		
		double theta = Math.atan(c1.position.y / c1.position.x);
		
		c1.rotate(-theta);
		if (c1.position.x * c1.velocity.x >= 0) return new Vect2fPair(new Vector2f(0,0), new Vector2f(0,0));
		System.out.println(c1.velocity.x + " " + c1.velocity.y + " " + c1.position.x + " " + c1.position.y + " ");
		
		
		float impulseMag = 0;
		if(c1.mass != Double.POSITIVE_INFINITY && c2.mass != Double.POSITIVE_INFINITY) {
			impulseMag = 2*c1.velocity.x*c1.mass*c2.mass/(c1.mass + c2.mass);
		}
		else if(c2.mass == Double.POSITIVE_INFINITY && c1.mass != Double.POSITIVE_INFINITY) {
			impulseMag = 2*c1.velocity.x*c1.mass;
		}
		else if (c1.mass == Double.POSITIVE_INFINITY&& c2.mass != Double.POSITIVE_INFINITY) {
			impulseMag = 2*c1.velocity.x*c2.mass;
		}
		else {
			impulseMag = 0;
		}
		Vector2f i1 = new Vector2f(impulseMag, 0);
		Vector2f i2 = new Vector2f(impulseMag, 0);
		i1 = Vector2f.scale(i1, -1.0f);
		i1 = Vector2f.rotate(i1, theta);
		i2 = Vector2f.rotate(i2, theta);

		return new Vect2fPair(i1, i2);

	}

}
