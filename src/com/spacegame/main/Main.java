package com.spacegame.main;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.LineElem;
import com.blade2d.engine.GameCore;
import com.spacegame.characters.AbstractCharacter;
import com.spacegame.characters.Asteriod;
import com.spacegame.characters.Earth;
import com.spacegame.characters.Najeeb;

public class Main extends GameCore {
	
	private ArrayList<AbstractCharacter> mCharacters;
	private AbstractCharacter player;
	private SideBar mSideBar;
	
	public int GameWidth, GameHeight, SideBarWidth;
	
	private ArrayList<Star> mStars;

	public Main(int w, int h, String t, int r) {
		super(w, h, t, r);
	}

	protected void init() {
		mSideBar = new SideBar(GameWidth, 0, SideBarWidth, GameHeight);
		
		mCharacters = new ArrayList<AbstractCharacter>();
		mStars = new ArrayList<Star>();
		
		player = new Najeeb(GameWidth/2, GameHeight/2, 20, GameWidth, GameHeight);
		mCharacters.add(player);
		
		int numStars = 100;
		for (int i = 0; i != numStars; i++) {
			mStars.add(new Star((int) (Math.random() * GameWidth), (int) (Math.random() * GameHeight)));
		}
		
		mCharacters.add(new Earth(GameWidth / 2, GameHeight / 2, 100));
	}

	protected boolean toTerminate() {
		return super.window.shouldClose();
	}

	protected void update() {
		
		// Do drawing
		
		super.draw_elems.clear();
		
		for (AbstractCharacter character: mCharacters) {
			if(!character.isHidden()) super.draw_elems.addAll(character.getShape());
		}
		for (Star star: mStars) {
			super.draw_elems.add(star.getShape());
		}
		super.draw_elems.addAll(mSideBar.getShapes());
		
		// Get game objects to update 
		
		for (AbstractCharacter character: mCharacters) {
			character.update();
		}
		
		// Spawn asteroids
		
		float spawnChance = 0.001f;
		if (Math.random() < spawnChance) {
			
			int assRadius = 20;
			
			int x = assRadius;
			int minY = assRadius;
			int maxY = GameHeight - assRadius;
			int y = (int) (Math.random() * (maxY - minY) + minY); 
			
			if (Math.random() < 0.5) {
				int temp = x;
				x = y;
				y = temp;
			}
			if (Math.random() < 0.5) x = maxY - x;
			if (Math.random() < 0.5) y = maxY - y;
			
			Asteriod ass = new Asteriod(x, y, assRadius, GameWidth / 2, GameHeight / 2);
			ass.addSpin((float) (Math.random() / 3));
			mCharacters.add(ass);
			
		}
		
		// Control player
		
		boolean[] keys = window.input.getKeys();
		if (keys[GLFW.GLFW_KEY_W]) player.addFowardImpulse( 1);
		if (keys[GLFW.GLFW_KEY_S]) player.addFowardImpulse(-1);
		if (keys[GLFW.GLFW_KEY_A]) player.addEngineSpin( 1);
		if (keys[GLFW.GLFW_KEY_D]) player.addEngineSpin(-1);
		
	}
	
	AbstractCharacter centered;
}

class Star {
	private static final double chanceVisible = 0.5;
	private static final int size = 5;
	
	public final int mX, mY;
	@SuppressWarnings("unused")
	private boolean mVisible;
	
	public Star (int x, int y) {
		mX = x;
		mY = y;
		mVisible = Math.random() < chanceVisible;
	}
	
	public AbstractDrawElem getShape() {
		double angle = Math.random() * 2 * Math.PI;
		int xDist = (int) (Math.cos(angle) * size);
		int yDist = (int) (Math.sin(angle) * size);
		
		float r = 1.0f, g = 0.0f, b = 1.0f, a = 1.0f;
		
		return new LineElem(mX, mY, mX+xDist, mY+yDist, r, g, b, a, -10000);
	}
}

