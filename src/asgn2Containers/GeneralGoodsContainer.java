/**
 * @author Thanat Chokwijitkul n9234900
 */
package asgn2Containers;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.InvalidContainerException;

/**
 * A general-purpose, or "dry", container is a freight container with
 * no special characteristics.  It is used for carrying furniture, boxes,
 * etc.
 * 
 * @author CAB302
 * @version 1.0
 */
public class GeneralGoodsContainer extends FreightContainer {

	/**
	 * Constructs a general-purpose freight container object with the given
	 * container code and gross weight.  See the constructor in class
	 * FreightContainer for details about valid container weights.
	 * 
	 * @param code the container's code
	 * @param grossWeight the container's weight in tonnes
	 * @throws InvalidContainerException if the gross weight is invalid
	 */
	public GeneralGoodsContainer(ContainerCode code, Integer grossWeight)
	throws InvalidContainerException {
		super (code, grossWeight);
		if (grossWeight < 4 || grossWeight > 30) {
			throw new InvalidContainerException("The gross weight is not between 4 and 30.");
		}
	}

}
