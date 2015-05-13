package asgn2Containers;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.InvalidContainerException;

/**
 * A dangerous goods container is a special type of tank container for
 * transporting flammable, explosive or toxic goods.  A dangerous
 * goods container must display a category label to identify the type
 * of hazard posed by its contents.
 * <p>
 * Category labels for dangerous goods are defined by Australian standard
 * AS&nbsp;1216.  Here we use a simplified version of the standard in which
 * the dangerous goods categories are represented by the numbers 1 to 9:
 * <ol>
 * <li>
 * Explosive substances
 * </li>
 * <li>
 * Flammable and toxic gases
 * </li>
 * <li>
 * Flammable liquids
 * </li>
 * <li>
 * Flammable solids
 * </li>
 * <li>
 * Oxidizing substances
 * </li>
 * <li>
 * Toxic and infectious substances
 * </li>
 * <li>
 * Radioactive and fissile materials
 * </li>
 * <li>
 * Corrosive substances
 * </li>
 * <li>
 * Miscellaneous dangerous substances
 * </li>
 * </ol>
 * 
 * @author CAB302
 * @author Thanat Chokwijitkul n923490
 * @version 1.0
 */
public class DangerousGoodsContainer extends FreightContainer {
	
	private Integer category;
	
	/**
	 * Constructs a dangerous goods container object with the given
	 * container code, gross weight and dangerous goods category.  See the constructor
	 * in class FreightContainer for details about valid container weights.
	 * 
	 * @param code the container's code
	 * @param grossWeight the container's weight in tonnes
	 * @param category the container's dangerous goods category as per AS&nbsp;1216
	 * @throws InvalidContainerException if the gross weight is invalid; or if the category label
	 * is not in the range 1 to 9, inclusive
	 */
	public DangerousGoodsContainer(ContainerCode code, Integer grossWeight, Integer category) 
	throws InvalidContainerException {
		super (code, grossWeight);
		if (invalidGrossWeight(grossWeight)) {
			throw new InvalidContainerException("The gross weight is not between 4 and 30.");
		}
		if (invalidCategory(category)) {
			throw new InvalidContainerException("The category label is not in the range 1 to 9");
		}
		this.category = category;
	}

	/**
	 * Returns the container's dangerous goods category.
	 * 
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}
	
	/**
	 * Returns <code>true</code> if the gross weight is not between 4 and 30.
	 * 
	 * @param grossWeight the gross weight of the container
	 * @return <code>true</code> if the gross weight is not between 4 and 30,
	 * <code>false</code> otherwise.
	 */
	private boolean invalidGrossWeight(Integer grossWeight) {
		return grossWeight < 4 || grossWeight > 30;
	}
	
	/**
	 * Returns <code>true</code> if the category label is not in the range 1 to 9.
	 * 
	 * @param grossWeight the gross weight of the container
	 * @return <code>true</code> if the category label is not in the range 1 to 9,
	 * <code>false</code> otherwise.
	 */
	private boolean invalidCategory(Integer category) {
		return category < 1 || category > 9;
	}
}
