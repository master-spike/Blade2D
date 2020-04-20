package com.spacegame.main;

import com.spacegame.characters.Asteroid;
import com.spacegame.guielems.ExplodingAsteriod;

public class AsteroidExplodeEvent extends AbstractEvent {
	
	Asteroid ass;
	
	public AsteroidExplodeEvent(Asteroid ass) {
		this.ass = ass;
	}

	public void trigger() {

		Main.instance.guielems.add(new ExplodingAsteriod((int)ass.getPosition().x,(int)ass.getPosition().y,ass.getmVertices()));
		AsteroidDespawnEvent despawn_event = new AsteroidDespawnEvent(ass);
		despawn_event.trigger();
		
		PlaySoundEvent pse = new PlaySoundEvent(Main.SFIND_EXPLOSION_1);
		pse.trigger();
		
	}

}
