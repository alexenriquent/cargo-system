/**
 * This file forms part of the CargoSystem Project
 * Assignment Two - CAB302 2015
 */
package asgn2GUI;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.CargoException;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;

/**
 * Creates a dialog box allowing the user to enter information required for loading a container.
 *
 * @author CAB302
 * @author Thanat Chokwijitkul n9234900
 * @author Month Yean Koh n9070095
 */
public class LoadContainerDialog extends AbstractDialog implements ActionListener, ItemListener {

    private static final int HEIGHT = 200;
    private static final int WIDTH = 350;

    private JPanel pnlCards;

    private JTextField txtDangerousGoodsType;
    private JTextField txtTemperature;
    private JTextField txtWeight;
    private JTextField txtCode;

    private JComboBox<String> cbType;
    private static String comboBoxItems[] = new String[] { "Dangerous Goods", "General Goods", "Refrigerated Goods" };

    private FreightContainer container;

    /**
     * Constructs a modal dialog box that gathers information required for loading a container.
     *
     * @param parent the frame which created this dialog box.
     */
    private LoadContainerDialog(JFrame parent) {
        super(parent, "Container Information", WIDTH, HEIGHT);
        setResizable(false);
        setName("Container Information");
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
        createCards();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new GridBagLayout());
        constraints.insets = new Insets(10,2,2,2);
        addToPanel(pnlContent, createCommonControls(), constraints, 0, 0, 2, 1);
        constraints.weighty = 10;
        constraints.insets = new Insets(0,2,2,2);
        addToPanel(pnlContent, pnlCards, constraints, 1, 1, 2, 1);

        return pnlContent;
    }

    private JPanel createCommonControls() {
        JPanel pnlCommonControls = new JPanel();
        pnlCommonControls.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 100;
        constraints.weighty = 100;

        //Don't modify - START 
        cbType = new JComboBox<String>(comboBoxItems);
        cbType.setEditable(false);
        cbType.addItemListener(this);
        cbType.setName("Container Type");
        //Don't modify - END 

        txtWeight = createTextField(5, "Container Weight");
        txtCode = createTextField(11, "Container Code");
        
        constraints.insets = new Insets(2,0,2,0);
        addToPanel(pnlCommonControls, new JLabel("Container Type: "), constraints, 0, 0, 2, 1);
        addToPanel(pnlCommonControls, new JLabel("Container Code: "), constraints, 0, 2, 2, 1);
        addToPanel(pnlCommonControls, new JLabel("Container Weight: "), constraints, 0, 4, 2, 1);
        constraints.anchor = GridBagConstraints.WEST;
        addToPanel(pnlCommonControls, cbType, constraints, 3, 0, 2, 1);
        addToPanel(pnlCommonControls, txtCode, constraints, 3, 2, 2, 1);
        addToPanel(pnlCommonControls, txtWeight, constraints, 3, 4, 2, 1);

        return pnlCommonControls;
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

    private void createCards() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 100;
        constraints.weighty = 100;

        JPanel cardDangerousGoods = new JPanel();
        cardDangerousGoods.setLayout(new GridBagLayout());
        txtDangerousGoodsType = createTextField(5, "Goods Category");
        JLabel lblDangerousGoodsType = new JLabel(txtDangerousGoodsType.getName() + ": ");
        
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(0,-70,0,0);
        addToPanel(cardDangerousGoods, lblDangerousGoodsType, constraints, 0, 0, 2, 1);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0,0,0,0);
        addToPanel(cardDangerousGoods, txtDangerousGoodsType, constraints, 3, 0, 2, 1);

        JPanel cardRefrigeratedGoods = new JPanel();
        cardRefrigeratedGoods.setLayout(new GridBagLayout());
        txtTemperature = createTextField(5, "Temperature");
        JLabel lblTemperature = new JLabel(txtTemperature.getName() + ": ");
        
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(0,-55,0,0);
        addToPanel(cardRefrigeratedGoods, lblTemperature, constraints, 0, 0, 2, 1);
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0,0,0,0);
        addToPanel(cardRefrigeratedGoods, txtTemperature, constraints, 3, 0, 2, 1);
        
        JPanel cardGeneralGoods = new JPanel();
        
        pnlCards = new JPanel(new CardLayout());
        pnlCards.add(cardDangerousGoods, comboBoxItems[0]);
        pnlCards.add(cardGeneralGoods, comboBoxItems[1]);
        pnlCards.add(cardRefrigeratedGoods, comboBoxItems[2]);
    }

    /**
     * @see java.awt.ItemListener.itemStateChanged(ItemEvent e)
     */
    @Override
    public void itemStateChanged(ItemEvent event) {
        CardLayout cl = (CardLayout) pnlCards.getLayout();
        cl.show(pnlCards, (String) event.getItem());
    }

    /**
     * @see AbstractDialog.dialogDone()
     */
    @Override
    protected boolean dialogDone() {
    	boolean dialogDone = false;
    	String type = cbType.getSelectedItem().toString();
    	String containerCode = txtCode.getText();
    	ContainerCode code = null;
    	Integer weight = 0;
    	Integer category = 0;
    	Integer temperature = 0;
    	
    	try {
    		code = new ContainerCode(containerCode);
    	} catch (InvalidCodeException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(),
    									  "Error", JOptionPane.ERROR_MESSAGE);
    		return dialogDone;
    	}
    	
    	try {
    		weight = Integer.parseInt(txtWeight.getText());
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "The maximum weight must be an integer.",
    									  "Error", JOptionPane.ERROR_MESSAGE);
    		return dialogDone;
    	}
    	
    	if (type.equals(comboBoxItems[0])) {
    		try {
    			try {
    				category = Integer.parseInt(txtDangerousGoodsType.getText());
    			} catch (NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "The category must be an integer.",
    											  "Error", JOptionPane.ERROR_MESSAGE);
    				return dialogDone;
    			}
    			container = new DangerousGoodsContainer(code, weight, category);
    			dialogDone = true;
    			return dialogDone;
    		} catch (InvalidContainerException e) {
    			JOptionPane.showMessageDialog(null, e.getMessage(), 
    										  "Error", JOptionPane.ERROR_MESSAGE);
    			return dialogDone;
    		}
    	} else if (type.equals(comboBoxItems[1])) {
    		try {
    			container = new GeneralGoodsContainer(code, weight);
    			dialogDone = true;
    			return dialogDone;
    		} catch (InvalidContainerException e) {
    			JOptionPane.showMessageDialog(null, e.getMessage(),
    										  "Error", JOptionPane.ERROR_MESSAGE);
    			return dialogDone;
    		}
    	} else if (type.equals(comboBoxItems[2])) {
    		try {
    			try {
    				temperature = Integer.parseInt(txtTemperature.getText());
    			} catch (NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "The temperature must be an integer.",
    											  "Error", JOptionPane.ERROR_MESSAGE);
    				return dialogDone;
    			}
    			container = new RefrigeratedContainer(code, weight, temperature);
    			dialogDone = true;
    			return dialogDone;
    		} catch (InvalidContainerException e) {
    			JOptionPane.showMessageDialog(null, e.getMessage(),
    										  "Error", JOptionPane.ERROR_MESSAGE);
    			return dialogDone;
    		}
    	}
    	return dialogDone;
    }

    /**
     * Shows a <code>LoadContainerDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>FreightContainer</code> instance with valid values.
     */
    public static FreightContainer showDialog(JFrame parent) {
       JDialog.setDefaultLookAndFeelDecorated(true);
       LoadContainerDialog dlgLoadContainer = new LoadContainerDialog(parent);
       dlgLoadContainer.setVisible(true);
       return dlgLoadContainer.container;
    }

}
