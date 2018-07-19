package googleMapViewer;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;

public class GoogleAPI {
	private File path = new File("maps");
	private static File file = null;
	
	
	public void downloadMap(String location, int zoomLevel) {
		try {
			String imageURL = "http://maps.googleapis.com/maps/api/staticmap?center="
							+URLEncoder.encode(location,"UTF-8")+"&zoom="+zoomLevel+"&size=612x612&scale=2";
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			
			if(!path.isDirectory()) path.mkdir();
			file = new File(path, location+"_"+zoomLevel+".jpg");
			
			OutputStream os = new FileOutputStream(file);
			byte[] b = new byte[2048];
			int length;
			while((length = is.read(b)) != -1) {
				os.write(b,0,length);
			}
			is.close();
			os.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public ImageIcon getMap() {
		return new ImageIcon((new ImageIcon(file.getPath())).getImage().getScaledInstance(612, 612, Image.SCALE_SMOOTH));
	}
	
	public static File getFile() {
		return file;
	}

	
}
