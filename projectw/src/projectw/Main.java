package projectw;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
	public static void main(String[] args) {
		if (!glfwInit()) {
			throw new IllegalStateException("failed");
		}
		
		long window = glfwCreateWindow(640, 480, "My program", 0, 0);
		
		
	}
}
