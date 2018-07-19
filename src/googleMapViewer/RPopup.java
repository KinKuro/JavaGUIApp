package googleMapViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class RPopup extends JPopupMenu{
	
	JMenuItem receive, send, htu;
	
	public RPopup() {
		receive = new JMenuItem("Receive");
		send = new JMenuItem("Send");
		htu = new JMenuItem("How To Use");
		
		receive.setName("receive");
		send.setName("send");
		htu.setName("htu");
		
		receive.addMouseListener(new CListener());
		send.addMouseListener(new CListener());
		htu.addMouseListener(new CListener());
		
		add(receive);
		add(send);
		add(htu);
		
	}
	
	class CListener extends MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getButton()==1) {
				if(e.getComponent().getName()==receive.getName()){
					ServerImage si = new ServerImage();
					si.setLocation(RPopup.this.getX()+100, RPopup.this.getX()+100);
					si.setVisible(true);
					
				}else if(e.getComponent().getName()==send.getName()) {
					ClientImage ci = new ClientImage();
					ci.setLocation(RPopup.this.getX()+500, RPopup.this.getX()+100);
					ci.setVisible(true);

				}else if(e.getComponent().getName()==htu.getName()) {
				JOptionPane.showMessageDialog(null, "사진을 받는 쪽 : \n"
												  + "1. 사진을 받을 컴퓨터의 지도화면에서 우클릭 후  Receive를 선택합니다.\n"
												  + "2. 사진을 받을 컴퓨터에서 \"받기\"버튼을 누릅니다.\n\n"
												  + "3. 완료창이 뜨고 지도 폴더가 열릴때까지 기다립니다.\n\n"
												  + "사진을 보내는 쪽 :\n"
												  + "1. 사진을 보낼 컴퓨터의 지도화면에서 우클릭 후 Send를 선택합니다.\n"
											 	  + "2. 사진을 보낼 컴퓨터에서 사진을 받을 컴퓨터의 ip주소를 입력합니다.\n"
												  + "3. 뜨는 팝업창의 \"보내기\"버튼을 누르고 기다립니다."
												 );
				}
			}
		}
		
	}

}
