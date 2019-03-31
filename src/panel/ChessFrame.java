package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listener.ChessActionLiatener;
import listener.SelectItemLiatener;
import listener.StartActionListener;
import values.StringValues;

public class ChessFrame extends JFrame{
	
	JPanel meun ;
	JPanel chess ; 
	
	public ChessFrame() {
	
		this.setTitle("井字棋");
		this.setLocation(300,00);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500,580) ;
		this.setResizable(false);
		this.init() ;
		this.setVisible(true) ;
	}

	private void init() {
		meun = new MeumPanel();
		chess = new ChessPanel() ; 
		this.add(meun,BorderLayout.SOUTH);
		this.add(chess,BorderLayout.CENTER) ;					
				
	}
	
	public void ReSet(){
		this.remove(meun);
		this.remove(chess);
		init() ;
		this.setVisible(true) ;
	}
	
	
}
