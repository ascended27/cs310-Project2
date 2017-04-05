package src;

public class Node {

	// Declare variables
	Node right, down;
	Value val;

	// Constructor for empty cell.
	public Node(int print_width) {
		val = new Value(print_width);
		right = down = null;
	}

	// Constructor for double cell.
	public Node(double num, int print_width) {
		val = new Value(num, print_width);
		right = down = null;
	}

	// Constructor for string cell.
	public Node(String s, int print_width) {
		val = new Value(s, print_width);
		right = down = null;
	}

}
