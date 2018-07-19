package googleMapViewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		JMenuBar menubar = new JMenuBar();
		JMenu program = new JMenu("program");
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon("images\\X.png"));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		program.add(exit);
		menubar.add(program);
		
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("지도검색창", new Panel1());
		tab.addTab("검색목록", new Panel2());
		tab.addTab("프로그램 정보", new Panel3());
		
		add(menubar, BorderLayout.NORTH);
		add(tab, BorderLayout.CENTER);
		setTitle("GoogleMap Search Program");
		setBounds(100,50,800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
