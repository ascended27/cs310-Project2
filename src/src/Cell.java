package src;

public class Cell {

	Cell right, down;

	Value val;

	public Cell() {
		val = new Value();
		right = down = null;
	}

	public Cell(int num) {
		val = new Value(num);
		right = down = null;
	}
	
	public Cell(Value val, Cell right, Cell down) {
		this.val = val;
		this.right = right;
		this.down = down;
	}
	
}
