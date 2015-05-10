package asgn2GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

    private CargoManifest manifest;

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
        //manifest = null;
        
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {

        txtNumStacks = createTextField(8, "Number of Stacks");
        txtMaxHeight = createTextField(8, "Maximum Height");
        txtMaxWeight = createTextField(8, "Maximum Weight");
        
        JLabel lblNumStacks = new JLabel(txtNumStacks.getName() + ":  ");
    	JLabel lblMaxHeight = new JLabel(txtMaxHeight.getName() + ":  ");
    	JLabel lblMaxWeight = new JLabel(txtMaxWeight.getName() + ":  ");

        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); 
        
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.5;
	    constraints.anchor = GridBagConstraints.LINE_END;
	    constraints.insets = new Insets(7,2,2,2);
	    addToPanel(toReturn, lblNumStacks, constraints, 0, 0, 1, 1);
	    constraints.insets = new Insets(2,2,2,2);
	    addToPanel(toReturn, lblMaxHeight, constraints, 0, 1, 1, 1);
	    addToPanel(toReturn, lblMaxWeight, constraints, 0, 2, 1, 1);

	    constraints.anchor = GridBagConstraints.LINE_START;
	    constraints.insets = new Insets(7,2,2,2);
	    addToPanel(toReturn, txtNumStacks, constraints, 1, 0, 1, 1);
	    constraints.insets = new Insets(2,2,2,2);
	    addToPanel(toReturn, txtMaxHeight, constraints, 1, 1, 1, 1);
	    addToPanel(toReturn, txtMaxWeight, constraints, 1, 2, 1, 1);

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
    	boolean dialogDone = false;
    	Integer numStacks = 0;
    	Integer maxHeight = 0;
    	Integer maxWeight = 0;	
    	try {
    		numStacks  = Integer.parseInt(txtNumStacks.getText());
    		maxHeight  = Integer.parseInt(txtMaxHeight.getText());
    		maxWeight  = Integer.parseInt(txtMaxWeight.getText());
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Please ensure that each input is an integer.");
    		return dialogDone;
    	}
    	try {
			manifest = new CargoManifest(numStacks, maxHeight, maxWeight);
			dialogDone = true;
			return dialogDone;
		} catch (ManifestException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return dialogDone;
		}
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>CargoManifest</code> instance with valid values.
     */
    public static CargoManifest showDialog(JFrame parent) {
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	ManifestDialog dlgManifest = new ManifestDialog(parent);
    	return dlgManifest.getManifest();
    }
    
    /**
     * Simple getter for the <code>manifest</code> field, called in
     * the <code>showDialog<code> method.
     * 
     * The <code>showDialog<code> method is a static method, therefore
     * it cannot make a reference to a non-static field. 
     * 
     * @return manifest the manifest with specified a number of stacks,
     * maximum height and maximum weight
     */
    public CargoManifest getManifest() {
    	return manifest;
    }
}
