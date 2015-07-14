import org.junit.Test;
import static org.junit.Assert.*;


public class JunitTest {
	
     @Test
     public static void testCompute(){
    	
    	 // Assert.assertEquals(expected, actual);  /Deprecated
    	 // Assert.assertTrue(expected == actual);   /Not JUnit
    	 assertEquals (0f, InsuranceCalculationHelper.conditionTest(-0.2f),  0.1f);
    	 assertEquals (500f, InsuranceCalculationHelper.conditionTest(550f),  0.1f);
    	 assertEquals (1.0f, InsuranceCalculationHelper.conditionTest(0.2f),  0.1f);
     }
}
     
