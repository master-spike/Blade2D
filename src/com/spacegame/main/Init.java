package com.spacegame.main;


public class Init {

	public static void main(String[] args) {
		
		final int WIDTH = 768, HEIGHT = 768;
		final int SIDEBARWIDTH = WIDTH - HEIGHT;
		
		Main game = new Main(WIDTH, HEIGHT, "Space Game", 60);
		game.GameWidth = WIDTH - SIDEBARWIDTH;
		game.GameHeight = HEIGHT;
		game.SideBarWidth = SIDEBARWIDTH;
		game.Start();
		


	}

}
