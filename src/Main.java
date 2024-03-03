public class Main {
    public static int whiteBallRow = 0;    // global white ball row.
    public static int whiteBallColumn = 0;     // global white ball column.
    public static int score = 0;     // initial score.
    public static void main(String[] args) {
        FileWriting.writeToFile("output.txt", "", false, false);
        boolean gameOver = false;      // for stopping the game when "*" fall into the hole.
        String boardfile = args[0];
        String[] board = FileReading.readFile(boardfile);
        int rowLength = board.length;
        int columnLength = board[0].split(" ").length;
        String[][] board2arr = new String[rowLength][columnLength];
        FileWriting.writeToFile("output.txt", "Game Board:", true, true);
        for (int i = 0; i < rowLength; i++) {
            board2arr[i] = board[i].split(" ");
        }
        showBoard(board2arr);
        FileWriting.writeToFile("output.txt", "", true, true);
        FileWriting.writeToFile("output.txt", "Your movement is:", true, true);
        String movefile = args[1];
        String[] movesLine = FileReading.readFile(movefile);
        String[] moves = movesLine[0].split(" ");
        outer:
        // check for every move
        for (String move : moves) {
            FileWriting.writeToFile("output.txt", move + " ", true, false);
            // for finding the location of "*"
            for (int i = 0; i <= rowLength - 1; i++) {
                for (int j = 0; j <= columnLength - 1; j++) {
                    if (board2arr[i][j].equals("*")) {
                        whiteBallRow = i;
                        whiteBallColumn = j;
                    }
                }
            }
            // check every move cases.
            // swap or
            // fall into the hole or
            // hits the wall and moves 2 units to the opposite direction from wall.
            String temp = "?";
            switch (move) {
                case "R":
                    temp = board2arr[whiteBallRow][Math.floorMod(whiteBallColumn + 1, columnLength)];
                    if (temp.equals("W")) {
                        temp = board2arr[whiteBallRow][Math.floorMod(whiteBallColumn - 1, columnLength)];
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                        board2arr[whiteBallRow][Math.floorMod(whiteBallColumn - 1, columnLength)] = "*";
                    } else if (temp.equals("H")) {
                        board2arr[whiteBallRow][whiteBallColumn] = " ";
                        gameOver = true;
                        break outer;
                    } else {
                    board2arr[whiteBallRow][Math.floorMod(whiteBallColumn + 1, columnLength)] = "*";
                    board2arr[whiteBallRow][whiteBallColumn] = temp;
                }
                    finalPoint(temp ,board2arr, whiteBallRow, whiteBallColumn);
                    break;
                case "L":
                    temp = board2arr[whiteBallRow][Math.floorMod(whiteBallColumn - 1 , columnLength)];
                    if (temp.equals("W")) {
                        temp = board2arr[whiteBallRow][Math.floorMod(whiteBallColumn + 1 , columnLength)];
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                        board2arr[whiteBallRow][Math.floorMod(whiteBallColumn + 1 , columnLength)] = "*";
                    } else if (temp.equals("H")) {
                        board2arr[whiteBallRow][whiteBallColumn] = " ";
                        gameOver = true;
                        break outer;
                    } else {
                        board2arr[whiteBallRow][Math.floorMod(whiteBallColumn - 1 , columnLength)] = "*";
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                    }
                    finalPoint(temp ,board2arr, whiteBallRow, whiteBallColumn);
                    break;
                case "U":
                    temp = board2arr[Math.floorMod(whiteBallRow - 1 , rowLength)][whiteBallColumn];
                    if (temp.equals("W")) {
                        temp = board2arr[Math.floorMod(whiteBallRow + 1 , rowLength)][whiteBallColumn];
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                        board2arr[Math.floorMod(whiteBallRow + 1 , rowLength)][whiteBallColumn] = "*";
                    } else if (temp.equals("H")) {
                        board2arr[whiteBallRow][whiteBallColumn] = " ";
                        gameOver = true;
                        break outer;
                    } else {
                        board2arr[Math.floorMod(whiteBallRow - 1 , rowLength)][whiteBallColumn] = "*";
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                    }
                    finalPoint(temp ,board2arr, whiteBallRow, whiteBallColumn);
                    break;
                case "D":
                    temp = board2arr[Math.floorMod(whiteBallRow + 1 , rowLength)][whiteBallColumn];
                    if (temp.equals("W")) {
                        temp = board2arr[Math.floorMod(whiteBallRow - 1 , rowLength)][whiteBallColumn];
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                        board2arr[Math.floorMod(whiteBallRow - 1 , rowLength)][whiteBallColumn] = "*";
                    } else if (temp.equals("H")) {
                        board2arr[whiteBallRow][whiteBallColumn] = " ";
                        gameOver = true;
                        break outer;
                    } else {
                        board2arr[Math.floorMod(whiteBallRow + 1 , rowLength)][whiteBallColumn] = "*";
                        board2arr[whiteBallRow][whiteBallColumn] = temp;
                    }
                    finalPoint(temp ,board2arr, whiteBallRow, whiteBallColumn);
                    break;
                }
            }
        FileWriting.writeToFile("output.txt",  "", true, true);
        FileWriting.writeToFile("output.txt",  "", true, true);
        FileWriting.writeToFile("output.txt", "Your output is:", true, true);
        showBoard(board2arr);
        FileWriting.writeToFile("output.txt", "", true, true);
        if (gameOver) {FileWriting.writeToFile("output.txt", "Game over!", true, true);}
        FileWriting.writeToFile("output.txt", "Score: " + score, true, false);
    }
    public static void finalPoint (String temp ,String[][] board, int row , int column){
        // method for calculating the score
        switch (temp) {
            case "B":
                board[row][column] = "X";
                score -= 5;
                break;
            case "R":
                board[row][column] = "X";
                score += 10;
                break;
            case "Y":
                board[row][column] = "X";
                score += 5;
                break;
        }
    }
    public static void showBoard(String[][] board) {
        // method for printing the board
        for (String[] strings : board) {
            for (int j = 0; j < board[0].length; j++) {
                FileWriting.writeToFile("output.txt", strings[j] + " ", true, false);
            }
            FileWriting.writeToFile("output.txt", "", true, true);
        }
    }
}