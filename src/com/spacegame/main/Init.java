package com.spacegame.main;

public class Init {

	public static void main(String[] args) {
		
		final int WIDTH = 1280, HEIGHT = 720;
		
		Main game = new Main(WIDTH, HEIGHT, "Space Game", 60);
		game.Start();

	}

}
