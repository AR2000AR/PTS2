package controller;

import application.Main;

public abstract class Controller {
	private static Main mainClass = null;
	private static String name;

	public static Main getMainClass() {
		return mainClass;
	}

	public static String getProfilName() {
		return Controller.name;
	}

	public static void setMainClass(Main mainClass) {
		Controller.mainClass = mainClass;
	}

	public static void setProfilName(String name) {
		Controller.name = name;
	}
}
