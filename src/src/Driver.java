package src;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		String input,val;
		boolean success;
		int rowA,rowB,rowC,colA,colB,colC,col,row;
		int sentinel = 1;
		Grid g = new Grid(10,10,6);
		Scanner in = new Scanner(System.in);
		
		while(sentinel == 1){
			printMenu();

			System.out.print("-> ");
			input = in.nextLine();
			
			switch(input){
			case "dis":
				System.out.println();
				g.print();
				System.out.println();
				break;
			case "as":
				System.out.println();
				System.out.print("cell row: ");
				row = Integer.valueOf(in.nextLine());
				System.out.print("cell column: ");
				col = Integer.valueOf(in.nextLine());
				System.out.print("with value: ");
				val = in.nextLine();
				success = g.assignNode(row, col, val);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter a valid row and column number\n\n");
				
				break;
			case "f":
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
				
				success = g.fill(rowA, colA, rowB, colB, val);
				
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid row and column numbers\n\n");
				
				break;
			case "n":
				System.out.println();
				System.out.print("from row: ");
				rowA = Integer.valueOf(in.nextLine());
				System.out.print("from column: ");
				colA = Integer.valueOf(in.nextLine());
				System.out.print("to row: ");
				rowB = Integer.valueOf(in.nextLine());
				System.out.print("to column: ");
				colB = Integer.valueOf(in.nextLine());
				success = g.number(rowA, colA, rowB, colB);
				System.out.println();
				
				if(!success)
					System.out.println("");
				
				break;
			case "a":
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
				
				success = g.addNodes(rowA, colA, rowB, colB, rowC, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid cell locations\n\n");
				break;
			case "s":
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
				
				success = g.subNodes(rowA, colA, rowB, colB, rowC, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid cell locations\n\n");
				break;
			case "m":
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
				
				success = g.mulNodes(rowA, colA, rowB, colB, rowC, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid cell locations\n\n");
				break;
			case "d":
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
				
				success = g.divRows(rowA, rowB, rowC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid cell locations\n\n");
				break;
			case "ar":
				System.out.println();
				System.out.print("first  row: ");
				rowA = Integer.valueOf(in.nextLine());
				System.out.print("second row: ");
				rowB = Integer.valueOf(in.nextLine());
				System.out.print("target row: ");
				rowC = Integer.valueOf(in.nextLine());
				
				success = g.addRows(rowA, rowB, rowC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid row numbers\n\n");
				break;
			case "sr":
				System.out.println();
				System.out.print("first  row: ");
				rowA = Integer.valueOf(in.nextLine());
				System.out.print("second row: ");
				rowB = Integer.valueOf(in.nextLine());
				System.out.print("target row: ");
				rowC = Integer.valueOf(in.nextLine());
				
				success = g.subRows(rowA, rowB, rowC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid row numbers\n\n");
				break;
			case "mr":
				System.out.println();
				System.out.print("first  row: ");
				rowA = Integer.valueOf(in.nextLine());
				System.out.print("second row: ");
				rowB = Integer.valueOf(in.nextLine());
				System.out.print("target row: ");
				rowC = Integer.valueOf(in.nextLine());
				
				success = g.multRows(rowA, rowB, rowC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid row numbers\n\n");
				break;
			case "dr":
				System.out.println();
				System.out.print("first  row: ");
				rowA = Integer.valueOf(in.nextLine());
				System.out.print("second row: ");
				rowB = Integer.valueOf(in.nextLine());
				System.out.print("target row: ");
				rowC = Integer.valueOf(in.nextLine());
				
				success = g.divRows(rowA, rowB, rowC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid row numbers\n\n");
				break;
			case "ac":
				System.out.println();
				System.out.print("first  column: ");
				colA = Integer.valueOf(in.nextLine());
				System.out.print("second column: ");
				colB = Integer.valueOf(in.nextLine());
				System.out.print("target column: ");
				colC = Integer.valueOf(in.nextLine());
				
				success = g.addCols(colA, colB, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid column numbers\n\n");
				break;
			case "sc":
				System.out.println();
				System.out.print("first  column: ");
				colA = Integer.valueOf(in.nextLine());
				System.out.print("second column: ");
				colB = Integer.valueOf(in.nextLine());
				System.out.print("target column: ");
				colC = Integer.valueOf(in.nextLine());
				
				success = g.subCols(colA, colB, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid column numbers\n\n");
				break;
			case "mc":
				System.out.println();
				System.out.print("first  column: ");
				colA = Integer.valueOf(in.nextLine());
				System.out.print("second column: ");
				colB = Integer.valueOf(in.nextLine());
				System.out.print("target column: ");
				colC = Integer.valueOf(in.nextLine());
				
				success = g.multCols(colA, colB, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid column numbers\n\n");
				break;
			case "dc":
				System.out.println();
				System.out.print("first  column: ");
				colA = Integer.valueOf(in.nextLine());
				System.out.print("second column: ");
				colB = Integer.valueOf(in.nextLine());
				System.out.print("target column: ");
				colC = Integer.valueOf(in.nextLine());
				
				success = g.divCols(colA, colB, colC);
				System.out.println();
				
				if(!success)
					System.out.printf("Please enter valid column numbers\n\n");
				break;
			case "ir":
				System.out.print("insert after row: ");
				row = Integer.valueOf(in.nextLine());
				success = g.addRow(row);
				System.out.println();
				if(!success)
					System.out.printf("%d is not a valid row number\n\n",row);
				break;
			case "ic":
				System.out.print("insert after column: ");
				col = Integer.valueOf(in.nextLine());
				success = g.addColumn(col);
				System.out.println();
				if(!success)
					System.out.printf("%d is not a valid column number\n\n",col);
				break;
			case "delr":
				System.out.print("row number: ");
				row = Integer.valueOf(in.nextLine());
				success = g.deleteRow(row);
				System.out.println();
				if(!success)
					System.out.printf("Please enter a valid option\n\n",row);
				break;
			case "delc":
				System.out.print("column number: ");
				col = Integer.valueOf(in.nextLine());
				success = g.deleteColumn(col);
				System.out.println();
				if(!success)
					System.out.printf("Please enter a valid option\n\n",col);
				break;
			case "q":
				in.close();
				sentinel = 0;
				break;
				default:
					System.out.printf("\nPlease enter a valid option\n\n");
			}
		}
	}
	
	private static void printMenu(){
		System.out.printf("Operations\n"+
						  "  display          \tdis \tassign cell       \tas  \n"+
						  "  fill             \tf   \tnumber            \tn   \n"+
						  "  add cells        \ta   \tsubtract cells    \ts   \n"+
						  "  multiply cells   \tm   \tdivide cells      \td   \n"+
						  "  add rows         \tar  \tsubtract rows     \tsr  \n"+
						  "  multiply rows    \tmr  \tdivide rows       \tdr  \n"+
						  "  add columns      \tac  \tsubtract columns  \tsc  \n"+
						  "  multiply columns \tmc  \tdivide columns    \tdc  \n"+
						  "  insert row       \tir  \tinsert column     \tic  \n"+
						  "  delete row       \tdelr\tdelete column     \tdelc\n"+
						  "  quit             \tq\n");
	}
}