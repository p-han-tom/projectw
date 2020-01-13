package projectw;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

public class Main {
	public static void main(String[] args) {
		if (!glfwInit()) {
			throw new IllegalStateException("failed");
		}
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(640, 480, "My program", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("failed to create window");
		}
		System.out.println(window);
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - 640)/2, (videoMode.height()-480)/2);
		
		glfwShowWindow(window);
		
		while (!glfwWindowShouldClose(window)) {
			glfwPollEvents();
		}
	}
}
