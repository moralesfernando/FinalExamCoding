package rocketBase;

import org.apache.poi.ss.formula.functions.*;

import java.util.ArrayList;
import rocketDomain.RateDomainModel;
import exceptions.RateException;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		ArrayList<RateDomainModel> ALLrates = _RateDAL.getAllRates();
		double givenRate = 0;
		
		for (RateDomainModel specificRate : ALLrates)
		{
			if(GivenCreditScore >= specificRate.getiMinCreditScore())
			{
				givenRate = specificRate.getdInterestRate();
			} 
			if (givenRate < 0)
			{
				throw new RateException(specificRate);
			}
			else return givenRate;
		}
		return givenRate;	
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return Math.abs(FinanceLib.pmt(r, n, p, f, t));
	}
}