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
		areaPanel.setBorder(BorderFactory.createTitledBorder("�˻����"));
		area = new JTextArea(20, 55);

		area.setEditable(false);
		JScrollPane pane = new JScrollPane(area);
		
		areaPanel.add(pane);
		pane.setBounds(90, 90, 610, 500);
		
		//AREAPANEL end...
		
		////////BUTTONPANEL////////////
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 2));
		btn1 = new JButton("�˻���� txt���� ����");
		btn2 = new JButton("�˻���� txt���� �����");
		btn3 = new JButton("�˻��� ���� ���� ����");
		btn4 = new JButton("�˻��� ���� ���� �����");
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

	}//Panel2 ������...
	
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
				if(JOptionPane.showConfirmDialog(Panel2.this, "������ �˻������ ����ðڽ��ϱ�?", "Caution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
				if(JOptionPane.showConfirmDialog(Panel2.this, "������ ���������� ����ðڽ��ϱ�?", "Caution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
						public void run() {
							clearMapsFolder();
						};
					}.start();
				}
			}//��ư�� ��ɵ� ������
		}//actionPerformed()...
	}//BtnListener �̳�Ŭ����...
	
	
	//////method ����////////////////
	
	//��� �о���� �޼ҵ�
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
			JOptionPane.showMessageDialog(Panel2.this, "����� �о�Խ��ϴ�.");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(Panel2.this, "������ �����ϴ�!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Panel2.this, "��� ����� �о�Խ��ϴ�.");
		}
	}
	
	//��� ������ ����� �޼ҵ�
	public void clearList() {
		if(file.exists()) {
			file.delete();
			JOptionPane.showMessageDialog(Panel2.this, "�˻������ ���������ϴ�.");
		}
		else {
			JOptionPane.showMessageDialog(Panel2.this, "������ �����ϴ�!");
		}
	}
	
	//����� �ʵ� ������ �����ִ� �޼ҵ�
	public void showMapsFolder() {
		try {
			Runtime run = Runtime.getRuntime();
			run.exec("explorer.exe "+imgPath);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(Panel2.this, "�̹��� ������ �����ϴ�");
		}
	}
	
	//����� �ʵ��� ����� �޼ҵ�
	public void clearMapsFolder() {
		if(imgPath.isDirectory()) {
			File[] files = imgPath.listFiles();
			for (File file : files) {
				file.delete();
			}
			imgPath.delete();
			JOptionPane.showMessageDialog(Panel2.this, "����� �������� ���������ϴ�.");
		}else {
			JOptionPane.showMessageDialog(Panel2.this, "����� ���������� �����ϴ�!");
		}
	}
}
