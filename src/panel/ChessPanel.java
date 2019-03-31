package panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import listener.ChessActionLiatener;
import utils.Button;

public class ChessPanel extends JPanel {

	public static Button[][] bt = new Button[3][3];

	public ChessPanel(){
		this.setLayout(new GridLayout(3,3)) ;
		ChessActionLiatener chessActionListener = new ChessActionLiatener() ;
		for (int i=0 ; i<3 ; i++){
			for ( int j=0 ; j<3 ; j++){
				bt[i][j] = new Button(i,j);
				bt[i][j].setEnabled(false) ;
				bt[i][j].addActionListener(chessActionListener);
				this.add(bt[i][j]) ;
			}
		}
		this.setPreferredSize(new Dimension(500,500));
	}
}
