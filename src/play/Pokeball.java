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
		setLocation(Player1.xPos, 100); // ó������ �÷��̾�1(����) ���� ����
		jp.add(this);
		updateUI();
	}

	public void move() {
		int netXPos = GameFrame.netXPos;
		int netYPos = GameFrame.netYPos;
		int netXSize = GameFrame.netXSize;
		int netYSize = GameFrame.netYSize;

		while (true) {
			// �¿� �� �ݻ�
			if (xPos <= 0 || xPos + xSize >= xMax) {
				xDir *= -1;
			}
			// �� �� �ݻ�
			if (yPos <= 0 ) {
				yDir *= -1;
			}
			
			
			
			if(yPos + ySize >= yMax - GameFrame.groundHeight) {
				break;
			}

			// �÷��̾� �ݻ�
			xPos += (xDir * xSpeed); // x��ǥ ����
			yPos += (yDir * ySpeed); // y��ǥ ����
			setLocation(xPos, yPos); // ��ġ ����
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
