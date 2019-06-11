package application;

public class Timer implements Runnable {

	double time=0;
	boolean enCours = false;
	PlateauController leController;
	
	public Timer(PlateauController c ) {
		leController =c;
	}
	
	@Override
	public void run() {
		try {
			enCours = true;
			while(enCours) {
			Thread.sleep(100);
			time+=0.1;
			System.out.println((double)Math.round(time*10) /10);
			
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public double getTime() {
		return time;
	}
	
}
