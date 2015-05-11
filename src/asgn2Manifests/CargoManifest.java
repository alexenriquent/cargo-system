/**
 * @author Month Yean KOH n9070095 
 */
package asgn2Manifests;

import java.util.ArrayList;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;

/**
 * A class for managing a container ship's cargo manifest.  It
 * allows freight containers of various types to be loaded and
 * unloaded, within various constraints.
 * <p>
 * In particular, the ship's captain has set the following rules
 * for loading of new containers:
 * <ol>
 * <li>
 * New containers may be loaded only if doing so does not exceed
 * the ship's weight limit.
 * </li>
 * <li>
 * New containers are to be loaded as close to the bridge as possible.
 * (Stack number zero is nearest the bridge.)
 * </li>
 * <li>
 * A new container may be added to a stack only if doing so will
 * not exceed the maximum allowed stack height.
 * <li>
 * A new container may be loaded only if a container with the same
 * code is not already on board.
 * </li>
 * <li>
 * Stacks of containers must be homogeneous, i.e., each stack must
 * consist of containers of one type (general,
 * refrigerated or dangerous goods) only.
 * </li>
 * </ol>
 * <p>
 * Furthermore, since the containers are moved by an overhead
 * crane, a container can be unloaded only if it is on top of
 * a stack.
 *  
 * @author CAB302
 * @version 1.0
 */
public class CargoManifest {

	private Integer numStacks;
	private Integer maxHeight;
	private Integer maxWeight;
	private Integer currentWeight;
	private ArrayList<ArrayList<FreightContainer>> manifest;
	
	/**
	 * Constructs a new cargo manifest in preparation for a voyage.
	 * When a cargo manifest is constructed the specific cargo
	 * parameters for the voyage are set, including the number of
	 * stack spaces available on the deck (which depends on the deck configuration
	 * for the voyage), the maximum allowed height of any stack (which depends on
	 * the weather conditions expected for the
	 * voyage), and the total weight of containers allowed onboard (which depends
	 * on the amount of ballast and fuel being carried).
	 * 
	 * @param numStacks the number of stacks that can be accommodated on deck
	 * @param maxHeight the maximum allowable height of any stack
	 * @param maxWeight the maximum weight of containers allowed on board 
	 * (in tonnes)
	 * @throws ManifestException if negative numbers are given for any of the
	 * parameters
	 */
	public CargoManifest(Integer numStacks, Integer maxHeight, Integer maxWeight)
	throws ManifestException {
		if (numStacks < 0) {
			throw new ManifestException("The number of stacks cannot be negative.");
		}
		if (maxHeight < 0) {
			throw new ManifestException("The maximum height of each stack cannot be negative.");
		}
		if (maxWeight < 0) {
			throw new ManifestException("The maximum weight of containers cannot be negative.");
		}
		this.numStacks = numStacks;
		this.maxHeight = maxHeight;
		this.maxWeight = maxWeight;
		this.currentWeight = 0;
		this.manifest = new ArrayList<ArrayList<FreightContainer>>(numStacks);
		for (int i = 0; i < numStacks; i++) {
			ArrayList<FreightContainer> stack = new ArrayList<FreightContainer>(maxHeight);
			this.manifest.add(stack);
		}
	}

	/**
	 * Loads a freight container onto the ship, provided that it can be
	 * accommodated within the five rules set by the captain.
	 * 
	 * @param newContainer the new freight container to be loaded
	 * @throws ManifestException if adding this container would exceed
	 * the ship's weight limit; if a container with the same code is
	 * already on board; or if no suitable space can be found for this
	 * container
	 */
	public void loadContainer(FreightContainer newContainer) throws ManifestException {
		boolean noSpace = false;
		if (containerIsNull(newContainer)) {
			throw new ManifestException("A container cannot be null.");
		}
		if (exceedMaxWeight(newContainer)) {
			throw new ManifestException("Adding this container would exceed maximum weight limit.");
		}
		if (sameContainerCodeOnBoard(newContainer)) {
			throw new ManifestException("A container with the same code is already on board.");
		}
		for (int i = 0; i < this.manifest.size(); i++) {
			if (this.manifest.get(i).isEmpty()) {
				this.manifest.get(i).add(newContainer);
				this.currentWeight += newContainer.getGrossWeight();
				return;
			} else if (!this.manifest.get(i).isEmpty() &&
					    this.manifest.get(i).size() < this.maxHeight) {
				if (newContainer.getClass().equals(this.manifest.get(i).get(0).getClass())) {
					this.manifest.get(i).add(newContainer);
					this.currentWeight += newContainer.getGrossWeight();
					return;
				}
			}
		}
		noSpace = true;
		if (noSpace) {
			throw new ManifestException("No suitable space can be found for this container.");
		}
	}

	/**
	 * Unloads a particular container from the ship, provided that
	 * it is accessible (i.e., on top of a stack).
	 * 
	 * @param containerId the code of the container to be unloaded
	 * @throws ManifestException if the container is not accessible because
	 * it's not on the top of a stack (including the case where it's not on board
	 * the ship at all)
	 */
	public void unloadContainer(ContainerCode containerId) throws ManifestException {
		int stackIndex = 0;
		int heightIndex = 0;
		if (containerIsNotOnBoard(containerId)) {
			throw new ManifestException("The specified container is not on board.");
		}
		for (int i = 0; i < this.manifest.size(); i++) {
			if (notEmpty(i)) {
				for (int j = 0; j < this.manifest.get(i).size(); j++) {
					if (this.manifest.get(i).get(j).getCode().equals(containerId)) {
						stackIndex = i;
						heightIndex = j;
					}
				}
			}
		}
		if (containerIsOnTopOfStack(stackIndex, heightIndex)) {
			this.currentWeight -= this.manifest.get(stackIndex).get(heightIndex).getGrossWeight();
			this.manifest.get(stackIndex).remove(heightIndex);
		} else {
			throw new ManifestException("The container is not accessible.");
		}
	}

	
	/**
	 * Returns which stack holds a particular container, if any.  The
	 * container of interest is identified by its unique
	 * code.  Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the number of the stack with the container in it, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer whichStack(ContainerCode queryContainer) {
		if (codeIsNotNull(queryContainer)) {
			for (int i = 0; i < this.manifest.size(); i++) {
				if (notEmpty(i)) {
					for (int j = 0; j < this.manifest.get(i).size(); j++) {
						if (this.manifest.get(i).get(j).getCode().equals(queryContainer)) {
							return i;
						}
					}
				}
			}
		}
		return null;
	}

	
	/**
	 * Returns how high in its stack a particular container is.  The container of
	 * interest is identified by its unique code.  Height is measured in the
	 * number of containers, counting from zero.  Thus the container at the bottom
	 * of a stack is at "height" 0, the container above it is at height 1, and so on.
	 * Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the container's height in the stack, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer howHigh(ContainerCode queryContainer) {
		if (codeIsNotNull(queryContainer)) {
			for (int i = 0; i < this.manifest.size(); i++) {
				if (notEmpty(i)) {
					for (int j = 0; j < this.manifest.get(i).size(); j++) {
						if (this.manifest.get(i).get(j).getCode().equals(queryContainer)) {
							return j;
						}
					}
				}
			}
		}
		return null;
	}


	/**
	 * Returns the contents of a particular stack as an array,
	 * starting with the bottommost container at position zero in the array.
	 * 
	 * @param stackNo the number of the stack of interest
	 * @return the stack's freight containers as an array
	 * @throws ManifestException if there is no such stack on the ship
	 */
	public FreightContainer[] toArray(Integer stackNo) throws ManifestException {
		if (noSuchStack(stackNo)) {
			throw new ManifestException("There is no such stack on the ship");
		}
		ArrayList<FreightContainer> specifiedStack = this.manifest.get(stackNo);
		FreightContainer[] stackOfInterest = new FreightContainer[specifiedStack.size()];
		if (specifiedStack.isEmpty()) {
			return stackOfInterest;
		}
		for (int i = 0; i < specifiedStack.size(); i++) {
			stackOfInterest[i] = specifiedStack.get(i);
		}
		return stackOfInterest;
	}

	
	/* ***** toString methods added to support the GUI ***** */
	
	public String toString(ContainerCode toFind) {
		String toReturn = "";
		for (int i = 0; i < manifest.size(); ++i) {
			ArrayList<FreightContainer> currentStack = manifest.get(i);
			toReturn += "|";
			for (int j = 0; j < currentStack.size(); ++j) {
				if (toFind != null && currentStack.get(j).getCode().equals(toFind))
					toReturn += "|*" + currentStack.get(j).getCode().toString() + "*|";
				else
					toReturn += "| " + currentStack.get(j).getCode().toString() + " |";
			}
			if (currentStack.size() == 0)
				toReturn +=  "|  ||\n";
			else
				toReturn += "|\n";
		}
		return toReturn;
	}

	@Override
	public String toString() {
		return toString(null);
	}
	
	/**
	 * Returns <code>true</code> if adding this container would exceed maximum
	 * weight limit.
	 * 
	 * @param newContainer the new freight container to be loaded
	 * @return <code>true</code> if adding this container would exceed maximum 
	 * weight limit, <code>false</code> otherwise.
	 */
	private boolean exceedMaxWeight(FreightContainer newContainer) {
		return this.currentWeight + newContainer.getGrossWeight() > this.maxWeight;
	}
	
	/**
	 * Returns <code>true</code> if a container with the same code is already 
	 * on board.
	 * 
	 * @param newContainer the new freight container to be loaded
	 * @return <code>true</code> if a container with the same code is already 
	 * on board, <code>false</code> otherwise.
	 */
	private boolean sameContainerCodeOnBoard(FreightContainer newContainer) {
		for (int i = 0; i < this.manifest.size(); i++) {
			if (notEmpty(i)) {
				for (int j = 0; j < this.manifest.get(i).size(); j++) {
					if (this.manifest.get(i).get(j).getCode().equals(newContainer.getCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns <code>true</code> if a container with the specified code is 
	 * on board.
	 * 
	 * @param containerId the code of the container
	 * @return <code>true</code> if a container with the specified code is  
	 * on board, <code>false</code> otherwise.
	 */
	private boolean containerIsNotOnBoard(ContainerCode containerId) {
		if (codeIsNotNull(containerId)) {
			for (int i = 0; i < this.manifest.size(); i++) {
				if (notEmpty(i)) {
					for (int j = 0; j < this.manifest.get(i).size(); j++) {
						if (this.manifest.get(i).get(j).getCode().equals(containerId)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns <code>true</code> if the specified container is on the top 
	 * of a stack.
	 * 
	 * @param stackIndex the stack that contains the specified container
	 * @param heightIndex the height value of the specified container
	 * @return <code>true</code> if the specified container is on the top 
	 * of a stack, <code>false</code> otherwise.
	 */
	private boolean containerIsOnTopOfStack(int stackIndex, int heightIndex) {
		return this.manifest.get(stackIndex).get(heightIndex) == 
			   this.manifest.get(stackIndex).get(this.manifest.get(stackIndex).size() - 1);
	}
	
	/**
	 * Returns <code>true</code> if there is no such stack on the ship.
	 * 
	 * @param stackNo the specified stack number
	 * @return <code>true</code> if there is no such stack on the ship, 
	 * <code>false</code> otherwise.
	 */
	private boolean noSuchStack(Integer stackNo) {
		return stackNo < 0 || stackNo > this.numStacks - 1;
	}
	
	/**
	 * Returns <code>true</code> if the current stack is not empty.
	 * 
	 * @param currentStack the specified stack number
	 * @return <code>true</code> if the current stack is not empty, 
	 * <code>false</code> otherwise.
	 */
	private boolean notEmpty(int currentStack) {
		return !this.manifest.get(currentStack).isEmpty();
	}
	
	/**
	 * Returns <code>true</code> if the specified container object is null.
	 * 
	 * @param container the specified container object
	 * @return <code>true</code> if the specified container object is null,
	 * <code>false</code> otherwise.
	 */
	private boolean containerIsNull(FreightContainer container) {
		return container == null;
	}
	
	/**
	 * Returns <code>true</code> if the specified container code
	 * is not null.
	 * 
	 * @param code the specified container code
	 * @return <code>true</code> if the specified container code
	 * is not null, <code>false</code> otherwise.
	 */
	private boolean codeIsNotNull(ContainerCode code) {
		return code != null;
	}
}
