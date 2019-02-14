import java.util.*;

public class TicTacToe {

	static Scanner in;
	static String[] board;
	static String turn;
	static int xturnleft;
	static int oturnleft;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		board = new String[9];
		turn = "X";
		xturnleft = 3;
		oturnleft = 3;
		String winner = null;
		populateEmptyBoard();
		System.out.print(String.format("\033[2J"));
		System.out.println("Welcome to Single Player Tic Tac Toe with Moveable Pieces");
		System.out.println("----------------------------------------------------");
		printBoard();
		System.out.println("You will be playing X. Enter a slot number:");
		System.out.println("(to move a piece, enter '0')");
		while (winner == null) {
            if (turn.equals("O")){
                
                
                int[] corners = {0, 2, 6, 8};
                int[][][] cornerRowCols = {{{1, 2}, {3, 6}, {4, 8}}, {{0, 1}, {4, 6}, {5, 8}}, {{0, 3}, {2, 4}, {7, 8}}, {{0, 4}, {2, 5}, {6, 7}}};
                int[] edges = {1, 3, 5, 7};
                int[][][] edgeRowCols = {{{0, 2}, {4, 7}}, {{0, 6}, {4, 5}}, {{2, 8}, {3, 4}}, {{1, 4}, {6, 8}}};
                int[][] centerRowCols = {{0, 8}, {1, 7}, {2, 6}, {3, 5}};
                boolean AIhasplayed = false;
            outerloop:
                for (int i=0; i<9; i++){
                    if (board[i].equals(String.valueOf(i+1))){
                        for (int j=0; j<4; j++){
                            if (corners[j] == i){
                                for (int k=0; k<3; k++){
                                    if (board[cornerRowCols[j][k][0]].equals("O") && board[cornerRowCols[j][k][1]].equals("O")){
                                        if (oturnleft == 0) {
                                            int nextmove = priorityPiece();
                                            if (nextmove == cornerRowCols[j][k][0] || nextmove == cornerRowCols[j][k][1]){
                                                nextmove = priorityPiece();
                                            }
                                            if (nextmove == cornerRowCols[j][k][0] || nextmove == cornerRowCols[j][k][1]){
                                                nextmove = priorityPiece();
                                            }
                                            board[nextmove] = String.valueOf(nextmove+1);
                                        }
                                        else {
                                            oturnleft --;
                                        }
                                        board[i] = "O";
                                        printBoard();
                                        winner = checkWinner();
                                        AIhasplayed = true;
                                        break outerloop;
                                    }
                                }
                            }
                            if (edges[j] == i){
                                for (int k=0; k<2; k++){
                                    if (board[edgeRowCols[j][k][0]].equals("O") && board[edgeRowCols[j][k][1]].equals("O")){
                                        if (oturnleft == 0) {
                                            int nextmove = priorityPiece();
                                            if (nextmove == edgeRowCols[j][k][0] || nextmove == edgeRowCols[j][k][1]){
                                                nextmove = priorityPiece();
                                            }
                                            if (nextmove == edgeRowCols[j][k][0] || nextmove == edgeRowCols[j][k][1]){
                                                nextmove = priorityPiece();
                                            }
                                            board[nextmove] = String.valueOf(nextmove+1);
                                        }
                                        else {
                                            oturnleft --;
                                        }
                                        board[i] = "O";
                                        printBoard();
                                        winner = checkWinner();
                                        AIhasplayed = true;
                                        break outerloop;
                                    }
                                }
                            }
                        }
                        if (i == 4){
                            for (int j=0; j<4; j++){
                                if (board[centerRowCols[j][0]].equals("O") && board[centerRowCols[j][1]].equals("O")){
                                    if (oturnleft == 0) {
                                        int nextmove = priorityPiece();
                                        if (nextmove == centerRowCols[j][0] || nextmove == centerRowCols[j][1]){
                                            nextmove = priorityPiece();
                                        }
                                        if (nextmove == centerRowCols[j][0] || nextmove == centerRowCols[j][1]){
                                            nextmove = priorityPiece();
                                        }
                                        board[nextmove] = String.valueOf(nextmove+1);
                                    }
                                    else {
                                        oturnleft --;
                                    }
                                    board[4] = "O";
                                    printBoard();
                                    winner = checkWinner();
                                    AIhasplayed = true;
                                    break outerloop;
                                }
                            }
                        }
                    }
                }
                if (!AIhasplayed){
                outerloop2:
                    for (int i=0; i<9; i++){
                        if (board[i].equals(String.valueOf(i+1))){
                            for (int j=0; j<4; j++){
                                if (corners[j] == i){
                                    for (int k=0; k<3; k++){
                                        if (board[cornerRowCols[j][k][0]].equals("X") && board[cornerRowCols[j][k][1]].equals("X")){
                                            if (oturnleft == 0) {
                                                int nextmove = priorityPiece();
                                                board[nextmove] = String.valueOf(nextmove+1);
                                            }
                                            else {
                                                oturnleft --;
                                            }
                                            board[i] = "O";
                                            printBoard();
                                            winner = checkWinner();
                                            AIhasplayed = true;
                                            break outerloop2;
                                        }
                                    }
                                }
                                if (edges[j] == i){
                                    for (int k=0; k<2; k++){
                                        if (board[edgeRowCols[j][k][0]].equals("X") && board[edgeRowCols[j][k][1]].equals("X")){
                                            if (oturnleft == 0) {
                                                int nextmove = priorityPiece();
                                                if (nextmove == -1){
                                                    System.out.println("System breakdown. AI stopped thinking. You win!");
                                                    return;
                                                }
                                                board[nextmove] = String.valueOf(nextmove+1);
                                            }
                                            else {
                                                oturnleft --;
                                            }
                                            board[i] = "O";
                                            printBoard();
                                            winner = checkWinner();
                                            AIhasplayed = true;
                                            break outerloop2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (!AIhasplayed){
                    int nextmove = -1;
                    if (oturnleft == 0){
                        nextmove = priorityPiece();
                        if (nextmove == -1){
                            System.out.println("System breakdown. AI stopped thinking. You win!");
                            return;
                        }
                    }
                    else {
                        oturnleft --;
                    }
                    board[prioritySpace()] = "O";
                    if (nextmove >= 0){
                        board[nextmove] = String.valueOf(nextmove+1);
                    }
                    printBoard();
                    winner = checkWinner();
                }
                turn = "X";
                
                continue;
            }
            int numInput;
            try {
                numInput = in.nextInt();
                if (!(numInput >= 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
			}
			if (numInput == 0) {
				System.out.println("Select a piece to move:");
				try {
					numInput = in.nextInt();
					if (!(numInput > 0 && numInput <= 9)) {
						System.out.println("Invalid input; re-enter slot number:");
						continue;
					}
					if (!(board[numInput-1].equals("X") || board[numInput-1].equals("O"))){
						System.out.println("This is an empty slot; re-enter 0");
						continue;						
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
				String movedTurn = board[numInput-1];
				int movedSlot = numInput;
				board[numInput-1] = String.valueOf(numInput);
				System.out.println("Where to move?");
				try {
					numInput = in.nextInt();
					if (!(numInput > 0 && numInput <= 9)) {
						System.out.println("Invalid input; re-enter slot number:");
						continue;
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
				if (board[numInput-1].equals("X") || board[numInput-1].equals("O")){
					System.out.println("This is NOT an empty slot; start over and re-enter 0");
					board[movedSlot-1] = movedTurn;
					continue;						
				}
				else {
					board[numInput-1] = movedTurn;					
				}
					turn = "O";
				printBoard();
				winner = checkWinner();
			}
			else if (board[numInput-1].equals(String.valueOf(numInput))) {
				if (turn.equals("X")) {
					if (xturnleft == 0) {
						System.out.println("No more pieces left for X!");
						continue;
					}
                    board[numInput-1] = turn;
                    if (turn.equals("X")) {
                        xturnleft --;
                        turn = "O";
                    } else {
                        oturnleft --;
                        turn = "X";
                    }
                    printBoard();
                    winner = checkWinner();
				}
				
			} else {
				System.out.println("Slot already taken; re-enter slot number:");
				continue;
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}
    
    static int priorityPiece() {
        int[] corners = {0, 2, 6, 8};
        int[][][] cornerRowCols = {{{1, 2}, {3, 6}, {4, 8}}, {{0, 1}, {4, 6}, {5, 8}}, {{0, 3}, {2, 4}, {7, 8}}, {{0, 4}, {2, 5}, {6, 7}}};
        int[] edges = {1, 3, 5, 7};
        int[][][] edgeRowCols = {{{0, 2}, {4, 7}}, {{0, 6}, {4, 5}}, {{2, 8}, {3, 4}}, {{1, 4}, {6, 8}}};
        for (int i=0; i<9; i++){
            if (board[i].equals("O")){
            outerloop:
                for (int j=0; j<4; j++){
                    if (corners[j] == i){
                        for (int k=0; k<3; k++){
                            if (board[cornerRowCols[j][k][0]].equals("X") && board[cornerRowCols[j][k][1]].equals("X")){
                                break outerloop;
                            }
                        }
                        return i;
                    }
                    if (edges[j] == i){
                        for (int k=0; k<2; k++){
                            if (board[cornerRowCols[j][k][0]].equals("X") && board[cornerRowCols[j][k][1]].equals("X")){
                                break outerloop;
                            }
                        }
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    static int prioritySpace() {
        if (board[4].equals(String.valueOf(5))){
            return 4;
        }
        int[] corners = {0, 2, 6, 8};
        int[][] cornerRowCols = {{1, 2, 3, 4, 6, 8}, {0, 1, 4, 5, 6, 8}, {0, 2, 3, 4, 7, 8}, {0, 2, 4, 5, 6, 7}};
        int[] edges = {1, 3, 5, 7};
        int[][] edgeRowCols = {{0, 2, 4, 7}, {0, 4, 5, 6}, {2, 3, 4, 8}, {1, 4, 6, 8}};
        int bestcorner = -1;
 

        int maxSumAI = 0;
        for (int i=0; i<4; i++){
            int sumAI = 0;
            if (board[corners[i]].equals(String.valueOf(corners[i]+1))){
                for (int j=0; j<6; j++){
                    if (board[cornerRowCols[i][j]].equals("O")){
                        sumAI ++;
                    }
                }
                if (sumAI > maxSumAI){
                    maxSumAI = sumAI;
                    bestcorner = corners[i];
                }
            }
        }
        if (bestcorner >= 0){
            return bestcorner;
        }
        
        int maxSumHuman = 0;
        for (int i=0; i<4; i++){
            int sumHuman = 0;
            if (board[corners[i]].equals(String.valueOf(corners[i]+1))){
                for (int j=0; j<6; j++){
                    if (board[cornerRowCols[i][j]].equals("X")){
                        sumHuman ++;
                    }
                }
                if (sumHuman > maxSumHuman){
                    maxSumHuman = sumHuman;
                    bestcorner = corners[i];
                }
            }
        }
        if (bestcorner >= 0){
            return bestcorner;
        }
        
        for (int i=0; i<4; i++){
            if (board[corners[i]].equals(String.valueOf(corners[i]+1))){
                return corners[i];
            }
        }

        int bestedge = -1;
        maxSumAI = 0;
        for (int i=0; i<4; i++){
            int sumAI = 0;
            if (board[corners[i]].equals(String.valueOf(corners[i]+1))){
                for (int j=0; j<4; j++){
                    if (board[edgeRowCols[i][j]].equals("O")){
                        sumAI ++;
                    }
                }
                if (sumAI > maxSumAI){
                    maxSumAI = sumAI;
                    bestedge = edges[i];
                }
            }
        }
        if (bestedge >= 0){
            return bestedge;
        }
        
        for (int i=0; i<4; i++){
            int sumHuman = 0;
            if (board[corners[i]].equals(String.valueOf(corners[i]+1))){
                for (int j=0; j<4; j++){
                    if (board[edgeRowCols[i][j]].equals("X")){
                        sumHuman ++;
                    }
                }
                if (sumHuman > maxSumHuman){
                    maxSumHuman = sumHuman;
                    bestedge = edges[i];
                }
            }
        }
        if (bestedge >= 0){
            return bestedge;
        }

        for (int i=0; i<4; i++){
            if (board[edges[i]].equals(String.valueOf(edges[i]+1))){
                return edges[i];
            }
        }
        
        return -1;
    }

	static String checkWinner() {
        for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a+1))) {
				break;
			}
			else if (a == 8) return "draw";
		}

		System.out.println("Enter a slot number:");
		System.out.println("(to move a piece, enter '0')");
		return null;
	}

	static void printBoard() {
		System.out.print(String.format("\033[2J"));
		System.out.println("Welcome to 1 Player Tic Tac Toe with Moveable Pieces");
		System.out.println("----------------------------------------------------");	
		System.out.println("  O                     X");
		System.out.println("/---\\  /---|---|---\\  /---\\");
		System.out.println("| "+ (oturnleft >= 1 ? "v" : " ") +" |  | " + board[0] + " | " + board[1] + " | " + board[2] + " |  | "+ (xturnleft >= 1 ? "v" : " ") +" |");
		System.out.println("|---|  |-----------|  |---|");
		System.out.println("| "+ (oturnleft >= 2 ? "v" : " ") +" |  | " + board[3] + " | " + board[4] + " | " + board[5] + " |  | "+ (xturnleft >= 2 ? "v" : " ") +" |");
		System.out.println("|---|  |-----------|  |---|");
		System.out.println("| "+ (oturnleft >= 3 ? "v" : " ") +" |  | " + board[6] + " | " + board[7] + " | " + board[8] + " |  | "+ (xturnleft >= 3 ? "v" : " ") +" |");
		System.out.println("/---\\  /---|---|---\\  /---\\");
	}

	static void populateEmptyBoard() {
		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a+1);
		}
	}


}
