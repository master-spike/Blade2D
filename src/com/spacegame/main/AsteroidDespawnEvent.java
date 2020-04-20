package com.spacegame.main;

import com.spacegame.characters.Asteroid;

public class AsteroidDespawnEvent extends AbstractEvent {

	private Asteroid asteroid;
	
	public AsteroidDespawnEvent(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
	
	public void trigger() {
		
		Main.instance.despawnCharacter(asteroid);
		Main.instance.mNumAsteroids--;
		
	}

}
