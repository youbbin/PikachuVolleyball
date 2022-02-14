package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import play.GameStartLabel;
import play.Player1;
import play.Player2;
import play.Pokeball;

public class GameFrame extends JFrame implements KeyListener {
	Container ct;
	public JLabel jlScore_p1;
	public JLabel jlScore_p2;
	public Player1 p1;
	public Player2 p2;
	public JPanel jp;
	public static boolean gameStart = false;
	public static int groundHeight;
	public JLabel jlNet;

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
		jlScore_p1.setFont(new Font("Cooper Black", Font.BOLD, 70));
		jlScore_p1.setForeground(Color.red);
		jlScore_p1.setSize(100, 100);
		jlScore_p1.setLocation(50, 0);
		jp.add(jlScore_p1);
		jlScore_p2 = new JLabel("0", JLabel.CENTER); // �÷��̾�2�� ���� ���̺�
		jlScore_p2.setFont(new Font("Cooper Black", Font.BOLD, 70));
		jlScore_p2.setForeground(Color.red);
		jlScore_p2.setSize(100, 100);
		jlScore_p2.setLocation(jp.getWidth() - 150, 0);
		jp.add(jlScore_p2);

		createGround(); // �ٴ� ����
		createNet(); // ��Ʈ ����
		createPlayer(); // �÷��̾� ����
		addNick(); // �г��� �߰�

		GameStartLabel gs = new GameStartLabel(this); // ���� ���̺� ������ ����
		jp.add(gs);
		new Thread((Runnable) gs).start(); // ���� ���̺� ������ ����

		ct.add(jp); // �����̳ʿ� jp �߰�
		setFocusable(true);
		addKeyListener(this); // Ű������ �߰�
		setTitle("Pikachu Volleyball");
		setSize(1000, 810);
		setResizable(false); // â ������ ���� ���ϰ� ����
		setLocationRelativeTo(null); // â�� ȭ���� ����� ���
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â ������ ���α׷� ����
	}

	public void createGround() { // �ٴ� ����
		ImageIcon iiGround = new ImageIcon("ground.png");
		Image imgGround = iiGround.getImage().getScaledInstance(jp.getWidth(), 50, Image.SCALE_SMOOTH);
		ImageIcon iiGround_setSize = new ImageIcon(imgGround);
		JLabel jlGround = new JLabel();
		jlGround.setIcon(iiGround_setSize);
		jlGround.setSize(iiGround_setSize.getIconWidth(), iiGround_setSize.getIconHeight());
		jlGround.setLocation(0, jp.getHeight() - jlGround.getHeight() - 10);
		groundHeight = jlGround.getHeight();
		jp.add(jlGround);
	}

	public void createPlayer() { // �÷��̾� ����
		p1 = new Player1(jp, jlNet);
		p1.setPikachu(50, jp.getHeight() - p1.getHeight() - groundHeight);
		p2 = new Player2(jp, jlNet);
		p2.setPikachu(jp.getWidth() - 200, jp.getHeight() - p2.getHeight() - groundHeight);
	}

	public void createNet() { // ��Ʈ ����
		ImageIcon iiNet = new ImageIcon("net.png");
		Image imgNet = iiNet.getImage().getScaledInstance(50, 300, Image.SCALE_SMOOTH);
		ImageIcon iiNet_setSize = new ImageIcon(imgNet);
		jlNet = new JLabel();
		jlNet.setIcon(iiNet_setSize);
		jlNet.setSize(iiNet_setSize.getIconWidth(), iiNet_setSize.getIconHeight());
		jlNet.setLocation(450, jp.getHeight() - jlNet.getHeight() - groundHeight);
		jp.add(jlNet);
	}

	public void addNick() { // �г��� �߰�
		JLabel jlNick1 = new JLabel(IntroFrame.nick1, JLabel.CENTER);
		jlNick1.setForeground(Color.red);
		jlNick1.setSize(200, 100);
		jlNick1.setLocation(0, 60);
		jlNick1.setFont(new Font("�޸վƹ�ü",Font.BOLD,50));
		JLabel jlNick2 = new JLabel(IntroFrame.nick2, JLabel.CENTER);
		jlNick2.setForeground(Color.red);
		jlNick2.setSize(200, 100);
		jlNick2.setLocation(jp.getWidth() - 200, 60);
		jlNick2.setFont(new Font("�޸վƹ�ü",Font.BOLD,50));
		jp.add(jlNick1);
		jp.add(jlNick2);
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
			if (e.getKeyCode() == KeyEvent.VK_R) {
				p1.jump();
			}
			// �÷��̾�2�� �¿� ����Ű�� �¿� �̵�
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				p2.moveLeft();
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				p2.moveRight();
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				p2.jump();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
