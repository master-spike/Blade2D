package com.blade2d.engine;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.blade2d.display.Window;
import com.blade2d.drawelements.AbstractDrawElem;

public abstract class GameCore {
	
	protected int resWidth;
	protected int resHeight;
	public String window_title;
	protected int update_rate; // updates per second
	
	public GameCore(int w, int h, String t, int r) {
		resWidth = w; resHeight = h; window_title = t; update_rate = r;
	}
	
	protected Window window;
	
	public void Start() {
		window = new Window(resWidth, resHeight, window_title);
		window.create();
		window.initOpenGl();
		init();
		long last_time = System.nanoTime();
		long one_bill = 1000000000;
		while(!toTerminate()) {
			updateWindow();
			update();
			render();
			while(System.nanoTime() - last_time < one_bill/update_rate);
			last_time = System.nanoTime();
		}
		terminate();
		
	}
	
	protected abstract void init();
	protected abstract boolean toTerminate();
	
	protected void updateWindow() {
		
		window.update();
	}
	
	protected abstract void update();
	
	protected void render() {
		draw_elems.sort(null);;
		for (AbstractDrawElem e : draw_elems) {
			e.draw();
		}
		window.swapBuffers();
	}
	
	protected void terminate() {
		window.destroy();
	}
	
	protected ArrayList<AbstractDrawElem> draw_elems = new ArrayList<AbstractDrawElem>();
}
