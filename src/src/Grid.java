package src;

public class Grid {

	// Declare variables
	private Node head;
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
		head = new Node(print_width);
		head.right = head;
		head.down = head;

		Node tmp = head;
		// Initialize the first row of the grid.
		for (int i = 0; i < col; i++) {
			Node toAdd = new Node(print_width);
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
		Node cur = head;

		// Declare a node to hold the front of the row
		Node tmpRow;

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
			Node firstRow = head;
			Node lastRow = getRow(rowNum - 1);

			// Loop over the row inserting new nodes and making the row
			// connections
			for (int i = 0; i < colNum; i++) {
				Node toAdd = new Node(print_width);
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
			Node tmp = head.down;

			// Set the head to the next row
			head = tmp;

			// Get the last row in the grid to make new column links
			Node lastRow = getRow(rowNum - 1);

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
		Node preCell;

		// If we are inserting before the head node then we need the cell at the
		// end of the row since we are circularly linked
		if (row == 0)
			preCell = getRow(rowNum - 1);
		// Otherwise we get the row preceding the one we specified
		else
			preCell = getRow(row - 1);

		// Loop over the row adding a new cell and creating column links
		for (int i = 0; i < colNum; i++) {
			Node toAdd = new Node(print_width);
			toAdd.down = preCell.down;
			preCell.down = toAdd;
			preCell = preCell.right;
		}

		// Get the first cell in the previous row
		preCell = getRow(row - 1);

		// Set tmp to the next cell in the column
		Node tmp = preCell.down;

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
	 *            index of the row before the row to remove
	 */
	private void removeRow(int row) {
		// Initialize a cell to hold a pointer to the row to delete
		Node cur = getRow(row);

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
			Node firstCol = head;
			Node lastCol = getCol(colNum - 1);

			// Loop over the column setting the row pointer to the column after
			for (int i = 0; i < rowNum; i++) {
				Node toAdd = new Node(print_width);
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

	/**
	 * Deletes the specified column from the grid
	 * 
	 * @param col
	 *            index of the the column to delete
	 * @return success status of the method.
	 */
	public boolean deleteColumn(int col) {
		// If there is only one column in the grid then notify the user we can't
		// delete and return false. The operation failed.
		if (colNum == 1) {
			System.out.printf("\n\nThere is must always be one column in the grid.");
			return false;
		}

		// If the column index is not within the grid then notify the user and
		// return false. The operation failed.
		if (col > colNum - 1) {
			System.out.printf("\n\nThe column does not exist\n\n");
			return false;
		}

		// If the column to delete is the head column then delete the column and
		// adjust the head node.
		if (col == 0) {
			// Get a pointer to the next column in the grid
			Node tmp = head.right;

			// Set the head node to the next column
			head = tmp;

			// Get the first cell in the last column in the grid
			Node lastCol = getCol(colNum - 1);

			// Loop over the column and adjust the column and row pointers
			for (int i = 0; i < colNum; i++) {
				lastCol.right = tmp;
				lastCol = lastCol.down;
				tmp = tmp.down;
			}
		}
		// Otherwise, we can delete the column normally.
		else
			removeColumn(col - 1);

		// Deleted a column so decrement the colNum
		colNum--;

		// Operation succeeded so return true
		return true;
	}

	/**
	 * Inserts a new column before the specified column.
	 * 
	 * @param col
	 *            index of the column to add before
	 */
	private void insertColumn(int col) {
		// Declare a pointer to the previous cell in the column
		Node preCell;

		// If the specified column is 0 then we need a pointer to the last
		// column in the grid
		if (col == 0)
			preCell = getCol(colNum - 1);
		// Otherwise we can get the column preceding the column specified
		else
			preCell = getCol(col - 1);

		// Loop over the column adding new cells and updating the row pointers
		for (int i = 0; i < rowNum; i++) {
			Node toAdd = new Node(print_width);
			toAdd.right = preCell.right;
			preCell.right = toAdd;
			preCell = preCell.down;
		}

		// Reset the preceding cell to the first cell in the preceding column
		// and get the first cell in the new column.
		preCell = getCol(col - 1);
		Node tmp = preCell.right;

		// Loop over the column and connect the new row's column pointer
		for (int i = 0; i < rowNum; i++) {
			// If the cell below the current preceding cell is the top of the
			// column then we are at the bottom of the column. Set the new
			// column's last cell to the top of of the new column.
			if (preCell.down == getCol(colNum - 1))
				tmp.down = getCol(colNum - 1).right;
			// Otherwise the bottom of the column hasn't been reached yet, set
			// the current new column cell to the next cell in the new column.
			else
				tmp.down = preCell.down.right;

			// Move to the next preceding cell and new cell
			preCell = preCell.down;
			tmp = tmp.down;
		}
	}

	/**
	 * Removes the specified column from the grid
	 * 
	 * @param preCol
	 *            index of the column before the column to remove
	 */
	private void removeColumn(int preCol) {
		// Initialize a pointer to the start of the column before the column to
		// delete.
		Node cur = getCol(preCol);

		// Loop over the preceding column removing the pointer to the column
		// being deleted
		for (int i = 0; i < rowNum; i++) {
			cur.right = cur.right.right;
			cur = cur.down;
		}
	}

	/**
	 * Adds the cells stored at rowA, colA and rowB, colB and stores them in
	 * rowC, colC. If Cell a, b, or c are null then the cell doesn't exist and
	 * the operation can't be performed. So on the driver if this fails
	 * re-prompt the user for correct input.
	 * 
	 * @param rowA
	 *            row index of the first cell to add
	 * @param colA
	 *            column index of the first cell to add
	 * @param rowB
	 *            row index of the second cell to add
	 * @param colB
	 *            column index of the second cell to add
	 * @param rowC
	 *            row index of the destination cell
	 * @param colC
	 *            column index of the destination cell
	 * @return success status of the method.
	 */
	public boolean addNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {

		// Get the cells to operate on.
		Node a = getCell(rowA, colA);
		Node b = getCell(rowB, colB);
		Node c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist.
		if (a == null || b == null || c == null)
			return false;

		// Added the cells and store them in c
		c.val = a.val.plus(b.val, c.val);

		// The operation has completed so return true
		return true;
	}

	/**
	 * Subtracts the cells stored at rowA, colA and rowB, colB and stores them
	 * in rowC, colC. If Cell a, b, or c are null then the cell doesn't exist
	 * and the operation can't be performed. So on the driver if this fails
	 * re-prompt the user for correct input.
	 * 
	 * @param rowA
	 *            row index of the first cell to subtract
	 * @param colA
	 *            column index of the first cell to subtract
	 * @param rowB
	 *            row index of the second cell to subtract
	 * @param colB
	 *            column index of the second cell to subtract
	 * @param rowC
	 *            row index of the destination cell
	 * @param colC
	 *            column index of the destination cell
	 * @return success status of the method.
	 */
	public boolean subNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Node a = getCell(rowA, colA);
		Node b = getCell(rowB, colB);
		Node c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		// Subtract b from and store in c
		c.val = a.val.minus(b.val, c.val);

		// The operation has completed so return true
		return true;
	}

	/**
	 * Multiplies the cells stored at rowA, colA and rowB, colB and stores them
	 * in rowC, colC. If Cell a, b, or c are null then the cell doesn't exist
	 * and the operation can't be performed. So on the driver if this fails
	 * re-prompt the user for correct input.
	 * 
	 * @param rowA
	 *            row index of the first cell to multiply
	 * @param colA
	 *            column index of the first cell to multiply
	 * @param rowB
	 *            row index of the second cell to multiply
	 * @param colB
	 *            column index of the second cell to multiply
	 * @param rowC
	 *            row index of the destination cell
	 * @param colC
	 *            column index of the destination cell
	 * @return success status of the method.
	 */
	public boolean mulNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Node a = getCell(rowA, colA);
		Node b = getCell(rowB, colB);
		Node c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		// Multiply a and b and store in c
		c.val = a.val.star(b.val, c.val);

		// The operation has completed so return true
		return true;
	}

	/**
	 * Divides the cells stored at rowA, colA and rowB, colB and stores them in
	 * rowC, colC. If Cell a, b, or c are null then the cell doesn't exist and
	 * the operation can't be performed. So on the driver if this fails
	 * re-prompt the user for correct input.
	 * 
	 * @param rowA
	 *            row index of the first cell to divide
	 * @param colA
	 *            column index of the first cell to divide
	 * @param rowB
	 *            row index of the second cell to divide
	 * @param colB
	 *            column index of the second cell to divide
	 * @param rowC
	 *            row index of the destination cell
	 * @param colC
	 *            column index of the destination cell
	 * @return success status of the method.
	 */
	public boolean divNodes(int rowA, int colA, int rowB, int colB, int rowC, int colC) {
		// Get the cells to operate on
		Node a = getCell(rowA, colA);
		Node b = getCell(rowB, colB);
		Node c = getCell(rowC, colC);

		// If either cell is null, it doesn't exist
		if (a == null || b == null || c == null)
			return false;

		// Divide a by b and store in c
		c.val = a.val.slash(b.val, c.val);

		// The operation has completed so return true
		return true;
	}

	/**
	 * Adds the rowA to rowB and stores in rowC. If any of the indices are
	 * outside the grid then returns false, the operation failed.
	 * 
	 * @param rowA
	 *            index of the first row to add
	 * @param rowB
	 *            index of the second row to add
	 * @param rowC
	 *            index of the destination row
	 * @return success status of the method.
	 */
	public boolean addRows(int rowA, int rowB, int rowC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (rowA > rowNum - 1 || rowB > rowNum - 1 || rowC > rowNum - 1)
			return false;

		// Loop over the nodes in the row adding them together by using the add
		// method for individual nodes
		for (int curCol = 0; curCol < colNum; curCol++) {
			success = addNodes(rowA, curCol, rowB, curCol, rowC, curCol);

			// If the addition fails then notify the user and end the row
			// addition
			if (!success) {
				System.out.printf("Unable to add the rows\n");
				break;
			}
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Subtracts the rowB from rowA and stores in rowC. If any of the indices
	 * are outside the grid then returns false, the operation failed.
	 * 
	 * @param rowA
	 *            index of the first row to subtract
	 * @param rowB
	 *            index of the second row to subtract
	 * @param rowC
	 *            index of the destination row
	 * @return success status of the method.
	 */
	public boolean subRows(int rowA, int rowB, int rowC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (rowA > rowNum - 1 || rowB > rowNum - 1 || rowC > rowNum - 1)
			return false;

		// Loop over the nodes in the row subtracting them by using the subtract
		// method for individual nodes
		for (int curCol = 0; curCol < colNum; curCol++) {
			success = subNodes(rowA, curCol, rowB, curCol, rowC, curCol);

			// If the subtraction fails then notify the user and end the row
			// subtraction
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Multiplies the rowA and rowB and stores in rowC. If any of the indices
	 * are outside the grid then returns false, the operation failed.
	 * 
	 * @param rowA
	 *            index of the first row to multiply
	 * @param rowB
	 *            index of the second row to multiply
	 * @param rowC
	 *            index of the destination row
	 * @return success status of the method.
	 */
	public boolean multRows(int rowA, int rowB, int rowC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (rowA > rowNum - 1 || rowB > rowNum - 1 || rowC > rowNum - 1)
			return false;

		// Loop over the nodes in the row multiplying them by using the multiply
		// method for individual nodes
		for (int curCol = 0; curCol < colNum; curCol++) {
			success = mulNodes(rowA, curCol, rowB, curCol, rowC, curCol);

			// If the multiplication fails then notify the user and end the row
			// multiplication
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Divides the rowA by rowB and stores in rowC. If any of the indices are
	 * outside the grid then returns false, the operation failed.
	 * 
	 * @param rowA
	 *            index of the first row to divide
	 * @param rowB
	 *            index of the second row to divide
	 * @param rowC
	 *            index of the destination row
	 * @return success status of the method.
	 */
	public boolean divRows(int rowA, int rowB, int rowC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (rowA > rowNum - 1 || rowB > rowNum - 1 || rowC > rowNum - 1)
			return false;

		// Loop over the nodes in the row dividing them by using the divide
		// method for individual nodes
		for (int curCol = 0; curCol < colNum; curCol++) {
			success = divNodes(rowA, curCol, rowB, curCol, rowC, curCol);

			// If the divide fails then notify the user and end the row
			// divide
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Adds the colA to colB and stores in colC. If any of the indices are
	 * outside the grid then returns false, the operation failed.
	 * 
	 * @param colA
	 *            index of the first column to add
	 * @param colB
	 *            index of the second column to add
	 * @param colC
	 *            index of the destination column
	 * @return success status of the method.
	 */
	public boolean addCols(int colA, int colB, int colC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (colA > colNum - 1 || colB > colNum - 1 || colC > colNum - 1)
			return false;

		// Loop over the nodes in the column adding them by using the addition
		// method for individual nodes
		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = addNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Subtracts the colB from colA and stores in colC. If any of the indices
	 * are outside the grid then returns false, the operation failed.
	 * 
	 * @param colA
	 *            index of the first column to subtract
	 * @param colB
	 *            index of the second column to subtract
	 * @param colC
	 *            index of the destination column
	 * @return success status of the method.
	 */
	public boolean subCols(int colA, int colB, int colC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (colA > colNum - 1 || colB > colNum - 1 || colC > colNum - 1)
			return false;

		// Loop over the nodes in the column subtracting them by using the
		// subtract method for individual nodes
		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = subNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Multiplies the colA and colB and stores in colC. If any of the indices
	 * are outside the grid then returns false, the operation failed.
	 * 
	 * @param colA
	 *            index of the first column to multiply
	 * @param colB
	 *            index of the second column to multiply
	 * @param colC
	 *            index of the destination column
	 * @return success status of the method.
	 */
	public boolean multCols(int colA, int colB, int colC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (colA > colNum - 1 || colB > colNum - 1 || colC > colNum - 1)
			return false;

		// Loop over the nodes in the column multiplying them by using the
		// multiply method for individual nodes
		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = mulNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Divides the colA by colB and stores in colC. If any of the indices are
	 * outside the grid then returns false, the operation failed.
	 * 
	 * @param colA
	 *            index of the first column to divide
	 * @param colB
	 *            index of the second column to divide
	 * @param colC
	 *            index of the destination column
	 * @return success status of the method.
	 */
	public boolean divCols(int colA, int colB, int colC) {
		// Initialize boolean flag to represent if the operation succeeded or
		// not
		boolean success = false;

		// If any of the rows are outside the bounds of the grid then return
		// false
		if (colA > colNum - 1 || colB > colNum - 1 || colC > colNum - 1)
			return false;

		// Loop over the nodes in the column dividing them by using the divide
		// method for individual nodes
		for (int curRow = 0; curRow < rowNum; curRow++) {
			success = divNodes(curRow, colA, curRow, colB, curRow, colC);
			if (!success)
				break;
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Fills in the sub-grid defined by rowA,colA and rowB,colB with the user
	 * specified value.
	 * 
	 * @param rowA
	 *            row index of the first cell to multiply
	 * @param colA
	 *            column index of the first cell to multiply
	 * @param rowB
	 *            row index of the second cell to multiply
	 * @param colB
	 *            column index of the second cell to multiply
	 * @param val
	 *            the value to fill in the sub-grid with
	 * 
	 * @return success status of the method.
	 */
	public boolean fill(int rowA, int colA, int rowB, int colB, String val) {
		// Boolean flag to store the results of fill
		boolean success = false;

		// If the second cell location is less than the first then notify the
		// user that isn't allowed and return false. The operation fails.
		if (rowA > rowB || colA > colB) {
			System.out.printf("From row and column must be less than to row and column\n");
			return false;
		}

		// If the cells specifying the sub-grid are outside the bounds of the
		// grid then notify the user and return false. The operation fails.
		if ((rowA >= rowNum) || (rowB >= rowNum) || (colA >= colNum) || (colB > colNum)) {
			System.out.printf("row and columns must be within the bounds of the grid");
			return false;
		}

		// Loop over the sub-grid and assign the value specified by the user
		for (int i = rowA; i <= rowB; i++) {
			for (int j = colA; j <= colB; j++) {
				success = assignNode(i, j, val);
			}
		}

		// The operation has completed so return true
		return success;

	}

	/**
	 * Numbers the sub-grid specified by the cells rowA,colA and rowB,colB
	 * 
	 * @param rowA
	 *            row index of the first cell to multiply
	 * @param colA
	 *            column index of the first cell to multiply
	 * @param rowB
	 *            row index of the second cell to multiply
	 * @param colB
	 *            column index of the second cell to multiply
	 * @return success status of the method.
	 */
	public boolean number(int rowA, int colA, int rowB, int colB) {
		// Initialize a counter value to store in each cell
		int count = 0;

		// Boolean flag to store the results of fill
		boolean success = false;

		// If the second cell location is less than the first then notify the
		// user that isn't allowed and return false. The operation fails.
		if (rowA > rowB || colA > colB) {
			System.out.printf("From row and column must be less than to row and column\n");
			return false;
		}

		// If the cells specifying the sub-grid are outside the bounds of the
		// grid then notify the user and return false. The operation fails.
		if ((rowA >= rowNum) || (rowB >= rowNum) || (colA >= colNum) || (colB > colNum)) {
			System.out.printf("row and columns must be within the bounds of the grid");
			return false;
		}

		// Loop over the sub-grid and assign the value specified by the user
		for (int i = rowA; i <= rowB; i++) {
			for (int j = colA; j <= colB; j++) {
				success = assignNode(i, j, "" + count++);
			}
		}

		// The operation has completed so return true
		return success;
	}

	/**
	 * Assigns the value passed to the cell located at row,col
	 * 
	 * @param row
	 *            row index of the cell to fill with value
	 * @param col
	 *            column index of the cell to fill with value
	 * @param val
	 *            value to store in the cell
	 * @return success status of the method.
	 */
	public boolean assignNode(int row, int col, String val) {

		// Get the cell at row,col
		Node a = getCell(row, col);
		double dVal = 0;

		// If a is null then the cell doesn't exist. Notify the user and return
		// false. The operation has failed.
		if (a == null) {
			System.out.printf("Cell doesn't exist\n");
			return false;
		}

		// If the value does not start with a " then the val must be a double
		if (!val.startsWith("\"")) {
			// Try to parse val as a double
			try {
				dVal = Double.parseDouble(val);
				a.val = new Value(dVal, print_width);
			}
			// If an exception is thrown then val is non-numeric and can't be
			// inserted
			catch (Exception e) {
				System.out.printf("Non-numeric fill value must start with a \"\n");
				return false;
			}
		}
		// Otherwise the val should be inserted as a string
		else {
			val = val.substring(1, val.length());
			a.val = new Value(val, print_width);
		}

		// The operation has completed so return true
		return true;
	}

	/**
	 * Returns the cell at location row,col if it exists
	 * 
	 * @param row
	 *            row index of cell to return
	 * @param col
	 *            column index of cell to return
	 * @return the cell at row,col
	 */
	private Node getCell(int row, int col) {
		// If the row or column are outside the bounds of the array then return
		// null
		if ((row > rowNum) || (col > colNum) || (col < 0) || (row < 0))
			return null;

		// Initialize a pointer to the head node
		Node current = head;

		// Move to the proper row
		for (int i = 0; i < row; i++)
			current = current.down;

		// Move to the proper column
		for (int j = 0; j < col; j++)
			current = current.right;

		// Return the cell located at row,col
		return current;
	}

	/**
	 * Returns the cell at the start of specified column if it exists
	 * 
	 * @param col
	 *            column to retrieve start of
	 * @return the cell at the start of col
	 */
	private Node getCol(int col) {
		// If the column is outside the bounds of the grid then return null
		if (col > colNum || col < 0)
			return null;

		// Initialize a pointer to the head of the grid
		Node current = head;

		// Move to the proper column
		for (int i = 0; i < col; i++)
			current = current.right;

		// Return the cell at the start of col
		return current;
	}

	/**
	 * Returns the cell at the start of specified row if it exists
	 * 
	 * @param row
	 *            row to retrieve start of
	 * @return the cell at the start of row
	 */
	private Node getRow(int row) {
		// If the column is outside the bounds of the grid then return null
		if (row > rowNum || row < 0)
			return null;

		// Initialize a pointer to the head of the grid
		Node current = head;

		// Move to the proper row
		for (int i = 0; i < row; i++)
			current = current.down;

		// Return the cell at the start of row
		return current;
	}
}