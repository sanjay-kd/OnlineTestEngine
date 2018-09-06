import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.nsit.testengine.constants.Constants;

class NewThread implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("This is : "+Thread.currentThread().getName());
//			Thread.yield();
//			Thread.sleep(1000);
		}
	}
	
}

public class TestFile extends Thread{
	
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("This is : "+Thread.currentThread().getName());
//			Thread.yield();
//			Thread.sleep(1000);
		}
	}
	
	public void readAndWrite() throws IOException{
		File file = new File(Constants.SELECT_FILE_PATH);
		File uploaded = new File(Constants.UPLOAD_FILE_PATH);
		if(file.exists()){
			FileOutputStream fs1 = new FileOutputStream(uploaded);
//			BufferedOutputStream bs1 = new BufferedOutputStream(fs1);
			FileInputStream fs = new FileInputStream(file);
			BufferedInputStream bs = new BufferedInputStream(fs);
			int singleByte = bs.read();
			while(singleByte != -1){
				System.out.println("Upload Done1");
				fs1.write(singleByte);
				System.out.println(singleByte);
				singleByte = bs.read();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestFile testFile = new TestFile();
		NewThread newThread = new NewThread();
		Thread thread2 = new Thread(newThread,"Shyam");
		testFile.setName("Ram");
		testFile.start();
		thread2.start();
		thread2.setPriority(MAX_PRIORITY);
		testFile.setPriority(MIN_PRIORITY);
		thread2.join();
//		thread2.start();
		
//		testFile.run();  -- > This will run this thread in the same thread and not in a diff stack.
		
		
		for(int i=0; i<10;i++){
			System.out.println("This is "+Thread.currentThread().getName() +" thread");
		}
		
		
//		try {
//			testFile.readAndWrite();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}

	}

}
