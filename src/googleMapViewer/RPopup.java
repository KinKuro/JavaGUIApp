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
				JOptionPane.showMessageDialog(null, "������ �޴� �� : \n"
												  + "1. ������ ���� ��ǻ���� ����ȭ�鿡�� ��Ŭ�� ��  Receive�� �����մϴ�.\n"
												  + "2. ������ ���� ��ǻ�Ϳ��� \"�ޱ�\"��ư�� �����ϴ�.\n\n"
												  + "3. �Ϸ�â�� �߰� ���� ������ ���������� ��ٸ��ϴ�.\n\n"
												  + "������ ������ �� :\n"
												  + "1. ������ ���� ��ǻ���� ����ȭ�鿡�� ��Ŭ�� �� Send�� �����մϴ�.\n"
											 	  + "2. ������ ���� ��ǻ�Ϳ��� ������ ���� ��ǻ���� ip�ּҸ� �Է��մϴ�.\n"
												  + "3. �ߴ� �˾�â�� \"������\"��ư�� ������ ��ٸ��ϴ�."
												 );
				}
			}
		}
		
	}

}
