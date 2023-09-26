package Sample;

import org.testng.annotations.Test;

public class TestClass {
	
	@Test(priority =1, enabled = true)
	public static void TC1_VerifyPlacingOrder() throws Exception{
			BaseClass.VerifyPlacingOrder();
		}


}
