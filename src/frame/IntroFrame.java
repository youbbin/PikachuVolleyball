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
		ImageIcon iiTitle = new ImageIcon("title.png"); // ���� Ÿ��Ʋ
		Image imgTitle = iiTitle.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH); // ������ ����
		ImageIcon iiTitle_setSize = new ImageIcon(imgTitle);
		jlTitle = new JLabel();
		jlTitle.setIcon(iiTitle_setSize);
		jlTitle.setSize(500, 100);
		jlTitle.setLocation(250, 300); // ��ġ ����

		ImageIcon iiStart = new ImageIcon("start_button.png"); // ���� ���� ��ư
		Image imgStart = iiStart.getImage().getScaledInstance(200, 70, Image.SCALE_SMOOTH); // ������ ����
		ImageIcon iiStart_setSize = new ImageIcon(imgStart);
		jbStart = new JButton();
		jbStart.setIcon(iiStart_setSize);
		jbStart.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		jbStart.setContentAreaFilled(false); // ��ư ä��� ����
		jbStart.setSize(200, 50);
		jbStart.setLocation(380, 500); // ��ġ ����
		jbStart.addActionListener(this); // ��ư�� �׼� ������ �߰�

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
		gf.createGameFrame(); // ���� �÷��� ȭ������ ��ȯ
	}
}
