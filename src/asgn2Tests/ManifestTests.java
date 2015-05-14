/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
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

/**
 * Defines unit tests for various use cases of the CargoManifest class.
 * 
 * @author Thanat Chokwijitkul n9234900
 *
 */
public class ManifestTests {

	private static final Integer NUM_STACKS = 3;
	private static final Integer MAX_HEIGHT = 3;
	private static final Integer MAX_WEIGHT = 100;
	private static final Integer GROSS_WEIGHT = 10;
	private static final Integer MAX_GROSS_WEIGHT = 30;
	private static final Integer CATEGORY = 1;
	private static final Integer TEMPERATURE = -10;
	private static final Integer NEGATIVE_VALUE = -1;
	
	private static final String CONTAINER_CODE_1 = "INKU2633836";
	private static final String CONTAINER_CODE_2 = "KOCU8090115";
	private static final String CONTAINER_CODE_3 = "MSCU6639871";
	private static final String CONTAINER_CODE_4 = "CSQU3054389";
	private static final String CONTAINER_CODE_5 = "QUTU7200318";
	private static final String CONTAINER_CODE_6 = "IBMU4882351";
	
	private CargoManifest manifest;
	private CargoManifest invalidManifest; // This CargoManifest object is only used for the constructor tests.
	
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
		invalidManifest = new CargoManifest(NEGATIVE_VALUE, MAX_HEIGHT, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the maximum height value of each stack.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeMaxHeight() throws ManifestException {
		invalidManifest = new CargoManifest(NUM_STACKS, NEGATIVE_VALUE, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the maximum weight of containers allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeMaxWeight() throws ManifestException {
		invalidManifest = new CargoManifest(NUM_STACKS, MAX_HEIGHT, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks and the maximum height value of each stack.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeNumStacksAndMaxHeight() throws ManifestException {
		invalidManifest = new CargoManifest(NEGATIVE_VALUE, NEGATIVE_VALUE, MAX_WEIGHT);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}.
	 * Confirm that the constructor throws an exception if given negative value
	 * for the number of stacks and the maximum weight of containers allowed on board.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeNumStacksAndMaxWeight() throws ManifestException {
		invalidManifest = new CargoManifest(NEGATIVE_VALUE, MAX_HEIGHT, NEGATIVE_VALUE);
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
		invalidManifest = new CargoManifest(NUM_STACKS, NEGATIVE_VALUE, NEGATIVE_VALUE);
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
		invalidManifest = new CargoManifest(NEGATIVE_VALUE, NEGATIVE_VALUE, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Confirm that the method throws an exception if trying to load a null container.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 */
	@Test (expected = ManifestException.class)
	public void nullContainer() throws ManifestException, InvalidContainerException {
		FreightContainer generalGoodsContainer1 = null;
		manifest.loadContainer(generalGoodsContainer1);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Confirm that the method throws an exception if adding a new container that exceed 
	 * maximum weight limit.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class)
	public void exceedMaxWeightLimit() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, MAX_GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, MAX_GROSS_WEIGHT);
		FreightContainer generalGoodsContainer3 = new GeneralGoodsContainer(code3, MAX_GROSS_WEIGHT);
		FreightContainer generalGoodsContainer4 = new GeneralGoodsContainer(code4, MAX_GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(generalGoodsContainer3);
		manifest.loadContainer(generalGoodsContainer4);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Confirm that the method throws an exception if adding a new container with the same code 
	 * as one of the containers on board.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class)
	public void loadContainerWithTheSameCodeAsContainerOnBoard() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#loadContainer(FreightContainer)}.
	 * Confirm that the method throws an exception if there is no space available for 
	 * a new container to be loaded.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class)
	public void noSpaceForANewContainer() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		ContainerCode code5 = new ContainerCode(CONTAINER_CODE_5);
		ContainerCode code6 = new ContainerCode(CONTAINER_CODE_6);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code2, GROSS_WEIGHT);
		FreightContainer generalGoodsContainer3 = new GeneralGoodsContainer(code3, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code4, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer dangerousGoodsContainer1 = new DangerousGoodsContainer(code5, GROSS_WEIGHT, CATEGORY);
		FreightContainer generalGoodsContainer4 = new GeneralGoodsContainer(code6, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(generalGoodsContainer3);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(dangerousGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer4);
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
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);;
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
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#unloadContainer(ContainerCode)}.
	 * Confirm that the method throws an exception if trying to unload a container
	 * given a null container code.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class) 
	public void uncloadContainerGivenNullContainerCode() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = null;
		manifest.unloadContainer(code1);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#unloadContainer(ContainerCode)}.
	 * Confirm that the method throws an exception if trying to unload a container
	 * which is currently not on board.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class)
	public void containerIsNotOnboard() 
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.unloadContainer(code2);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#unloadContainer(ContainerCode)}.
	 * Confirm that the method throws an exception if trying to unload an unaccessible
	 * container i.e. the specified container is not on the top of the stack.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test (expected = ManifestException.class)
	public void unloadUnaccessibleContainer() 
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
		manifest.unloadContainer(code1);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#unloadContainer(ContainerCode)}.
	 * Confirm that the method unloads the correct container specified b
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void successfullyUnloadContainer()
		   throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code2, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer dangerousGoodsContainer1 = new DangerousGoodsContainer(code3, GROSS_WEIGHT, CATEGORY);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(dangerousGoodsContainer1);
		Integer expectedStackDGC = 2;
		assertEquals(expectedStackDGC, manifest.whichStack(code3));
		manifest.unloadContainer(code3);
		expectedStackDGC = null;
		assertEquals(expectedStackDGC, manifest.whichStack(code3));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#whichStack(ContainerCode)}.
	 * Confirm that the method returns null if given a null container query.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void findWhichStackWithNullQueryContainer() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = null;
		Integer expectedStack = null;
		assertEquals(expectedStack, manifest.whichStack(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#whichStack(ContainerCode)}.
	 * Confirm that the method returns null if the manifest is empty.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void findWhichStackOnEmptyManifest() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		Integer expectedStack = null;
		assertEquals(expectedStack, manifest.whichStack(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#whichStack(ContainerCode)}.
	 * Confirm that the method returns the correct stack that contains the specified
	 * container.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 * @throws InvalidContainerException 
	 */
	@Test 
	public void findCorrectStackOnManifest() 
			throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code2, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer refrigeratedContainer2 = new RefrigeratedContainer(code3, GROSS_WEIGHT, TEMPERATURE);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(refrigeratedContainer2);
		Integer expectedStack = 1;
		assertEquals(expectedStack, manifest.whichStack(code3));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#howHigh(ContainerCode)}.
	 * Confirm that the method returns null if given a null container query.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void findHowHeightWithNullQueryContainer() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = null;
		Integer expectedHeight = null;
		assertEquals(expectedHeight, manifest.howHigh(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#howHigh(ContainerCode)}.
	 * Confirm that the method returns null if the manifest is empty.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test 
	public void findHowHeightOnEmptyManifest() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		Integer expectedHeight = null;
		assertEquals(expectedHeight, manifest.howHigh(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#whichStack(ContainerCode)}.
	 * Confirm that the method returns the correct height value of the specified
	 * container.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 * @throws InvalidContainerException 
	 */
	@Test 
	public void findCorrectContainerHeight() 
			throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code2, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer refrigeratedContainer2 = new RefrigeratedContainer(code3, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer refrigeratedContainer3 = new RefrigeratedContainer(code4, GROSS_WEIGHT, TEMPERATURE);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(refrigeratedContainer2);
		manifest.loadContainer(refrigeratedContainer3);
		Integer expectedHeight = 2;
		assertEquals(expectedHeight, manifest.howHigh(code4));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toArray(Integer)}.
	 * Confirm that the method throws an exception if given a negative stack number.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void negativeStackNumber() throws ManifestException {
		manifest.toArray(NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toArray(Integer)}.
	 * Confirm that the method throws an exception if given an excessive stack number.
	 * @throws ManifestException
	 */
	@Test (expected = ManifestException.class)
	public void excessiveStackNumber() throws ManifestException {
		manifest.toArray(NUM_STACKS);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toArray(Integer)}.
	 * Confirm that the method returns an empty array if the specified stack
	 * is empty.
	 * @throws ManifestException
	 */
	@Test
	public void emptyStackAsEmptyArray() throws ManifestException {
		Integer expectedStack = 0;
		assertTrue(manifest.toArray(expectedStack).length == 0);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toArray(Integer)}.
	 * Confirm that the method returns correct contents of a particular stack
	 * as an array, starting with the bottommost container at position zero in 
	 * the array.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void correctStackContentsAsArray() 
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
		FreightContainer[] expectedArray = new FreightContainer[MAX_HEIGHT];
		Integer expectedStack = 0;
		expectedArray[0] = generalGoodsContainer1;
		expectedArray[1] = generalGoodsContainer2;
		expectedArray[2] = generalGoodsContainer3;
		assertEquals(expectedArray[0], manifest.toArray(expectedStack)[0]);
		assertEquals(expectedArray[1], manifest.toArray(expectedStack)[1]);
		assertEquals(expectedArray[2], manifest.toArray(expectedStack)[2]);
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toString()}.
	 * Confirm that the method returns correct formatted string if the given
	 * container code is null.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void nullManifestToString() throws ManifestException, InvalidCodeException {
		String expectedString = "||  ||\n" +
								"||  ||\n" +
								"||  ||\n";
		assertEquals(expectedString, manifest.toString());
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toString(ContainerCode)}.
	 * Confirm that the method returns correct formatted string given a container code
	 * but the manifest is currently empty.
	 * @throws ManifestException
	 * @throws InvalidCodeException 
	 */
	@Test
	public void emptyManifestToString() throws ManifestException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		String expectedString = "||  ||\n" +
								"||  ||\n" +
								"||  ||\n";
		assertEquals(expectedString, manifest.toString(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toString(ContainerCode)}.
	 * Confirm that the method returns correct formatted string if the manifest
	 * only contain one container;
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void simpleManifestToString() 
			throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		manifest.loadContainer(generalGoodsContainer1);
		String expectedString = "||*" + code1 + "*||\n" +
								"||  ||\n" +
								"||  ||\n";
		assertEquals(expectedString, manifest.toString(code1));
	}
	
	/**
	 * Test method for {@link asgn2Manifests.CargotManifest#toString(ContainerCode)}.
	 * Confirm that the method returns correct formatted string.
	 * @throws ManifestException
	 * @throws InvalidContainerException 
	 * @throws InvalidCodeException 
	 */
	@Test
	public void manifestToString() 
			throws ManifestException, InvalidContainerException, InvalidCodeException {
		ContainerCode code1 = new ContainerCode(CONTAINER_CODE_1);
		ContainerCode code2 = new ContainerCode(CONTAINER_CODE_2);
		ContainerCode code3 = new ContainerCode(CONTAINER_CODE_3);
		ContainerCode code4 = new ContainerCode(CONTAINER_CODE_4);
		ContainerCode code5 = new ContainerCode(CONTAINER_CODE_5);
		FreightContainer generalGoodsContainer1 = new GeneralGoodsContainer(code1, GROSS_WEIGHT);
		FreightContainer refrigeratedContainer1 = new RefrigeratedContainer(code2, GROSS_WEIGHT, TEMPERATURE);
		FreightContainer dangerousGoodsContainer1 = new DangerousGoodsContainer(code3, GROSS_WEIGHT, CATEGORY);
		FreightContainer generalGoodsContainer2 = new GeneralGoodsContainer(code4, GROSS_WEIGHT);
		FreightContainer dangerousGoodsContainer2 = new DangerousGoodsContainer(code5, GROSS_WEIGHT, CATEGORY);
		manifest.loadContainer(generalGoodsContainer1);
		manifest.loadContainer(refrigeratedContainer1);
		manifest.loadContainer(dangerousGoodsContainer1);
		manifest.loadContainer(generalGoodsContainer2);
		manifest.loadContainer(dangerousGoodsContainer2);
		String expectedString = "|| " + code1 + " || " + code4 + " ||\n" + 
								"|| " + code2 + " ||\n" + 
								"|| " + code3 + " ||*" + code5 + "*||\n";
		assertEquals(expectedString, manifest.toString(code5));
	}
	
}
