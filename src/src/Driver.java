package src;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		// Declare variables.
		String input, val;
		boolean success;
		int rowA, rowB, rowC, colA, colB, colC, col, row;

		// Initialize variables.
		int sentinel = 1;
		Grid g = new Grid(10, 10, 6);
		Scanner in = new Scanner(System.in);

		// Beginning of menu loop.
		while (sentinel == 1) {
			// Display the menu for the user.
			printMenu();

			// Prompt user for input.
			System.out.print("-> ");
			input = in.nextLine();

			// Start menu switch.
			switch (input) {
			// Display the current grid.
			case "dis":
				System.out.println();
				g.print();
				System.out.println();
				break;
			// Assign a user specified value to the user specified cell.
			case "as":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("cell row: ");
					row = Integer.valueOf(in.nextLine());
					System.out.print("cell column: ");
					col = Integer.valueOf(in.nextLine());
					System.out.print("with value: ");
					val = in.nextLine();

					// Assign the value to the cell at row, col.
					success = g.assignNode(row, col, val);

					// If the assign operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter a valid row and column number\n\n");
				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}

				System.out.println();

				break;
			// Fill a rectangular region of the grid with the user specified
			// value
			case "f":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("from row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("from column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("to row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("to column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("with value: ");
					val = in.nextLine();

					// Fill in the sub-grid specified by the user with val.
					success = g.fill(rowA, colA, rowB, colB, val);

					// If the assign operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid row and column numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				System.out.println();

				break;
			// Number a rectangular region of the grid starting at 0.
			case "n":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("from row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("from column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("to row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("to column: ");
					colB = Integer.valueOf(in.nextLine());

					// Number the sub-grid specified by the user.
					success = g.number(rowA, colA, rowB, colB);
					System.out.println();

					// If the assign operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.println("");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Add two cells together and store the result in a third cell.
			case "a":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first cell row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("first cell column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second cell row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("second cell column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("destination cell row: ");
					rowC = Integer.valueOf(in.nextLine());
					System.out.print("destination cell column: ");
					colC = Integer.valueOf(in.nextLine());

					// Add the two cells specified by the user and store them in
					// the 3rd cell specified by the user.
					success = g.addNodes(rowA, colA, rowB, colB, rowC, colC);
					System.out.println();

					// If the addition operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid cell locations\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Subtract two cells and store the result in a third cell.
			case "s":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first cell row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("first cell column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second cell row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("second cell column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("destination cell row: ");
					rowC = Integer.valueOf(in.nextLine());
					System.out.print("destination cell column: ");
					colC = Integer.valueOf(in.nextLine());

					// Subtract the two cells specified by the user and store
					// them in the 3rd cell specified by the user.
					success = g.subNodes(rowA, colA, rowB, colB, rowC, colC);
					System.out.println();

					// If the subtract operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid cell locations\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Multiply two cells and store the result in a third cell.
			case "m":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first cell row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("first cell column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second cell row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("second cell column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("destination cell row: ");
					rowC = Integer.valueOf(in.nextLine());
					System.out.print("destination cell column: ");
					colC = Integer.valueOf(in.nextLine());

					// Multiply the two cells specified by the user and store
					// them in the 3rd cell specified by the user.
					success = g.mulNodes(rowA, colA, rowB, colB, rowC, colC);
					System.out.println();

					// If the multiply operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid cell locations\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Divide two cells and store the result in a third cell.
			case "d":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first cell row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("first cell column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second cell row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("second cell column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("destination cell row: ");
					rowC = Integer.valueOf(in.nextLine());
					System.out.print("destination cell column: ");
					colC = Integer.valueOf(in.nextLine());

					// Divide the two cells specified by the user and store
					// them in the 3rd cell specified by the user.
					success = g.divNodes(rowA, colA, rowB, colB, rowC, colC);
					System.out.println();

					// If the divide operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid cell locations\n\n");
				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Add two rows together and store them in a third row.
			case "ar":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("second row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("target row: ");
					rowC = Integer.valueOf(in.nextLine());

					// Add the two rows specified by the user and store
					// them in the 3rd row specified by the user.
					success = g.addRows(rowA, rowB, rowC);
					System.out.println();

					// If the add rows operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter valid row numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Subtract two rows together and store them in a third row.
			case "sr":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("second row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("target row: ");
					rowC = Integer.valueOf(in.nextLine());

					// Subtract the two rows specified by the user and store
					// them in the 3rd row specified by the user.
					success = g.subRows(rowA, rowB, rowC);
					System.out.println();

					// If the subtract rows operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid row numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Multiply two rows together and store them in a third row.
			case "mr":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("second row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("target row: ");
					rowC = Integer.valueOf(in.nextLine());

					// Multiply the two rows specified by the user and store
					// them in the 3rd row specified by the user.
					success = g.multRows(rowA, rowB, rowC);
					System.out.println();

					// If the multiply rows operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid row numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Divide two rows together and store them in a third row.
			case "dr":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  row: ");
					rowA = Integer.valueOf(in.nextLine());
					System.out.print("second row: ");
					rowB = Integer.valueOf(in.nextLine());
					System.out.print("target row: ");
					rowC = Integer.valueOf(in.nextLine());

					// Divide the two rows specified by the user and store
					// them in the 3rd row specified by the user.
					success = g.divRows(rowA, rowB, rowC);
					System.out.println();

					// If the divide rows operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid row numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Add two columns together and store them in a third column.
			case "ac":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("target column: ");
					colC = Integer.valueOf(in.nextLine());

					// Add the two columns specified by the user and store
					// them in the 3rd column specified by the user.
					success = g.addCols(colA, colB, colC);
					System.out.println();

					// If the add columns operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid column numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Subtract two columns and store them in a third column.
			case "sc":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("target column: ");
					colC = Integer.valueOf(in.nextLine());

					// Subtract the two columns specified by the user and store
					// them in the 3rd column specified by the user.
					success = g.subCols(colA, colB, colC);
					System.out.println();

					// If the subtract columns operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid column numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Multiply two columns together and store them in a third column.
			case "mc":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("target column: ");
					colC = Integer.valueOf(in.nextLine());

					// Multiply the two columns specified by the user and store
					// them in the 3rd column specified by the user.
					success = g.multCols(colA, colB, colC);
					System.out.println();

					// If the multiply columns operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid column numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Divide two columns together and store them in a third column.
			case "dc":
				try {
					// Prompt for user input.
					System.out.println();
					System.out.print("first  column: ");
					colA = Integer.valueOf(in.nextLine());
					System.out.print("second column: ");
					colB = Integer.valueOf(in.nextLine());
					System.out.print("target column: ");
					colC = Integer.valueOf(in.nextLine());

					// Divide the two columns specified by the user and store
					// them in the 3rd column specified by the user.
					success = g.divCols(colA, colB, colC);
					System.out.println();

					// If the divide columns operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter valid column numbers\n\n");

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Insert a row after the user specified row.
			case "ir":
				try {
					// Prompt for user input.
					System.out.print("insert after row: ");
					row = Integer.valueOf(in.nextLine());

					// Insert a new row after the row that the user specified.
					success = g.addRow(row);
					System.out.println();

					// If the add row operation fails then the user specified a
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("%d is not a valid row number\n\n", row);

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Insert a column after the user specified column.
			case "ic":
				try {
					// Prompt for user input.
					System.out.print("insert after column: ");
					col = Integer.valueOf(in.nextLine());

					// Insert a new column after the row that the user
					// specified.
					success = g.addColumn(col);
					System.out.println();

					// If the add column operation fails then the user specified
					// a cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("%d is not a valid column number\n\n", col);

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Delete the user specified row.
			case "delr":
				try {
					// Prompt for user input.
					System.out.print("row number: ");
					row = Integer.valueOf(in.nextLine());

					// Delete the row that the user has specified.
					success = g.deleteRow(row);
					System.out.println();

					// If the delete row operation fails then the user specified
					// cell not in the grid. Notify the user of the error.
					if (!success)
						System.out.printf("Please enter a valid option\n\n", row);

				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Delete the user specified column.
			case "delc":
				try {
					// Prompt for user input.
					System.out.print("column number: ");
					col = Integer.valueOf(in.nextLine());

					// Delete the column that the user has specified.
					success = g.deleteColumn(col);
					System.out.println();

					// If the delete column operation fails then the user
					// specified a cell not in the grid. Notify the user of the
					// error.
					if (!success)
						System.out.printf("Please enter a valid option\n\n", col);
				}
				// If a user enters a non-numeric value or no value as an index
				// then catch the exception generated and notify the user of the
				// error.
				catch (Exception e) {
					System.out.println("Please enter a integer value for indices");
				}
				break;
			// Quit the application
			case "q":
				in.close();
				sentinel = 0;
				break;
			// If the user input is not a defined case then notify them and
			// restart the menu loop.
			default:
				System.out.printf("\nPlease enter a valid option\n\n");
			}
		}
	}

	// Prints out the application menu
	private static void printMenu() {
		System.out.printf("Operations\n" 
				+ "  display          \tdis \tassign cell       \tas  \n"
				+ "  fill             \tf   \tnumber            \tn   \n"
				+ "  add cells        \ta   \tsubtract cells    \ts   \n"
				+ "  multiply cells   \tm   \tdivide cells      \td   \n"
				+ "  add rows         \tar  \tsubtract rows     \tsr  \n"
				+ "  multiply rows    \tmr  \tdivide rows       \tdr  \n"
				+ "  add columns      \tac  \tsubtract columns  \tsc  \n"
				+ "  multiply columns \tmc  \tdivide columns    \tdc  \n"
				+ "  insert row       \tir  \tinsert column     \tic  \n"
				+ "  delete row       \tdelr\tdelete column     \tdelc\n"
				+ "  quit             \tq\n");
	}
}