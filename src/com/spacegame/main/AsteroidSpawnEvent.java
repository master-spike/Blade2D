package com.spacegame.main;

import com.spacegame.characters.Asteroid;
import com.spacegame.physics.Vector2f;

public class AsteroidSpawnEvent extends AbstractEvent {

	private Vector2f position;
	
	public AsteroidSpawnEvent(Vector2f p) {
		this.position = p;
	}
	
	private static final float ASTEROID_VEL_RANDOM_FACTOR = 1;

	public void trigger() {
		
		Asteroid ass = new Asteroid((int)position.x, (int)position.y, Main.ASS_RADIUS, Main.instance.GameWidth / 2, Main.instance.GameHeight / 2);
		ass.addSpin((float) (Math.random() / 3) * (Math.random() < 0.5 ? 1 : -1));
		Vector2f spawnDir = Vector2f.rotate(Vector2f.add(position, Vector2f.scale(Main.instance.getEarthPos(), -1)), Math.PI/2);
		
		Vector2f impulse = Vector2f.scale(ass.getGravityForce(Main.instance.getEarthPos().x, Main.instance.getEarthPos().y), 
											Vector2f.magnitude(spawnDir) * ass.getWeight());
		impulse = Vector2f.scale(Vector2f.normalise(impulse), (float) Math.sqrt(Vector2f.magnitude(impulse)));
		
		ass.addImpulse(impulse.y, -impulse.x);
		ass.addImpulse(randomVel()*ass.getWeight(), randomVel()*ass.getWeight());
		Main.instance.spawnCharacter(ass);
		
	}
	
	private static float randomVel() {
		return (float) (Math.random()-0.5)*ASTEROID_VEL_RANDOM_FACTOR;
	}

}
