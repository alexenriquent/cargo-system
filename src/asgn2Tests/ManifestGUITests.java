/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import javax.swing.JFrame;

import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

/**
 * Defines FEST Swing tests for various scenarios of the Cargo System. 
 * 
 * @author 
 */
public class ManifestGUITests {
	
	private static final String CARGO_TEXT_AREA = "Cargo Text Area";	
    private static final String NEW_MANIFEST = "New Manifest";
    private static final String LOAD = "Load";
    private static final String UNLOAD = "Unload";
	private static final String FIND = "Find";
	
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
    private static final String TEMPERATURE = "Temperature";

	private static final String CONTAINER_CODE_1 = "INKU2633836";
	private static final String CONTAINER_CODE_2 = "KOCU8090115";
	private static final String CONTAINER_CODE_3 = "MSCU6639871";
	private static final String CONTAINER_CODE_4 = "CSQU3054389";
	private static final String CONTAINER_CODE_5 = "QUTU7200318";
	private static final String CONTAINER_CODE_6 = "IBMU4882351";
	private static final String CONTAINER_CODE_7 = "ABCU1234564";
    private static final String CONTAINER_CODE_8 = "ZZZU6549874";
    private static final String CONTAINER_CODE_9 = "JHGU1716760";
	
	private static final boolean GRAPHIC_VERSION = true;
    private static final int NO_PAUSE = 0;
    private static final int SHORT_PAUSE = 1500;
    private static final int LONG_PAUSE = 3000;

    private static final Pattern CARGO_EXCEPTION_PATTERN = Pattern.compile("CargoException:.+");

    private FrameFixture testFrame;
    private JFrame frameUnderTest;
	
}
