package com.blade2d.drawelements;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

/**
 * An image designed for use as an OpenGL texture
 */
public class Image {
	/**
	 * Raw pixel data
	 */
	private final int[] data;
	
	/**
	 * Width in pixels
	 */
	public final int width;
	
	/**
	 * Height in pixels
	 */
	public final int height;
	
	/**
	 * Pixel data as a buffer
	 */
	public final IntBuffer buffer;
	
	/**
	 * @param filename - path to image to load
	 */
	public Image(String filename) {
		BufferedImage image = loadImageFile(filename);
		
		width = image.getWidth();
		height = image.getHeight();
		
		data = getPixelsFromImage(image);
		buffer = Buffers.createIntBuffer(data).asReadOnlyBuffer();
	}
	
	
	// create an Image from only a section of the file
	public Image(String filename, int x, int y, int w, int h) {
		BufferedImage image = loadImageFile(filename);
		
		width = w;
		height = h;
		
		data = getPixelsFromImageSection(image, x, y, w, h);
		buffer = Buffers.createIntBuffer(data).asReadOnlyBuffer();
	}
	
	private BufferedImage loadImageFile(String filename) {
		BufferedImage image = null;
		
		// Attempt to load image file
		try {
			image = ImageIO.read(new FileInputStream(filename));
		} catch (IOException e){
			e.printStackTrace();
			throw new Error("Could not load image: " + filename);
		}
		
		return image;
	}
	
	private int[] getPixelsFromImage(BufferedImage image) {
		return getPixelsFromImageSection(image, 0,0,image.getWidth(),image.getHeight());
	}
	int[] getPixelsFromImageSection(BufferedImage image, int x, int y, int w, int h) {
		
		// Grab raw pixel data
		int[] pixels = new int[w * h];
		image.getRGB(x, y, w, h, pixels, 0, w);
		
		// Reorder argb into abgr
		int[] data = new int[w * h];
		for(int i = 0; i < w * h; i++){
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		return data;
	}
}

