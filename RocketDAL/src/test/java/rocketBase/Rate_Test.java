package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> ALLrates = RateDAL.getAllRates();
		
		assertEquals(ALLrates.size(),5);
		
		for(RateDomainModel RDM : ALLrates){
			System.out.println (RDM);
		}
		
		assertEquals(ALLrates.get(0).getiRateID(),1);
		assertEquals(ALLrates.get(0).getdInterestRate(),5);
		assertEquals(ALLrates.get(0).getiMinCreditScore(),600);
		
	}

}