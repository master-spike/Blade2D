package com.spacegame.main;

import com.blade2d.audio.AudioMaster;

public class Init {

	public static void main(String[] args) {
		
		final int WIDTH = 768, HEIGHT = 768;
		final int SIDEBARWIDTH = WIDTH - HEIGHT;
		
		Main game = new Main(WIDTH, HEIGHT, "Space Game", 60);
		game.GameWidth = WIDTH - SIDEBARWIDTH;
		game.GameHeight = HEIGHT;
		game.SideBarWidth = SIDEBARWIDTH;
		
		loadSounds();
		game.audio = audio;
		game.soundbank = soundbank;
		
		do {
			game.Start();
		} while (game.pauseStatus == Main.RESTARTED);
		
		audio.destroy();
		


	}
	
	public static AudioMaster audio;

	public static SoundBank soundbank;
	
	private static void loadSounds() {
		audio = new AudioMaster();
		audio.init();
		soundbank = new SoundBank(20);
		
		soundbank.load(Main.SFIND_EXPLOSION_1, "res/Explosion_1.ogg", 1000);
		soundbank.load(Main.SFIND_COLLISION_1, "res/Collision_1.ogg", 300);
		soundbank.load(Main.SFIND_ALARM, "res/Alarm.ogg", 1900);
		soundbank.load(Main.SFIND_SCORE_UP, "res/Score_up.ogg", 1000);
		soundbank.load(Main.SFIND_EXPLOSION_2, "res/Explosion_2.ogg", 3000);
	}

}
