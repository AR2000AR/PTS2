package gestionDeDonnee;

public class Score implements Comparable<Score> {

	private String name;
	private int time;

	/**
	 * @param name
	 * @param time
	 */
	public Score(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}

	@Override
	public int compareTo(Score score) {
		Integer v1 = getTime();
		Integer v2 = score.getTime();
		return v1.compareTo(v2);
	}

	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}
}