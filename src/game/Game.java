package game;

import java.awt.Color;
import panel.ChessFrame;
import panel.ChessPanel;
import panel.EndDialog;
import values.StringValues;

public class Game {
	
	static ChessFrame chessFrame ;
	public static final int MAX=0 ; 
	public static final int MIN=1 ;
	public static int deep = 9 ;
	public static boolean firstOrNot = true ;
	public static int[][] chess = new int[3][3];
	
	
	private static void init(){
		deep = 9 ;
		firstOrNot = true ;
		for (int i=0 ; i<3 ; i++)
			for ( int j=0 ; j<3 ; j++)
				chess[i][j] = 0 ; 
	}
	
	//AlphaBeta剪枝法
	public static int game(int l,int x,int a,int b) {  //l：深度。x：MAX或MIN。a、b：Alpha和Beta
		//如果递归深度超过剩余空格数，变为剩余空格数
		if ( l>blank() ){
			deep = blank() ;
			l = blank() ;
		}
		//如果递归为最下一层，返回预判输赢数值
		if ( l==0 ){
			return whoWIn();
		}
		//记录需要改变的位置
		int changeX = 0 ; 
		int changeY = 0 ;
		
		//for循环每一步下棋
		for(int i=0 ; i<3 ; i++){
			for ( int j=0 ; j<3 ; j++){
				if (chess[i][j]==1 || chess[i][j]==-1){
					continue ; //如果有棋子则跳过
				}
				else {
					//MAX代表AI先走
					if (x==MAX){
						//AI走子
						chess[i][j] = 1 ;
						//如果这步赢了,返回100
						if (AIWin()){
							changeX=i ;changeY=j ;
							chess[i][j] = 0 ;a=100 ;
							if (l==deep){
								play(changeX,changeY);
							}
							return a ;
						}
						//递归下子
						else {
							int get = game(l-1,MIN,a,b) ;
							//如果大于b，Beta剪枝，剩余的子不用下了
							if (get>b){
								changeX=i ;	changeY=j ;
								chess[i][j]=0;
								if (l==deep){
									play(changeX,changeY);
								}
								return get ;
							}
							if(get>a){
								a=get ;
								changeX=i ;changeY=j ;
							}
						}
						chess[i][j]=0;
					}
					
					else if (x==MIN){
						chess[i][j] = -1 ;
						if (HumenWin()){
							changeX=i ; changeY=j ;
							chess[i][j] = 0 ; b=-100 ;			
							if (l==deep){
								play(changeX,changeY);
							}
							return b ;
						}
						else {
							int get = game(l-1,MAX,a,b) ;
							//如果小于a，Alpha剪枝，剩余不用下了
							if (get<a){
								changeX=i ;changeY=j ;		
								chess[i][j]=0;
								if (l==deep){
									play(changeX,changeY);
								}
								return get ;
							}
							if(get<b){
								b=get ;
								changeX=i ;	changeY=j ;
							}
						}
						chess[i][j]=0;
					}			
				}
			}
		}		
		if (l==deep){
			play(changeX,changeY);
		}
		if(x==MAX){
			return a ;
		}
		else {
			return b ;
		}		
	}

	//最大最小值法
	public static int game(int l,int x) {
		//如果递归深度超过剩余空格数，变为剩余空格数
		if ( l>blank() ){
			deep = blank() ;
			l = blank() ;
		}
		//如果递归为最下一层，返回预判输赢数值
		if ( l==0 ){
			return whoWIn();
		}
		int result ;  //预判输赢数值
		if (x==MAX){
			result = -100 ; 
		}
		else {
			result = 100 ;
		}
		//记录需要改变的位置
		int changeX = 0; 
		int changeY = 0 ;
		
		//for循环每一步下棋
		for(int i=0 ; i<3 ; i++){
			for ( int j=0 ; j<3 ; j++){
				if (chess[i][j]==1 || chess[i][j]==-1){
					continue ; //如果有棋子则跳过
				}
				else {
					
					if (x==MAX){
						chess[i][j] = 1 ;
						if (AIWin()){
							changeX=i ;
							changeY=j ;
							chess[i][j] = 0 ;
							result=100 ;
							if (l==deep){
								play(changeX,changeY);
							}
							return result ;
						}
						else {
							int get = game(l-1,MIN) ;
							if(get>result){
								result=get ;
								changeX=i ;
								changeY=j ;
							}
							chess[i][j] = 0 ;
						}
					}
					
					else if (x==MIN){
						chess[i][j] = -1 ;
						if (HumenWin()){
							changeX=i ;
							changeY=j ;
							chess[i][j] = 0 ;
							result=-100 ;
							if (l==deep){
								play(changeX,changeY);
							}
							return result ;
						}
						else {
							int get = game(l-1,MAX) ;
							if(get<result){
								result=get ;
								changeX=i ;
								changeY=j ;
							}
							chess[i][j] = 0 ;
						}
					}
				}
			}
		}		
		if (l==deep){
			play(changeX,changeY);
		}
		return result ;
}
	
	public static int blank() {
		int blank=0 ;
		for ( int i=0 ; i<3 ; i++){
			for ( int j=0 ; j<3 ; j++){
				if (chess[i][j]==0)
					blank++ ;
			}
		}
		return blank ;
	}

	private static void play(int changeX, int changeY) {
		if (chess[changeX][changeY]!=0){
			for(int i=0 ; i<3 ; i++)
				for( int j=0 ; j<3 ; j++)
					if (chess[i][j]==0){
						changeX=i ;
						changeY=j ;
						break ;
					}
		}
		chess[changeX][changeY] = 1 ;
		ChessPanel.bt[changeX][changeY].setBackground(new Color(220,20,60));
		ChessPanel.bt[changeX][changeY].setEnabled(false);
		if (AIWin()){
			for (int i=0 ; i<3 ; i++)
				for ( int j=0 ; j<3 ; j++)
					ChessPanel.bt[i][j].setEnabled(false);
			new EndDialog(StringValues.AIWIN) ;
		}
		else if (HumenWin()){
			for (int i=0 ; i<3 ; i++)
				for ( int j=0 ; j<3 ; j++)
					ChessPanel.bt[i][j].setEnabled(false);
			new EndDialog(StringValues.HUMENWIN) ;
		}
		else if(blank()==0){
			for (int i=0 ; i<3 ; i++)
				for ( int j=0 ; j<3 ; j++)
					ChessPanel.bt[i][j].setEnabled(false);
			new EndDialog(StringValues.BREAKEVEN) ;
		}
	}

	private static int whoWIn() {
		int AIWin = 0 ;
		int HuWin = 0 ;
		
		if (AIWin()){
			return 100 ;
		}
		else if (HumenWin()){
			return -100 ;
		}
		
		if (chess[0][0]>=0 && chess[0][1]>=0 && chess[0][2]>=0) AIWin++ ;
		if (chess[1][0]>=0 && chess[1][1]>=0 && chess[1][2]>=0) AIWin++ ;
		if (chess[2][0]>=0 && chess[2][1]>=0 && chess[2][2]>=0) AIWin++ ;
		if (chess[0][0]>=0 && chess[1][0]>=0 && chess[2][0]>=0) AIWin++ ;
		if (chess[0][1]>=0 && chess[1][1]>=0 && chess[2][1]>=0) AIWin++ ;
		if (chess[0][2]>=0 && chess[1][2]>=0 && chess[2][2]>=0) AIWin++ ;
		if (chess[0][0]>=0 && chess[1][1]>=0 && chess[2][2]>=0) AIWin++ ;
		if (chess[0][2]>=0 && chess[1][1]>=0 && chess[2][0]>=0) AIWin++ ;
		
		if (chess[0][0]<=0 && chess[0][1]<=0 && chess[0][2]<=0) HuWin++ ;
		if (chess[1][0]<=0 && chess[1][1]<=0 && chess[1][2]<=0) HuWin++ ;
		if (chess[2][0]<=0 && chess[2][1]<=0 && chess[2][2]<=0) HuWin++ ;
		if (chess[0][0]<=0 && chess[1][0]<=0 && chess[2][0]<=0) HuWin++ ;
		if (chess[0][1]<=0 && chess[1][1]<=0 && chess[2][1]<=0) HuWin++ ;
		if (chess[0][2]<=0 && chess[1][2]<=0 && chess[2][2]<=0) HuWin++ ;
		if (chess[0][0]<=0 && chess[1][1]<=0 && chess[2][2]<=0) HuWin++ ;
		if (chess[0][2]<=0 && chess[1][1]<=0 && chess[2][0]<=0) HuWin++ ;
		
		return AIWin-HuWin;
	}

	public static boolean AIWin() {
		boolean win = false ;
		if (chess[0][0]==1 && chess[0][1]==1 && chess[0][2]==1) win = true ;
		if (chess[1][0]==1 && chess[1][1]==1 && chess[1][2]==1) win = true ;
		if (chess[2][0]==1 && chess[2][1]==1 && chess[2][2]==1) win = true ;
		if (chess[0][0]==1 && chess[1][0]==1 && chess[2][0]==1) win = true ;
		if (chess[0][1]==1 && chess[1][1]==1 && chess[2][1]==1) win = true ;
		if (chess[0][2]==1 && chess[1][2]==1 && chess[2][2]==1) win = true ;
		if (chess[0][0]==1 && chess[1][1]==1 && chess[2][2]==1) win = true ;
		if (chess[0][2]==1 && chess[1][1]==1 && chess[2][0]==1) win = true ;
		return win;
	}

	public static boolean HumenWin() {
		boolean win = false ;
		if (chess[0][0]==-1 && chess[0][1]==-1 && chess[0][2]==-1) win = true ;
		if (chess[1][0]==-1 && chess[1][1]==-1 && chess[1][2]==-1) win = true ;
		if (chess[2][0]==-1 && chess[2][1]==-1 && chess[2][2]==-1) win = true ;
		if (chess[0][0]==-1 && chess[1][0]==-1 && chess[2][0]==-1) win = true ;
		if (chess[0][1]==-1 && chess[1][1]==-1 && chess[2][1]==-1) win = true ;
		if (chess[0][2]==-1 && chess[1][2]==-1 && chess[2][2]==-1) win = true ;
		if (chess[0][0]==-1 && chess[1][1]==-1 && chess[2][2]==-1) win = true ;
		if (chess[0][2]==-1 && chess[1][1]==-1 && chess[2][0]==-1) win = true ;
		return win;
	}

	public static void start() {
		if (firstOrNot!=true){
			game(deep,MAX,-100,100);
		}
	}
		
	public static void ReStart() {
		init() ;
		chessFrame.ReSet();
	}
	
				
	public static void main ( String[] args) {
		init() ;
		chessFrame = new ChessFrame() ;	
	}

}
