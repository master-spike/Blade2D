package com.spacegame.main;

import com.spacegame.characters.Asteroid;
import com.spacegame.physics.Vector2f;

public class AsteroidSpawnEvent extends AbstractEvent {

	private Vector2f position;
	
	public AsteroidSpawnEvent(Vector2f p) {
		this.position = p;
	}

	void trigger() {
		
		Asteroid ass = new Asteroid((int)position.x, (int)position.y, Main.ASS_RADIUS, Main.instance.GameWidth / 2, Main.instance.GameHeight / 2);
		ass.addSpin((float) (Math.random() / 3) * (Math.random() < 0.5 ? 1 : -1));
		Vector2f spawnDir = Vector2f.rotate(Vector2f.add(position, Vector2f.scale(Main.instance.getEarthPos(), -1)), Math.PI/2);
		ass.addScaledImpulse(spawnDir.x,spawnDir.y, 17);
		Main.instance.spawnCharacter(ass);
		
		
		
	}

}
