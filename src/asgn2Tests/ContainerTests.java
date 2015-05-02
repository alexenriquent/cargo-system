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
	
	//Implementation Here - includes tests for ContainerCode and for the actual container classes. 
	private asgn2Containers.DangerousGoodsContainer DGCTest;
	private asgn2Containers.FreightContainer FCTest;
	private asgn2Containers.GeneralGoodsContainer GGCTest;
	private asgn2Containers.RefrigeratedContainer RCTest;
	
	
	//Define Static
	private static final ContainerCode CODE = null;
	private static final int GROSSWEIGHT = 5;
	private static final int CATEGORY = 5;
	
	//Before
	@Before
	public void setUP() throws InvalidContainerException {
		DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
	}
	
	//=================================================================
	//Test For Constructor DangerousGoodsContainer(ContainerCode , int  , int)
	
	@Test
	public void ConstructorDefaultInput() throws InvalidContainerException{
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,CATEGORY);
	}
	
	
	@Test (expected = InvalidContainerException.class)
	public void ConstructorNullCodeInput() throws InvalidContainerException{
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(null,GROSSWEIGHT,CATEGORY);
	}
	
	@Test (expected = InvalidContainerException.class)
	public void ConstructorNullGrossweightInput() throws InvalidContainerException{
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,null,CATEGORY);
	}
	
	@Test (expected = InvalidContainerException.class)
	public void ConstructorNullCategoryInput() throws InvalidContainerException{
		DangerousGoodsContainer DGCTest = new DangerousGoodsContainer(CODE,GROSSWEIGHT,null);
	}
	
}
