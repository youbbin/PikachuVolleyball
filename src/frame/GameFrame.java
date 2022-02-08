package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import play.Play;
import play.Player1;
import play.Player2;
import play.Pokeball;

public class GameFrame extends JFrame implements KeyListener {
	Container ct;
	JLabel jlScore_p1;
	JLabel jlScore_p2;
	public static int groundHeight;
	Player1 p1;
	Player2 p2;
	JPanel jp;
	public static boolean gameStart = false;
	public static int netXSize;
	public static int netYSize;
	public static int netXPos;
	public static int netYPos;

	public GameFrame() {
		ct = getContentPane();
	}
	public void createGameFrame() {
		setLayout(null);
		jp = new JPanel(); // ��� ������Ʈ���� �߰��� JPanel
		jp.setSize(1000, 800);
		jp.setLayout(null);
		jp.setBackground(Color.cyan);

		jlScore_p1 = new JLabel("0", JLabel.CENTER); // �÷��̾�1�� ���� ���̺�
		jlScore_p1.setFont(new Font("Papyrus", Font.BOLD, 70));
		jlScore_p1.setForeground(Color.red);
		jlScore_p1.setSize(100, 100);
		jlScore_p1.setLocation(50, 0);

		jlScore_p2 = new JLabel("0", JLabel.CENTER); // �÷��̾�2�� ���� ���̺�
		jlScore_p2.setFont(new Font("Papyrus", Font.BOLD, 70));
		jlScore_p2.setForeground(Color.red);
		jlScore_p2.setSize(100, 100);
		jlScore_p2.setLocation(jp.getWidth() - 150, 0);

		createGround(); // �ٴ� ����
		createPlayer(); // �÷��̾� ����
		createNet(); // ��Ʈ ����
		//createBall(); // ���Ϻ� ����

		gameStart gs = new gameStart(jp); // ���� ���̺� ������ ����
		jp.add(gs);
		new Thread((Runnable) gs).start(); // ���� ���̺� ������ ����
		jp.add(jlScore_p1);
		jp.add(jlScore_p2);

		ct.add(jp); //�����̳ʿ� jp �߰�
		setFocusable(true);
		addKeyListener(this); //Ű������ �߰�
		setTitle("Pikachu Volleyball");
		setSize(1000, 810);
		setResizable(false); //â ������ ���� ���ϰ� ����
		setLocationRelativeTo(null); //â�� ȭ���� ����� ���
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createGround() { // �ٴ� ����
		ImageIcon iiGround = new ImageIcon("ground.png");
		Image imgGround = iiGround.getImage().getScaledInstance(jp.getWidth(), 50, Image.SCALE_SMOOTH);
		ImageIcon iiGround_setSize = new ImageIcon(imgGround);
		JLabel jlGround = new JLabel();
		jlGround.setIcon(iiGround_setSize);
		jlGround.setSize(iiGround_setSize.getIconWidth(), iiGround_setSize.getIconHeight());
		jlGround.setLocation(0, jp.getHeight() - jlGround.getHeight()-10);
		groundHeight = jlGround.getHeight();
		jp.add(jlGround);
	}

	public void createPlayer() { // �÷��̾� ����
		p1 = new Player1(jp);
		p1.setPikachu(50, jp.getHeight() - p1.getHeight() - groundHeight);
		p2 = new Player2(jp);
		p2.setPikachu(jp.getWidth() - 200, jp.getHeight() - p2.getHeight() - groundHeight);
	}

	public void createNet() { // ��Ʈ ����
		ImageIcon iiNet = new ImageIcon("net.png");
		Image imgNet = iiNet.getImage().getScaledInstance(50, 300, Image.SCALE_SMOOTH);
		ImageIcon iiNet_setSize = new ImageIcon(imgNet);
		JLabel jlNet = new JLabel();
		jlNet.setIcon(iiNet_setSize);
		jlNet.setSize(iiNet_setSize.getIconWidth(), iiNet_setSize.getIconHeight());
		jlNet.setLocation(450, jp.getHeight() - jlNet.getHeight() - groundHeight);
		jp.add(jlNet);
		netXPos=jlNet.getX()-jlNet.getWidth();
		netYPos=jlNet.getY();
		netXSize=jlNet.getWidth();
		netYSize=jlNet.getHeight();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (gameStart) {
			// �÷��̾�1�� D,G Ű�� �¿� �̵�
			if (e.getKeyCode() == KeyEvent.VK_D)
				p1.moveLeft();
			if (e.getKeyCode() == KeyEvent.VK_G)
				p1.moveRight();
			
			// �÷��̾�2�� �¿� ����Ű�� �¿� �̵�
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				p2.moveLeft();
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				p2.moveRight();
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
}

class gameStart extends JLabel implements Runnable { //���� ���� ���̺��� ������ �߰����� ������ 1�ʰ� ���� �� ������ ���۵�
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
					Pokeball ball=new Pokeball(jp);
					jp.add(ball);
					new Thread((Runnable) ball).start();
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
