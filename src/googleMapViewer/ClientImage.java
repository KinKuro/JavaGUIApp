package googleMapViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientImage extends JFrame {
	
	Socket socket;
	
	FileInputStream fis;
	DataOutputStream dos;
	
	JTextField ipField;
	JButton click;
	
	public ClientImage() {
		setSize(300,150);
		setTitle("Send");
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("ip주소");
		ipField = new JTextField(20);
		click = new JButton("보내기");
		click.setName("Click");
		click.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						sendImage();
					};
				}.start();
			}
		});
		
		ipField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					new Thread() {
						public void run() {
							sendImage();
						};
					}.start();
				}
			}
		});
	
		
		panel.add(label);
		panel.add(ipField);
		panel.add(click);
		add(panel);
		setAlwaysOnTop(true);
	}
	
	void sendImage() {
		try {
			socket = new Socket(ipField.getText(), 11011);
			File sndFile = GoogleAPI.getFile();
			JOptionPane.showMessageDialog(ClientImage.this, "접속되었습니다.\n지도["+sndFile.getPath()+"] 보내기를 시작합니다.");
			
			fis = new FileInputStream(sndFile);
			dos = new DataOutputStream(socket.getOutputStream());
			int length;
			while ((length = fis.read()) != -1 ) {
				dos.write(length);
			}
			
			fis.close();
			dos.flush();
			dos.close();
			
			JOptionPane.showMessageDialog(ClientImage.this, "지도 보내기가 완료되었습니다.");
			socket.close();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
