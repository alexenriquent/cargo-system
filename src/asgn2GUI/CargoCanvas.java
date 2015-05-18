/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * Creates a JPanel in which graphical components are laid out to represent the cargo manifest.
 *
 * @author CAB302.
 * @author Thanat Chokwijitkul n9234900
 */
public class CargoCanvas extends JPanel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;
    private static final int HSPACE = 10;
    private static final int VSPACE = 20;

    private final CargoManifest cargo;
    private ContainerCode toFind;   
    
    /**
     * Constructor
     *
     * @param cargo The <code>CargoManifest</code> on which the graphics is based so that the
     * number of stacks and height can be adhered to.
     */
    public CargoCanvas(CargoManifest cargo) {
        this.cargo = cargo;
        setName("Canvas");
    }

    /**
     * Highlights a container.
     *
     * @param code ContainerCode to highlight.
     */
    public void setToFind(ContainerCode code) {
    	toFind = code;
    	repaint();
    }

    /**
     * Draws the containers in the cargo manifest on the Graphics context of the Canvas.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paint(Graphics g) {
    	int x = HSPACE;
    	int y = VSPACE;
    	int xLineSpace = 5;
    	int yLineSpace = 50;
    	int containerSpace = 130;
    	int stackSpace = 60;
    	
    	if (cargo != null) {
    		try {
    			for (int i = 0; i < cargo.getNumStack(); i++) {
        			FreightContainer[] currentStack = cargo.toArray(i);
        			g.setColor(Color.BLACK);
        			g.drawLine(x - xLineSpace, y, x - xLineSpace, y + yLineSpace);
        			for (int j = 0; j < currentStack.length; j++) {
        				drawContainer(g, currentStack[j], x, y);
        				x += containerSpace;
        			}
        			x = HSPACE;
        			y += stackSpace;
        		}
    		} catch (ManifestException e) {
    			JOptionPane.showConfirmDialog(null, "Cannot display a container object.", 
    										  "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }

    /**
     * Draws a container at the given location.
     *
     * @param g The Graphics context to draw on.
     * @param container The container to draw - the type determines the colour and ContainerCode is
     *            used to identify the drawn Rectangle.
     * @param x The x location for the Rectangle.
     * @param y The y location for the Rectangle.
     */
    private void drawContainer(Graphics g, FreightContainer container, int x, int y) {
    	int xSpace = x + 20;
    	int ySpace = y + 15;
    	int foundSpace = x + 10;
    	if (container.getClass().equals(DangerousGoodsContainer.class)) {
    		g.setColor(Color.RED);
    		g.drawRect(x, y, WIDTH, HEIGHT);
    		g.fillRect(x, y, WIDTH, HEIGHT);
    		g.setColor(Color.WHITE);
    		if (toFind != null) {
    			if (toFind.equals(container.getCode())) {
        			g.drawString("|| " + container.getCode().toString() + " ||", foundSpace, ySpace);
        		} else {
        			g.drawString(container.getCode().toString(), xSpace, ySpace);
        		}
    		} else {
    			g.drawString(container.getCode().toString(), xSpace, ySpace);
    		}
    	} else if (container.getClass().equals(GeneralGoodsContainer.class)) {
    		g.setColor(Color.GRAY);
    		g.drawRect(x, y, WIDTH, HEIGHT);
    		g.fillRect(x, y, WIDTH, HEIGHT);
    		g.setColor(Color.WHITE);
    		if (toFind != null) {
    			if (toFind.equals(container.getCode())) {
        			g.drawString("|| " + container.getCode().toString() + " ||", foundSpace, ySpace);
        		} else {
        			g.drawString(container.getCode().toString(), xSpace, ySpace);
        		}
    		} else {
    			g.drawString(container.getCode().toString(), xSpace, ySpace);
    		}
    	} else if (container.getClass().equals(RefrigeratedContainer.class)) {
    		g.setColor(Color.BLUE);
    		g.drawRect(x, y, WIDTH, HEIGHT);
    		g.fillRect(x, y, WIDTH, HEIGHT);
    		g.setColor(Color.WHITE);
    		if (toFind != null) {
    			if (toFind.equals(container.getCode())) {
        			g.drawString("|| " + container.getCode().toString() + " ||", foundSpace, ySpace);
        		} else {
        			g.drawString(container.getCode().toString(), xSpace, ySpace);
        		}
    		} else {
    			g.drawString(container.getCode().toString(), xSpace, ySpace);
    		}
    	}
    }
}
