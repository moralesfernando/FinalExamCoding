package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> ALLrates = RateDAL.getAllRates();
		System.out.println ("The size of ALLRATES is:" + ALLrates.size());
		assert(ALLrates.size() > 0);
		
	}

}