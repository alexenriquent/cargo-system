
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
	
	
	private asgn2Containers.DangerousGoodsContainer DGCTest;
	private asgn2Containers.FreightContainer FCTest;
	private asgn2Containers.GeneralGoodsContainer GGCTest;
	private asgn2Containers.RefrigeratedContainer RCTest;
	
	
	//Define Static
	private static final ContainerCode CODE = null;
	private static Integer GROSSWEIGHT = 5;
	private static Integer CATEGORY = 5;
	private static Integer TEMPERATURE = 5;
	
	
	//Before
	@Before
	public void setUP() throws InvalidContainerException {
		DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
		FCTest = new FreightContainer(CODE,GROSSWEIGHT);
		GGCTest = new GeneralGoodsContainer(CODE,GROSSWEIGHT);
		RCTest = new RefrigeratedContainer(CODE,GROSSWEIGHT,TEMPERATURE);
	}
	
	//=================================================================
	//Test For Constructor DangerousGoodsContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test Constructor Default Input
	 * @throws InvalidContainerException
	 */
	@Test
	public void DGCConstructorDefaultInput() throws InvalidContainerException{
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
	}

	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#getCategory()}
	 * Test GetCategoris
	 * @throws InvalidContainerException
	 */
	@Test
	public void DGCgetCategories() throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
		assertEquals(DGCTest.getCategory(),CATEGORY);
	}
	
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight LESS THEN 4
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightLessThen4() throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,2,CATEGORY);
	}
	
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight More Then 30
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeightMoreThen30() throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,31,CATEGORY);
	}
	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories Less Then 1
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesLessthen1 () throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,0);
	}

	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories More Then 9
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategoriesMorethen9 () throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,10);
	}
	
	//=================================================================
	//Test For Constructor FreightContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * Test For Constructor Default Input
	 * @throws InvalidContainerException
	 */
	@Test 
	public void FCConstructorDefaultInput() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,GROSSWEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * Test For Constructor Less Then 4
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void FCConstructorInvalidGrossWeightLessThen4() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,3);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * Test For Constructor More Then 30
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void FCConstructorInvalidGrossWeightLessMoreThen30() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,31);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * Test For GetCode
	 * @throws InvalidContainerException
	 */
	@Test 
	public void FCgetCode() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,GROSSWEIGHT);
		assertEquals(FCTest.getCode(),CODE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * Test For GetCode
	 * @throws InvalidContainerException
	 */
	@Test 
	public void FCgetGrossWeight() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,GROSSWEIGHT);
		assertEquals(FCTest.getGrossWeight(),GROSSWEIGHT);
	}
	//=================================================================
	//Test For Constructor GeneralGoodsContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Constructor Default Input
	 * @throws InvalidContainerException
	 */
	@Test
	public void GGCConstructorDefaultInput() throws InvalidContainerException {
		GeneralGoodsContainer GGCTest = new GeneralGoodsContainer(CODE,GROSSWEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight If Less Then 4
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightLessThen4() throws InvalidContainerException {
		GeneralGoodsContainer GGCTest = new GeneralGoodsContainer(CODE,3);
	}
	
	/**
	 * Test method for {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 * Test For Invalid GrossWeight If More Then 30
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void GGCInvalidGrossWeightMoreThen30() throws InvalidContainerException {
		GeneralGoodsContainer GGCTest = new GeneralGoodsContainer(CODE,31);
	}
	
	//=================================================================
	//Test For Constructor ReFrigeratedContainer Class
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For Constructor Default Input
	 * @throws InvalidContainerException
	 */
	@Test
	public void RCConstructorDefaultInput() throws InvalidContainerException {
		RefrigeratedContainer RCTest = new RefrigeratedContainer(CODE,GROSSWEIGHT,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight Less Then 4
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightLessThen4() throws InvalidContainerException {
		RefrigeratedContainer RCTest = new RefrigeratedContainer(CODE,3,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For InvalidGrossWeight Less Then 4
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void RCConstructorInvalidGrossWeightMoreThen30() throws InvalidContainerException {
		RefrigeratedContainer RCTest = new RefrigeratedContainer(CODE,31,TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For getTemperature
	 * @throws InvalidContainerException
	 */
	@Test
	public void RCGetTemperature() throws InvalidContainerException {
		RefrigeratedContainer RCTest = new RefrigeratedContainer(CODE,GROSSWEIGHT,TEMPERATURE);
		assertEquals(RCTest.getTemperature(),TEMPERATURE);
	}
	
	/**
	 * Test method for {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 * Test For Set Temperature and getTemperature
	 * @throws InvalidContainerException
	 */
	@Test
	public void RCSetTemperature() throws InvalidContainerException {
		Integer temperature = 7;
		RCTest.setTemperature(temperature);;
		assertTrue(RCTest.getTemperature()==temperature);
		}
}
