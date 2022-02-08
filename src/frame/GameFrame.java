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
		jp = new JPanel(); // 모든 컴포넌트들이 추가될 JPanel
		jp.setSize(1000, 800);
		jp.setLayout(null);
		jp.setBackground(Color.cyan);

		jlScore_p1 = new JLabel("0", JLabel.CENTER); // 플레이어1의 점수 레이블
		jlScore_p1.setFont(new Font("Papyrus", Font.BOLD, 70));
		jlScore_p1.setForeground(Color.red);
		jlScore_p1.setSize(100, 100);
		jlScore_p1.setLocation(50, 0);

		jlScore_p2 = new JLabel("0", JLabel.CENTER); // 플레이어2의 점수 레이블
		jlScore_p2.setFont(new Font("Papyrus", Font.BOLD, 70));
		jlScore_p2.setForeground(Color.red);
		jlScore_p2.setSize(100, 100);
		jlScore_p2.setLocation(jp.getWidth() - 150, 0);

		createGround(); // 바닥 생성
		createPlayer(); // 플레이어 생성
		createNet(); // 네트 생성
		//createBall(); // 포켓볼 생성

		gameStart gs = new gameStart(jp); // 시작 레이블 스레드 생성
		jp.add(gs);
		new Thread((Runnable) gs).start(); // 시작 레이블 스레드 시작
		jp.add(jlScore_p1);
		jp.add(jlScore_p2);

		ct.add(jp); //컨테이너에 jp 추가
		setFocusable(true);
		addKeyListener(this); //키리스너 추가
		setTitle("Pikachu Volleyball");
		setSize(1000, 810);
		setResizable(false); //창 사이즈 조절 못하게 설정
		setLocationRelativeTo(null); //창을 화면의 가운데에 띄움
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createGround() { // 바닥 생성
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

	public void createPlayer() { // 플레이어 생성
		p1 = new Player1(jp);
		p1.setPikachu(50, jp.getHeight() - p1.getHeight() - groundHeight);
		p2 = new Player2(jp);
		p2.setPikachu(jp.getWidth() - 200, jp.getHeight() - p2.getHeight() - groundHeight);
	}

	public void createNet() { // 네트 생성
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
			// 플레이어1은 D,G 키로 좌우 이동
			if (e.getKeyCode() == KeyEvent.VK_D)
				p1.moveLeft();
			if (e.getKeyCode() == KeyEvent.VK_G)
				p1.moveRight();
			
			// 플레이어2는 좌우 방향키로 좌우 이동
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

class gameStart extends JLabel implements Runnable { //게임 시작 레이블이 위에서 중간까지 내려와 1초간 멈춘 후 게임이 시작됨
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
					GameFrame.gameStart = true; // 게임 시작
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
