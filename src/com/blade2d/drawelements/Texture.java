package com.blade2d.drawelements;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class Texture {

	private int id;
	
	private final Image image;
	
	public Texture(String filename){
		image = new Image(filename);
		
		// Generate ID
		id = GL11.glGenTextures();
		
		createTexture(id, image);
	}
	public Texture(String filename, int x, int y, int w, int h){
		image = new Image(filename, x, y, w, h);
		
		// Generate ID
		id = GL11.glGenTextures();
		
		createTexture(id, image);
	}
	
	private void createTexture(int id, Image image) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		bind();

		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		
		GL11.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.width, image.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.buffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		unbind();
	}

	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public int getWidth() {
		return image.width;
	}
	
	public int getHeight() {
		return image.height;
	}
}