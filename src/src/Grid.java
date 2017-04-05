package src;

public class Grid {

	// Declare variables
	private Cell head;
	private int rowNum, colNum, print_width;

	/**
	 * Default constructor for grid. Sets private fields and initializes the
	 * grid.
	 * 
	 * @param print_width
	 *            Default width of columns
	 * @param rowStart
	 *            Number of rows to initialize
	 * @param colStart
	 *            Number of columns to initialize
	 */
	public Grid(int print_width, int rowStart, int colStart) {
		head = null;
		rowNum = rowStart;
		colNum = colStart;
		this.print_width = print_width;
		init(rowStart, colStart);

	}

	/**
	 * Initializes the grid with a specified number of rows and columns
	 * 
	 * @param row
	 *            The number of rows to initialize
	 * @param col
	 *            The number of columns to initialize
	 */
	private void init(int row, int col) {
		// Initialize the head node of the grid
		head = new Cell(print_width);
		head.right = head;
		head.down = head;

		Cell tmp = head;
		// Initialize the first row of the grid.
		for (int i = 0; i < col; i++) {
			Cell toAdd = new Cell(print_width);
			toAdd.right = tmp.right;
			toAdd.down = toAdd;
			tmp.right = toAdd;
			tmp = tmp.right;
		}

		// Loop over the passed row number and insert new rows.
		for (int i = 0; i < row - 1; i++)
			insertRow(1);

		// Loop over the passed column number and insert new cows.
		for (int i = 0; i < col; i++)
			insertColumn(1);
	}

	/**
	 * Displays the grid
	 */
	public void print() {
		// Get a pointer to the head node in the grid
		Cell cur = head;

		// Declare a node to hold the front of the row
		Cell tmpRow;

		// Print out the initial white space to offset the column headers
		for (int i = 0; i <= print_width - 1; i++)
			System.out.print(" ");

		// Loop over the header row and print the column number
		for (int i = 0; i < colNum; i++) {
			// Create a new string builder to store the current row
			StringBuilder sb = new StringBuilder();

			// Append the column number
			sb.append("Col " + i);

			// If the length of sb is less than the print width then append
			// white space till it is of that length
			if (sb.length() < print_width) {
				for (int j = sb.length(); j <= print_width; j++)
					sb.append(" ");
			}

			// Print out the header row
			System.out.print(sb.toString());
		}

		// Add a new line to start the next row
		System.out.println();

		// Loop over the rows and print out their values
		for (int i = 0; i < rowNum; i++) {
			// Set tmp row to the start of the row.
			tmpRow = cur;

			// Instantiate a new string builder to store the row.
			StringBuilder sb = new StringBuilder();

			// Append the row number and the white space to fill out it's column
			sb.append("Row " + i + " ");
			for (int j = sb.length(); j < print_width; j++)
				sb.append(" ");

			// Print out the row header
			System.out.print(sb.toString());

			// Print out the values current row
			for (int k = 0; k < colNum; k++) {
				// When current's value is printed it is already formated to fit
				// it's column
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
			// Add a new line to start the next row.
			System.out.println();

			// Move to the next row
			cur = tmpRow.down;
		}
	}

	/**
	 * Inserts a new row before the row specified.
	 * 
	 * @param row
	 *            Row number to insert before.
	 * @return success status of the method.
	 */
	public boolean addRow(int row) {
		// If the row given is greater than the number of rows in the grid then
		// return false
		if (row > rowNum - 1)
			return false;

		// If the row number is 0 then add a row at the head of the grid.
		if (row == 0) {
			// Get the first and last cell in the grid.
			Cell firstRow = head;
			Cell lastRow = getRow(rowNum - 1);

			// Loop over the row inserting new nodes and making the row
			// connections
			for (int i = 0; i < colNum; i++) {
				Cell toAdd = new Cell(print_width);
				lastRow.down = toAdd;
				toAdd.down = firstRow;
				firstRow = firstRow.right;
				lastRow = lastRow.right;
			}

			// Get the last row again so we can loop over the row and make the
			// column connections
			lastRow = getRow(rowNum - 1);

			// Loop over the row and make the column connections
			for (int i = 0; i < colNum; i++) {
				lastRow.down.right = lastRow.right.down;
				lastRow = lastRow.right;
			}

			// Set the head to the new head node
			head = getRow(rowNum);
		}
		// Otherwise we are inserting somewhere else in the grid
		else
			insertRow(row);

		// New row so increment rowNum
		rowNum++;

		// Row should have been added successfully so return true
		return true;

	}

	/**
	 * Deletes the specified row
	 * 
	 * @param row
	 *            Row number to delete
	 * @return success status of the method.
	 */
	public boolean deleteRow(int row) {
		// If the grid only has one row in it then fail. We must always have a
		// row in the grid.
		if (rowNum == 1) {
			System.out.printf("\n\nThere is must always be one row in the grid.");
			return false;
		}

		// If the row given is greater than the number of rows in the grid then
		// return false
		if (row > rowNum - 1) {
			System.out.printf("\n\nThe row does not exist\n");
			return false;
		}

		// If the row specified is the head node then delete the head row and
		// set head to next row down.
		if (row == 0) {
			// Get a pointer the next row
			Cell tmp = head.down;

			// Set the head to the next row
			head = tmp;

			// Get the last row in the grid to make new column links
			Cell lastRow = getRow(rowNum - 1);

			// Loop over the row and set the column links
			for (int i = 0; i < colNum; i++) {
				lastRow.down = tmp;
				lastRow = lastRow.right;
				tmp = tmp.right;
			}

		}
		// Otherwise remove the row without worrying if it is the head node
		else
			removeRow(row - 1);

		// Deleted a row so decrement the rowNum
		rowNum--;

		// Method finished successfully so return true
		return true;
	}

	/**
	 * Inserts a new row before the row specified
	 * 
	 * @param row
	 *            index of the row to be inserted before.
	 */
	private void insertRow(int row) {
		// Declare a pointer to store the current cell in the row we are
		// inserting before.
		Cell preCell;

		// If we are inserting before the head node then we need the cell at the
		// end of the row since we are circularly linked
		if (row == 0)
			preCell = getRow(rowNum - 1);
		// Otherwise we get the row preceding the one we specified
		else
			preCell = getRow(row - 1);

		// Loop over the row adding a new cell and creating column links
		for (int i = 0; i < colNum; i++) {
			Cell toAdd = new Cell(print_width);
			toAdd.down = preCell.down;
			preCell.down = toAdd;
			preCell = preCell.right;
		}

		// Get the first cell in the previous row
		preCell = getRow(row - 1);

		// Set tmp to the next cell in the column
		Cell tmp = preCell.down;

		// Loop over the row setting the row links.
		for (int i = 0; i < colNum; i++) {
			// If the cell to the right of this cell is the first cell in the
			// row then we need to establish a circular link
			if (preCell.right == getRow(rowNum - 1))
				tmp.right = getRow(rowNum - 1).down;
			// Otherwise we set tmp's right cell to the cell down and to the
			// right of preCell
			else {
				tmp.right = preCell.right.down;
			}
			// Move the cells pointers to the next in the row
			preCell = preCell.right;
			tmp = tmp.right;
		}
	}

	/**
	 * Removes the specified row from the grid
	 * 
	 * @param row
	 *            The row to remove
	 */
	private void removeRow(int row) {
		// Initialize a cell to hold a pointer to the row to delete
		Cell cur = getRow(row);

		// Loop over the row and set the down pointers to the row after the one
		// being deleted.
		for (int i = 0; i < colNum; i++) {
			cur.down = cur.down.down;
			cur = cur.right;
		}
	}

	/**
	 * Inserts a new column before the column specified.
	 * 
	 * @param col
	 *            The column to add before
	 * @return success status of the method.
	 */
	public boolean addColumn(int col) {
		// If the column is greater than the number of columns in the grid then
		// return false. The column doesn't exist.
		if (col > colNum - 1)
			return false;

		// If the column specified is 0 then insert at the head of the grid
		if (col == 0) {
			// Get the first cell in the first and last columns
			Cell firstCol = head;
			Cell lastCol = getCol(colNum - 1);

			// Loop over the column setting the row pointer to the column after
			for (int i = 0; i < rowNum; i++) {
				Cell toAdd = new Cell(print_width);
				lastCol.right = toAdd;
				toAdd.right = firstCol;
				firstCol = firstCol.down;
				lastCol = lastCol.down;
			}

			// Get the first cell in the last column
			lastCol = getCol(colNum - 1);

			// Loop over the column and set the column pointers
			for (int i = 0; i < rowNum; i++) {
				lastCol.right.down = lastCol.down.right;
				lastCol = lastCol.down;
			}

			// Set the head pointer to the new column
			head = getCol(colNum);
		}
		// Otherwise we can insert a column normally
		else
			insertColumn(col);
		// New column so increment colNum
		colNum++;

		// Operations is successful so return true
		return true;
	}

	public boolean deleteColumn(int col) {
		if (rowNum == 1) {
			System.out.printf("\n\nThere is must always be one column in the grid.");
			return false;
		}

		if (col > colNum - 1) {
			System.out.printf("\n\nThe column does not exist\n\n");
			return false;
		}

		if (col == 0) {
			Cell tmp = head.right;
			head = tmp;
			Cell lastCol = getCol(colNum - 1);
			for (int i = 0; i < colNum; i++) {
				lastCol.right = tmp;
				lastCol = lastCol.down;
				tmp = tmp.down;
			}

		} else
			removeColumn(col - 1);

		colNum--;

		return true;
	}

	private void insertColumn(int beforeCol) {
		Cell preCell;

		if (beforeCol == 0)
			preCell = getCol(colNum - 1);
		else
			preCell = getCol(beforeCol - 1);

		for (int i = 0; i < rowNum; i++) {
			Cell toAdd = new Cell(print_width);
			toAdd.right = preCell.right;
			preCell.right = toAdd;
			preCell = preCell.down;
		}

		preCell = getCol(beforeCol - 1);
		Cell tmp = preCell.right;

		for (int i = 0; i < rowNum; i++) {
			if (preCell.down == getCol(colNum - 1))
				tmp.down = getCol(colNum - 1).right;
			else {
				if (preCell.down == null)
					preCell.down = getCol(beforeCol - 1);
				tmp.down = preCell.down.right;
			}
			preCell = preCell.down;
			tmp = tmp.down;
		}
	}

	private void removeColumn(int preCol) {
		Cell cur;
		if (preCol == -1) {
			cur = getCol(colNum - 1);
		} else
			cur = getCol(preCol);

		for (int i = 0; i < rowNum; i++) {
			cur.right = cur.right.right;
			cur = cur.down;
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
			success = addNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		return success;
	}

	public boolean subCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = subNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		return success;
	}

	public boolean multCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = mulNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		return success;
	}

	public boolean divCols(int colA, int colB, int colC) {
		boolean success = false;

		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = divNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		return success;
	}

	public boolean fill(int rowA, int colA, int rowB, int colB, String val) {

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
			try {
				dVal = Double.parseDouble(val);
				val = null;
			} catch (Exception e) {
				System.out.printf("Non-numeric fill value must start with a \"\n");
				return false;
			}
		} else
			val = val.substring(1, val.length());

		if (a == null) {
			System.out.printf("Cell doesn't exist\n");
			return false;
		}

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