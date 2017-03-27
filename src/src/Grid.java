package src;

@SuppressWarnings("unused")
public class Grid {

	Cell head;
	private int rowNum, colNum, print_width;

	public Grid(int print_width) {
		head = null;
		rowNum = colNum = 0;
		this.print_width = print_width;
	}

	public void init(int row, int col){
		head = new Cell(0);
		for(int i = 0; i < row; i++)
			addRow(0);
		
		
		for(int i = 0; i < col; i++)
			addColumn(0);
	}
	
	public void print(){
		
		Cell cur = head;
		Cell tmpRow;
		System.out.printf("      ");
		for(int i = 0; i < colNum; i++)
			System.out.print("Col " + i + " ");
		
		System.out.println();
		
		for(int i = 0; i < rowNum; i++){		
			tmpRow = cur;
			System.out.print("Row " + i + " ");
			for(int j = 0; j < colNum; j++){
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
			System.out.println();
			cur = tmpRow.down;
		}
			
	}
	
	// Inserts a new row before the row specified
	// First row starts at 0.
	public boolean addRow(int row) {
		Cell current = getRow(row);
		if(current == null)
			return false;
		
		insertRow(current);
		rowNum++;

		return true;
		
	}

	// Inserts a new column before the column specified
	// First column starts at 0
	public boolean addColumn(int col) {
		Cell current = getCol(col);
		if(current == null)
			return false;
		
		insertColumn(current);
		colNum++;
		
		return true;
	}

	private void insertColumn(Cell current) {
		if (current != null) {
			Cell toAdd = new Cell();

			// If current's right is null then we are at the farthest right
			// column.
			if (current.right == null) {
				// Set current's right to the new cell. Don't need to worry bout
				// toAdd's right since we are inserting at the end of the grid.
				current.right = toAdd;

				// Recursively add the next Cell in the column
				insertColumn(current.down);

				// If there is a Cell below the current Cell then we need to
				// assign current's right Cell a down Cell.
				if (current.down != null)
					current.right.down = current.down.right;
			}
			// Otherwise we are inside the grid somewhere
			else {
				// Set current's right to the new cell. Now we need to worry
				// about the columns to the right so set toAdd's right to
				// current's right.
				toAdd.right = current.right;
				current.right = toAdd;

				// Recursively add the next Cell in the column
				insertColumn(current.down);

				// If there is a Cell below the current Cell then we need to
				// assign current's right Cell a down Cell.
				if (current.down != null)
					current.right.down = current.down.right;
			}
		}
	}
	
	private void insertRow(Cell current){
		if (current != null) {
			Cell toAdd = new Cell();

			// If current's down is null then we are at the farthest right
			// column.
			if (current.down == null) {
				// Set current's down to the new cell. Don't need to worry bout
				// toAdd's down since we are inserting at the bottom of the grid.
				current.down = toAdd;

				// Recursively add the next Cell in the row
				insertRow(current.right);

				// If there is a Cell below the current Cell then we need to
				// assign current's down Cell a right Cell.
				if (current.right != null)
					current.down.right = current.right.down;
			}
			// Otherwise we are inside the grid somewhere
			else {
				// Set current's down to the new cell. Now we need to worry
				// about the columns to the down so set toAdd's down to
				// current's down.
				toAdd.down = current.down;
				current.down = toAdd;

				// Recursively add the next Cell in the column
				insertColumn(current.right);

				// If there is a Cell to the right of the current Cell then we need to
				// assign current's down Cell a right Cell.
				if (current.right != null)
					current.down.right = current.right.down;
			}
		}
	}

	public boolean addNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);

		// If either cell is null, it doesn't exist
		if (a == null || b == null)
			return false;

		return true;
	}

	public boolean subNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);

		// If either cell is null, it doesn't exist
		if (a == null || b == null)
			return false;

		return true;
	}

	public boolean mulNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);

		// If either cell is null, it doesn't exist
		if (a == null || b == null)
			return false;

		return true;
	}

	public boolean divNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);

		// If either cell is null, it doesn't exist
		if (a == null || b == null)
			return false;

		return true;
	}

	private Cell getCell(int row, int col) {
		if ((row >= rowNum) && (col >= colNum))
			return null;

		Cell current = head;

		// Get cell
		for (int i = 0; i < row; i++)
			current = current.down;

		for (int j = 0; j < col; j++)
			current = current.right;

		return current;
	}

	private Cell getCol(int col) {
		if (col > colNum)
			return null;

		Cell current = head;

		for (int i = 0; i < col; i++)
			current = current.right;

		return current;
	}
	
	private Cell getRow(int row) {
		if (row > rowNum)
			return null;

		Cell current = head;

		for (int i = 0; i < row; i++)
			current = current.down;

		return current;
	}
}
