package googleMapViewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	
	private JButton btnSearch;
	private static JTextField tfSearch;
	private static JSlider zoom;
	
	private File file = new File("search_list.txt");
	private FileWriter fw;
	
	private GoogleAPI googleAPI = new GoogleAPI();
	private JLabel googleMap;
	
	private File sound = new File("SE\\cegC.wav");
	
	RPopup popup;
	
	public Panel1() {
		setLayout(new BorderLayout());
		
		/////////screenPanel/////////////
		JPanel screenPanel = new JPanel();
		screenPanel.setBorder(BorderFactory.createTitledBorder("Screen"));
		
		googleMap = new JLabel() ;
		googleMap.setIcon(new ImageIcon("images\\maparea.png"));
		
		popup = new RPopup();
		googleMap.setComponentPopupMenu(popup);		//Java 7부터
		screenPanel.add(googleMap, BorderLayout.CENTER);
		//screenPanel end...
		
		/////////searchPanel/////////////
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		
		tfSearch = new JTextField(30);
		btnSearch = new JButton("　　지도검색　　");
		
		zoom = new JSlider(JSlider.HORIZONTAL, 5, 20, 5);
		zoom.setValue(17);
		zoom.setMajorTickSpacing(5);
		zoom.setMinorTickSpacing(1);
		zoom.setPaintLabels(true);
		
		tfSearch.addKeyListener(new tfListener());
		btnSearch.addActionListener(new btnListener());

		searchPanel.add(tfSearch);
		searchPanel.add(zoom);
		searchPanel.add(btnSearch);
		//searchPanel end...
		
		add(screenPanel, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.SOUTH);
	}//Panel1 생성자....
	
	
	//////Listener 모음////////////////
	//버튼용
	class btnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			WriteThread wt = new WriteThread();
			DrawingThread dt = new DrawingThread();
			wt.start();
			dt.start();
		}
		
	}//bntListener class...
	
	//textfield 엔터키용
	class tfListener extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				WriteThread wt = new WriteThread();
				DrawingThread dt = new DrawingThread();
				wt.start();
				dt.start();
			}
		}
	}//tfListener class...
	
	//////Thread 모음////////////////
	
	//txt파일 출력 스레드
	class WriteThread extends Thread{
		@Override
		public void run() {
			makeList();
			
		}
	}//WriteThread class...
	
	//JLabel에 그림 그리는 & 성공 알림 사운드 스레드
	class DrawingThread extends Thread{
		@Override
		public void run() {
			try {
				setMap(tfSearch.getText(), zoom.getValue());
				listenSound(sound);
			} catch(Exception e) {} 
		}
	}
	
	//////method 모음////////////////
	
	//serach_list.txt 만드는 메소드
	public void makeList() {
		try {
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(tfSearch.getText());
			pw.flush();
			pw.close();
			tfSearch.setText("");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Panel1.this, "파일을 만들 수 없습니다.");
		}
	}//makeList()...
	
	//지도 그림 그리는 메소드
	public void setMap(String location, int zoomLevel) {
		if(!location.equals("")) {
			googleAPI.downloadMap(location, zoomLevel);
			googleMap.setIcon(googleAPI.getMap());
		} else {
			googleMap.setIcon(new ImageIcon("images\\maparea.png"));
		}
		googleMap.repaint();
	}
	
	//성공 소리를 알려주는 메소드
	public void listenSound(File sound) {
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e)	{}
		
	}

}
