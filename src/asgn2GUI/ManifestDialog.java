package asgn2GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import asgn2Exceptions.CargoException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * Creates a dialog box allowing the user to enter parameters for a new <code>CargoManifest</code>.
 *
 * @author CAB302
 */
public class ManifestDialog extends AbstractDialog {
	
    private static final int HEIGHT = 150;
    private static final int WIDTH = 250;

    private JTextField txtNumStacks;
    private JTextField txtMaxHeight;
    private JTextField txtMaxWeight;

    private static CargoManifest manifest;

    /**
     * Constructs a modal dialog box that gathers information required for creating a cargo
     * manifest.
     *
     * @param parent the frame which created this dialog box.
     */
    private ManifestDialog(JFrame parent) {
        super(parent, "Create Manifest", WIDTH, HEIGHT);
        setName("New Manifest");
        setResizable(false);
        manifest = null;
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {

        txtNumStacks = createTextField(8, "Number of Stacks");
        txtMaxHeight = createTextField(8, "Maximum Height");
        txtMaxWeight = createTextField(8, "Maximum Weight");

        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); 
        
        constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    constraints.gridwidth = 2;
        constraints.gridheight = 1;
	    toReturn.add(txtNumStacks, constraints);
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.gridwidth = 2;
        constraints.gridheight = 1;
	    toReturn.add(txtMaxHeight, constraints);
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.gridwidth = 2;
        constraints.gridheight = 1;
	    toReturn.add(txtMaxWeight, constraints);

       return toReturn;
    }

    /*
     * Factory method to create a named JTextField
     */
    private JTextField createTextField(int numColumns, String name) {
        JTextField text = new JTextField();
        text.setColumns(numColumns);
        text.setName(name);
        return text;
    }

    @Override
    protected boolean dialogDone() {
    	Integer numStacks = 0;
    	Integer maxHeight = 0;
    	Integer maxWeight = 0;
    	boolean dialogDone = false;
    	try {
    		numStacks  = Integer.parseInt(txtNumStacks.getText());
    	} catch (NumberFormatException e) {
    		// Error
    	}
    	try {
    		maxHeight  = Integer.parseInt(txtMaxHeight.getText());
    	} catch (NumberFormatException e) {
    		// Error
    	}
    	try {
    		maxWeight  = Integer.parseInt(txtMaxWeight.getText());
    	} catch (NumberFormatException e) {
    		// Error
    	}
    	try {
			manifest = new CargoManifest(numStacks, maxHeight, maxWeight);
			dialogDone = true;
		} catch (ManifestException e) {
			// Error
			dialogDone = false;
		}
    	return dialogDone;
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>CargoManifest</code> instance with valid values.
     */
    public static CargoManifest showDialog(JFrame parent) {
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	JDialog dlgManifest = new ManifestDialog(parent);
    	dlgManifest.setVisible(true);
    	return manifest;
    }
}
