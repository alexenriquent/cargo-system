/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2GUI;

import java.awt.Font;

import javax.swing.JTextArea;

import asgn2Codes.ContainerCode;
import asgn2Manifests.CargoManifest;

/**
 * Creates a JTextArea in which textual components are laid out to represent the cargo manifest.
 *
 * @author CAB302.
 * @author Thanat Chokwijitkul n9234900
 * @author Month Yean Koh n9070095
 */
public class CargoTextArea extends JTextArea {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;
    private static final int HSPACE = 10;
    private static final int VSPACE = 20;

    private final CargoManifest cargo;

    private ContainerCode toFind;

    /**
     * Constructor initialises the JTextArea.
     *
     * @param cargo the <code>CargoManifest</code> on which the text area is based 
     * 
     */
    public CargoTextArea(CargoManifest cargo) {
        setFont(new Font("Calibri", Font.PLAIN, 12));
        setName("Cargo Text Area");
        setSize(WIDTH, HEIGHT);
        setEditable(false);
        this.cargo = cargo;
    }

    /**
     * Highlights a container.
     *
     * @param code ContainerCode to highlight.
     */
    public void setToFind(ContainerCode code) {
        toFind = code;
    	updateDisplay();
    }

    /**
     * Outputs the container representation from the cargo manifest on the text area.
     *
     */
    public void updateDisplay() {
    	if (toFind == null) {
    		this.setText(cargo.toString());
    	} else {
    		this.setText(cargo.toString(toFind));
    	}
    }
}
