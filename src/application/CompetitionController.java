package application;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CompetitionController extends PlateauController {

	@FXML
	private Label lblTime;
	static Timer monTimer;
	@Override
	public void initialize() {
		try {

			gc1 = canvas1.getGraphicsContext2D();
			Platform.runLater(() -> {
				try {
					initialiserPlateau(gc1, 2);
				} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initPiece();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		monTimer = new Timer(this);
		Thread t = new Thread(monTimer);
		t.start();

	}

	public void ecrireTemps(double d) {
		Platform.runLater(() -> {
			lblTime.setText(Double.toString(d));
			});
	}
		
	
		
		@Override
		public void testJeuFini() {
			boolean test = false;
			int k = 0;
			for(int i=0;i<3;i++) {
				if(estPlacer[i]) {
					k++;
				}
				if(k==3){
					test = true;
					monTimer.stop();
					System.out.println("<#<|JEU FINI|>#>");
					
					
					imgBravo.setImage(bravoImg);
					imgBravo.setVisible(true);
					imgBravo.setOpacity(0);
					imgBravo.toFront();
					double x=0;
					while(imgBravo.getOpacity()<1) {
						x+=0.005;
						imgBravo.setOpacity(x);
					}
				}
			}
		}
		
		

}
