package play;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.GameFrame;

public class Play extends Thread {
	Player1 p1;
	Player2 p2;
	Pokeball ball;
	public void Play(Player1 p1, Player2 p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void run() {
		new Thread((Runnable) p1).start(); // Player1 ������ ����
		new Thread((Runnable) p2).start(); // Player2 ������ ����
		new Thread((Runnable) ball).start(); // Pokeball ������ ����
	}
}

class gameStart extends JLabel implements Runnable { // ���� ���� ���̺��� ������ �߰����� ������ 1�ʰ� ���� �� ������ ���۵�
	JPanel jp;

	public gameStart(JPanel jp) {
		this.jp = jp;
		ImageIcon iiStartLabel = new ImageIcon("start_label.png");
		Image imgStartLabel = iiStartLabel.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
		ImageIcon iiStartLabel_setSize = new ImageIcon(imgStartLabel);
		setIcon(iiStartLabel_setSize);
		setSize(500, 150);
		setLocation(jp.getWidth() / 4, 0);
	}

	public void run() {
		while (true) {
			try {
				if (getY() >= jp.getHeight() * (0.4)) {
					Thread.sleep(1000);
					setVisible(false);
					GameFrame.gameStart = true; // ���� ����
					Play play = new Play();
					play.start();
					break;
				}
				setLocation(jp.getWidth() / 4, getY() + 1);
				updateUI();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
