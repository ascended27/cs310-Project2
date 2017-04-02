package src;

public class Cell {

	Cell right, down;

	Value val;

	public Cell(int print_width) {
		val = new Value(print_width);
		right = down = null;
	}

	public Cell(double num,int print_width) {
		val = new Value(num,print_width);
		right = down = null;
	}
	
	public Cell(String s,int print_width){
		val = new Value(s,print_width);
		right = down = null;
	}
	
	public Cell(Value val, Cell right, Cell down) {
		this.val = val;
		this.right = right;
		this.down = down;
	}
	
}
