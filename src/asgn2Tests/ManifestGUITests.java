/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import javax.swing.JFrame;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asgn2GUI.CargoFrame;
import asgn2GUI.CargoTextFrame;

/**
 * Defines FEST Swing tests for various scenarios of the Cargo System.
 * This test class uses the CargoFrameTests class created by Malcolm 
 * as a template. 
 * 
 * @author Thanat Chokwijitkul n9234900
 * @author Month Yean Koh n9070095
 */
public class ManifestGUITests {
	
	private static final String CARGO_TEXT_AREA = "Cargo Text Area";	
    private static final String NEW_MANIFEST = "New Manifest";
    private static final String LOAD = "Load";
    private static final String UNLOAD = "Unload";
	private static final String FIND = "Find";
	private static final String OK = "OK";
	private static final String CANCEL = "Cancel";
	
    private static final String NUMBER_OF_STACKS = "Number of Stacks";
    private static final String MAXIMUM_HEIGHT = "Maximum Height";
    private static final String MAXIMUM_WEIGHT = "Maximum Weight";
    
    private static final String CONTAINER_INFORMATION = "Container Information";
    private static final String CONTAINER_CODE = "Container Code";
    private static final String CONTAINER_WEIGHT = "Container Weight";
    private static final String CONTAINER_TYPE = "Container Type";
    private static final String GENERAL_GOODS = "General Goods";
    private static final String REFRIGERATED_GOODS = "Refrigerated Goods";
    private static final String DANGEROUS_GOODS = "Dangerous Goods";
    private static final String GOODS_CATEGORY = "Goods Category";
    private static final String TEMPERATURE2 = "Temperature";

	private static final String CONTAINER_CODE_1 = "INKU2633836";
	private static final String CONTAINER_CODE_2 = "KOCU8090115";
	private static final String CONTAINER_CODE_3 = "MSCU6639871";
	private static final String CONTAINER_CODE_4 = "CSQU3054389";
	private static final String CONTAINER_CODE_5 = "QUTU7200318";
	
	private static final String INVALID_CONTAINER_CODE_1 = "IBMU48823511"; // Invalid length
	private static final String INVALID_CONTAINER_CODE_2 = "1BMU4882351"; // Numeric owner code ("I")
	private static final String INVALID_CONTAINER_CODE_3 = "ABCJ1234564"; // Identifier is not "U"
    private static final String INVALID_CONTAINER_CODE_4 = "ZZZU65A9874"; // Serial number is not numeric ("4")
    private static final String INVALID_CONTAINER_CODE_5 = "JHGU1716761"; // Incorrect check digit ("0")
    
    private static final String STACKS_1 = "1";
    private static final String STACKS_2 = "2";
    private static final String STACKS_3 = "3";
    private static final String STACKS_5 = "5";
    private static final String HEIGHT_1 = "1";
    private static final String HEIGHT_3 = "3";
    private static final String WEIGHT_3 = "3";
    private static final String WEIGHT_10 = "10";
    private static final String WEIGHT_20 = "20";
    private static final String WEIGHT_30 = "30";
    private static final String WEIGHT_31 = "31";
    private static final String WEIGHT_100 = "100";
    private static final String CATEGORY_1 = "1";
    private static final String TEMPERATURE_MINUS_5 = "-5";
    private static final String ZERO = "0";
    private static final String NEGATIVE = "-1";
    private static final String NOT_NUMERIC = "A";
	
	private static final boolean GRAPHIC_VERSION = true;
    private static final int SHORT_PAUSE = 1500;

    private static final Pattern MANIFEST_EXCEPTION_PATTERN = Pattern.compile(".*ManifestException:.+");

    private FrameFixture testFrame;
    private JFrame frameUnderTest;
    
    /**
     * @author Malcolm
     * 
     * Turn on automated check to verify all Swing component updates are done in the Swing Event
     * Dispatcher Thread (EDT). The EDT ensures that the application never loses responsiveness to
     * user gestures.
     */
    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }
    
    /**
     * @author Malcolm
     * 
     * Ensures that frame is launched through FEST to ensure the EDT is used properly. 
     * JUnit runs in its own thread.
     */
    @Before
    public void setUp() {
        if (GRAPHIC_VERSION) {
        	frameUnderTest = GuiActionRunner.execute(new GuiQuery<CargoFrame>() {
                @Override
                protected CargoFrame executeInEDT() {
                    CargoFrame cargo = new CargoFrame("Cargo Manifest 1.0");
                    return cargo;
                }
            });
        } else {
        	frameUnderTest = GuiActionRunner.execute(new GuiQuery<CargoTextFrame>() {
                @Override
                protected CargoTextFrame executeInEDT() {
                    CargoTextFrame cargo = new CargoTextFrame("Cargo Manifest 1.0");
                    return cargo;
                }
            });
        }
        testFrame = new FrameFixture(frameUnderTest);
    }
    
    /**
     * @author Malcolm
     * 
     * Unload the frame and associated resources.
     */
    @After
    public void tearDown() {
        delay(SHORT_PAUSE);
        testFrame.cleanUp();
    }
    
    /**
     * @author Malcolm
     * 
     * Add a delay so that screen status can be viewed between tests.
     *
     * @param milliseconds The amount of time flow which to pause.
     */
    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
    
    /*
     * @author Malcolm
     *      
     * Helper - Brings up a ManifestDilaog for further interaction in tests.
     */
    private DialogFixture prepareManifestDialog() {
        testFrame.button(NEW_MANIFEST).click();
        DialogFixture manifestFixture = testFrame.dialog(NEW_MANIFEST);
        return manifestFixture;
    }

    /*
     * @author Malcolm
     * 
     * Helper - Puts text in the relevant text areas of the ManifestDialog.
     */
    private void manifestDialogEnterText(DialogFixture dialog, String stacks, String height, String weight) {
        if (stacks != null) {
            dialog.textBox(NUMBER_OF_STACKS).enterText(stacks);
        }
        if (height != null) {
            dialog.textBox(MAXIMUM_HEIGHT).enterText(height);
        }
        if (weight != null) {
            dialog.textBox(MAXIMUM_WEIGHT).enterText(weight);
        }
        dialog.button(OK).click();
    }
    
    /*
     * @author Malcolm
     * 
     * Helper - Enters data into a Create Manifest dialog
     */
    private void loadContainer(String containerType, String code, String weight,
            String goodsCat, String temperature) {
        testFrame.button(LOAD).click();
        DialogFixture containerDialog = testFrame.dialog(CONTAINER_INFORMATION);
        containerDialog.comboBox(CONTAINER_TYPE).selectItem(containerType);
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.textBox(CONTAINER_WEIGHT).enterText(weight);
        if (containerType.equals(DANGEROUS_GOODS)) {
            containerDialog.textBox(GOODS_CATEGORY).enterText(goodsCat);
        } else if (containerType.equals(REFRIGERATED_GOODS)) {
            containerDialog.textBox(TEMPERATURE2).enterText(temperature);
        }
        containerDialog.button(OK).click();
    }
    
    /*
     * @author Malcolm
     * 
     * Helper - Clicks the Unload button and enters a valid container code.
     */
    private void unloadContainer(String code) {
        testFrame.button(UNLOAD).click();
        DialogFixture containerDialog = testFrame.dialog("Container Dialog");
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.button(OK).click();
    }
    
    /*
     * @author Malcolm
     * 
     * Helper - Clicks the Find button and enters a valid container code.
     */
    private void findContainer(String code) {
        testFrame.button(FIND).click();
        DialogFixture containerDialog = testFrame.dialog("Container Dialog");
        containerDialog.textBox(CONTAINER_CODE).enterText(code);
        containerDialog.button(OK).click();
    }
    
//    /**
//     * Tests that the frame used in test cases has been instantiated.
//     */
//    @Test
//    public void frameConstruction() {
//    	assertNotNull(testFrame);
//    }
//    
//    /**
//     * Tests that only "New Manifest" button is enabled at the
//     * initial state (when the manifest has not been instantiated).
//     */
//    @Test
//    public void initialButtonState() {
//    	testFrame.button(NEW_MANIFEST).requireEnabled();
//    	testFrame.button(LOAD).requireDisabled();
//    	testFrame.button(UNLOAD).requireDisabled();
//    	testFrame.button(FIND).requireDisabled();
//    }
//    
//    /**
//     * Tests that an error message appears if the "OK" button is clicked 
//     * but no input data given in the New Manifest dialog.
//     */
//    @Test
//    public void newManifestNoInputData() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, null, null, null);
//    	manifestDialog.optionPane().requireErrorMessage();
//    }
//    
//    /**
//     * Tests that an error message appears if given an invalid data type as
//     * a number of stacks and the "OK" button is clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is not instantiated in this test.
//     */
//    @Test
//    public void newManifestWithAlphabetInputAsStackNumber() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, NOT_NUMERIC, null, null);
//    	manifestDialog.optionPane().requireErrorMessage();
//    }
//    
//    /**
//     * Tests that an error message appears if given a negative number of stacks
//     * but valid values for both maximum height and weight and the "OK" button is 
//     * clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is instantiated in this test.
//     */
//    @Test
//    public void newManifestWithNegativeStackNumber() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, NEGATIVE, HEIGHT_1, WEIGHT_100);
//    	manifestDialog.optionPane().requireErrorMessage();
//    	manifestDialog.optionPane().requireMessage(MANIFEST_EXCEPTION_PATTERN);
//    }
//    
//    /**
//     * Tests that an error message appears if given an invalid data type as
//     * a maximum height and the "OK" button is clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is not instantiated in this test.
//     */
//    @Test
//    public void newManifestWithAlphabetInputAsMaximumHeight() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, STACKS_1, NOT_NUMERIC, null);
//    	manifestDialog.optionPane().requireErrorMessage();
//    }
//    
//    /**
//     * Tests that an error message appears if given a negative number of maximum 
//     * height but valid number of stacks and maximum weight and the "OK" button is 
//     * clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is instantiated in this test.
//     */
//    @Test
//    public void newManifestWithNegativeMaximumHeight() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, STACKS_1, NEGATIVE, WEIGHT_100);
//    	manifestDialog.optionPane().requireErrorMessage();
//    	manifestDialog.optionPane().requireMessage(MANIFEST_EXCEPTION_PATTERN);
//    }
//    
//    /**
//     * Tests that an error message appears if given an invalid data type as
//     * a maximum weight and the "OK" button is clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is not instantiated in this test.
//     */
//    @Test
//    public void newManifestWithAlphabetInputAsMaximumWeight() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, NOT_NUMERIC);
//    	manifestDialog.optionPane().requireErrorMessage();
//    }   
//    
//    /**
//     * Tests that an error message appears if given a negative number of maximum 
//     * weight but valid number of stacks and maximum height and the "OK" button is 
//     * clicked in the New Manifest dialog.
//     * 
//     * A Manifest object is instantiated in this test.
//     */
//    @Test
//    public void newManifestWithNegativeMaximumWeight() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, NEGATIVE);
//    	manifestDialog.optionPane().requireErrorMessage();
//    	manifestDialog.optionPane().requireMessage(MANIFEST_EXCEPTION_PATTERN);
//    }
//    
//    /**
//     * Tests that all buttons including New Manifest, Load, Unload and Find
//     * buttons are enabled after a Manifest object has been instantiated with 
//     * valid values.
//     */
//    @Test
//    public void ButtonStateAfterInstantiatingNewManifest() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//    	manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_100);
//    	testFrame.button(NEW_MANIFEST).requireEnabled();
//        testFrame.button(LOAD).requireEnabled();
//        testFrame.button(UNLOAD).requireEnabled();
//        testFrame.button(FIND).requireEnabled();
//    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * if the manifest contains zero stack.
     */
    @Test
    public void manifestWithZeroEmptyStack() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, ZERO, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
//    /**
//     * Tests that the canvas displays the correct representation 
//     * for a single empty stack.
//     */
//    @Test
//    public void manifestWithOneEmptyStack() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_100);
//        if (frameUnderTest instanceof CargoTextFrame) {
//        	String expectedString = "||  ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
//    
//    /**
//     * Tests that the canvas displays the correct string representation 
//     * for three empty stacks.
//     */
//    @Test
//    public void manifestWithThreeEmptyStack() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_1, WEIGHT_100);
//        if (frameUnderTest instanceof CargoTextFrame) {
//        	String expectedString = "||  ||\n||  ||\n||  ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * for five empty stacks.
     */
    @Test
    public void manifestWithFiveEmptyStack() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_5, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * if creating a new manifest with different stack number.
     */
    @Test
    public void createNewManifestAgain() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_5, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * if trying to create a new manifest and then the cancel the operation.
     */
    @Test
    public void manifestRemaninsTheSame() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_5, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        manifestDialog = prepareManifestDialog();
        manifestDialog.button(CANCEL).click();
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * for five empty stacks, then displays the correct representation for five empty 
     * stacks after creating a new manifest.
     */
    @Test
    public void manifestWithFiveStackThenCreateNewManifestWithThreeStacks() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_5, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
//    /**
//     * Tests that an error message is displayed if loading a new container
//     * would exceed the maximum weight limit.
//     */
//    @Test
//    public void overload() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_20);
//        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
//        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_20, null, null);
//        testFrame.optionPane().requireErrorMessage();
//        if (frameUnderTest instanceof CargoTextFrame) {
//            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
    
    /**
     * Tests that an error message is displayed if there is no space for
     * a new container to be loaded.
     */
    @Test
    public void noSpaceAvilableForNewContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_2, HEIGHT_3, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(DANGEROUS_GOODS, CONTAINER_CODE_2, WEIGHT_10, CATEGORY_1, null);
        loadContainer(REFRIGERATED_GOODS, CONTAINER_CODE_3, WEIGHT_10, null, TEMPERATURE_MINUS_5);
        testFrame.optionPane().requireErrorMessage();
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n"
            					  + "|| " + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that an error message is displayed if the gross weight of
     * the container is lower than 4 tons.
     */
    @Test
    public void loadConatinerWithWeightLowerThanMinimumWeight() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_3, null, null);
        testFrame.optionPane().requireErrorMessage();
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that an error message is displayed if the gross weight of
     * the container is greater than 30 tons.
     */
    @Test
    public void loadConatinerWithWeightGreaterThanMaximumWeight() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_31, null, null);
        testFrame.optionPane().requireErrorMessage();
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that an error message is displayed if trying to load a new
     * container with the same container code as one of the containers on board.
     */
    @Test
    public void loadConatinerWithDuplicateContainerCode() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        testFrame.optionPane().requireErrorMessage();
    }
    
//    /**
//     * Tests that a valid general goods container is loaded to the manifest
//     * and displayed as expected.
//     */
//    @Test
//    public void loadGeneralGoodsConatiner() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
//        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
//        if (frameUnderTest instanceof CargoTextFrame) {
//            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
//    
//    /**
//     * Tests that a valid dangerous goods container is loaded to the manifest
//     * and displayed as expected.
//     */
//    @Test
//    public void loadDangerousGoodsConatiner() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
//        loadContainer(DANGEROUS_GOODS, CONTAINER_CODE_1, WEIGHT_10, CATEGORY_1, null);
//        if (frameUnderTest instanceof CargoTextFrame) {
//            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
//    
//    /**
//     * Tests that a valid refrigerated goods container is loaded to the manifest
//     * and displayed as expected.
//     */
//    @Test
//    public void loadRefrigeratedGoodsConatiner() {
//    	DialogFixture manifestDialog = prepareManifestDialog();
//        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
//        loadContainer(REFRIGERATED_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, TEMPERATURE_MINUS_5);
//        if (frameUnderTest instanceof CargoTextFrame) {
//            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
//            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
//        }
//    }
    
    /**
     * Tests that three valid general goods containers are loaded to the maximum
     * height and displayed as expected.
     */
    @Test
    public void loadThreeGeneralGoodsConatinerToMaxHeight() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_3, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || "
            							  + CONTAINER_CODE_2 + " || "
            							  + CONTAINER_CODE_3 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
	
    /**
     * Tests that four valid general goods containers are loaded to the maximum
     * height and displayed as expected (by staring a new stack because the first
     * stack has reached its maximum height limit).
     */
    @Test
    public void loadFourGeneralGoodsConatinerToStartNewStack() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_2, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_3, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_4, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || "
            							  + CONTAINER_CODE_2 + " || "
            							  + CONTAINER_CODE_3 + " ||\n"
            					  + "|| " + CONTAINER_CODE_4 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that two containers with different types (general and dangerous 
     * goods containers) are loaded to the manifest and displayed as expected.
     */
    @Test
    public void loadTwoConatinersWithTwoDifferntTypes() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_2, HEIGHT_1, WEIGHT_20);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(DANGEROUS_GOODS, CONTAINER_CODE_2, WEIGHT_10, CATEGORY_1, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n"
            					  + "|| " + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that three containers with different types (general, dangerous 
     * and refrigerated goods containers) are loaded to the manifest and displayed 
     * as expected.
     */
    @Test
    public void loadThreeConatinersWithThreeDifferntTypes() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_1, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(DANGEROUS_GOODS, CONTAINER_CODE_2, WEIGHT_10, CATEGORY_1, null);
        loadContainer(REFRIGERATED_GOODS, CONTAINER_CODE_3, WEIGHT_10, null, TEMPERATURE_MINUS_5);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n"
            					  + "|| " + CONTAINER_CODE_2 + " ||\n"
            					  + "|| " + CONTAINER_CODE_3 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that five containers with different types (general, dangerous 
     * and refrigerated goods containers) are loaded to the manifest and displayed 
     * as expected.
     */
    @Test
    public void loadFiveConatinersWithThreeDifferntTypes() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(DANGEROUS_GOODS, CONTAINER_CODE_2, WEIGHT_10, CATEGORY_1, null);
        loadContainer(REFRIGERATED_GOODS, CONTAINER_CODE_3, WEIGHT_10, null, TEMPERATURE_MINUS_5);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_4, WEIGHT_10, null, null);
        loadContainer(REFRIGERATED_GOODS, CONTAINER_CODE_5, WEIGHT_10, null, TEMPERATURE_MINUS_5);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || " + CONTAINER_CODE_4 + " ||\n"
            					  + "|| " + CONTAINER_CODE_2 + " ||\n"
            					  + "|| " + CONTAINER_CODE_3 + " || " + CONTAINER_CODE_5 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the canvas displays the correct representation 
     * if creating a new manifest with different stack number after loading
     * a container i.e the canvas is reset and display the representation
     * of the new manifest.
     */
    @Test
    public void loadContainerAndCreateNewManifestAgain() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_3, HEIGHT_1, WEIGHT_100);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "||  ||\n||  ||\n||  ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the manifest remains the same if trying to create a new manifest,
     * then cancel the operation and keep loading a new container into the
     * same manifest.
     */
    @Test
    public void manifestRemainTheSameAfterCancellingCreatingNewManifest() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        manifestDialog = prepareManifestDialog();
        manifestDialog.button(CANCEL).click();
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
        								  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the manifest remains the same if trying to load a new container,
     * then cancel the operation and keep loading a new container into the
     * same manifest.
     */
    @Test
    public void manifestRemainTheSameAfterCancellingLoadingNewContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        testFrame.button(LOAD).click();
        DialogFixture containerDialog = testFrame.dialog(CONTAINER_INFORMATION);
        containerDialog.button(CANCEL).click();
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
        								  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that an error message appears if the container to be unloaded 
     * is not on board.
     */
    @Test
    public void UnloadButContainerIsNotOnBoard() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        unloadContainer(CONTAINER_CODE_2);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if trying to unload an 
     * unaccessible container.
     */
    @Test
    public void unloadUnaccessibleContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
            							  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        unloadContainer(CONTAINER_CODE_1);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that the specified container can be successfully unloaded if
     * it is accessible i.e. on top of a stack.
     */
    @Test
    public void unloadAccessibleContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
            							  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        unloadContainer(CONTAINER_CODE_2);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the specified container can be successfully unloaded if
     * it is accessible i.e. on top of a stack, then replace the with 
     * a new container.
     */
    @Test
    public void unloadAndReplaceWithNewContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_20);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        unloadContainer(CONTAINER_CODE_1);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_20, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that an error message appears if the specified container code
     * is not 11 characters long.
     */
    @Test
    public void containerCodeWithInvalidLength() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_10);
        findContainer(INVALID_CONTAINER_CODE_1);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if the specified container code
     * contains invalid owner code.
     */
    @Test
    public void containerCodeWithInvalidOwnerCode() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_10);
        findContainer(INVALID_CONTAINER_CODE_2);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if the specified container code
     * contains invalid identifier.
     */
    @Test
    public void containerCodeWithInvalidIdentifier() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_10);
        findContainer(INVALID_CONTAINER_CODE_3);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if the specified container code
     * contains invalid serialNumber.
     */
    @Test
    public void containerCodeWithInvalidSerialNumber() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
        findContainer(INVALID_CONTAINER_CODE_4);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if the specified container code
     * contains incorrect check digit.
     */
    @Test
    public void containerCodeWithIncorrectCheckDigit() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_1, WEIGHT_20);
        findContainer(INVALID_CONTAINER_CODE_5);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that an error message appears if the container with the
     * specified code is not on board.
     */
    @Test
    public void findButContainerIsNotOnBoard() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_20);
        findContainer(CONTAINER_CODE_1);
        testFrame.optionPane().requireErrorMessage();
    }
    
    /**
     * Tests that the container with specified container code is highlighted.
     */
    @Test
    public void findCorrectContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_30);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_20, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
            							  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        findContainer(CONTAINER_CODE_1);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "||*" + CONTAINER_CODE_1 + "*|| " 
            							  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the manifest remains the same if trying to find a container,
     * then cancel the operation.
     */
    @Test
    public void manifestRemainTheSameAfterCancellingFindingContainer() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        testFrame.button(FIND).click();
        DialogFixture findDialog = testFrame.dialog("Container Dialog");
        findDialog.button(CANCEL).click();
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
    
    /**
     * Tests that the container highlighting is turned on if the specified
     * container is found on board and turned off when an action other than
     * Find is initiated.
     */
    @Test
    public void containerHighlightingFunctionsCorrectly() {
    	DialogFixture manifestDialog = prepareManifestDialog();
        manifestDialogEnterText(manifestDialog, STACKS_1, HEIGHT_3, WEIGHT_100);
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_1, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        findContainer(CONTAINER_CODE_1);
        if (frameUnderTest instanceof CargoTextFrame) {
            String expectedString = "||*" + CONTAINER_CODE_1 + "*||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
        loadContainer(GENERAL_GOODS, CONTAINER_CODE_2, WEIGHT_10, null, null);
        if (frameUnderTest instanceof CargoTextFrame) {
        	String expectedString = "|| " + CONTAINER_CODE_1 + " || " 
        								  + CONTAINER_CODE_2 + " ||\n";
            assertEquals(expectedString, testFrame.textBox(CARGO_TEXT_AREA).text());
        }
    }
}
