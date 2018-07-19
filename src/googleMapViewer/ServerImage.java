package googleMapViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ServerImage extends JFrame {
	
	ServerSocket serverSocket;
	Socket socket;
	
	DataInputStream dis;
	FileOutputStream fos;
	
	JButton btn;	
	
	public ServerImage() {

		JPanel panel = new JPanel();
		btn = new JButton("���� �ޱ�");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						receiveImage();
					};
				}.start();
			}
		});
		
		panel.add(btn);
		add(panel);
		
		setTitle("Receive");
		setSize(300,150);
		setAlwaysOnTop(true);
	}
	
	void receiveImage() {
		try {
			JOptionPane.showMessageDialog(this, "���� ���� �غ� �ϰڽ��ϴ�.");
			serverSocket = new ServerSocket(11011);
			socket = serverSocket.accept();
			JOptionPane.showMessageDialog(this, "���ӵǾ����ϴ�.\n���� �ޱ⸦ �����մϴ�.");
			
			File path = new File("maps");
			if(!path.isDirectory())		path.mkdir();
			File rcvFile = new File(path, "receive.jpg");
			dis = new DataInputStream(socket.getInputStream());
			fos = new FileOutputStream(rcvFile);
			int length;
			while((length = dis.read()) != -1) {
				fos.write(length);
			}
			
			dis.close();
			fos.flush();
			fos.close();
			
			JOptionPane.showMessageDialog(ServerImage.this, "������ [receive.jpg]���Ϸ� ����Ǿ����ϴ�.");
			Runtime.getRuntime().exec("explorer.exe maps");
			
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
