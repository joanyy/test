package com.yuangying.cn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
 

 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 

public class LinkGrames implements ActionListener {
	//将面板中的每个按钮编号 执行消除时判断按钮编号和按钮的图片名是否相等
	//消除时根据两个按钮的位置关系执行删除
	JFrame jf=new JFrame("连连看");
	JPanel sortPane=new JPanel();
	int rows=4;
	int cols=4;
	JPanel imagePane=new JPanel(new GridLayout(rows,cols));
	JPanel buttonPanel=new JPanel();
	JLabel sortLable=new JLabel("0"); 
	JButton stopButton=new JButton("退出游戏");
	JButton resetButton=new JButton("重新排列"); 
	int firstMsg,secondMsg;
	//创建二维数组存放按钮
	 JButton buttonArray[][]=new JButton[rows][cols];
	//创建数组存放按钮的位置 
	int place[][]=new int[rows][cols];
	int x0;
	int y0;
	int x;
	int y;
	int k;
	//标记两次点击的按钮
	JButton  firstButton=buttonArray[x0][y0];
	JButton  secondButton=buttonArray[x][y];
	//存放图片
	List<ImageIcon> imageList=new ArrayList<ImageIcon>();
	//是否有鼠标点击事件
	Boolean pressInfo=false;
	//初始化
	public void init(){
		sortPane.add(sortLable);  
		initList();
		initButtonArray(); 
		resetButton.addActionListener(this);
		stopButton.addActionListener(this);
		buttonPanel.add(resetButton);
		buttonPanel.add(stopButton);
		jf.add(sortPane,BorderLayout.NORTH);
		jf.add(imagePane,BorderLayout.CENTER);
		jf.add(buttonPanel,BorderLayout.SOUTH); 
		jf.setSize(new Dimension(500, 600));
		jf.setVisible(true);
	}
	//将图片放到集合中
	public void initList(){
		for(int i=0;i<2;i++){
			imageList.add(new ImageIcon("草莓.jpg"));
			imageList.add(new ImageIcon("何炅.jpg"));
			imageList.add(new ImageIcon("西瓜.jpg"));
			imageList.add(new ImageIcon("小樱.jpg"));
			imageList.add(new ImageIcon("杨桃.jpg"));
			imageList.add(new ImageIcon("樱桃.jpg"));
			imageList.add(new ImageIcon("葡萄.jpg"));
			imageList.add(new ImageIcon("苹果.jpg"));
		} 
	}
	//初始化按钮数组
	public void initButtonArray(){
		int num=0;
		for(int row=0;row<4;row++){
			for(int col=0;col<4;col++){ 
				buttonArray[row][col]=new JButton((imageList.get(num)));
				buttonArray[row][col].addActionListener(this); 
				num++; 
			}
		} 
		 rebuild();
	}
	//向面板中添加按钮数组
	public void initImagePane(){
		int n=0;
		for(int row=0;row<4;row++){
			for(int col=0;col<4;col++){ 
				imagePane.add(buttonArray[row][col]);
				place[row][col]=n;
				n++; 
			}
		}
	} 
	//重建按钮
	public void rebuild(){ 
		//打乱数组100次
		for(int i=0;i<100;i++){
			int rows1=(int) (Math.random()*4);
			int cols1=(int) (Math.random()*4);
			int rows2=(int) (Math.random()*4); 
			int cols2=(int) (Math.random()*4);
			JButton bt1=buttonArray[rows1][cols1]; 
			JButton bt2=buttonArray[rows2][cols2]; 
			buttonArray[rows1][cols1]=bt2;
			buttonArray[rows2][cols2]=bt1;
			initImagePane();//将新的按钮数组放进面板中 
		} 
		pressInfo=false; 
	}
	//得到分数
	public void sort(){
		if(pressInfo){
			sortLable.setText(String.valueOf(Integer.parseInt(sortLable.getText())+100));
		} 
	}
	//删除
	public void move(){
		firstButton.setVisible(false);
		secondButton.setVisible(false);
		sort();
		pressInfo=false; 
	}
	//判断按钮1和按钮2图片和位置是否相同
	public void judgeMove(int placex,int placey,JButton jb){ 
		if(pressInfo==false){
			firstButton=buttonArray[x0][y0];
			x=placex;
			y=placey;
			secondButton=jb;
			secondMsg=place[x][y];  
			pressInfo=true; 
		}else{
			x0=x;
			y0=y;
			firstButton=secondButton;
			firstMsg=place[x0][y0];
			x=placex;
			y=placey;
			secondButton=jb; 
			secondMsg=place[x][y]; 
		} 
		if((firstButton.getIcon()).getIconHeight()==(secondButton.getIcon()).getIconHeight()&&firstMsg!=secondMsg ) { 
			moveLine(); 
 		} 
		pressInfo=true; 
	} 
	//连线删除
	public void moveLine(){
		//边框上的
		if((x0==0&&x==0)||(y0==0&&y==0)||(y==rows-1&&y0==rows-1)||(x0==cols-1&&x==cols-1)){
			move(); 
		}
		//相邻的
		if((x0==x+1&&y==y0)||(x0==x-1&&y==y0)||(y0==y+1&&x==x0)||(y0==y-1&&x0==x)){
			move(); 
		}
		//同一行不相邻的删除
		if(y==y0){ 
				int k=0;
				int k1=0;
				int x1=x-x0;
				int x2=x0-x;
				if(x1>0){
					for(int i=x0+1;i<x;i++){//同行空按钮个数
						if(buttonArray[y][i].isVisible()==false){
							k++;
						}
					}
					if(k==x1-1){
						move(); 
					}else{
						k=0;k1=0;
						//向下比较 
						for(int i=y+1;i<rows;i++){
							for(int j=x0;j<=x;j++){
								if(buttonArray[i][j].isVisible()==false){
									k++;
								}
							} 
							if(k==2*(i-y)+x1-1){
								move(); 
							}else{
								if(buttonArray[i][x0].isVisible()==false&&buttonArray[i][x].isVisible()==false){
									k1++;
								}
							} 
					} 
						if(k1==rows-y-1){
							move();
						}else{//向上比较
							k=0;k1=0;
							for(int i=y-1;i>=0;i--){
								for(int j=x0;j<=x;j++){
									if(buttonArray[i][j].isVisible()==false){
										k++;
									}
								} 
								if(k==2*(y-i)+x1-1){
									move();
									k=0;
								}else{
									if(buttonArray[i][x0].isVisible()==false&&buttonArray[i][x].isVisible()==false){
										k1++;
									}
								} 
						     }
							if(k1==y){
								move();
								k=0;k1=0;
							} 
						}  
					}
					}else{//x1<0即x2>0 x0>x 
						k=0;k1=0;
						for(int i=x+1;i<x0;i++){
							if(buttonArray[y][i].isVisible()==false){
								k++;
							}
						}
						if(k==x2-1){
							move();
							k=0;
						}else{
							//向下比较
							k=0;k1=0;
							for(int i=y+1;i<rows;i++){
								for(int j=x;j<=x0;j++){
									if(buttonArray[i][j].isVisible()==false){
										k++;
									}
								} 
								if(k==2*(i-y)+x2-1){
									move(); 
									k=0;
								}else{
									if(buttonArray[i][x0].isVisible()==false&&buttonArray[i][x].isVisible()==false){
										k1++;
									}
								}
							} 
							if(k1==rows-y-1){
								move();
								k1=0;
							}else{//向上比较
								k=0;k1=0;
								for(int i=y-1;i>=0;i--){
									for(int j=x;j<=x0;j++){
										if(buttonArray[i][j].isVisible()==false){
											k++;
										}
									} 
									if(k==2*(y-i)+x2-1){
										move();
										k=0;
									}else{
										if(buttonArray[i][x0].isVisible()==false&&buttonArray[i][x].isVisible()==false){
											k1++;
										}
									} 
							     }
								if(k1==y){
									move();
									k1=0;
									k=0;
								} 
							} 
						}
					}
				} 
		 if(x==x0){//同一列的删除  
				int k =0;
				int k1=0;
				int y1=y-y0;
				int y2=y0-y;
				if(y1>0){//y>y0  比较两者间的空按钮
					for(int i=y0+1;i<y;i++){
						if(buttonArray[i][x].isVisible()==false){
							k++;
						}
					}
					if(k==y1-1){
						move();
						k=0;
					}else{ 
						//向右比较
						k=0;k1=0;
						for(int i=x+1;i<cols;i++){
							for(int j=y0;j<=y;j++){
								if(buttonArray[j][i].isVisible()==false){
									k++;
								}
							}
							if(k==2*(i-x)+y1-1){
								move();
								k=0;
							}else{
								if(buttonArray[y0][i].isVisible()==false&&buttonArray[y][i].isVisible()==false){
									k1++;
								}
							}
						}
						if(k1==cols-x-1){
							move();
							k1=0;
							k=0;
						}else{
							//向左判断
							k=0;k1=0;
							for(int i=x-1;i>=0;i--){
								for(int j=y0;j<=y;j++){
									if(buttonArray[j][i].isVisible()==false){
										k++;
									}
								}
								if(k==2*(x-i)+y1-1){
									move();
									k=0;
								}else{
									if(buttonArray[y0][i].isVisible()==false&&buttonArray[y][i].isVisible()==false){
										k1++;
									}
								}
							}
							if(k1==x){
								move();
								k1=0;
							}
						}
					}
				}else{//y2>0  即y<y0 
					for(int i=y+1;i<y0;i++){
						if(buttonArray[i][x].isVisible()==false){
							k++;
						}
					}
					if(k==y2-1){
						move();
						k=0;
					}else{ 
						//向右比较
						k=0;k1=0;
						for(int i=x+1;i<cols;i++){
							for(int j=y;j<=y0;j++){
								if(buttonArray[j][i].isVisible()==false){
									k++;
								}
							}
							if(k==2*(i-x)+y2-1){
								move();
								k=0;
							}else{
								if(buttonArray[y0][i].isVisible()==false&&buttonArray[y][i].isVisible()==false){
									k1++;
								}
							}
						}
						if(k1==cols-x-1){
							move();
							k1=0;
						}else{
							//向左判断
							k=0;k1=0;
							for(int i=x-1;i>=0;i--){
								for(int j=y;j<=y0;j++){
									if(buttonArray[j][i].isVisible()==false){
										k++;
									}
								}
								if(k==2*(x-i)+y2-1){
									move();
									k=0;
								}else{
									if(buttonArray[y0][i].isVisible()==false&&buttonArray[y][i].isVisible()==false){
										k1++;
									}
								}
							}
							if(k1==x){
								move();
							}
						}
					} 
				} 
		}else{ //不同行不同列  分四种情况 左上角 右上角 左下角 右下角
			if(x0>x&&y0>y){
				judge(x,x0,y,y0);
			}
			else if(x0>x&&y0<y){
				judge(x,x0,y0,y);
			}else if(x0<x&&y0>y){
				judge(x0,x,y,y0);
			}else {
				judge(x0,x,y0,y);
			}  
		} 
	} 
	//不同行不同列时  位置关系 分四种情况 左上角 右上角 左下角 右下角
	public void judge(int x,int x0,int y,int y0){
		int k=0;
		int k1=0; 
		// x y在x0 y0的左上角
		if(x0>x&&y0>y){ 
			for(int i=x+1;i<=x0;i++){//向右 向下
				if(buttonArray[y][i].isVisible()==false){
					k++;
				}
			}
			for(int i=y+1;i<y0;i++){
				if(buttonArray[i][x0].isVisible()==false){
					k1++;
				}
			}
			if((k1+k)==(x0-x+y0-y-1)){
				move();
				k=0;
				k1=0;
			}else{
				k=0;
				k1=0;
				for(int i=y+1;i<=y0;i++){//向下向右
					if(buttonArray[i][x].isVisible()==false){
						k++;
					}
				}
				for(int i=x+1;i<x0;i++){
					if(buttonArray[y0][i].isVisible()==false){
						k1++;
					}
				}
				if((k+k1)==(x0-x+y0-y-1)){
					move(); 
				}else{//向右
					k=0;
					k1=0;
					for(int i=x+1;i<cols;i++){
						if(buttonArray[y][i].isVisible()==false){
							k++;
						}
					}
					for(int i=x0+1;i<cols;i++){
						 if(buttonArray[y0][i].isVisible()==false){
							k1++;
						} 
					}
					if((k+k1)==(cols-x-1+cols-x0-1)){
						move(); 
					}else{//向上
						k=0;
						k1=0;
						for(int i=y-1;i>=0;i--){
							if(buttonArray[i][x].isVisible()==false){
								k++;
							}
						}
						for(int i=y0-1;i>=0;i--){
							if(buttonArray[i][x].isVisible()==false){
								k1++;
							}
						}
						if(k==y&&k1==y0){
							move();
						}else{//向下
							k=0;
							k1=0;
							for(int i=y+1;i<rows;i++){
								if(buttonArray[i][x].isVisible()==false){
									k++;
								}
							}
							for(int i=y0+1;i<rows;i++){
								if(buttonArray[i][x0].isVisible()==false){
									k1++;
								}
							}
							if(k1==rows-y0-1&& k==rows-y-1){
								move();
								k=0;
								k1=0;
							}
						}
					}
				}
			} 
		}
	} 
	//添加事件
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==stopButton){
			 int i=JOptionPane.showConfirmDialog(null, "你确定要退出吗？","提示",JOptionPane.YES_NO_CANCEL_OPTION);
			 if(i==JOptionPane.OK_OPTION){
				 System.exit(0);
			 } 
		 } else if(e.getSource()==resetButton){
			 jf.setVisible(false);
			  rebuild();
			  jf.setVisible(true);
		 } 
		for(int row=0;row<rows;row++){
			for(int col=0;col<cols;col++){
				if(e.getSource()==buttonArray[row][col]){ 
					judgeMove(row, col, buttonArray[row][col]); 
				}
			}
		}  
	} 
	public static void main(String[] args) {
		LinkGrames te=new LinkGrames();
		 te.init(); 
	} 
}   
		 
