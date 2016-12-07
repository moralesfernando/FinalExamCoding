package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;

import exceptions.RateException;

public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) 
		{
			resetOutput();
			LoanRequest lq = (LoanRequest) message;
			
			 try
			 {
				 lq.setdRate(_RateBLL.getRate(lq.getiCreditScore()));
				 lq.getIncome();
			 }	 
				
			 catch (RateException e)
			 {
				 sendToAll(e);
			 }
			
			double r = lq.getdRate();
			double n = (double)lq.getiTerm();
			double p = lq.getdAmount();
			double f = 0;
			boolean t = false;
			
			double payment = RateBLL.getPayment(r, n, p, f, t);
			lq.setdPayment(payment);
			
			sendToAll(lq);
		}
	}
}
