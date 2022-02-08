package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IntroFrame extends JFrame implements ActionListener {
	ImageIcon introBack = new ImageIcon("intro_back.jpg");
	Container ct;
	JLabel jlTitle;
	JButton jbStart;

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
		jlTitle.setLocation(250, 300); // 위치 설정

		ImageIcon iiStart = new ImageIcon("start_button.png"); // 게임 시작 버튼
		Image imgStart = iiStart.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH); // 사이즈 조정
		ImageIcon iiStart_setSize = new ImageIcon(imgStart);
		jbStart = new JButton();
		jbStart.setIcon(iiStart_setSize);
		jbStart.setBorderPainted(false); // 버튼 외곽선 없애기
		jbStart.setContentAreaFilled(false); // 버튼 채우기 안함
		jbStart.setSize(200, 50);
		jbStart.setLocation(380, 500); // 위치 설정
		jbStart.addActionListener(this); // 버튼에 액션 리스너 추가

		ct.add(jlTitle);
		ct.add(jbStart);
		setTitle("Pikachu Volleyball Intro");
		setSize(1000, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		GameFrame gf = new GameFrame();
		gf.createGameFrame(); // 게임 플레이 화면으로 전환
	}
}
