package rocketBase;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import java.util.ArrayList;

public class rate_test 
{
	@Test
	public void RateExceptionTest() throws RateException{
		ArrayList<RateDomainModel> ALLrates = RateDAL.getAllRates();
		try
		{
			double specificRate = RateBLL.getRate(0);
		}	
			catch(RateException e)
		{
			throw e;
		}
	}
	
	@Test
	public void PaymentTest(){
		double payment = RateBLL.getPayment(.04/12, 360, 300000, 0, false);
		Assert.assertEquals(1432.25,payment,1);
	}

}