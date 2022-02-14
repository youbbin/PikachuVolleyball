package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import data.*;

public class IntroFrame extends JFrame implements ActionListener {
	ImageIcon introBack = new ImageIcon("intro_back.jpg");
	Container ct;
	JLabel jlTitle;
	JButton jbStart;
	JTextField jtfNick1;
	JTextField jtfNick2;
	public static String nick1;
	public static String nick2;
	
	public IntroFrame() {
		ct = getContentPane();
		ct.setLayout(null);
		ct.setBackground(Color.GREEN);
		ImageIcon iiTitle = new ImageIcon("title.png"); // 게임 타이틀
		Image imgTitle = iiTitle.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH); // 사이즈 조정
		ImageIcon iiTitle_setSize = new ImageIcon(imgTitle);
		jlTitle = new JLabel();
		jlTitle.setIcon(iiTitle_setSize);
		jlTitle.setSize(500, 100);
		jlTitle.setLocation(250, 200); // 위치 설정

		ImageIcon iiStart = new ImageIcon("start_button.png"); // 게임 시작 버튼
		Image imgStart = iiStart.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH); // 사이즈 조정
		ImageIcon iiStart_setSize = new ImageIcon(imgStart);
		jbStart = new JButton();
		jbStart.setIcon(iiStart_setSize);
		jbStart.setBorderPainted(false); // 버튼 외곽선 없애기
		jbStart.setContentAreaFilled(false); // 버튼 채우기 안함
		jbStart.setSize(iiStart_setSize.getIconWidth(),iiStart_setSize.getIconHeight() );
		jbStart.setLocation(500, 450); // 위치 설정
		jbStart.addActionListener(this); // 버튼에 액션 리스너 추가
		jbStart.setEnabled(true);
		
		JPanel jpNick=new JPanel(); //닉네임 입력 패널
		jpNick.setBackground(Color.green);
		jpNick.setLayout(new GridLayout(4,1,30,0));
		jpNick.setLocation(250, 400);
		jpNick.setSize(200,150);
		JLabel jlNick1=new JLabel("1P의 닉네임을 입력하세요");
		jlNick1.setFont(new Font("휴먼아미체",Font.BOLD,20));
		jtfNick1=new JTextField(10);
		JLabel jlNick2=new JLabel("2P의 닉네임을 입력하세요");
		jlNick2.setFont(new Font("휴먼아미체",Font.BOLD,20));
		jtfNick2=new JTextField(10);
		jpNick.add(jlNick1);
		jpNick.add(jtfNick1);
		jpNick.add(jlNick2);
		jpNick.add(jtfNick2);
		
		Database db=new Database();
		createWinnerBoard(db.getWinner()); //우승자를 가져와 보드 생성
		createPikachu(); //피카츄 생성
		
		ct.add(jpNick);
		ct.add(jlTitle);
		ct.add(jbStart);
		setTitle("Pikachu Volleyball Intro");
		setSize(1000, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createWinnerBoard(String winner) { //오늘의 우승자를 볼 수 있는 보드
		JLabel jlCrown=new JLabel(); //왕관 레이블
		ImageIcon iiCrown = new ImageIcon("crown.png");
		Image imgCrown = iiCrown.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
		ImageIcon iiCrown_setSize = new ImageIcon(imgCrown);
		jlCrown.setIcon(iiCrown_setSize);
		jlCrown.setSize(iiCrown_setSize.getIconWidth(),iiCrown_setSize.getIconHeight());
		jlCrown.setLocation(850, 20);
		ct.add(jlCrown);
		
		String[] arrWinner=winner.split(" "); //우승자들이 저장된 String을 " " 기준으로 나누어 배열에 저장
		JPanel jpBoard=new JPanel();
		jpBoard.setLayout(new BorderLayout());
		jpBoard.setSize(100,arrWinner.length*50);
		jpBoard.setLocation(850, 100);
		JLabel jlBoardTitle=new JLabel("오늘의 우승자",JLabel.CENTER);
		jlBoardTitle.setFont(new Font("휴먼아미체",Font.BOLD,25));
		JLabel[] jlArr=new JLabel[arrWinner.length];
		JPanel jpWinner=new JPanel();
		jpWinner.setOpaque(false);
		jpWinner.setLayout(new GridLayout(arrWinner.length,1));
		for(int i=0;i<arrWinner.length;i++) {
			JLabel jlWinner=new JLabel(arrWinner[i],JLabel.CENTER);
			jlWinner.setFont(new Font("바탕",Font.BOLD,15));
			jpWinner.add(jlWinner);
		}
		jpBoard.add(jlBoardTitle,BorderLayout.NORTH);
		jpBoard.add(jpWinner,BorderLayout.CENTER);
		jpBoard.setBackground(Color.yellow);
		jpBoard.setOpaque(false);
		ct.add(jpBoard);
	}
	
	public void createPikachu() {
		ImageIcon pikachuL = new ImageIcon("pikachu_L.png");
		Image imgL = pikachuL.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon pikachuL_setSize = new ImageIcon(imgL);
		JLabel jlPikachu=new JLabel();
		jlPikachu.setIcon(pikachuL_setSize);
		jlPikachu.setSize(pikachuL_setSize.getIconWidth(),pikachuL_setSize.getIconHeight());
		jlPikachu.setLocation(800, 550);
		ct.add(jlPikachu);
	}
	
	public void actionPerformed(ActionEvent ae) {
		nick1=jtfNick1.getText();
		nick2=jtfNick2.getText();
		setVisible(false);
		GameFrame gf = new GameFrame();
		gf.createGameFrame(); // 게임 플레이 화면으로 전환
	}
}
