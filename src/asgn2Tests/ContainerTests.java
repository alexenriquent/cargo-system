/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;

/**
 * Defines unit tests for use cases of the <code>ContainerCode</code> class and
 * all of the container classes, including <code>FreightContainer<code>,
 * <code>DangerousGoodsContainer</code>, <code>GeneralGoodsContainer</code>
 * and <code>refrigeratedContainer</code>.
 * 
 * @author Month Yean KOH n9070095
 */
public class ContainerTests {

	//Define Static
	private static final Integer GROSSWEIGHT = 5;
	private static final Integer CATEGORY = 5;
	private static final Integer TEMPERATURE = 5;
	
	private static final String CONTAINER_CODE_1 = "INKU2633836";
	private static final String CONTAINER_CODE_2 = "KOCU8090115";
	private static final String CONTAINER_CODE_3 = "MSCU6639871";
	private static final String CONTAINER_CODE_4 = "CSQU3054387";
	private static final String CONTAINER_CODE_5 = "QUTU7200318";
	private static final String CONTAINER_CODE_6 = "IBMU4882351";
	
	private ContainerCode code1;
	private ContainerCode code2;
	
	private ContainerCode CCTest;
	private DangerousGoodsContainer DGCTest;
	private GeneralGoodsContainer GGCTest;
	private RefrigeratedContainer RCTest;

	//Before
	/**
	 * Build a Object before each test case
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException
	 */
	@Before
	public void setUp() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		code2 = new ContainerCode(CONTAINER_CODE_2);
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
		 CCTest = new ContainerCode(CONTAINER_CODE_1);
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Length  More then 11 Character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCodeLength() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338376");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Length is Less then 11 Character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCodeLengthLessThen11() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU263376");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Does Not Consist of three Upper-case character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidOwnerUpperCaseCode() throws InvalidCodeException {
		CCTest = new ContainerCode("InkU2633836");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode Owner Code Does Not Consist of three Upper-case character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidOwnerCode() throws InvalidCodeException {
		CCTest = new ContainerCode("INkU2633836");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode Owner Code Does Not Consist of three character
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidOwnerCodeWithDigit() throws InvalidCodeException {
		CCTest = new ContainerCode("I23U2633836");
	}
	
	/**
	 * Test method for {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode have No Owner Code
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void ContainerCodeMissingOwnerCode() throws InvalidCodeException {
		CCTest = new ContainerCode("U2633839");
	}
	
	/**
	 * Test method for {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode have not Identifier 
	 * @throws InvalidCodeException
	 */
	@Test(expected = InvalidCodeException.class)
	public void ContainerCodeMissingCatergoryIdentifier() throws InvalidCodeException {
		CCTest = new ContainerCode("INK2633836");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Category Identifier is Not 'U'
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCategoryIdentifier() throws InvalidCodeException {
		CCTest = new ContainerCode("INKX2633836");
		
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Category Identifier is Not Upper Case 'U'
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidCategoryIdentifiersmallercase() throws InvalidCodeException {
		CCTest = new ContainerCode("INKu2633836");
	}
	
	/**
	 * Test method for {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode Have empty code
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void ContainerCodeEmptyString() throws InvalidCodeException {
		CCTest = new ContainerCode("");
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If ContainerCode Serial number have no Digit
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void ContainerCodeMissingSerialCheckDigit() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Serial Number Does not consist of six digit
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidSerialNumber() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338A6");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * IF ContainerCode Serial Number Does not consist of six digit and a Case In Between
	 * @throws InvalidCodeException
	 */
	@Test (expected = InvalidCodeException.class)
	public void CCInvalidSerialNumberAndaCaseinBetween() throws InvalidCodeException {
		CCTest = new ContainerCode("INKU26338A67A");
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#ContainerCode(String)}
	 * Confirm that the method throws an exception 
	 * If More then 6 Digit
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
		CCTest = new ContainerCode(CONTAINER_CODE_1);
		assertEquals(CCTest.toString(),CONTAINER_CODE_1);
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#toString()}
	 * Test toString Whether is not the same with Another Code
	 * @throws InvalidCodeException
	 */
	@Test 
	public void CCtoStringFalse() throws InvalidCodeException {
		CCTest = new ContainerCode(CONTAINER_CODE_1);
		assertFalse(CCTest.toString() == CONTAINER_CODE_2);
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#equals(Object)}
	 * Test Equal whether there are same or not with another COde
	 * @throws InvalidCodeException
	 */
	@Test 
	public void CCequals() throws InvalidCodeException {
		CCTest = new ContainerCode(CONTAINER_CODE_1);
		CCTest.equals(CONTAINER_CODE_1);
		assertTrue(CCTest.equals(CONTAINER_CODE_1));
		assertFalse(CCTest.equals(CONTAINER_CODE_2));
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#equals(Object)}
	 * Test if Two ContainerCode Are Not Equal
	 * @throws InvalidCodeException
	 */
	@Test
	public void ContainerCodeEqualsFalse() throws InvalidCodeException {
		ContainerCode CCTest = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode CCTest2 = new ContainerCode(CONTAINER_CODE_2);
		assertFalse(CCTest.equals(CCTest2));
	}
	
	/**
	 * Test method For {@link asgn2Codes.ContainerCode#equals(Object)}
	 * Test the Method with Checking Whether if Two ContainerCode Are Equal or Not
	 * @throws InvalidCodeException
	 */
	@Test
	public void ContainerCodeEqualsTrue() throws InvalidCodeException {
		ContainerCode CCTest = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode CCTest2 = new ContainerCode(CONTAINER_CODE_1);
		assertTrue(CCTest.equals(CCTest2));
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
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, GROSSWEIGHT, CATEGORY);
	}

	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#getCategory()}
	 * Test GetCategoris
	 * Test If Both Categories Value Are the Same
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void DGCgetCategories() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, GROSSWEIGHT, CATEGORY);
		assertEquals(DGCTest.getCategory(), CATEGORY);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#getCategory()}
	 * Test Get False Categories
	 * Test If Both Value are not The Same
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void DGCgetFalseCategories() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, GROSSWEIGHT, CATEGORY);
		assertFalse(DGCTest.getCategory() == 8);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight 
	 * Confirm that the method throws an exception IF Gross Weight Less Then 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, 2, CATEGORY);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight 
	 * Confirm that the method throws an exception If GrossWeight More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, 31, CATEGORY);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories
	 * Confirm that the method throws an exception If Categories Less Then 1
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesLessthen1 () throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, GROSSWEIGHT,0);
	}

	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories 
	 * Confirm that the method throws an exception If Categories More Then 9
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesMorethen9 () throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		DGCTest = new DangerousGoodsContainer(code1, GROSSWEIGHT,10);
	}
	
	//=================================================================
	//Test For Constructor FreightContainer Class
		
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()}
	 * Test For GetCode
	 * Test Whether Both of the Code Have The Same Code
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetCode() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, GROSSWEIGHT);
		assertEquals(GGCTest.getCode(), code1);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()}
	 * Test For GetCode
	 * Test If Both Of The Code Are Not Same.
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetFalseCode() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, GROSSWEIGHT);
		assertFalse(GGCTest.getCode() == code2);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 * Test For GetGrossWeight
	 * Test if Both of the value are the same.
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetGrossWeight() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, GROSSWEIGHT);
		assertEquals(GGCTest.getGrossWeight(), GROSSWEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 * Test For GetGrossWeight 
	 * Test if Gross weight are not Equal 
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void FCgetFalseGrossWeight() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, GROSSWEIGHT);
		assertFalse(GGCTest.getGrossWeight() == 8);
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
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, GROSSWEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight 
	 * Test If Gross Weight Less Then 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, 3);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight 
	 * Test If Gross Weight More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		GGCTest = new GeneralGoodsContainer(code1, 31);
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
		code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, GROSSWEIGHT, TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight 
	 * Test If  Gross Weight Less Then 4
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightLessThen4() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, 3, TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight 
	 * Test If Gross Weight More Then 30
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightMoreThen30() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, 31, TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#getTemperature()}
	 * Test For getTemperature 
	 * Test Whether both of them are the same.
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCGetTemperature() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, GROSSWEIGHT, TEMPERATURE);
		assertEquals(RCTest.getTemperature(), TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#getTemperature()}
	 * Test For get False Temperature
	 * Test if the Temperature are not the same
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCGetFalseTemperature() throws InvalidContainerException, InvalidCodeException {
		code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, GROSSWEIGHT, TEMPERATURE);
		assertFalse(RCTest.getTemperature() == 8);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#setTemperature(Integer)}
	 * Test For Set Temperature and getTemperature
	 * Test if both of them are Equal
	 * @throws InvalidContainerException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void RCSetTemperature() throws InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		RCTest = new RefrigeratedContainer(code1, GROSSWEIGHT, TEMPERATURE);
		Integer temperature = 7;
		RCTest.setTemperature(temperature);
		assertTrue(RCTest.getTemperature() == temperature);
		}
	
}
