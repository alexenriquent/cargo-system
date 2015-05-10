package asgn2GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;


import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.CargoException;
import asgn2Exceptions.InvalidCodeException;

/**
 * Creates a dialog box allowing the user to enter a ContainerCode.
 *
 * @author CAB302
 */
public class ContainerCodeDialog extends AbstractDialog {

    private final static int WIDTH = 250;
    private final static int HEIGHT = 120;

    private JTextField txtCode;
    private JLabel lblErrorInfo;

    private ContainerCode code;

    /**
     * Constructs a modal dialog box that requests a container code.
     *
     * @param parent the frame which created this dialog box.
     */
    private ContainerCodeDialog(JFrame parent) {
        super(parent, "Container Code", WIDTH, HEIGHT);
        setName("Container Dialog");
        setResizable(true);
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        txtCode = new JTextField();
        txtCode.setColumns(11);
        txtCode.setName("Container Code");
        txtCode.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            /*
             * Attempts to validate the ContainerCode entered in the Container Code text field.
             */
            private void validate() {
            	//implementation here 
            }
        });
        
        JLabel lblCode = new JLabel(txtCode.getName() + ": ");
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(20,2,20,2);
        addToPanel(toReturn, lblCode, constraints, 0, 0, 1, 1);
        constraints.anchor = GridBagConstraints.LINE_START;
        addToPanel(toReturn, txtCode, constraints, 1, 0, 1, 1);

        return toReturn;
    }

    @Override
    protected boolean dialogDone() {
    	boolean dialogDone = false;
    	String containerCode = txtCode.getText();
    	try {
    		code = new ContainerCode(containerCode);
    		dialogDone = true;
    		return dialogDone;
    	} catch (InvalidCodeException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    		return dialogDone;
    	}
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>ContainerCode</code> instance with valid values.
     */
    public static ContainerCode showDialog(JFrame parent) {
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	ContainerCodeDialog dlgContainerCode = new ContainerCodeDialog(parent);
    	return dlgContainerCode.getCode();
    }
    
    /**
     * Simple getter for the <code>code</code> field, called in
     * the <code>showDialog<code> method.
     * 
     * The <code>showDialog<code> method is a static method, therefore
     * it cannot make a reference to a non-static field. 
     * 
     * @return code the container code
     */
    public ContainerCode getCode() {
    	return code;
    }
}
