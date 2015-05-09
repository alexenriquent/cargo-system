
package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
public class ContainerTests {
	
	private asgn2Codes.ContainerCode CCTest;
	private asgn2Containers.DangerousGoodsContainer DGCTest;
//	private asgn2Containers.FreightContainer FCTest;
	private asgn2Containers.GeneralGoodsContainer GGCTest;
	private asgn2Containers.RefrigeratedContainer RCTest;
	
	
	
	//Define Static
	private static final String C_CODE = "INKU2633836";

	private static final Integer GROSSWEIGHT = 5;
	private static final Integer CATEGORY = 5;
	private static final Integer TEMPERATURE = 5;
	
	private static final String CONTAINER_CODE_1 = "INKU2633836";
//	private static final String CONTAINER_CODE_2 = "KOCU8090115";
//	private static final String CONTAINER_CODE_3 = "MSCU6639871";
//	private static final String CONTAINER_CODE_4 = "CSQU3054387";
//	private static final String CONTAINER_CODE_5 = "QUTU7200318";
//	private static final String CONTAINER_CODE_6 = "IBMU4882351";
	

	//Before
	/**
	 * Build a Object before each test case
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException
	 */
	@Before
	public void setUp() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
	
		
		CCTest = new ContainerCode(C_CODE);
		FreightContainer DGCTest = new DangerousGoodsContainer(code1,GROSSWEIGHT,CATEGORY);
		FreightContainer GGCTest = new GeneralGoodsContainer(code1,GROSSWEIGHT);
		FreightContainer RCTest = new RefrigeratedContainer(code1,GROSSWEIGHT,TEMPERATURE);
	}
	
	//=================================================================
	//Test For Constructor ContainerCode Class
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test Constructor Default Input
	 * @throws InvalidCodeException
	 */
	@Test 
	public void CCConstructorDefaultInput() throws InvalidCodeException {
		CCTest = new ContainerCode(C_CODE);
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test Invalid Code Length If NOT 11 Character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCodeLength() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338376");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test Invalid Owner Code Does Not Consister of three Upper-case character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidOwnerCode() throws InvalidCodeException {
		CCTest = new ContainerCode("InkU2633836");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test Invalid Category Identifier is Not 'U'
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCategoryIdentifier() throws InvalidCodeException {
		CCTest = new ContainerCode("INKX2633836");
		
	}

	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test Serial Number Does not consist of six digit
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidSerialNumber() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338A6");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Test InCorrect Digit more then 6 Digit
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCheckDigit() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338786");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#toString()}
	 * Test toString whether Equals to Default Value
	 * @throws InvalidCodeException
	 */
	@Test 
	public void CCtoString() throws InvalidCodeException {
		CCTest = new ContainerCode(C_CODE);
		assertEquals(CCTest.toString(),C_CODE);
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#equals(Object)}
	 * Test toString whether Equals to Default Value
	 * @throws InvalidCodeException
	 */
	@Test 
	public void CCequals() throws InvalidCodeException {
		CCTest.equals(C_CODE);
		assertTrue(CCTest.equals(C_CODE));
	}
	
	//=================================================================
	//Test For Constructor DangerousGoodsContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test Constructor Default Input
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void DGCConstructorDefaultInput() throws InvalidContainerException, InvalidCodeException{
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,GROSSWEIGHT,CATEGORY);
	}

	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#getCategory()}
	 * Test GetCategoris
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void DGCgetCategories() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,GROSSWEIGHT,CATEGORY);
		assertEquals(DGCTest.getCategory(),CATEGORY);
	}
	
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight LESS THEN 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,2,CATEGORY);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,31,CATEGORY);
	}
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories Less Then 1
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesLessthen1 () throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,GROSSWEIGHT,0);
	}

	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories More Then 9
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesMorethen9 () throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1,GROSSWEIGHT,10);
	}
	
	//=================================================================
	//Test For Constructor FreightContainer Class
		
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()}
	 * Test For GetCode
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetCode() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1,GROSSWEIGHT);
		assertEquals(GGCTest.getCode(),code1);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 * Test For GetGrossWeight
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetGrossWeight() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1,GROSSWEIGHT);
		assertEquals(GGCTest.getGrossWeight(),GROSSWEIGHT);
	}
	//=================================================================
	//Test For Constructor GeneralGoodsContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Constructor Default Input
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void GGCConstructorDefaultInput() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1,GROSSWEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight If Less Then 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1,3);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight If More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1,31);
	}
	
	//=================================================================
	//Test For Constructor ReFrigeratedContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For Constructor Default Input
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCConstructorDefaultInput() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1,GROSSWEIGHT,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight Less Then 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1,3,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1,31,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#getTemperature()}
	 * Test For getTemperature
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCGetTemperature() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1,GROSSWEIGHT,TEMPERATURE);
		assertEquals(RCTest.getTemperature(),TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#setTemperature(Integer)}
	 * Test For Set Temperature and getTemperature
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCSetTemperature() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1,GROSSWEIGHT,TEMPERATURE);
		Integer temperature = 7;
		RCTest.setTemperature(temperature);
		assertTrue(RCTest.getTemperature() == temperature);
		}
	
}
