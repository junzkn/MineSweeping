package utils;

import javax.swing.JRadioButton;

public class RadioButton  extends JRadioButton {
	
	public int id ;
	
	
	public RadioButton(String str , boolean b , int id ){
		super(str,b) ;
		this.id = id ; 
	}
	
	public RadioButton(String str , int id ){
		super(str) ;
		this.id = id ;
	}

	
}
