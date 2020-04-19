package com.spacegame.main;

import com.spacegame.characters.Asteriod;
import com.spacegame.physics.Vector2f;

public class AsteroidSpawnEvent extends AbstractEvent {

	private Vector2f position;
	
	public AsteroidSpawnEvent(Vector2f p, Main m) {
		super(m);
		this.position = p;
	}

	public AsteroidSpawnEvent(Vector2f p) {
		super();
		position = p;
	}

	void trigger() {
		
		Asteriod ass = new Asteriod((int)position.x, (int)position.y, Main.ASS_RADIUS, main.GameWidth / 2, main.GameHeight / 2);
		ass.addSpin((float) (Math.random() / 3) * (Math.random() < 0.5 ? 1 : -1));
		Vector2f spawnDir = Vector2f.rotate(Vector2f.add(position, Vector2f.scale(main.getEarthPos(), -1)), Math.PI/2);
		ass.addScaledImpulse(spawnDir.x,spawnDir.y, 17);
		main.spawnCharacter(ass);
		
		
		
	}

}
