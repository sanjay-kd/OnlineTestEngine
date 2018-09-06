import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadDemo {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter url :");
		String strUrl = sc.next();
		URL url = new URL(strUrl);
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream is = connection.getInputStream();
		BufferedInputStream bi = new BufferedInputStream(is);
		int singleByte = bi.read();
		FileOutputStream fo = new FileOutputStream("/Users/starhawk/website.html");
		BufferedOutputStream bs = new BufferedOutputStream(fo);
		
		while(singleByte != -1){
			bs.write(singleByte);
			singleByte = bi.read();
		}
		
		System.out.println("File downloaded....");
		bs.close();
		fo.close();
		bi.close();
		is.close();
		sc.close();
	}
	
}
