
class DaemonThread implements Runnable{

	@Override
	public void run() {
		while(true){
			doSomething();
		}
	}
	
	public void doSomething(){
		System.out.println("Hello i m done!!!");
	}
	
}

public class DaemonThreadDemo {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new DaemonThread());
		thread.setDaemon(true);
		thread.start();
		
		ThreadGroup tg1 = new ThreadGroup("First");
		
		
		for(int i=0;i<5;i++){
			System.out.println("I valuedsffgdfdgfdjkgk is : "+i);
		}
		
	}
	
}
