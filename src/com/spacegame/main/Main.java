package com.spacegame.main;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.blade2d.drawelements.AbstractDrawElem;
import com.blade2d.drawelements.Font;
import com.blade2d.drawelements.QuadElem;
import com.blade2d.engine.GameCore;
import com.spacegame.characters.AbstractCharacter;
import com.spacegame.characters.Asteroid;
import com.spacegame.characters.Earth;
import com.spacegame.characters.Najeeb;
import com.spacegame.guielems.AbstractGUIElem;
import com.spacegame.guielems.AsteroidWarning;
import com.spacegame.physics.CollidableCircleModel;
import com.spacegame.physics.PhysicsCalc;
import com.spacegame.physics.Vect2fPair;
import com.spacegame.physics.Vector2f;

public class Main extends GameCore {
	
	public static Main instance;
	
	private ArrayList<AbstractCharacter> mCharacters;
	private AbstractCharacter player;
	private AbstractCharacter earth;
	private SideBar mSideBar;
	
	public ArrayList<AbstractGUIElem> guielems;
	public ArrayList<Timer> timers;
	
	// CONSTS
	
	public static final float GRAVITY_CONST = 10;
	public static final float ASTEROID_MIN_SPAWN_HEIGHT = 200;
	public static final float ASTEROID_MAX_SPAWN_HEIGHT = 300;
	public static final float ASTEROID_SPAWN_CHANCE_PER_FRAME = 0.004f;
	public static final int ASTEROID_SPAWN_WARNING_TIME = 120;
	public static final int ASS_RADIUS = 10;
	public static final int EARTH_RADIUS = 70;
	public static final int NUM_STARS = 200;

	
	public int GameWidth, GameHeight, SideBarWidth;
	
	private ArrayList<Star> mStars;
	
	public Font font;

	public Main(int w, int h, String t, int r) {
		super(w, h, t, r);
		instance = this;
	}

	protected void init() {
		font = new Font("res/mc_0.png","res/mc.fnt");
		mSideBar = new SideBar(GameWidth, 0, SideBarWidth, GameHeight);
		mCharacters = new ArrayList<AbstractCharacter>();
		mStars = new ArrayList<Star>();
		guielems = new ArrayList<AbstractGUIElem>();
		timers = new ArrayList<Timer>();
		
		player = new Najeeb((int) (GameWidth /2 - EARTH_RADIUS * 2),
						    (int) (GameHeight/2 - EARTH_RADIUS * 2), 
						    20, GameWidth, GameHeight);
		mCharacters.add(player);
		
		for (int i = 0; i != NUM_STARS; i++) {
			mStars.add(new Star((int) (Math.random() * GameWidth), (int) (Math.random() * GameHeight)));
		}
		
		earth = new Earth(GameWidth / 2, GameHeight / 2, EARTH_RADIUS);
		mCharacters.add(earth);
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
		for (AbstractGUIElem g : guielems) {
			super.draw_elems.addAll(g.getShape());
		}
		super.draw_elems.addAll(mSideBar.getShapes());
		
		// update game elements
		for (AbstractGUIElem g : guielems) {
			g.update();
		}
		for (AbstractCharacter character: mCharacters) {
			character.update();
		}
		for (Star s : mStars) {
			s.update();
		}
		
		
		// Spawn asteroids
		
		if (Math.random() < ASTEROID_SPAWN_CHANCE_PER_FRAME) {
			
			
			

			float theta = (float) (Math.random()*Math.PI*2);
			float height = (float) (ASTEROID_MIN_SPAWN_HEIGHT+Math.random()*(ASTEROID_MAX_SPAWN_HEIGHT - ASTEROID_MIN_SPAWN_HEIGHT));
			Vector2f spawnPos = new Vector2f((float)(height*Math.cos(theta)), (float)(height*Math.sin(theta)));
			
			AsteroidSpawnEvent ase = new AsteroidSpawnEvent(Vector2f.add(spawnPos, earth.getPosition()));
			AsteroidWarning aw = new AsteroidWarning(Vector2f.add(spawnPos, earth.getPosition()), ASS_RADIUS);
			guielems.add(aw);
			DeleteGUIElemEvent del = new DeleteGUIElemEvent(aw);
			Timer t = new Timer(Main.ASTEROID_SPAWN_WARNING_TIME);
			
			t.addEvent(ase);
			t.addEvent(del);
			
			t.start();
		}
		
		// Collisions
		
		for (int i = 0; i != mCharacters.size(); i++) {
			for (int j = i+1; j != mCharacters.size(); j++) {
				AbstractCharacter c1 = mCharacters.get(i);
				AbstractCharacter c2 = mCharacters.get(j);
				
				
				
				if (c1.hasCollided(c2)) { // Oh No!!!
					CollidableCircleModel m1 = new CollidableCircleModel(c1.getWeight(), c1.getVelocity(), c1.getPosition());
					CollidableCircleModel m2 = new CollidableCircleModel(c2.getWeight(), c2.getVelocity(), c2.getPosition());
					Vect2fPair ans = PhysicsCalc.getElasticCollision(m1, m2);
					c1.addImpulse(ans.v1.x, ans.v1.y);
					c2.addImpulse(ans.v2.x, ans.v2.y);
					c1.onCollide();
					c2.onCollide();
				}
			}
		}
		
		// explode asteroids colliding into Earth
		ArrayList<AsteroidExplodeEvent> explosions = new ArrayList<AsteroidExplodeEvent>();
		for (AbstractCharacter c : mCharacters) {
			if (c.getClass() == Asteroid.class && c.hasCollided(earth)) {
				explosions.add(new AsteroidExplodeEvent((Asteroid)c));
				
			}
		}
		for (AsteroidExplodeEvent e : explosions) {
			e.trigger();
		}
		
		// gravity
		
		for (AbstractCharacter c : mCharacters) {
			if (!c.equals(earth)) c.applyGravity(earth.getPosition().x, earth.getPosition().y);
		}
		
		// Control player
		
		boolean[] keys = window.input.getKeys();
		if (keys[GLFW.GLFW_KEY_W]) player.addFowardImpulse( 1);
		if (keys[GLFW.GLFW_KEY_S]) player.addFowardImpulse(-1);
		if (keys[GLFW.GLFW_KEY_A]) player.addEngineSpin( 1);
		if (keys[GLFW.GLFW_KEY_D]) player.addEngineSpin(-1);
		
		// decrement timers
		for(Timer t : timers) {
			t.decrement();
		}
		
	}
	
	public Vector2f getEarthPos(){
		return earth.getPosition();
	}
	
	public void spawnCharacter(AbstractCharacter c) {
		mCharacters.add(c);
	}
	
	public void despawnCharacter(AbstractCharacter c) {
		mCharacters.remove(c);
	}
}

class Star {
	private static final float size = 3;
	
	public final int mX, mY;
	
	public Star (int x, int y) {
		mX = x;
		mY = y;
		brightness = (float)Math.random();
	}
	
	private float brightness;
	
	public void update() {
		double r = Math.random();
		if (r < 0.5) {
			brightness -= 0.1f;
		}
		else {
			brightness += 0.2f;
		}
		if (brightness > 1f) brightness = 1f;
		if (brightness < 0.4f) brightness = 0.5f;
	}
	
	public AbstractDrawElem getShape() {
		
		return new QuadElem(mX - size/2, mY - size/2,
				mX + size/2, mY - size/2,
				mX + size/2, mY + size/2,
				mX - size/2, mY + size/2,
				brightness, brightness, brightness, 1.0f, -10000);
	}
}

