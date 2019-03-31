package listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import game.Game;
import utils.RadioButton;
import values.StringValues;

public class SelectItemLiatener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		RadioButton bt = (RadioButton) e.getSource() ;
		switch(bt.id){
		case StringValues.STANDRAD1 :
			Game.deep = StringValues.STANDRAD1 ;
			break ;
		case StringValues.STANDRAD2 :
			Game.deep = StringValues.STANDRAD2 ;
			break ;
		case StringValues.STANDRAD3 :
			Game.deep = StringValues.STANDRAD3 ;
			break ;
		case StringValues.STANDRAD4 :
			Game.deep = StringValues.STANDRAD4 ;
			break ;
		case StringValues.STANDRAD5:
			Game.deep = StringValues.STANDRAD5 ;
			break ;
		case StringValues.FIRST:
			Game.firstOrNot = true ;
			break ;
		case StringValues.LAST:
			Game.firstOrNot = false ;
			break ;
		default :
			break ;
		}		
	}

}
