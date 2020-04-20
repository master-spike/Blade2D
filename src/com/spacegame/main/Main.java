package com.spacegame.main;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.blade2d.audio.AudioMaster;
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
import com.spacegame.guielems.HealthDecreaseIndicator;
import com.spacegame.guielems.Healthbar;
import com.spacegame.guielems.ScoreMeter;
import com.spacegame.physics.CollidableCircleModel;
import com.spacegame.physics.PhysicsCalc;
import com.spacegame.physics.Vect2fPair;
import com.spacegame.physics.Vector2f;

public class Main extends GameCore {

	public static Main instance;

	private ArrayList<AbstractCharacter> mCharacters;
	private AbstractCharacter player;
	private AbstractCharacter earth;
	
	public ArrayList<AbstractEvent> immediate_events;

	public ArrayList<AbstractGUIElem> guielems;
	public ArrayList<Timer> timers;

	// CONSTS

	public static final float GRAVITY_CONST = 4000;
	public static final float ASTEROID_MIN_SPAWN_HEIGHT = 190;
	public static final float ASTEROID_MAX_SPAWN_HEIGHT = 280;
	public static final float ASTEROID_SPAWN_CHANCE_PER_FRAME = 0.004f;
	public static final int ASTEROID_SPAWN_WARNING_TIME = 120;
	public static final int ASS_RADIUS = 10;
	public static final int EARTH_RADIUS = 70;
	public static final int NUM_STARS = 200;
	public static final int MAX_HP = 1000;

	public static final int SFIND_EXPLOSION_1 = 0;
	public static final int SFIND_COLLISION_1= 1;
	public static final int SFIND_ALARM= 2;
	
	public int GameWidth, GameHeight, SideBarWidth;

	private ArrayList<Star> mStars;

	public Font font;

	public int hp;
	
	private int score;

	private boolean paused;
	
	ArrayList<AbstractDrawElem> pausemenu;
	
	public AudioMaster audio;

	public SoundBank soundbank;

	public Main(int w, int h, String t, int r) {
		super(w, h, t, r);
		instance = this;
	}
	
	private void loadSounds() {
		audio = new AudioMaster();
		audio.init();
		soundbank = new SoundBank(20);
		
		soundbank.load(0, "res/Explosion_1.ogg", 1000);
		soundbank.load(1, "res/Collision_1.ogg", 300);
		soundbank.load(2, "res/Alarm.ogg", 1900);
		soundbank.load(3, "res/Score_up.ogg", 1000);
	}

	protected void init() {

		loadSounds();
		
		font = new Font("res/mc_0.png", "res/mc.fnt");
		mCharacters = new ArrayList<AbstractCharacter>();
		mStars = new ArrayList<Star>();
		guielems = new ArrayList<AbstractGUIElem>();
		immediate_events = new ArrayList<AbstractEvent>();
		timers = new ArrayList<Timer>();
		hp = MAX_HP;
		paused = false;
		score = 0;
		keysPrevious = new boolean[window.input.getKeys().length];
		
		pausemenu = new ArrayList<AbstractDrawElem>();
		pausemenu.addAll(font.getString("Paused: press ESCAPE to exit", 30, 360, 3.0f,1f, 0.0f, 1.0f, 1.0f, 12000000));
		pausemenu.addAll(font.getString("     or SPACE to continue", 30, 320, 3.0f, 1f, 0.0f, 1.0f, 1.0f, 12000000));
		
		
		player = new Najeeb((int) (GameWidth / 2 - EARTH_RADIUS * 2), (int) (GameHeight / 2 - EARTH_RADIUS * 2), 20,
				GameWidth, GameHeight);
		mCharacters.add(player);

		for (int i = 0; i != NUM_STARS; i++) {
			mStars.add(new Star((int) (Math.random() * GameWidth), (int) (Math.random() * GameHeight)));
		}

		guielems.add(new Healthbar(100, 10, 334, 300, 1, 0f, 0f, 0f, 0f, 0f, 0f, MAX_HP / 3, (2 * MAX_HP) / 3));
		guielems.add(new ScoreMeter());

		earth = new Earth(GameWidth / 2, GameHeight / 2, EARTH_RADIUS);
		mCharacters.add(earth);
		
		Vector2f spawnDir = Vector2f.subtract(earth.getPosition(), player.getPosition());
		Vector2f impulse = Vector2f.scale(player.getGravityForce(Main.instance.getEarthPos().x, Main.instance.getEarthPos().y), 
											Vector2f.magnitude(spawnDir) * player.getWeight());
		impulse = Vector2f.scale(Vector2f.normalise(impulse), (float) Math.sqrt(Vector2f.magnitude(impulse)));
		player.addImpulse(impulse.y, -impulse.x);
	}

	protected boolean toTerminate() {
		return super.window.shouldClose();
	}
	
	boolean[] keysPrevious;

	protected void update() {
		
		// clear expired sounds
		audio.checkSources();

		// Do drawing

		super.draw_elems.clear();

		for (AbstractCharacter character : mCharacters) {
			if (!character.isHidden())
				super.draw_elems.addAll(character.getShape());
		}
		for (Star star : mStars) {
			super.draw_elems.add(star.getShape());
		}
		for (AbstractGUIElem g : guielems) {
			super.draw_elems.addAll(g.getShape());
		}
		
		
		
		// do this only if not paused
		if (!paused) {
			// update game elements
			for (AbstractGUIElem g : guielems) {
				g.update();
			}
			for (AbstractCharacter character : mCharacters) {
				character.update();
			}
			for (Star s : mStars) {
				s.update();
			}

			// Spawn asteroids

			if (Math.random() < ASTEROID_SPAWN_CHANCE_PER_FRAME) {

				float theta = (float) (Math.random() * Math.PI * 2);
				float height = (float) (ASTEROID_MIN_SPAWN_HEIGHT
						+ Math.random() * (ASTEROID_MAX_SPAWN_HEIGHT - ASTEROID_MIN_SPAWN_HEIGHT));
				Vector2f spawnPos = new Vector2f((float) (height * Math.cos(theta)),
						(float) (height * Math.sin(theta)));

				AsteroidSpawnEvent ase = new AsteroidSpawnEvent(Vector2f.add(spawnPos, earth.getPosition()));
				AsteroidWarning aw = new AsteroidWarning(Vector2f.add(spawnPos, earth.getPosition()), ASS_RADIUS);
				PlaySoundEvent pse = new PlaySoundEvent(SFIND_ALARM);
				guielems.add(aw);
				DeleteGUIElemEvent del = new DeleteGUIElemEvent(aw);
				Timer t = new Timer(Main.ASTEROID_SPAWN_WARNING_TIME);
				
				immediate_events.add(pse);

				t.addEvent(ase);
				t.addEvent(del);

				t.start();
			}

			// Collisions

			for (int i = 0; i != mCharacters.size(); i++) {
				for (int j = i + 1; j != mCharacters.size(); j++) {
					AbstractCharacter c1 = mCharacters.get(i);
					AbstractCharacter c2 = mCharacters.get(j);

					if (c1.hasCollided(c2)) { // Oh No!!!
						CollidableCircleModel m1 = new CollidableCircleModel(c1.getWeight(), c1.getVelocity(),
								c1.getPosition());
						CollidableCircleModel m2 = new CollidableCircleModel(c2.getWeight(), c2.getVelocity(),
								c2.getPosition());
						Vect2fPair ans = PhysicsCalc.getElasticCollision(m1, m2);
						c1.addImpulse(ans.v1.x, ans.v1.y);
						c2.addImpulse(ans.v2.x, ans.v2.y);
						
						// threshhold for collision strength to determine whether the sound plays
						if(Vector2f.magnitude(ans.v1)/c1.getWeight() + Vector2f.magnitude(ans.v2)/c2.getWeight() > 1.6f) {
							PlaySoundEvent pse = new PlaySoundEvent(Main.SFIND_COLLISION_1);
							Main.instance.immediate_events.add(pse);
						}
					}
				}
			}

			// explode asteroids colliding into Earth, and reduce hp by energy
			// of impact
			for (AbstractCharacter c : mCharacters) {
				if (c.getClass() == Asteroid.class && c.hasCollided(earth)) {
					immediate_events.add(new AsteroidExplodeEvent((Asteroid) c));
					int r = (int) (Vector2f.magnitude(c.getVelocity()) * c.getWeight());
					immediate_events.add(new ReduceHPEvent(r));
					HealthDecreaseIndicator hdind =  new HealthDecreaseIndicator(c.getPosition().x, c.getPosition().y, r);
					guielems.add(hdind);
					Timer hdind_timer = new Timer(HealthDecreaseIndicator.DURATION);
					hdind_timer.addEvent(new DeleteGUIElemEvent(hdind));
					hdind_timer.start();
					PlaySoundEvent pse = new PlaySoundEvent(SFIND_EXPLOSION_1);
					immediate_events.add(pse);
				}
			}
			
		
			
			// trigger all immediate (non-timed) events and clear the list (to avoid re-triggering)
			for (AbstractEvent e : immediate_events) {
				e.trigger();
			}
			immediate_events.clear();

			// gravity

			for (AbstractCharacter c : mCharacters) {
				if (!c.equals(earth) && c.getDistanceTo(earth) > earth.getSize()+ c.getSize())
					c.applyGravity(earth.getPosition().x, earth.getPosition().y);
			}

			// Control player

			boolean[] keys = window.input.getKeys();
			if (keys[GLFW.GLFW_KEY_W])
				player.addFowardImpulse(1);
			if (keys[GLFW.GLFW_KEY_S])
				player.addFowardImpulse(-1);
			if (keys[GLFW.GLFW_KEY_A])
				player.addEngineSpin(1);
			if (keys[GLFW.GLFW_KEY_D])
				player.addEngineSpin(-1);

			// decrement timers
			for (Timer t : timers) {
				t.decrement();
			}
			
			// Check pause button pressed
			if (keys[GLFW.GLFW_KEY_SPACE] && !keysPrevious[GLFW.GLFW_KEY_SPACE]) {
				paused = true;
				System.out.println();
			}
			
			
		}
		else {
			super.draw_elems.addAll(pausemenu);
			if (window.input.getKeys()[GLFW.GLFW_KEY_ESCAPE] && !keysPrevious[GLFW.GLFW_KEY_ESCAPE]) {
				terminate();
			}
			if (window.input.getKeys()[GLFW.GLFW_KEY_SPACE] && !keysPrevious[GLFW.GLFW_KEY_SPACE]) {
				paused = false;
			}
		}
		keysPrevious = window.input.getKeys().clone();

	}

	public Vector2f getEarthPos() {
		return earth.getPosition();
	}

	public void spawnCharacter(AbstractCharacter c) {
		mCharacters.add(c);
	}

	public void despawnCharacter(AbstractCharacter c) {
		mCharacters.remove(c);
	}
	
	public int getScore() {
		return score;
	}
	public void increaseScore(int amount) {
		score += amount;
	}
}

class Star {
	private static final float size = 3;

	public final int mX, mY;

	public Star(int x, int y) {
		mX = x;
		mY = y;
		brightness = (float) Math.random();
	}

	private float brightness;

	public void update() {
		double r = Math.random();
		if (r < 0.5) {
			brightness -= 0.1f;
		} else {
			brightness += 0.2f;
		}
		if (brightness > 1f)
			brightness = 1f;
		if (brightness < 0.4f)
			brightness = 0.5f;
	}

	public AbstractDrawElem getShape() {

		return new QuadElem(mX - size / 2, mY - size / 2, mX + size / 2, mY - size / 2, mX + size / 2, mY + size / 2,
				mX - size / 2, mY + size / 2, brightness, brightness, brightness, 1.0f, -10000);
	}
}
