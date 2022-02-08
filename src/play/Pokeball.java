package play;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

import frame.GameFrame;

public class Pokeball extends JLabel implements Runnable {
	JPanel jp;
	int xSize;
	int ySize;
	int xPos;
	int yPos;
	int xDir;
	int yDir;
	int xMax;
	int yMax;
	int xSpeed;
	int ySpeed;
	Random r = new Random();

	public Pokeball(JPanel jp) {
		this.jp = jp;
		ImageIcon iiBall = new ImageIcon("pokeball.png");
		Image imgBall = iiBall.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon iiBall_setSize = new ImageIcon(imgBall);
		setIcon(iiBall_setSize);

		xMax = jp.getWidth();
		yMax = jp.getHeight();
		xSize = iiBall_setSize.getIconWidth();
		ySize = iiBall_setSize.getIconHeight();
		setSize(xSize, ySize);
		xPos = Player1.xPos;
		yPos = 100;
		xDir = 1;
		yDir = 1;
		xSpeed = 5;
		ySpeed = 5;

		setVisible(true);
	}

	public void setPokeball() {
		setLocation(Player1.xPos, 100); // 처음에는 플레이어1(왼쪽) 위에 셋팅
		jp.add(this);
		updateUI();
	}

	public void move() {
		int netXPos = GameFrame.netXPos;
		int netYPos = GameFrame.netYPos;
		int netXSize = GameFrame.netXSize;
		int netYSize = GameFrame.netYSize;

		while (true) {
			// 좌우 벽 반사
			if (xPos <= 0 || xPos + xSize >= xMax) {
				xDir *= -1;
			}
			// 위 벽 반사
			if (yPos <= 0 ) {
				yDir *= -1;
			}
			
			
			
			if(yPos + ySize >= yMax - GameFrame.groundHeight) {
				break;
			}

			// 플레이어 반사
			xPos += (xDir * xSpeed); // x좌표 설정
			yPos += (yDir * ySpeed); // y좌표 설정
			setLocation(xPos, yPos); // 위치 셋팅
			updateUI();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run() {
		move();
	}
}
