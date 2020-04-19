package com.blade2d.drawelements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Font {
	
	private int[] x_pos;
	private int[] y_pos;
	private int[] widths;
	private int[] heights;
	
	private Texture[] charTextures;
	
	public Font(String filepath, String datapath) {
		x_pos = new int[256];
		y_pos = new int[256];
		widths = new int[256];
		heights = new int[256];
		charTextures = new Texture[256];
		
		try {
			Scanner sc = new Scanner(new FileInputStream(datapath));
			while (sc.hasNext()) {
				String ln = sc.nextLine();
				String[] lnsplit = ln.split(" ");
				int c = Integer.parseInt(lnsplit[0]);
				if (c <= 255 && c >=0) {
					x_pos[c] = Integer.parseInt(lnsplit[1]);
					y_pos[c] = Integer.parseInt(lnsplit[2]);
					widths[c] = Integer.parseInt(lnsplit[3]);
					heights[c] = Integer.parseInt(lnsplit[4]);
				}
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < 256; i++) {
			if (heights[i] !=0) charTextures[i] = new Texture(filepath, x_pos[i], y_pos[i], widths[i], heights[i]);
		}
		
	}
	
	public TexRectElem getCharacter(char c, float px, float py, float scale, float r, float g, float b, float a, int layer) {
		return new TexRectElem(px, py,
				px + widths[c]*scale, py,
				px + widths[c]*scale, py + heights[c]*scale,
				px, py + heights[c]*scale, layer, r,g,b,a, charTextures[c]);
		
	}
	
	public ArrayList<TexRectElem> getString(String str, float px, float py, float scale, float r, float g, float b, float a, int layer) {
		
		ArrayList<TexRectElem> chars = new ArrayList<TexRectElem>();
		float cursorpos = px;
		for (char c : str.toCharArray()) {
			chars.add(getCharacter(c,cursorpos,py,scale,r,g,b,a,layer));
			cursorpos += scale*widths[c];
		}
		return chars;
		
	}

}
