package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054387
 * QUTU7200318
 * IBMU4882351
 */

import org.junit.Before;
import org.junit.Test;
import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;
import static org.junit.Assert.*;

public class ManifestTests {

	private static final int NUM_STACKS = 5;
	private static final int MAX_HEIGHT = 3;
	private static final int MAX_WEIGHT = 100;
	private static final Integer GROSS_WEIGHT = 10;
	private static final Integer CATEGORY = 1;
	private static final Integer TEMPERATURE = -10;
	private static final int NEGATIVE_VALUE = -1;
	
	private static final String CONTAINER_CODE_1 = "INKU2633836";
	private static final String CONTAINER_CODE_2 = "KOCU8090115";
	private static final String CONTAINER_CODE_3 = "MSCU6639871";
	private static final String CONTAINER_CODE_4 = "CSQU3054387";
	private static final String CONTAINER_CODE_5 = "QUTU7200318";
	
	
	private CargoManifest manifest;
	
	/**
	 * Create a CargoManifest object before each test case 
	 * @throws ManifestException
	 */
	@Before 
	public void setUp() throws ManifestException {
		manifest = new CargoManifest(NUM_STACKS, MAX_HEIGHT, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeNumStacks() throws ManifestException {
		CargoManifest negNumStacks = new CargoManifest(NEGATIVE_VALUE, MAX_HEIGHT, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the maximum height value of each stack.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeMaxHeight() throws ManifestException {
		CargoManifest negMaxHeight = new CargoManifest(NUM_STACKS, NEGATIVE_VALUE, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the maximum weight of containers allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeMaxWeight() throws ManifestException {
		CargoManifest negMaxWeights = new CargoManifest(NUM_STACKS, MAX_HEIGHT, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks and the maximum height value of each stack.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeNumStacksAndMaxHeight() throws ManifestException {
		CargoManifest negNumStacksAndMaxHeight = new CargoManifest(NEGATIVE_VALUE, NEGATIVE_VALUE, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks and the maximum weight of containers allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeNumStacksAndMaxWeight() throws ManifestException {
		CargoManifest negNumStacksAndMaxWeightt = new CargoManifest(NEGATIVE_VALUE, MAX_HEIGHT, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the maximum height value of each stack and the maximum weight of containers 
	 * allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeMaxHeightAndMaxWeight() throws ManifestException {
		CargoManifest negMaxHeightAndMaxWeight = new CargoManifest(NUM_STACKS, NEGATIVE_VALUE, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks, the maximum height value of each stack and the maximum 
	 * weight of containers allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void cargoManifestWithNegativeValues() throws ManifestException {
		CargoManifest manifaestWithNegValues = new CargoManifest(NEGATIVE_VALUE, NEGATIVE_VALUE, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the first container loaded to the manifest is in the correct
	 * position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadFirstContainer() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		FreightContainer generalGoodsContainer = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer);
		Integer expectedHeightGGC = 0;
		Integer expectedStackGGC = 0;
		assertEquals(expectedHeightGGC, manifest.howHigh(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with the same category loaded to the manifest are in the correct
	 * position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadSameTypeContainers() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGGC2 = 1;
		Integer expectedStackGGC = 0;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGGC2, manifest.howHigh(code2));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code2));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with the same category loaded to the maximum height of the first stack 
	 * are in the correct position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadSameTypeContainersToMaxHeight() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer3 = new GeneralGoodsContainer(code3, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(generalGoodsContainer3);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGGC2 = 1;
		Integer expectedHeightGGC3 = 2;
		Integer expectedStackGGC = 0;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGGC2, manifest.howHigh(code2));
		assertEquals(expectedHeightGGC3, manifest.howHigh(code3));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code2));
		assertEquals(expectedStackGGC, manifest.whichStack(code3));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with the same category loaded to the maximum height of the first stack 
	 * are in the correct position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadSameTypeContainersToMaxHeightAndBeginANewStack() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer3 = new GeneralGoodsContainer(code3, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer4 = new GeneralGoodsContainer(code4, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(generalGoodsContainer3);
		manifest.loadContainer(generalGoodsContainer4);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGGC2 = 1;
		Integer expectedHeightGGC3 = 2;
		Integer expectedHeightGGC4 = 0;
		Integer expectedStackGGC1 = 0;
		Integer expectedStackGGC2 = 1;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGGC2, manifest.howHigh(code2));
		assertEquals(expectedHeightGGC3, manifest.howHigh(code3));
		assertEquals(expectedHeightGGC4, manifest.howHigh(code4));
		assertEquals(expectedStackGGC1, manifest.whichStack(code1));
		assertEquals(expectedStackGGC1, manifest.whichStack(code2));
		assertEquals(expectedStackGGC1, manifest.whichStack(code3));
		assertEquals(expectedStackGGC2, manifest.whichStack(code4));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with two different categories loaded to the manifest are in the correct
	 * position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadTwoDifferentTypeContainers() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code3, GROSS_WEIGHT, TEMPERATURE);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(refrigeratedContainer1);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGGC2 = 1;
		Integer expectedHeightRC1 = 0;
		Integer expectedStackGGC = 0;
		Integer expectedStackRC = 1;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGGC2, manifest.howHigh(code2));
		assertEquals(expectedHeightRC1, manifest.howHigh(code3));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code2));
		assertEquals(expectedStackRC, manifest.whichStack(code3));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with three different categories loaded to the manifest are in the correct
	 * position.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void loadThreeDifferentTypeContainers() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code3, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer dangerousGoodsContainer1 = new DangerousGoodsContainer(code4, GROSS_WEIGHT, CATEGORY);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(dangerousGoodsContainer1);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGCC2 = 1;
		Integer expectedHeightRC1 = 0;
		Integer expectedHeightDGC1 = 0;
		Integer expectedStackGGC = 0;
		Integer expectedStackRC = 1;
		Integer expectedStackDGC = 2;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGCC2, manifest.howHigh(code2));
		assertEquals(expectedHeightRC1, manifest.howHigh(code3));
		assertEquals(expectedHeightDGC1, manifest.howHigh(code4));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code2));
		assertEquals(expectedStackRC, manifest.whichStack(code3));
		assertEquals(expectedStackDGC, manifest.whichStack(code4));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Test that the containers with three different categories loaded to the manifest are in the correct
	 * position, and the method can find the correct stack for a new container of an existing goods category
	 * on board.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException
	 */
	@Test
	public void findRightStackForNewContainerOfExistingCategory() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		ContainerCode code5 = new ContainerCode(CONTAINER_CODE_5);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code3, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer dangerousGoodsContainer1 = new DangerousGoodsContainer(code4, GROSS_WEIGHT, CATEGORY);
		FreightContainer dangerousGoodsContainer2 = new DangerousGoodsContainer(code5, GROSS_WEIGHT, CATEGORY);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(dangerousGoodsContainer1);
		manifest.loadContainer(dangerousGoodsContainer2);
		Integer expectedHeightGGC1 = 0;
		Integer expectedHeightGCC2 = 1;
		Integer expectedHeightRC1 = 0;
		Integer expectedHeightDGC1 = 0;
		Integer expectedHeightDGC2 = 1;
		Integer expectedStackGGC = 0;
		Integer expectedStackRC = 1;
		Integer expectedStackDGC = 2;
		assertEquals(expectedHeightGGC1, manifest.howHigh(code1));
		assertEquals(expectedHeightGCC2, manifest.howHigh(code2));
		assertEquals(expectedHeightRC1, manifest.howHigh(code3));
		assertEquals(expectedHeightDGC1, manifest.howHigh(code4));
		assertEquals(expectedHeightDGC2, manifest.howHigh(code5));
		assertEquals(expectedStackGGC, manifest.whichStack(code1));
		assertEquals(expectedStackGGC, manifest.whichStack(code2));
		assertEquals(expectedStackRC, manifest.whichStack(code3));
		assertEquals(expectedStackDGC, manifest.whichStack(code4));
		assertEquals(expectedStackDGC, manifest.whichStack(code5));
	}
}
