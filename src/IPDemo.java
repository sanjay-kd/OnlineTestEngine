import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPDemo {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress inet = InetAddress.getLocalHost();
		System.out.println(inet.toString());
		String hostName = new Scanner(System.in).next();
		InetAddress inetArr[] = InetAddress.getAllByName(hostName);
		for(InetAddress inet2 : inetArr){
			System.out.println(inet2.toString());
		}
		
	}
	
}
