package play;

import java.awt.*;
import javax.swing.*;

import frame.GameFrame;

//�÷��̾�2(������) ������
public class Player2 extends JLabel implements Runnable {
	ImageIcon pikachuL_setSize; // ���� ���� ��ī��
	ImageIcon pikachuR_setSize; // ������ ���� ��ī��
	JPanel jp;
	int xSize;
	int ySize;
	static int xPos;
	static int yPos;

	public Player2(JPanel jp) {
		ImageIcon pikachuL = new ImageIcon("pikachu_L.png");
		Image imgL = pikachuL.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuL_setSize = new ImageIcon(imgL);
		setIcon(pikachuL_setSize);
		this.jp = jp;
		xSize = pikachuL_setSize.getIconWidth();
		ySize = pikachuL_setSize.getIconHeight();
		setSize(xSize, ySize);

		ImageIcon pikachuR = new ImageIcon("pikachu_R.png");
		Image imgR = pikachuR.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuR_setSize = new ImageIcon(imgR);
	}

	public void setPikachu(int xPos, int yPos) { // ������ ��ǥ (x,y)�� ��ī�� ����
		this.xPos = xPos;
		this.yPos = yPos;
		jp.add(this);
		setLocation(xPos, yPos);
		updateUI();
	}

	public void moveRight() {
		setIcon(pikachuR_setSize);
		if (getX() >= jp.getWidth() - 160)
			setLocation(jp.getWidth() - 160, yPos);
		else
			setLocation(getX() + 10, yPos);
		updateUI();
	}

	public void moveLeft() {
		setIcon(pikachuL_setSize);
		if (getX() <= GameFrame.netXPos + 100) 
			setLocation(GameFrame.netXPos + 100, yPos);
		else
			setLocation(getX() - 10, yPos);
		updateUI();
	}

	public void run() {

	}

}
