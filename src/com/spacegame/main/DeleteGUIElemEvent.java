package com.spacegame.main;

import com.spacegame.guielems.AbstractGUIElem;

public class DeleteGUIElemEvent  extends AbstractEvent {

	AbstractGUIElem guielem;

	public DeleteGUIElemEvent(AbstractGUIElem g) {
		guielem = g;
	}
	void trigger() {
		
		Main.instance.guielems.remove(guielem);
		
	}

}
