package com.blade2d.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {

	private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	
	public boolean[] getKeys() {
		return keys;
	}

	public boolean[] getButtons() {
		return buttons;
	}

	private boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private double mousex, mousey;

	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback mousePos;
	private GLFWMouseButtonCallback mouseButtons;

	public Input() {
		keyboard = new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				
				if (key < 0) return;
				keys[key] = (action != GLFW.GLFW_RELEASE);
				
			}
		};
		mousePos = new GLFWCursorPosCallback() {
			public void invoke(long window, double xpos, double ypos) {
				mousex = xpos; mousey = ypos;
			}
			
		};
		mouseButtons = new GLFWMouseButtonCallback(){

			public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action != GLFW.GLFW_RELEASE);
				
			}
		};
		
	}

	public double getMousex() {
		return mousex;
	}

	public double getMousey() {
		return mousey;
	}
	
	public GLFWKeyCallback getKeyboard() {
		return keyboard;
	}

	public GLFWCursorPosCallback getMousePos() {
		return mousePos;
	}

	public GLFWMouseButtonCallback getMouseButtons() {
		return mouseButtons;
	}
	
	public void destroy() {
		keyboard.free();
		mousePos.free();
		mouseButtons.free();
	}

}
