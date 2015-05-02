/**
 * @author Thanat Chokwijitkul n9234900 
 */
package asgn2Codes;

import asgn2Exceptions.InvalidCodeException;

/* Note to self:
 * Use option "-noqualifier java.lang" when generating Javadoc from this
 * file so that, for instance, we get "String" instead of
 * "java.lang.String".
 */

/**
 * This class provides cargo container codes in a format similar to that
 * prescribed by international standard ISO 6346.  (The only difference
 * is that we use a simpler algorithm for calculating the check digit.)
 * <p>
 * A container code is an 11-character string consisting of the following
 * fields:
 * <ul>
 * <li>
 * An <em>Owner Code</em> which uniquely identifies the company that owns
 * the container.  The owner code consists of three upper-case letters.
 * (To ensure uniqueness in practice, owner codes must be registered at
 * the <em>Bureau International des Containers</em> in Paris.)
 * </li>
 * <li>
 * A <em>Category Identifier</em> which identifies the type of container.
 * The identifier is one of three letters, 'U', 'J' or 'Z'.  For our
 * purposes the category identifier is <em>always</em> 'U', which denotes a
 * freight container.
 * </li>
 * <li>
 * A <em>Serial Number</em> used by the owner to uniquely identify the
 * container.  The number consists of six digits.
 * </li>
 * <li>
 * A <em>Check Digit</em> used to ensure that the number has not been
 * transcribed incorrectly.  It consists of a single digit derived from the
 * other 10 characters according to a specific algorithm, described below.
 * </li>
 * </ul>
 * <p>
 * <strong>Calculating the check digit:</strong> ISO 6346 specifies a
 * particular algorithm for deriving the check digit from the
 * other 10 characters in the code.  However, because this algorithm is
 * rather complicated, we use a simpler version for this assignment.
 * Our algorithm works as follows:
 * <ol>
 * <li>
 * Each of the 10 characters in the container code (excluding the check
 * digit) is treated as a number.  The digits '0' to '9' each have
 * their numerical value.  The upper case alphabetic letters 'A' to
 * 'Z' are treated as the numbers '0' to '25', respectively.
 * </li>
 * <li>
 * These 10 numbers are added up.
 * </li>
 * <li>
 * The check digit is the least-significant digit in the sum produced
 * by the previous step.
 * </li>
 * </ol>
 * For example, consider container code <code>MSCU6639871</code> which
 * has owner code <code>MSC</code>, category identifier <code>U</code>,
 * serial number <code>663987</code> and check digit <code>1</code>.  We can 
 * confirm that this code is valid by treating the letters as numbers (e.g.,
 * '<code>M</code>' is 12, '<code>S</code>' is 18, etc) and calculating the
 * following sum.
 * <p>
 * <center>
 * 12 + 18 + 2 + 20 + 6 + 6 + 3 + 9 + 8 + 7 = 91
 * </center>
 * <p>
 * The check digit is then the least-sigificant digit in the number 91,
 * i.e., '<code>1</code>', thus confirming that container code 
 * <code>MSCU6639871</code> is valid.
 * 
 * @author CAB302 
 * @version 1.0
 */ 
public class ContainerCode {
	
	private static final int CODE_LENGTH = 11;
	private static final int ASCII_INT_DIFF = 48;
	private static final int ASCII_CHAR_DIFF = 65;
	private static final int CATEGORY_IDENTIFIER_POSITION = 3;
	private static final char CATEGORY_IDENTIFIER = 'U';
	private static final int SERIAL_NUMBER_POSITION = 4;

	private String code;

	/**
	 * Constructs a new container code.
	 * 
	 * @param code the container code as a string
	 * @throws InvalidCodeException if the container code is not eleven characters long; if the
	 * Owner Code does not consist of three upper-case letters; if the
	 * Category Identifier is not 'U'; if the Serial Number does not consist
	 * of six digits; or if the Check Digit is incorrect.
	 */
	public ContainerCode(String code) throws InvalidCodeException {
		if (invalidLength(code)) {
			throw new InvalidCodeException("The container code is not eleven characters long.");
		}
		if (invalidOwnerCode(code)) {
			throw new InvalidCodeException("The Owner Code does not consist of three upper-case letters.");
		}
		if (invalidCategoryIdentifier(code)) {
			throw new InvalidCodeException("The Category Identifier is not 'U'.");
		}
		if (invalidSerialNumber(code)) {
			throw new InvalidCodeException("The Serial Number does not consist of six digits.");
		}
		if (invalidCheckDigit(code)) {
			throw new InvalidCodeException("The Check Digit is incorrect.");
		}
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.code;
	}

	
	/**
	 * Returns true iff the given object is a container code and has an
	 * identical value to this code.
	 * 
	 * @param obj an object
	 * @return true if the given object is a container code with the
	 * same string value as this code, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		return this.code.equals(obj.toString());
	}
	
	/**
	 * Returns <code>true</code> if the container code is not eleven characters long.
	 * 
	 * @return <code>true</code> if the length of the container code is not eleven
	 * character long, <code>false</code> otherwise.
	 */
	private boolean invalidLength(String code) {
		return code.length() != CODE_LENGTH;
	}
	
	/**
	 * Returns <code>true</code> if the owner code does not consist of three
	 * upper-case characters.
	 * 
	 * @return <code>true</code> if the owner code does not consist of three
	 * upper-case characters, <code>false</code> otherwise.
	 */
	private boolean invalidOwnerCode(String code) {
		return !code.substring(0, CATEGORY_IDENTIFIER_POSITION).equals
			   (code.substring(0, CATEGORY_IDENTIFIER_POSITION).toUpperCase());
	}
	
	/**
	 * Returns <code>true</code> if the Category Identifier is not 'U'.
	 * 
	 * @return <code>true</code> if the Category Identifier is not 'U',
	 * <code>false</code> otherwise.
	 */
	private boolean invalidCategoryIdentifier(String code) {
		return code.charAt(CATEGORY_IDENTIFIER_POSITION) != CATEGORY_IDENTIFIER;
	}
	
	/**
	 * Returns <code>true</code> if the Serial Number does not consist of six digits.
	 * 
	 * @return <code>true</code> if the Serial Number does not consist of six digits,
	 * <code>false</code> otherwise.
	 */
	private boolean invalidSerialNumber(String code) {
		for (int i = SERIAL_NUMBER_POSITION; i < code.length()-1; i++) {
			if (!Character.isDigit(code.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns <code>true</code> if if the Check Digit is incorrect.
	 * 
	 * @return <code>true</code> if the Check Digit is incorrect,
	 * <code>false</code> otherwise.
	 */
	private boolean invalidCheckDigit(String code) {
		int ascii;
		int sum = 0;
		int lsd; // Least Significant Digit (LSD)
		for (int i = 0; i < code.length() - 1; i++) {
			if (Character.isDigit(code.charAt(i))) {
				ascii = (int) code.charAt(i) - ASCII_INT_DIFF;
				sum += ascii;
			} else {
				ascii = (int) code.charAt(i) - ASCII_CHAR_DIFF;
				sum += ascii;
			}
		}
		lsd = sum;
		while (sum > 0) {
			if (lsd > sum % 10) {
				lsd = sum % 10;
			}
			sum /= 10;
		}
		return code.charAt(code.length()-1) != (char) ('0' + lsd);
	}

}

