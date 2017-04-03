package src;

public class Cell {

	// Declare variables
	Cell right, down;
	Value val;

	// Constructor for empty cell.
	public Cell(int print_width) {
		val = new Value(print_width);
		right = down = null;
	}

	// Constructor for double cell.
	public Cell(double num, int print_width) {
		val = new Value(num, print_width);
		right = down = null;
	}

	// Constructor for string cell.
	public Cell(String s, int print_width) {
		val = new Value(s, print_width);
		right = down = null;
	}

}
