
class DepositMoney implements Runnable{
	private int depositAmount;
	
	DepositMoney(int depositAmount){
		this.depositAmount = depositAmount;
	}

	@Override
	public void run() {
		WithDrawMoney w = new WithDrawMoney(depositAmount);
		notify();
	}
	
	
}

class WithDrawMoney implements Runnable{
	private int amount;
	
	WithDrawMoney(int amount){
		this.amount = amount;
	}

	@Override
	public void run() {
		if(amount>1000){
			System.out.println("Money Deposited.....");
		}
		else{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

public class InterThreadingDemo {
	
	public static void main(String[] args) {
		WithDrawMoney wD = new WithDrawMoney(200);
		DepositMoney dm = new DepositMoney(100);
		Thread tg1 = new Thread(wD);
		Thread tg2 = new Thread(dm);
		tg1.start();
		tg2.start();
		
	}
	
}
