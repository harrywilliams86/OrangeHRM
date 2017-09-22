package com.orangehrm.driverfactory;

import org.testng.annotations.Test;

public class AppTest
{
	@Test
	public void kickStart()
	{
		DriverScript ds = new DriverScript();
		try
		{
			ds.startTest();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
