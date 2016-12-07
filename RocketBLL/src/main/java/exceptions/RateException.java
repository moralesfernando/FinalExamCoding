package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception 
{

	private RateDomainModel RateDomainModel = null;
	
	public RateDomainModel getRateDomainModel() 
	{
		return RateDomainModel;
	}
	
	public RateException(RateDomainModel rate) 
	{	
		this.RateDomainModel = RateDomainModel;	
	}

}