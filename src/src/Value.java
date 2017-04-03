package src;

public class Value {
	// Declare Variables
	private double dval;
	private String sval;
	private String tag;
	private int print_width;

	// Constructor for empty cell
	public Value(int print_width) {
		dval = 0;
		sval = null;
		tag = "STRING";
		this.print_width = print_width;
	}

	// Constructor for string cell
	public Value(String val, int print_width) {
		dval = 0;
		sval = val;
		tag = "STRING";
		this.print_width = print_width;
	}

	// Constructor for double cell
	public Value(double val, int print_width) {
		dval = val;
		sval = "";
		tag = "DBL";
		this.print_width = print_width;
	}

	// Adds Value val to this Value. If an invalid operation occurs the
	// destination value is returned so that the destination cell is maintained.
	public Value plus(Value val, Value dest) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value(this.print_width);
		rtnVal.tag = "INVALID";

		// If both tags are double then do addition.
		if (tag.equals(val.tag) && tag.equals("DBL")) {
			// Set the return Value's dval to the sum of this and val's dvals.
			rtnVal.dval = dval + val.dval;
			// Set the return Value's tag to "DBL" to signify that it is a
			// double.
			rtnVal.tag = "DBL";
			// Return the new Value.
			return rtnVal;
		}
		// Otherwise an invalid operation occurred.
		else
			rtnVal.tag = "INVALID";

		// Check if an invalid operation occurred.
		return checkInvalid(rtnVal, dest);

	}

	// Subtracts Value val to this Value. If an invalid operation occurs the
	// destination value is returned so that the destination cell is maintained.
	public Value minus(Value val, Value dest) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value(this.print_width);
		rtnVal.tag = "INVALID";

		// If both tags are double then do addition.
		if (tag.equals(val.tag) && tag.equals("DBL")) {
			// Set the return Value's dval to the sum of this and val's dvals.
			rtnVal.dval = dval - val.dval;
			// Set the return Value's tag to "DBL" to signify that it is a
			// double.
			rtnVal.tag = "DBL";
			// Return the new Value.
		}
		// Otherwise an invalid operation occurred.
		else
			rtnVal.tag = "INVALID";

		return checkInvalid(rtnVal, dest);
	}

	// Multiplies Value val to this Value. If an invalid operation occurs the
	// destination value is returned so that the destination cell is maintained.
	public Value star(Value val, Value dest) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value(this.print_width);
		rtnVal.tag = "INVALID";

		// If both tags are double then do addition.
		if (tag.equals(val.tag) && tag.equals("DBL")) {
			// Set the return Value's dval to the sum of this and val's dval.
			rtnVal.dval = dval * val.dval;
			// Set the return Value's tag to "DBL" to signify that it is a
			// double.
			rtnVal.tag = "DBL";
			// Return the new Value.
		}
		// Otherwise an invalid operation occurred.
		else
			rtnVal.tag = "INVALID";

		return checkInvalid(rtnVal, dest);
	}

	// Divides Value val to this Value. If an invalid operation occurs the
	// destination value is returned so that the destination cell is maintained.
	public Value slash(Value val, Value dest) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value(print_width);

		// If both tags are double then do division.
		if (tag.equals(val.tag) && tag.equals("DBL")) {
			if (val.dval == 0)
				rtnVal.tag = "INVALID";
			else {
				// Set the return Value's dval to the result of this and val's
				// dval.
				rtnVal.dval = dval / val.dval;
				// Set the return Value's tag to "DBL" to signify that it is a
				// double.
				rtnVal.tag = "DBL";
				// Return the new Value.
			}
		}
		// Otherwise an invalid operation has occurred
		else
			rtnVal.tag = "INVALID";

		return checkInvalid(rtnVal, dest);

	}

	// Returns val if it is valid otherwise returns dest.
	private Value checkInvalid(Value val, Value dest) {
		// If an invalid operation occurred return a Value with the same
		// value and tag as the destination because no operation is performed
		if (val.tag.equals("INVALID")) {
			val.tag = dest.tag;
			val.sval = dest.sval;
			val.dval = dest.dval;
		}
		// Return the valid value
		return val;
	}

	@Override
	// Prints out a formating string of the proper data field in this Value.
	public String toString() {
		// If the value is a string then pass format the sval.
		if (tag.equals("STRING")) {
			// If sval is null then format an empty string.
			if (sval == null)
				return format("");
			// else return the formatted sval.
			return format(sval);
		}
		// Else if the value is a double then convert it to a string and return
		// a formated version of it.
		else if (tag.equals("DBL")) {
			String tmp = "" + dval;
			return format(tmp);
		}
		// Otherwise format an empty string.
		else
			return format("");

	}

	// Formats the passed string to the proper print_width of the grid.
	private String format(String val) {
		// Initialize a new StringBuilder to store our formated string.
		StringBuilder sb = new StringBuilder();

		// Get the length of the passed string.
		int length = val.length();

		// If the length of value is longer than the print_width then get the
		// substring from 0 to the grid's print_width.
		if (length > print_width)
			sb.append(val.substring(0, print_width));
		
		// Otherwise append the val and the proper number of spaces to fit the
		// grid.
		else {
			sb.append(val);
			for (int i = 0; i < print_width - length; i++)
				sb.append(" ");
		}

		return sb.toString();
	}

}
