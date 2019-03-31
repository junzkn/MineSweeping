package listener;

import game.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.ChessPanel;
import panel.EndDialog;
import utils.Button;
import values.StringValues;

public class ChessActionLiatener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Button bt = (Button)e.getSource() ;
		bt.setBackground(new Color(0,0,0));
		bt.setEnabled(false) ;
		Game.chess[bt.x][bt.y] = -1 ; 
		if (Game.HumenWin()){
			new EndDialog(StringValues.HUMENWIN) ;
			for (int i=0 ; i<3 ; i++)
				for ( int j=0 ; j<3 ; j++)
					ChessPanel.bt[i][j].setEnabled(false);
			return ;
		}
		else if(Game.blank()==0){
			for (int i=0 ; i<3 ; i++)
				for ( int j=0 ; j<3 ; j++)
					ChessPanel.bt[i][j].setEnabled(false);
			new EndDialog(StringValues.BREAKEVEN) ;
			return ;
		}
		Game.game(Game.deep,Game.MAX,-100,100);
		
	}

}
