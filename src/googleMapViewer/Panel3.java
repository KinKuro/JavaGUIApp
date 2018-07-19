package googleMapViewer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel3 extends JPanel {
	
	private JLabel[] labels = new JLabel[4];
	private boolean[] flags = new boolean[4];
	private JButton btn;
	
	private Image bgImg;
	
	public Panel3() {
		setLayout(new BorderLayout());
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		bgImg = tool.getImage("images\\p3bg4(779x658).png");

		JPanel lPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgImg, 0, 0, this);
				setOpaque(false);
			}
		};
		lPanel.setBorder(BorderFactory.createTitledBorder("Infomation..."));
		lPanel.setLayout(new GridLayout(5, 0, 20, 40));
		
		for(int i = 0 ; i < labels.length; i++) {
			labels[i] = new JLabel();
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setFont(new Font(null, Font.BOLD|Font.ITALIC, 30));
			labels[i].setOpaque(false);
			lPanel.add(labels[i]);
			labels[i].setVisible(false);
		}
		
		labels[0].setText("18.01.31 ~ 18.02.13");
		labels[1].setText("Java (JDK9.0.1)");
		labels[2].setText("JAVA GUI Application 과제용");
		labels[3].setFont(new Font(null, Font.BOLD, 20));
		labels[3].setText(" 미래능력개발원 안드로이드웹앱콘텐츠개발자양성 3회차 김건오");
		
		JPanel btnPanel = new JPanel(); 
		btn = new JButton("정보 보기");
		btn.setFont(new Font(null, Font.BOLD, 20));
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0 ; i < flags.length; i++) {
					Random rnd = new Random();
					flags[i] = rnd.nextBoolean();
					if(flags[i])	labels[i].setVisible(true);
					else 			labels[i].setVisible(false);
				}
				if(flags[0] && flags[1] && flags[2] && flags[3]) {
					ImageIcon icon = new ImageIcon("images\\pps_other.jpg");
					JOptionPane.showMessageDialog(Panel3.this, "축하합니다!\n모든 정보가 한 화면에 나왔습니다!", "Congratuation", JOptionPane.PLAIN_MESSAGE,icon);
				}
			}
		});		
		btnPanel.add(btn);
		
		
		add(lPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		


	}//Panel3 생성자...
	

}
