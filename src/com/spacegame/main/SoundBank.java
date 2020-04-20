package com.spacegame.main;

import com.blade2d.audio.Sound;

public class SoundBank {
	
	public Sound[] sounds;
	
	public SoundBank(int num_sounds) {
		sounds = new Sound[num_sounds];
	}
	
	public void play(int index) {
		Main.instance.audio.play(sounds[index]);
	}
	
	public void load(int index, String filepath) {
		sounds[index] = new Sound(filepath);
	}

}
