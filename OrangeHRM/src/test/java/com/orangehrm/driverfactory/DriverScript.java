package com.orangehrm.driverfactory;

import org.openqa.selenium.WebDriver;

import com.orangehrm.commonfunctionality.CommonFunctions;
import com.orangehrm.utilities.ExcelUtils;

public class DriverScript 
{
	WebDriver driver;
	public void startTest() throws Exception
	{
		ExcelUtils excel = new ExcelUtils();
		for(int i=1;i<=excel.getRowCount("MasterTestCases");i++)
		{
			if(excel.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{	
				String tc_Module = excel.getCellData("MasterTestCases", i, 1);
				for(int j=1; j<= excel.getRowCount(tc_Module);j++)
				{
					String Description = excel.getCellData(tc_Module, j, 0);
					String Object_Type = excel.getCellData(tc_Module, j, 1);
					String Locator_Type = excel.getCellData(tc_Module, j, 2);
					String Locator_Value = excel.getCellData(tc_Module, j, 3);
					String Test_Data = excel.getCellData(tc_Module, j, 4);
					try
					{
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver = CommonFunctions.startBrowser(driver);
					}
					if(Object_Type.equalsIgnoreCase("openApplication"))
					{
						CommonFunctions.openApplication(driver);
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						CommonFunctions.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						CommonFunctions.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					}	 
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						CommonFunctions.clickAction(driver, Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("logout"))
					{
						CommonFunctions.logout(driver);
					}
					if(Object_Type.equalsIgnoreCase("titleValidation"))
					{
						CommonFunctions.titleValidation(driver, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("closeApplication"))
					{
						CommonFunctions.closeApplication(driver);
					}
					excel.setData(tc_Module, j, 5, "PASS");
					excel.setData("MasterTestCases", i, 3, "PASS");
					}
					catch(Exception e)
					{
						excel.setData(tc_Module, j, 5, "FAIL");
					}
					catch(AssertionError a)
					{
						excel.setData(tc_Module, j, 5, "FAIL");
					}
					
				}
			}
			else
			{
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
		}
	}
}
