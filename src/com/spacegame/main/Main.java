package com.spacegame.main;

import java.util.ArrayList;

import com.blade2d.engine.GameCore;
import com.spacegame.characters.AbstractCharacter;
import com.spacegame.characters.Najeeb;

public class Main extends GameCore {
	
	private ArrayList<AbstractCharacter> mCharacters;

	public Main(int w, int h, String t, int r) {
		super(w, h, t, r);
	}

	@Override
	protected void init() {
		mCharacters = new ArrayList<AbstractCharacter>();
		mCharacters.add(new Najeeb(super.resWidth/2, super.resHeight/2, 100));
	}

	@Override
	protected boolean toTerminate() {
		return super.window.shouldClose();
	}

	@Override
	protected void update() {
		super.draw_elems.clear();
		
		for (AbstractCharacter character: mCharacters) {
			super.draw_elems.addAll(character.getShape());
		}
		
		
	}

	
}
