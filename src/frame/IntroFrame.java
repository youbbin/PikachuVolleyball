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
		ImageIcon iiTitle = new ImageIcon("title.png"); // ���� Ÿ��Ʋ
		Image imgTitle = iiTitle.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH); // ������ ����
		ImageIcon iiTitle_setSize = new ImageIcon(imgTitle);
		jlTitle = new JLabel();
		jlTitle.setIcon(iiTitle_setSize);
		jlTitle.setSize(500, 100);
		jlTitle.setLocation(250, 200); // ��ġ ����

		ImageIcon iiStart = new ImageIcon("start_button.png"); // ���� ���� ��ư
		Image imgStart = iiStart.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH); // ������ ����
		ImageIcon iiStart_setSize = new ImageIcon(imgStart);
		jbStart = new JButton();
		jbStart.setIcon(iiStart_setSize);
		jbStart.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		jbStart.setContentAreaFilled(false); // ��ư ä��� ����
		jbStart.setSize(iiStart_setSize.getIconWidth(),iiStart_setSize.getIconHeight() );
		jbStart.setLocation(500, 450); // ��ġ ����
		jbStart.addActionListener(this); // ��ư�� �׼� ������ �߰�
		jbStart.setEnabled(true);
		
		JPanel jpNick=new JPanel(); //�г��� �Է� �г�
		jpNick.setBackground(Color.green);
		jpNick.setLayout(new GridLayout(4,1,30,0));
		jpNick.setLocation(250, 400);
		jpNick.setSize(200,150);
		JLabel jlNick1=new JLabel("1P�� �г����� �Է��ϼ���");
		jlNick1.setFont(new Font("�޸վƹ�ü",Font.BOLD,20));
		jtfNick1=new JTextField(10);
		JLabel jlNick2=new JLabel("2P�� �г����� �Է��ϼ���");
		jlNick2.setFont(new Font("�޸վƹ�ü",Font.BOLD,20));
		jtfNick2=new JTextField(10);
		jpNick.add(jlNick1);
		jpNick.add(jtfNick1);
		jpNick.add(jlNick2);
		jpNick.add(jtfNick2);
		
		Database db=new Database();
		createWinnerBoard(db.getWinner()); //����ڸ� ������ ���� ����
		createPikachu(); //��ī�� ����
		
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

	public void createWinnerBoard(String winner) { //������ ����ڸ� �� �� �ִ� ����
		JLabel jlCrown=new JLabel(); //�հ� ���̺�
		ImageIcon iiCrown = new ImageIcon("crown.png");
		Image imgCrown = iiCrown.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
		ImageIcon iiCrown_setSize = new ImageIcon(imgCrown);
		jlCrown.setIcon(iiCrown_setSize);
		jlCrown.setSize(iiCrown_setSize.getIconWidth(),iiCrown_setSize.getIconHeight());
		jlCrown.setLocation(850, 20);
		ct.add(jlCrown);
		
		String[] arrWinner=winner.split(" "); //����ڵ��� ����� String�� " " �������� ������ �迭�� ����
		JPanel jpBoard=new JPanel();
		jpBoard.setLayout(new BorderLayout());
		jpBoard.setSize(100,arrWinner.length*50);
		jpBoard.setLocation(850, 100);
		JLabel jlBoardTitle=new JLabel("������ �����",JLabel.CENTER);
		jlBoardTitle.setFont(new Font("�޸վƹ�ü",Font.BOLD,25));
		JLabel[] jlArr=new JLabel[arrWinner.length];
		JPanel jpWinner=new JPanel();
		jpWinner.setOpaque(false);
		jpWinner.setLayout(new GridLayout(arrWinner.length,1));
		for(int i=0;i<arrWinner.length;i++) {
			JLabel jlWinner=new JLabel(arrWinner[i],JLabel.CENTER);
			jlWinner.setFont(new Font("����",Font.BOLD,15));
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
		gf.createGameFrame(); // ���� �÷��� ȭ������ ��ȯ
	}
}
