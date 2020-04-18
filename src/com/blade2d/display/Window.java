package com.blade2d.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {

	private int width, height;
	private String title;
	private long window;
	public Input input;

	private float backr, backg, backb;

	public Window(int width, int height, String title) {

		this.width = width;
		this.height = height;
		this.title = (title == null) ? "" : title;

		backr = 0.0f;
		backg = 0.0f;
		backb = 0.0f;

		input = new Input();

	}

	public int create() {

		if (!GLFW.glfwInit()) {
			System.err.println("Err: GLFW not initialised");
			return 1;
		}

		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0) {
			System.err.println("Err: failed to create window");
			return 2;
		}

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();

		GLFW.glfwSetKeyCallback(window, input.getKeyboard());
		GLFW.glfwSetCursorPosCallback(window, input.getMousePos());
		GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtons());

		GLFW.glfwShowWindow(window);

		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);

		GLFW.glfwSwapInterval(0);

		return 0;

	}

	public void initOpenGl() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
	}

	public void update() {
		GL11.glClearColor(backr, backg, backb, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwPollEvents();
		GL11.glClear(0);
	}

	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}

	public void destroy() {
		GLFW.glfwWindowShouldClose(window);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
		input.destroy();
	}

	public void setBackgroundColour(float r, float g, float b) {
		backr = r;
		backg = g;
		backb = b;
	}

	public int getMousex() {
		return (int) input.getMousex();
	}

	public int getMousey() {
		return height - (int) input.getMousey();
	}
}
