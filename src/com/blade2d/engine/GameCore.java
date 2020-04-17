package com.blade2d.engine;

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
	
	protected void Start() {
		window = new Window(resWidth, resHeight, window_title);
		window.create();
		window.initOpenGl();
		init();
		long start_time = System.nanoTime();
		int frames = 1;
		while(!toTerminate()) {
			updateWindow();
			update();
			render();
			while(System.nanoTime() - start_time < (frames*1000000)/update_rate);
			frames += 1;
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
		for (AbstractDrawElem e : draw_elems) {
			e.draw();
		}
		window.swapBuffers();
	}
	
	protected void terminate() {
		window.destroy();
	}
	
	protected PriorityQueue<AbstractDrawElem> draw_elems = new PriorityQueue<AbstractDrawElem>();
}
