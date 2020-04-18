package com.blade2d.audio;

import org.lwjgl.openal.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.libc.LibCStdlib.*;

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
	
	public void playSound() {
		
	}

}
