/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Provides base class for dialog boxes used for data entry.
 *
 * @author CAB302
 * @author Thanat Chokwijitkul n9234900
 * @author Month Yean Koh n9070095
 */
public abstract class AbstractDialog extends JDialog implements ActionListener {

	private JButton btnOK;
    private JButton btnCancel;

    /**
     * This constructor calls the JDialog parent constructor to create a modal dialog
     * box with the specified parent and title. The dialog box is set to the specified
     * width and height. Before the constructor returns it calls the
     * createContentPanel method. No GUI creation code should be placed in
     * child constructors calling this constructor
     *
     * @param parent The parent frame the dialog box is to be attached to
     * @param title The title of the dialog box
     * @param width The dialog box width
     * @param height The dialog box height
     */
    protected AbstractDialog(Frame parent, String title, int width, int height) {
        super(parent, title, true);

        JPanel pnlDialogControls = createDialogControls();
        JPanel pnlContent = createContentPanel();
        
        setSize(width, height);
	    setLayout(new BorderLayout());	   
	    
	    this.getContentPane().add(pnlDialogControls, BorderLayout.SOUTH);
	    this.getContentPane().add(pnlContent, BorderLayout.NORTH);
    }

    /** 
     * This abstract method is called by the AbstractDialog constructor to
     * build the JPanel that will be put in the centre of the dialog box.
     * Override and implement to create the contents of your dialog box.
     *
     * @return The JPanel that is the contents of the dialog box
     */
    protected abstract JPanel createContentPanel();

    /**
     * Lays out OK and Cancel buttons used by all dialogs.
     * @return JPanel with OK and Cancel buttons.
     */
    private JPanel createDialogControls() {
    	JPanel pnlDialogControls = new JPanel();
    	btnOK = createButton("OK");
        btnCancel = createButton("Cancel");
    	GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints constraints = new GridBagConstraints(); 
    	
    	btnOK.setLayout(layout);
    	btnCancel.setLayout(layout);
    	constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.EAST;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.5;
	    addToPanel(pnlDialogControls, btnOK, constraints, 0, 0, 2, 1);
	    addToPanel(pnlDialogControls, btnCancel, constraints, 1, 0, 2, 1);
	    
    	return pnlDialogControls;
    }

    /**
     * Factory method which creates a <code>JButton</code> instance with the given text used for the
     * button text and the component name.
     *
     * @param text String to use on the JButton and as the component name.
     * @return a JButton instance.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setName(text);
        button.addActionListener(this);
        return button;
    }

    /**
     * Helper function from the Week 8 prac to help with adding items
     * to panels with GridBagLayouts
     *
     * @param panel The JPanel to add the component to
     * @param component The component to add
     * @param constraints The default constraints
     * @param x The row the component is to be placed in
     * @param y The column the component is to be placed in
     * @param width The columns to span
     * @param height The rows to span
     */
    protected void addToPanel(JPanel panel, Component component, GridBagConstraints constraints,
            int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        panel.add(component, constraints);
    }

    /**
     * Handles the closing of the dialog box. This implementation
     * calls the dialogDone() method to determine if the dialogBox should close
     *
     * @param event The event to handle.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnCancel) {
            setVisible(false);
            return;
        }

        if (dialogDone()) {
            setVisible(false);
        }
    }

    /**
     * Called by the actionPerformed method when the OK button has been clicked to
     * determine if the dialog box should close. In this method you should process
     * the input, set the output field to its final value. If there is a problem
     * and you need the user to modify their data return false to keep the dialog
     * open.
     *
     * @return true if the dialog box should close, false otherwise
     */
    protected abstract boolean dialogDone();

    /**
     * Called to cause the JDialog to completely redraw itself
     */
    protected void redraw() {
        invalidate();
        validate();
        repaint();
    }

    /**
     * Disables the OK button
     */
    protected void disableSubmit() {
    	btnOK.setEnabled(false);
    }

    /**
     * Enables the OK button
     */
    protected void enableSubmit() {
    	btnOK.setEnabled(true);    
    }
}
