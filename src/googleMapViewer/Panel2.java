package googleMapViewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Panel2 extends JPanel {
	private JTextArea area;
	private JButton btn1, btn2, btn3, btn4;
	
	private File file = new File("search_list.txt");
	private FileReader fr;
	
	private File imgPath = new File("maps");
	
	private Image bgImg;
	
	public Panel2() {
		setLayout(new BorderLayout());
		/////////AREAPANEL/////////////
		JPanel areaPanel = new JPanel();
		areaPanel.setLayout(null);
		areaPanel.setBorder(BorderFactory.createTitledBorder("검색목록"));
		area = new JTextArea(20, 55);

		area.setEditable(false);
		JScrollPane pane = new JScrollPane(area);
		
		areaPanel.add(pane);
		pane.setBounds(90, 90, 610, 500);
		
		//AREAPANEL end...
		
		////////BUTTONPANEL////////////
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 2));
		btn1 = new JButton("검색목록 txt파일 열기");
		btn2 = new JButton("검색목록 txt파일 지우기");
		btn3 = new JButton("검색한 지도 폴더 열기");
		btn4 = new JButton("검색한 지도 파일 지우기");
		btn1.addActionListener(new BtnListener());
		btn2.addActionListener(new BtnListener());
		btn3.addActionListener(new BtnListener());
		btn4.addActionListener(new BtnListener());
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		btnPanel.add(btn4);
		
		//BUTTONPANEL end...
		
		add(areaPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

	}//Panel2 생성자...
	
	//////Listener////////////////
	
	class BtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if(s.equals(btn1.getText())) {
				new Thread() {
					public void run() {
						readList();
					};
				}.start();
			}else if(s.equals(btn2.getText())){
				if(JOptionPane.showConfirmDialog(Panel2.this, "정말로 검색목록을 지우시겠습니까?", "Caution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					new Thread() {
						public void run() {
							clearList();
						};
					}.start();
					area.setText("");
				}
			}else if(s.equals(btn3.getText())) {
				new Thread() {
					public void run() {
						showMapsFolder();
					};
				}.start();
			}else if(s.equals(btn4.getText())) {
				if(JOptionPane.showConfirmDialog(Panel2.this, "정말로 지도폴더를 지우시겠습니까?", "Caution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
						public void run() {
							clearMapsFolder();
						};
					}.start();
				}
			}//버튼별 기능들 돌리기
		}//actionPerformed()...
	}//BtnListener 이너클래스...
	
	
	//////method 모음////////////////
	
	//목록 읽어오는 메소드
	public void readList() {
		try {
			area.setText("");
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line != null) {
				area.append(line+"\n");
				line = br.readLine();
			}
			br.close();
			JOptionPane.showMessageDialog(Panel2.this, "목록을 읽어왔습니다.");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(Panel2.this, "파일이 없습니다!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Panel2.this, "모든 목록을 읽어왔습니다.");
		}
	}
	
	//목록 파일을 지우는 메소드
	public void clearList() {
		if(file.exists()) {
			file.delete();
			JOptionPane.showMessageDialog(Panel2.this, "검색목록이 지워졌습니다.");
		}
		else {
			JOptionPane.showMessageDialog(Panel2.this, "파일이 없습니다!");
		}
	}
	
	//저장된 맵들 폴더를 보여주는 메소드
	public void showMapsFolder() {
		try {
			Runtime run = Runtime.getRuntime();
			run.exec("explorer.exe "+imgPath);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(Panel2.this, "이미지 폴더가 없습니다");
		}
	}
	
	//저장된 맵들을 지우는 메소드
	public void clearMapsFolder() {
		if(imgPath.isDirectory()) {
			File[] files = imgPath.listFiles();
			for (File file : files) {
				file.delete();
			}
			imgPath.delete();
			JOptionPane.showMessageDialog(Panel2.this, "저장된 지도들이 지워졌습니다.");
		}else {
			JOptionPane.showMessageDialog(Panel2.this, "저장된 지도파일이 없습니다!");
		}
	}
}
