
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
	private static int GROSSWEIGHT = 5;
	private static int CATEGORY = 5;
	
	//Before
	@Before
	public void setUP() throws InvalidContainerException {
		DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
		FCTest = new FreightContainer(CODE,GROSSWEIGHT);
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
	 * Test For InvalidGrossWeight
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void  DGCinvalidGrossWeight() throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,2,CATEGORY);
	}


	/**
	 * Test method for {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 * Test For Invalid Categories
	 * @throws InvalidContainerException
	 */
	@Test (expected = InvalidContainerException.class)
	public void DGCinvalidCategories () throws InvalidContainerException {
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,10);
	}
	
	//=================================================================
	//Test For Constructor FreightContainer Class
	
	@Test (expected = InvalidContainerException.class)
	public void FCConstructorDefaultInput() throws InvalidContainerException {
		FreightContainer FCTest = new FreightContainer(CODE,GROSSWEIGHT);
	}
}
