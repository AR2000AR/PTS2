package application;

import controller.CompetitionController;

public class Timer implements Runnable {

	double time = 0;
	boolean enCours = false;
	CompetitionController leController;

	public Timer(CompetitionController c) {
		leController = c;
	}

	public double getTime() {
		return time;
	}

	@Override
	public void run() {
		try {
			enCours = true;
			Thread.sleep(500);
			while (enCours) {
				Thread.sleep(100);
				time += 0.1;
				System.out.println((double) Math.round(time * 10) / 10);
				leController.ecrireTemps((double) Math.round(time * 10) / 10);
				// leController.ecrireTemps((double) Math.round(time * 10) / 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void stop() {
		this.enCours = false;
	}

}
