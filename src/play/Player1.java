package play;

import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.*;

import frame.GameFrame;

//�÷��̾�1(����) ������
public class Player1 extends JLabel {
	ImageIcon pikachuR_setSize;
	ImageIcon pikachuL_setSize;
	JPanel jp;
	int netXPos; // ��Ʈ�� x ��ǥ
	static int xSize;
	static int ySize;
	int field;
	int yPos;
	boolean fall; // �ϰ� ������ ǥ��
	boolean jump; // ���� ������ ǥ��

	public Player1(JPanel jp, JLabel jlNet) {
		this.jp = jp;
		field = jp.getHeight() - GameFrame.groundHeight; //�ٴ��� y��ǥ
		netXPos = jlNet.getX(); // ��Ʈ�� x��ǥ
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

	public void setPikachu(int xPos, int yPos) { // ������ ��ǥ (x,y)�� ��ī�� ����
		this.yPos = yPos;
		jp.add(this);
		setLocation(xPos, yPos);
		updateUI();
	}

	public void moveRight() {
		setIcon(pikachuR_setSize); // ���������� �����̸� ������ ���� ��ī��� ����
		if (getX() + getWidth() >= netXPos)
			setLocation(getX(), yPos);
		else
			setLocation(getX() + 20, yPos);
		updateUI();
	}

	public void moveLeft() {
		setIcon(pikachuL_setSize); // �������� �����̸� ���� ���� ��ī��� ����
		if (getX() <= 0)
			setLocation(0, yPos);
		else
			setLocation(getX() - 20, yPos);
		updateUI();
	}

	public void jump() { // ����
		Jump jump = new Jump();
	}

	class Jump { // ���� Ŭ����
		public Jump() {
			Thread up = new Thread() { // ��� ������
				public void run() {
					int y = getY(); //��ī���� y��ǥ
					int foot = y + ySize; // �߹ٴ� ��ġ�� ��ī���� y��ǥ+��ī���� ����
					if (fall == false) { //�������� ���� �ƴ� �� ����
						jump = true; // ���������� ����
						long t1 = getTime(); // ����ð��� ������
						long t2;
						int set = 8; // ���� ��� ����
						int jumpY = 1; //��·� (����� ����)
						while (jumpY > 0) { //��·��� 0�϶����� �ݺ�
							t2 = getTime() - t1; // ���� �ð����� t1�� ����
							jumpY = set - (int) (t2 / 40); //��·� ����
							y = y - jumpY; //y��ǥ ����
							foot = y + ySize; // �߹ٴ� ��ġ ����
							setLocation(getX(), y); //��ġ ����
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

			Thread down = new Thread() { // �ϰ� ������
				public void run() {
					while (true) {
						int y = getY();
						int foot = y + ySize;

						if (jump == false && foot < field && fall == false) {
							fall = true;
							long t1 = getTime(); // ����ð��� ������
							long t2;
							int set = 1; // ���� ��� ����
							while (foot < field) { // ���� ���� ��� ������ �ݺ�
								t2 = getTime() - t1; // ���� �ð����� t1�� ����
								int jumpY = set + (int) (t2 / 40); //�ϰ��� ����
								y = y + jumpY; // y��ǥ�� ���Ϸ��� ���Ѵ�
								foot = y + ySize; // �߹ٴ� ��ġ ����
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