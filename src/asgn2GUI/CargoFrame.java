/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * The main window for the Cargo Manifest graphics application.
 *
 * @author CAB302
 * @author Thanat Chokwijitkul n9234900
 * @author Month Yean Koh n9070095
 */
public class CargoFrame extends JFrame {

	private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JButton btnLoad;
    private JButton btnUnload;
    private JButton btnFind;
    private JButton btnNewManifest;

    private CargoCanvas canvas;

    private JPanel pnlControls;
    private JPanel pnlDisplay;

    private CargoManifest cargo;

    /**
     * Constructs the GUI.
     *
     * @param title The frame title to use.
     * @throws HeadlessException from JFrame.
     */
    public CargoFrame(String title) throws HeadlessException {
        super(title);
        constructorHelper();
        disableButtons();
        redraw();
        setVisible(true);
    }

    /**
     * Initialises the container display area.
     *
     * @param cargo The <code>CargoManifest</code> instance containing necessary state for display.
     */
    private void setCanvas(CargoManifest cargo) {
        if (canvas != null) {
            pnlDisplay.remove(canvas);
        }
        if (cargo == null) {
            disableButtons();
        } else {       	
            canvas = new CargoCanvas(cargo);
            pnlDisplay.add(canvas, BorderLayout.CENTER);
    		add(pnlDisplay, BorderLayout.CENTER);
    		enableButtons();    
        }
        redraw();
    }

    /**
     * Enables buttons for user interaction.
     */
    private void enableButtons() {
    	btnLoad.setEnabled(true);
    	btnUnload.setEnabled(true);
    	btnFind.setEnabled(true);   
    }

    /**
     * Disables buttons from user interaction.
     */
    private void disableButtons() {
    	btnLoad.setEnabled(false);
    	btnUnload.setEnabled(false);
    	btnFind.setEnabled(false);  
    }

    /**
     * Initialises and lays out GUI components.
     */
    private void constructorHelper() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLoad = createButton("Load", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.resetCanvas();
                        CargoFrame.this.doLoad();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnUnload = createButton("Unload", new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.resetCanvas();
                        CargoFrame.this.doUnload();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }    
        });
        btnFind = createButton("Find", new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.resetCanvas();
                        CargoFrame.this.doFind();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }   
        });
        btnNewManifest = createButton("New Manifest", new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.setNewManifest();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }   
        });

        setLayout(new BorderLayout());
        pnlControls = createControlPanel();
        add(pnlControls, BorderLayout.SOUTH);
        pnlDisplay = new JPanel(new BorderLayout());
        repaint();
    }

    /**
     * Creates a JPanel containing user controls (buttons).
     *
     * @return User control panel.
     */
    private JPanel createControlPanel() {
    	JPanel pnlButtons = new JPanel();
	    pnlButtons.setLayout(new FlowLayout());
	    pnlButtons.add(btnNewManifest);
	    pnlButtons.add(btnLoad);
	    pnlButtons.add(btnUnload);
	    pnlButtons.add(btnFind);
    	return pnlButtons;   
    }

    /**
     * Factory method to create a JButton and add its ActionListener.
     *
     * @param name The text to display and use as the component's name.
     * @param btnListener The ActionListener to add.
     * @return A named JButton with ActionListener added.
     */
    private JButton createButton(String name, ActionListener btnListener) {
        JButton btn = new JButton(name);
        btn.setName(name);
        btn.addActionListener(btnListener);
        return btn;
    }

    /**
     * Initiate the New Manifest dialog which sets the instance of CargoManifest to work with.
     */
    private void setNewManifest() {
    	CargoManifest currentCargo = cargo;
    	cargo = ManifestDialog.showDialog(this);    	
    	if (cargo != null) {
    		setCanvas(cargo);
       	} else if (cargo == null) {
    		cargo = currentCargo;
    		redraw();
       	}
    }

    /**
     * Turns off container highlighting when an action other than Find is initiated.
     */
    private void resetCanvas() {
    	canvas.setToFind(null);  
    }

    /**
     * Initiates the Load Container dialog.
     */
    private void doLoad() {
    	FreightContainer container = LoadContainerDialog.showDialog(this);	
    	if (container != null) {
    		try {
    			cargo.loadContainer(container);
    		} catch (ManifestException e) {
    			JOptionPane.showMessageDialog(this, e.getMessage(),
    										  "Error", JOptionPane.ERROR_MESSAGE);
    		}
        	redraw();
    	}
    }

    private void doUnload() {
    	ContainerCode code = ContainerCodeDialog.showDialog(this);
    	if (code != null) {
    		try {
    			cargo.unloadContainer(code);
    		} catch (ManifestException e) {
    			JOptionPane.showMessageDialog(this, e.getMessage(),
    										  "Error", JOptionPane.ERROR_MESSAGE);
    		}
        	redraw();
    	}
    }

    private void doFind() {
    	ContainerCode code = ContainerCodeDialog.showDialog(this);
    	if (code != null) {
    		if (cargo.whichStack(code) == null) {
    			JOptionPane.showMessageDialog(this, "The specified container is not on board.",
						  "Error", JOptionPane.ERROR_MESSAGE);
    		} else {
    			canvas.setToFind(code);
    		}
    	}
    }

    private void redraw() {
        invalidate();
        validate();
        repaint();
    }
}
