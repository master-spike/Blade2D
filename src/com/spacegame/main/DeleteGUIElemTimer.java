package com.spacegame.main;

import java.util.ArrayList;

import com.spacegame.guielems.AbstractGUIElem;

public class DeleteGUIElemTimer extends Timer {

	public DeleteGUIElemTimer(int c, AbstractGUIElem e) {
		super(c, new ArrayList<AbstractEvent>());
		events.add(new DeleteGUIElemEvent(e));
		// TODO Auto-generated constructor stub
	}

}
