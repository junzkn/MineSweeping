package panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listener.SelectItemLiatener;
import listener.StartActionListener;
import utils.Button;
import utils.RadioButton;
import values.StringValues;

public class MeumPanel extends JPanel {
	
	public MeumPanel () {
		this.setLayout(new GridLayout(1,3));
		SelectItemLiatener selectItemLiatener = new SelectItemLiatener() ;
		
		JPanel selectStandard = new JPanel() ;
		selectStandard.setLayout(new GridLayout(3,1));
		JLabel selectLabel = new JLabel("请选择难度",JLabel.CENTER) ;
		selectStandard.add(selectLabel);
		RadioButton standard1 = new RadioButton("难度1",StringValues.STANDRAD1);
		RadioButton standard2 = new RadioButton("难度2",StringValues.STANDRAD2);
		RadioButton standard3 = new RadioButton("难度3",StringValues.STANDRAD3);
		RadioButton standard4 = new RadioButton("难度4",StringValues.STANDRAD4);
		RadioButton standard5 = new RadioButton("难度5",true,StringValues.STANDRAD5);
		standard1.addItemListener(selectItemLiatener);
		standard2.addItemListener(selectItemLiatener);
		standard3.addItemListener(selectItemLiatener);
		standard4.addItemListener(selectItemLiatener);
		standard5.addItemListener(selectItemLiatener);
        ButtonGroup groupStandard = new ButtonGroup();
        groupStandard.add(standard1) ;
        groupStandard.add(standard2) ;
        groupStandard.add(standard3) ;
        groupStandard.add(standard4) ;
        groupStandard.add(standard5) ;
		selectStandard.add(standard1) ;
		selectStandard.add(standard2) ;
		selectStandard.add(standard3) ;
		selectStandard.add(standard4) ;
		selectStandard.add(standard5) ;
		this.add(selectStandard) ;
		
				
		JPanel selectOrder = new JPanel() ;
		selectOrder.setLayout(new GridLayout(3,1));
		JLabel orderLabel = new JLabel("请选择先手后手");
		selectOrder.add(orderLabel);
		RadioButton first = new RadioButton("先手",true,StringValues.FIRST);
		RadioButton last = new RadioButton("后手",StringValues.LAST);
		first.addItemListener(selectItemLiatener);
		last.addItemListener(selectItemLiatener);
        ButtonGroup groupOrder = new ButtonGroup();
        groupOrder.add(first) ;
        groupOrder.add(last) ;
        selectOrder.add(first) ;
        selectOrder.add(last) ;
        this.add(selectOrder) ;
		
				
		JPanel start = new JPanel() ;
		Button btStart = new Button(StringValues.START) ;
		btStart.setText("开始");
		btStart.setPreferredSize(new Dimension(100,20));
		btStart.addActionListener(new StartActionListener()) ;
		start.add(btStart);
		Button btRestart = new Button(StringValues.RESTART) ;
		btRestart.setText("从新开始") ;
		btRestart.setPreferredSize(new Dimension(100,20));
		btRestart.addActionListener(new StartActionListener()) ;
		start.add(btRestart);
		this.add(start) ;
		
		this.setPreferredSize(new Dimension(0,80));
	}
}
