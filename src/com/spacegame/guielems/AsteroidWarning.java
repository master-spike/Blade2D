package com.spacegame.guielems;

import com.blade2d.drawelements.LineElem;
import com.spacegame.main.Timer;
import com.spacegame.physics.Vector2f;

public class AsteroidWarning extends AbstractGUIElem{
	
	Vector2f position;
	float size;
	Timer timer;
	
	public AsteroidWarning(Vector2f p, float s) {
		position = p;
		size = s;
	}
	
	public void update() {
		drawelems.clear();
		drawelems.add(new LineElem(position.x - size, position.y - size,
				position.x + size, position.y - size, 1.0f, 0.0f, 0.0f, 1.0f, 10000));
		drawelems.add(new LineElem(position.x + size, position.y - size,
				position.x + size, position.y + size, 1.0f, 0.0f, 0.0f, 1.0f, 10000));
		drawelems.add(new LineElem(position.x + size, position.y + size,
				position.x - size, position.y + size, 1.0f, 0.0f, 0.0f, 1.0f, 10000));
		drawelems.add(new LineElem(position.x - size, position.y + size,
				position.x - size, position.y - size, 1.0f, 0.0f, 0.0f, 1.0f, 10000));
	}

}
