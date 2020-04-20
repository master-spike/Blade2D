package com.blade2d.audio;

import org.lwjgl.openal.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioMaster {
	String defaultDeviceName;
	long device;
	int[] attributes = {0};
	long context;
	ALCCapabilities alcCapabilities;
	ALCapabilities  alCapabilities;
	ShortBuffer rawAudioBuffer;
	
	public void init() {
		defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
		device = alcOpenDevice(defaultDeviceName);
		
		context = alcCreateContext(device, attributes);
		alcMakeContextCurrent(context);

		alcCapabilities = ALC.createCapabilities(device);
		alCapabilities = AL.createCapabilities(alcCapabilities);
		

	}
	
	public void play(Sound sound) {
		int sourcePointer = alGenSources();

		//Assign the sound we just loaded to the source
		alSourcei(sourcePointer, AL_BUFFER, sound.bufferPointer);
		

		//Play the sound
		alSourcePlay(sourcePointer);
	}
	
	public void destroy() {
		alcDestroyContext(context);
		alcCloseDevice(device);
	}

}
