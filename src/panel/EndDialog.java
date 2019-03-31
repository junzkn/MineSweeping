package panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.Game;
import values.StringValues;

public class EndDialog extends JDialog {
	
	
	int result ;
	
	public EndDialog (int result){
		this.result = result ;
		this.setModal(true);
		this.setLayout(new GridLayout(2,1));
		this.setTitle("游戏结束");
		this.setLocationRelativeTo(null);
		this.setSize(200, 150);
		this.init();
		this.setVisible(true);
	}

	private void init() {
		JLabel tips= new JLabel() ;
		JButton bt = new JButton("从新开始") ;
		bt.setSize(new Dimension(20,20));
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EndDialog.this.dispose() ;
				Game.ReStart();
			}
		});
		bt.setSize(new Dimension(50,50));
		switch(result){
		case StringValues.AIWIN:
			tips.setText("你输了");
			break ; 
		case StringValues.HUMENWIN: 
			tips.setText("你赢了");
			break ; 
		case StringValues.BREAKEVEN:
			tips.setText("打成平手");
			break ; 
		default : 
			break ;
		}
		tips.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(tips);
		add(bt);
		
		
	}
	
}
