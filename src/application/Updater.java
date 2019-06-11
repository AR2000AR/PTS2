package application;

import java.util.Timer;
import java.util.TimerTask;

public class Updater extends Timer{
    public static int UPDATE_PERIODE = 1000; // en millisecondes
 
    public Updater() {
        super("Updater");
    }
 
    public void start() {
        schedule(new Updater (), 0, UPDATE_PERIODE);
    }
    private class UpdaterTask extends TimerTask {      
        public void run() {
            System.out.println("execution ici");
        }
    }
}
