package com.blade2d.audio;

import org.lwjgl.openal.*;

import java.nio.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioMaster {
	String defaultDeviceName;
	long device;
	int[] attributes = { 0 };
	long context;
	ALCCapabilities alcCapabilities;
	ALCapabilities alCapabilities;
	ShortBuffer rawAudioBuffer;

	int[] sources = new int[16];
	long[] times = new long[16];

	public void init() {
		defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
		device = alcOpenDevice(defaultDeviceName);

		context = alcCreateContext(device, attributes);
		alcMakeContextCurrent(context);

		alcCapabilities = ALC.createCapabilities(device);
		alCapabilities = AL.createCapabilities(alcCapabilities);

	}

	// play a sound with duration in millis
	public void playSound(Sound sound, int d) {
		
		long current_time = System.currentTimeMillis();
		int ind = -1;
		for (int i = 0; i < 16; i++) {
			if (times[i] < current_time) {
				ind = i;
				break;
			}
		}
		if (ind == -1) {
			System.err.println("too many sources, failed to play sound");
			return;
		}
		
		int sourcePointer = alGenSources();

		// Assign the sound to the sourceww
		alSourcei(sourcePointer, AL_BUFFER, sound.bufferPointer);

		// Play the sound
		alSourcePlay(sourcePointer);

		sources[ind] = sourcePointer;
		times[ind] = current_time + d;

	}

	public void checkSources() {
		long current_time = System.currentTimeMillis();
		for (int i = 0; i < 16; i++) {
			if (times[i] < current_time)
				alDeleteSources(sources[i]);
		}
	}

	public void destroy() {
		alcDestroyContext(context);
		alcCloseDevice(device);
	}

}
