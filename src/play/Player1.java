package play;

import java.awt.*;
import javax.swing.*;

import frame.GameFrame;

//ÇÃ·¹ÀÌ¾î1(¿ÞÂÊ) ½º·¹µå
public class Player1 extends JLabel implements Runnable {
	ImageIcon pikachuR_setSize;
	ImageIcon pikachuL_setSize;
	JPanel jp;
	int xSize;
	int ySize;
	static int xPos; // x ÁÂÇ¥
	static int yPos; // y ÁÂÇ¥

	public Player1(JPanel jp) {
		ImageIcon pikachuR = new ImageIcon("pikachu_R.png");
		Image img = pikachuR.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuR_setSize = new ImageIcon(img);
		setIcon(pikachuR_setSize);
		this.jp = jp;
		xSize = pikachuR_setSize.getIconWidth();
		ySize = pikachuR_setSize.getIconHeight();
		setSize(xSize, ySize);

		ImageIcon pikachuL = new ImageIcon("pikachu_L.png");
		Image imgL = pikachuL.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		pikachuL_setSize = new ImageIcon(imgL);
	}

	public void setPikachu(int xPos, int yPos) { // ÇÁ·¹ÀÓ ÁÂÇ¥ (x,y)¿¡ ÇÇÄ«Ãò ¼ÂÆÃ
		this.xPos = xPos;
		this.yPos = yPos;
		jp.add(this);
		setLocation(xPos, yPos);
		updateUI();
	}

	public void moveRight() {
		setIcon(pikachuR_setSize);
		if (getX() >= GameFrame.netXPos - 100)
			setLocation(GameFrame.netXPos - 100, yPos);
		else
			setLocation(getX() + 10, yPos);
		updateUI();
	}

	public void moveLeft() {
		setIcon(pikachuL_setSize);
		if (getX() <= 0)
			setLocation(0, yPos);
		else
			setLocation(getX() - 10, yPos);
		updateUI();
	}

	public void run() {
		
	}

}
