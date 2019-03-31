package utils;

import javax.swing.JButton;

public class Button extends JButton{
	public int x ; 
	public int y ;
	public int id ; 
	
	public Button(int x , int y) {
		this.x = x ; 
		this.y = y ; 
		
	}

	public Button(int id) {
		this.id = id ;
	}
	
}
