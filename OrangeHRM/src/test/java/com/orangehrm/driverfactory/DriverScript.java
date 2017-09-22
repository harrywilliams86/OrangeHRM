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
		for(int i=0;i<=excel.getRowCount("MasterTestCases");i++)
		{
			if(excel.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{	
				String tc_Module = excel.getCellData("MasterTestCases", i, 1);
				for(int j=0; j<= excel.getRowCount(tc_Module);j++)
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
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						CommonFunctions.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					}	
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						CommonFunctions.clickAction(driver, Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("clickLogout"))
					{
						CommonFunctions.clickLogout(driver);
					}
					if(Object_Type.equalsIgnoreCase("titleValidation"))
					{
						CommonFunctions.titleValidation(driver, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("closeApplication"))
					{
						CommonFunctions.closeApplication(driver);
					}
					excel.setCellData(tc_Module, j, 5, "PASS");
					}
					catch(Exception e)
					{
						excel.setCellData(tc_Module, j, 5, "FAIL");
					}
					catch(AssertionError a)
					{
						excel.setCellData(tc_Module, j, 5, "FAIL");
					}
					
				}
			}
			else
			{
				excel.setCellData("MasterTestCases", i, 3, "Not Executed");
			}
		}
	}
}
