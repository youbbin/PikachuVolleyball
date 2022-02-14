package play;

import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.*;

import frame.GameFrame;

//플레이어1(왼쪽) 스레드
public class Player1 extends JLabel {
	ImageIcon pikachuR_setSize;
	ImageIcon pikachuL_setSize;
	JPanel jp;
	int netXPos; // 네트의 x 좌표
	static int xSize;
	static int ySize;
	int field;
	int yPos;
	boolean fall; // 하강 중인지 표시
	boolean jump; // 점프 중인지 표시

	public Player1(JPanel jp, JLabel jlNet) {
		this.jp = jp;
		field = jp.getHeight() - GameFrame.groundHeight; //바닥의 y좌표
		netXPos = jlNet.getX(); // 네트의 x좌표
		ImageIcon pikachuR = new ImageIcon("pikachu_R.png");
		Image img = pikachuR.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuR_setSize = new ImageIcon(img);
		setIcon(pikachuR_setSize);
		xSize = pikachuR_setSize.getIconWidth();
		ySize = pikachuR_setSize.getIconHeight();
		setSize(xSize, ySize);
		ImageIcon pikachuL = new ImageIcon("pikachu_L.png");
		Image imgL = pikachuL.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuL_setSize = new ImageIcon(imgL);
	}

	public void setPikachu(int xPos, int yPos) { // 프레임 좌표 (x,y)에 피카츄 셋팅
		this.yPos = yPos;
		jp.add(this);
		setLocation(xPos, yPos);
		updateUI();
	}

	public void moveRight() {
		setIcon(pikachuR_setSize); // 오른쪽으로 움직이면 오른쪽 보는 피카츄로 셋팅
		if (getX() + getWidth() >= netXPos)
			setLocation(getX(), yPos);
		else
			setLocation(getX() + 20, yPos);
		updateUI();
	}

	public void moveLeft() {
		setIcon(pikachuL_setSize); // 왼쪽으로 움직이면 왼쪽 보는 피카츄로 셋팅
		if (getX() <= 0)
			setLocation(0, yPos);
		else
			setLocation(getX() - 20, yPos);
		updateUI();
	}

	public void jump() { // 점프
		Jump jump = new Jump();
	}

	class Jump { // 점프 클래스
		public Jump() {
			Thread up = new Thread() { // 상승 스레드
				public void run() {
					int y = getY(); //피카츄의 y좌표
					int foot = y + ySize; // 발바닥 위치는 피카츄의 y좌표+피카츄의 높이
					if (fall == false) { //떨어지는 중이 아닐 때 점프
						jump = true; // 점프중으로 변경
						long t1 = getTime(); // 현재시간을 가져옴
						long t2;
						int set = 8; // 점프 계수 설정
						int jumpY = 1; //상승량 (상승할 높이)
						while (jumpY > 0) { //상승량이 0일때까지 반복
							t2 = getTime() - t1; // 현재 시간에서 t1을 뺀다
							jumpY = set - (int) (t2 / 40); //상승량 셋팅
							y = y - jumpY; //y좌표 셋팅
							foot = y + ySize; // 발바닥 위치 저장
							setLocation(getX(), y); //위치 셋팅
							updateUI();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						jump = false;
					}
				}
			};
			up.start();

			Thread down = new Thread() { // 하강 스레드
				public void run() {
					while (true) {
						int y = getY();
						int foot = y + ySize;

						if (jump == false && foot < field && fall == false) {
							fall = true;
							long t1 = getTime(); // 현재시간을 가져옴
							long t2;
							int set = 1; // 낙하 계수 설정
							while (foot < field) { // 발이 땅에 닿기 전까지 반복
								t2 = getTime() - t1; // 현재 시간에서 t1을 뺀다
								int jumpY = set + (int) (t2 / 40); //하강량 셋팅
								y = y + jumpY; // y좌표에 낙하량을 더한다
								foot = y + ySize; // 발바닥 위치 저장
								setLocation(getX(), y);
								updateUI();
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							fall = false;
							break;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
			down.start();
		}

		public long getTime() {
			return Timestamp.valueOf(LocalDateTime.now()).getTime();
		}
	}
}