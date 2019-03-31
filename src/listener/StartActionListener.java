package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;
import panel.ChessPanel;
import utils.Button;
import values.StringValues;

public class StartActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Button bt = (Button)e.getSource() ;

		switch (bt.id){
			case StringValues.START :
				for (int i=0 ; i<3 ; i++){
					for ( int j=0 ; j<3 ; j++){
						ChessPanel.bt[i][j].setEnabled(true) ;
					}
				}
				bt.setEnabled(false);
				Game.start();
				break ; 
			case StringValues.RESTART :
				Game.ReStart();
				break ;
			default :
				break ;
		}
	}

}
