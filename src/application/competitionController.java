package application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class competitionController {

	private boolean running;
	
	@FXML
	private Label timer;
	
	
	
	@SuppressWarnings("deprecation")
	@FXML
	void initialize() throws InterruptedException {
		//runTimer();
		}
		
	
//	void runTimer() {
//		System.out.println("thread");
//		running =true;
//		new Thread() {
//			public void run() {
//				long last = System.nanoTime();
//				double delta=0;
//				double ns = 10000000.0 /2;
//				int count =0;
//				
//				while(running)
//				{
//					long now = System.nanoTime();
//					delta+= (now-last) / ns;
//					last=now;
//					
//					while(delta>=1) {
//						count = (count +1)%60;
//						System.out.println("pulse ...."+ count);
//						delta--;
//					}
//				}
//			}
//	}.start();
	//}	
		
//		Thread t = new Thread();
//		t.start();
//		for(int i=1; i<100; i++)
//		{
//			timer.setText(String.valueOf(i));
//			t.sleep(1000);
//		}
//		timer.setText("100+");
//		t.stop();
	}

