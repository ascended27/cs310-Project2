package src;

@SuppressWarnings("unused")
public class Grid {

	Cell head;
	private int rowNum, colNum, print_width;

	public Grid(int print_width, int rowStart, int colStart) {
		head = null;
		rowNum = colNum = 0;
		this.print_width = print_width;
		init(rowStart, colStart);

	}

	private void init(int row, int col) {
		head = new Cell(print_width);
		for (int i = 0; i < row; i++)
			addRow(0);

		for (int i = 0; i < col; i++)
			addColumn(0);
	}

	public void print() {

		Cell cur = head;
		Cell tmpRow;
		int length;
		for (int i = 0; i <= print_width; i++)
			System.out.print(" ");

		for (int i = 0; i < colNum; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("Col " + i);
			if (sb.length() < print_width) {
				for (int j = sb.length(); j <= print_width; j++)
					sb.append(" ");
			}
			System.out.print(sb.toString());
		}

		System.out.println();

		for (int i = 0; i < rowNum; i++) {
			tmpRow = cur;
			System.out.print("Row " + i + " ");
			StringBuilder sb = new StringBuilder();
			sb.append("Row " + i + " ");
			for (int j = sb.length(); j < print_width; j++)
				sb.append(" ");
			for (int k = 0; k < colNum; k++) {

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
		if (current == null)
			return false;

		insertRow(current);
		rowNum++;

		return true;

	}

	public boolean deleteRow(int row) {
		if (rowNum == 1) {
			System.out.printf("\n\nThere is must always be one row in the grid.");
			return false;
		}

		Cell current;

		if (row == 0)
			current = head;
		else
			current = getRow(row - 1);

		if (current == null) {
			System.out.printf("\n\nThe row does not exist\n\n");
			return false;
		}

		removeRow(current);
		rowNum--;

		return true;
	}

	private void insertRow(Cell current) {
		if (current != null) {
			Cell toAdd = new Cell(print_width);

			// If current's down is null then we are at the farthest right
			// column.
			if (current.down == null) {
				// Set current's down to the new cell. Don't need to worry bout
				// toAdd's down since we are inserting at the bottom of the
				// grid.
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

				// If there is a Cell to the right of the current Cell then we
				// need to
				// assign current's down Cell a right Cell.
				if (current.right != null)
					current.down.right = current.right.down;
			}
		}
	}

	private void removeRow(Cell current) {
		if (current == head) {
			head = head.down;
		} else if (current != null) {

			// If current's right is null then we are at the farthest right
			// row.
			if (current.down == null) {
				// Set current's down to null
				current.down = null;

				// Recursively remove the next Cell in the row
				removeRow(current.right);

			}
			// Otherwise we are inside the grid somewhere
			else {
				// Set current's down to the next row below the one being
				// removed
				current.down = current.down.down;

				// Recursively add the next Cell in the row.
				removeRow(current.right);

			}
		}
	}

	// Inserts a new column before the column specified
	// First column starts at 0,
	public boolean addColumn(int col) {
		Cell current = getCol(col);
		if (current == null)
			return false;

		insertColumn(current);
		colNum++;

		return true;
	}

	public boolean deleteColumn(int col) {
		if (rowNum == 1) {
			System.out.printf("\n\nThere is must always be one column in the grid.");
			return false;
		}

		Cell current;
		if (col == 0)
			current = head;
		else
			current = getCol(col - 1);

		if (current == null) {
			System.out.printf("\n\nThe column does not exist\n\n");
			return false;
		}

		removeColumn(current);
		colNum--;

		return true;
	}

	private void insertColumn(Cell current) {
		if (current != null) {
			Cell toAdd = new Cell(print_width);

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

	private void removeColumn(Cell current) {
		if (current == head) {
			head = head.right;
		} else if (current != null) {
			// If current's right is null then we are at the farthest right
			// column.
			if (current.right == null) {
				// Set current's right to the new cell. Don't need to worry bout
				// toAdd's right since we are inserting at the end of the grid.
				current.right = null;

				// Recursively add the next Cell in the column
				removeColumn(current.down);

			}
			// Otherwise we are inside the grid somewhere
			else {
				// Set current's right to the new cell. Now we need to worry
				// about the columns to the right so set toAdd's right to
				// current's right.
				current.right = current.right.right;

				// Recursively add the next Cell in the column
				removeColumn(current.down);

			}
		}
	}

	// Adds the cells store at rowA, colA and rowB, colB and stores them in
	// rowC, colC. If Cell a, b, or c are null then the cell doesn't exist and
	// the operation can't be performed. So on the driver if this fails
	// re-prompt the user for correct input.
	public boolean addNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {

		// Get the cells to operate on.
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);
		Cell c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist.
		if (a == null || b == null || c == null)
			return false;

		c.val = a.val.plus(b.val, c.val);

		return true;
	}

	// Subtracts the cells store at rowA, colA and rowB, colB and stores them in
	// rowC, colC. If Cell a, b, or c are null then the cell doesn't exist and
	// the operation can't be performed. So on the driver if this fails
	// re-prompt the user for correct input.
	public boolean subNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);
		Cell c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		c.val = a.val.minus(b.val, c.val);

		return true;
	}

	// Multiplies the cells store at rowA, colA and rowB, colB and stores them
	// in rowC, colC. If Cell a, b, or c are null then the cell doesn't exist
	// and
	// the operation can't be performed. So on the driver if this fails
	// re-prompt the user for correct input.
	public boolean mulNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);
		Cell c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		c.val = a.val.star(b.val, c.val);

		return true;
	}

	// Divides the cells store at rowA, colA and rowB, colB and stores them in
	// rowC, colC. If Cell a, b, or c are null then the cell doesn't exist and
	// the operation can't be performed. So on the driver if this fails
	// re-prompt the user for correct input.
	public boolean divNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Cell a = getCell(rowA, colA);
		Cell b = getCell(rowB, colB);
		Cell c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		c.val = a.val.slash(b.val, c.val);

		return true;
	}

	public boolean addRows(int rowA, int rowB, int rowC) {
		boolean success = false;

		for (int curCol = 0; curCol < colNum; curCol++) {
			success = addNodes(rowA, curCol, rowB, curCol, rowC, curCol);
			if (!success)
				break;
		}

		return success;
	}

	public boolean subRows(int rowA, int rowB, int rowC) {
		boolean success = false;

		for (int curCol = 0; curCol < colNum; curCol++) {
			success = subNodes(rowA, curCol, rowB, curCol, rowC, curCol);
			if (!success)
				break;
		}

		return success;
	}

	public boolean multRows(int rowA, int rowB, int rowC) {
		boolean success = false;

		for (int curCol = 0; curCol < colNum; curCol++) {
			success = mulNodes(rowA, curCol, rowB, curCol, rowC, curCol);
			if (!success)
				break;
		}

		return success;
	}

	public boolean divRows(int rowA, int rowB, int rowC) {
		boolean success = false;

		for (int curCol = 0; curCol < colNum; curCol++) {
			success = divNodes(rowA, curCol, rowB, curCol, rowC, curCol);
			if (!success)
				break;
		}

		return success;
	}

	public boolean addCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = addNodes(colA, curRow, colB, curRow, colC, curRow);
			if (!success)
				break;
		}

		return success;
	}

	public boolean subCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = subNodes(colA, curRow, colB, curRow, colC, curRow);
			if (!success)
				break;
		}

		return success;
	}

	public boolean multCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = mulNodes(colA, curRow, colB, curRow, colC, curRow);
			if (!success)
				break;
		}

		return success;
	}

	public boolean divCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = divNodes(colA, curRow, colB, curRow, colC, curRow);
			if (!success)
				break;
		}

		return success;
	}

	public boolean fill(int rowA, int colA, int rowB, int colB, String val) {
		double dVal = 0;

		if (rowA > rowB || colA > colB) {
			System.out.printf("From row and column must be less than to row and column\n");
			return false;
		}

		if ((rowA >= rowNum) || (rowB >= rowNum) || (colA >= colNum) || (colB > colNum)) {
			System.out.printf("row and columns must be within the bounds of the grid");
			return false;
		}

		for (int i = rowA; i <= rowB; i++) {

			for (int j = colA; j <= colB; j++) {
				assignNode(i, j, val);
			}
		}

		return true;

	}

	public boolean number(int rowA, int colA, int rowB, int colB) {
		int count = 0;
		if (rowA > rowB || colA > colB) {
			System.out.printf("From row and column must be less than to row and column\n");
			return false;
		}

		if ((rowA >= rowNum) || (rowB >= rowNum) || (colA >= colNum) || (colB > colNum)) {
			System.out.printf("row and columns must be within the bounds of the grid");
			return false;
		}

		for (int i = rowA; i <= rowB; i++) {
			for (int j = colA; j <= colB; j++) {
				assignNode(i, j, "" + count++);
			}
		}

		return true;
	}

	public boolean assignNode(int row, int col, String val) {

		Cell a = getCell(row, col);
		double dVal = 0;

		if (!val.startsWith("\"")) {
			dVal = Double.parseDouble(val);
			val = null;
		} else
			val = val.substring(1, val.length());

		if (a == null)
			return false;

		if (val != null)
			a.val = new Value(val, print_width);
		else
			a.val = new Value(dVal, print_width);

		return true;
	}

	private Cell getCell(int row, int col) {
		if ((row > rowNum) || (col > colNum) || (col < 0) || (row < 0))
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
