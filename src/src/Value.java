package src;

public class Value {
	private double dval;
	private String sval;
	// TODO: Convert tag to enum?
	private String tag;

	public Value() {
		dval = 0;
		sval = "";
		tag = "STRING";
	}

	public Value(String val) {
		dval = 0;
		sval = val;
		tag = "STRING";
	}

	public Value(double val) {
		dval = val;
		sval = "";
		tag = "DBL";
	}

	public Value plus(Value val) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value();
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

		// Otherwise an invalid addition was attempted so return
		// a invalid Value.
		return rtnVal;
	}

	public Value minus(Value val) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value();
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

		// Otherwise an invalid addition was attempted so return
		// a invalid Value.
		return rtnVal;
	}

	public Value star(Value val) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value();
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

		// Otherwise an invalid addition was attempted so return
		// a invalid Value.
		return rtnVal;
	}

	public Value slash(Value val) {
		// Initialize a new Value to return. Default it's tag to INVALID.
		Value rtnVal = new Value();
		rtnVal.tag = "INVALID";

		// If both tags are double then do division.
		if (tag.equals(val.tag) && tag.equals("DBL")) {
			// Set the return Value's dval to the result of this and val's dval.
			rtnVal.dval = dval / val.dval;
			// Set the return Value's tag to "DBL" to signify that it is a
			// double.
			rtnVal.tag = "DBL";
			// Return the new Value.
		}

		// Otherwise an invalid addition was attempted so return
		// a invalid Value.
		return rtnVal;
	}

	@Override
	public String toString() {
		if (tag.equals("STRING"))
			return sval;
		else if (tag.equals("DBL")) {
			//TODO: Format this dynamically?
			return String.format("%.2f",dval);
		} else
			return "";

	}

}
