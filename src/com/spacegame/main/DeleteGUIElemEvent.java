package com.spacegame.main;

import com.spacegame.guielems.AbstractGUIElem;

public class DeleteGUIElemEvent  extends AbstractEvent {

	AbstractGUIElem guielem;

	public DeleteGUIElemEvent(Main m, AbstractGUIElem g) {
		super(m);
		guielem = g;
	}
	public DeleteGUIElemEvent(AbstractGUIElem g) {
		super();
		guielem = g;
	}
	void trigger() {
		
		main.guielems.remove(guielem);
		
	}

}
