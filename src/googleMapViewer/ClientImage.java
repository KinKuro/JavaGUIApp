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
		JLabel label = new JLabel("ip�ּ�");
		ipField = new JTextField(20);
		click = new JButton("������");
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
			JOptionPane.showMessageDialog(ClientImage.this, "���ӵǾ����ϴ�.\n����["+sndFile.getPath()+"] �����⸦ �����մϴ�.");
			
			fis = new FileInputStream(sndFile);
			dos = new DataOutputStream(socket.getOutputStream());
			int length;
			while ((length = fis.read()) != -1 ) {
				dos.write(length);
			}
			
			fis.close();
			dos.flush();
			dos.close();
			
			JOptionPane.showMessageDialog(ClientImage.this, "���� �����Ⱑ �Ϸ�Ǿ����ϴ�.");
			socket.close();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
