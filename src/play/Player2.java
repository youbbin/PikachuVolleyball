package play;

import java.awt.*;
import javax.swing.*;

import frame.GameFrame;

//플레이어2(오른쪽) 스레드
public class Player2 extends JLabel implements Runnable {
	ImageIcon pikachuL_setSize; // 왼쪽 보는 피카츄
	ImageIcon pikachuR_setSize; // 오른쪽 보는 피카츄
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

	public void setPikachu(int xPos, int yPos) { // 프레임 좌표 (x,y)에 피카츄 셋팅
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
