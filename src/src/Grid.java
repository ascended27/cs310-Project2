package src;

@SuppressWarnings("unused")
public class Grid {

	Cell head;
	private int rowNum, colNum, print_width;

	public Grid(int print_width, int rowStart, int colStart) {
		head = null;
		rowNum = rowStart;
		colNum = colStart;
		this.print_width = print_width;
		init(rowStart, colStart);

	}

	private void init(int row, int col) {
		head = new Cell(print_width);
		head.right = head;
		head.down = head;

		for (int i = 0; i < col; i++) {
			Cell toAdd = new Cell(print_width);
			toAdd.right = head.right;
			toAdd.down = toAdd;
			head.right = toAdd;
		}

		for (int i = 0; i < row - 1; i++)
			insertRow(1);

		for (int i = 0; i < col; i++)
			insertColumn(1);
	}

	public void print() {

		Cell cur = head;
		Cell tmpRow;
		int length;
		for (int i = 0; i <= print_width - 1; i++)
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
			StringBuilder sb = new StringBuilder();
			sb.append("Row " + i + " ");
			for (int j = sb.length(); j < print_width; j++)
				sb.append(" ");
			System.out.print(sb.toString());
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

		if (row > rowNum - 1)
			return false;

		insertRow(row);
		rowNum++;

		return true;

	}

	public boolean deleteRow(int row) {
		if (rowNum == 1) {
			System.out.printf("\n\nThere is must always be one row in the grid.");
			return false;
		}

		if (row > colNum - 1) {
			System.out.printf("\n\nThe row does not exist\n\n");
			return false;
		}

		removeRow(row - 1);
		rowNum--;

		return true;
	}

	private void insertRow(int beforeRow) {
		Cell preCell;

		if (beforeRow == 0)
			preCell = getRow(rowNum - 1);
		else
			preCell = getRow(beforeRow - 1);

		for (int i = 0; i < colNum; i++) {
			Cell toAdd = new Cell(print_width);
			toAdd.down = preCell.down;
			preCell.down = toAdd;
			preCell = preCell.right;
		}

		preCell = getRow(rowNum - 1);
		Cell tmp = preCell.down;

		for (int i = 0; i < colNum; i++) {
			if (preCell.right == getRow(beforeRow - 1))
				tmp.right = getRow(rowNum - 1).down;
			else {
				if (preCell.right == null)
					preCell.right = getRow(beforeRow - 1);
				tmp.right = preCell.right.down;
			}
			preCell = preCell.right;
			tmp = tmp.right;
		}
		
	}

	private void removeRow(int preRow) {
		Cell cur;
		if (preRow == -1)
			cur = getRow(rowNum - 1);
		else
			cur = getRow(rowNum - 1);

		for (int i = 0; i < colNum; i++) {
			cur.down = cur.down.down;
			cur = cur.right;
		}
	}

	// Inserts a new column before the column specified
	// First column starts at 0,
	public boolean addColumn(int col) {
		if (col > colNum - 1)
			return false;

		insertColumn(col);
		colNum++;

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