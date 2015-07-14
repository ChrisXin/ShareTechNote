public class Account
{
	private float balance;
	public void deposit (float amount)
	{
	  balance += amount;
	}
	
	public void withdraw (float amount) 
		{ balance -=amount;} }
	
	public void transferFunds(Account destination, float amount) {}
	
	public float getBalance() 
			 {return balance;}
	}
	
Import static org.junit.Assert .*
import org.junit.Test;

public class AccounTest{
	 private Account account1;
	 
	 // sharing resources among tests
	 // Before notion help setting up the objects which use more than once
	 // also, you can free any resources that you open in @Before by @After
	 @Before public void setup(){
		 account1 = new Account();
		 account1.deposit(200.00f); 
		 account2 = new Account();
		 account2.deposit(150.00f);
	 }
	 
	 account1.transferFunds(account2, 100.00f);
	 assertEquals("Account2_balance_balance", 250.00f, account2.getBalance(), 0.01)
	 assertEquals("Account1_balance", 100.00f, account1.getBalance(), 0.01)
}

