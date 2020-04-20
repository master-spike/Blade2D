package com.spacegame.main;

import com.blade2d.audio.Sound;

public class SoundBank {
	
	public Sound[] sounds;
	public int[] durations;
	
	public int sourcePointer;
	
	public SoundBank(int num_sounds) {
		sounds = new Sound[num_sounds];
		durations = new int[num_sounds];
	}
	
	public void play(int index) {
		Main.instance.audio.playSound(sounds[index], durations[index]);
	}
	
	public void load(int index, String filepath, int duration) {
		sounds[index] = new Sound(filepath);
		durations[index] = duration;
	}

}
