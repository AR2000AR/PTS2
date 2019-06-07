package application;

public abstract class Controller {
	private static Main mainClass = null;

	public static Main getMainClass() {
		return mainClass;
	}

	public static void setMainClass(Main mainClass) {
		Controller.mainClass = mainClass;
	}
}
