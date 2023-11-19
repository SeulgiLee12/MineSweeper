import java.util.Random;
import java.util.Scanner;

public class MineSweeperBoard {
    int boardSize = 0;
    int mineNum = 0;
    final String MINE = "X";
    final String EMPTY = "0";
    final int MIN_NUMBER_OF_MINES = 4;
    final int MAX_NUMBER_OF_MINES = 16;
    int patternForCheckMines[] = {-1,0,1};
    int numberOfMinesAroundCell = 0;
    String board[][];
    int positionOfMines[];
    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    public void makeAndPrintBoard() {
        setBoardRowAndMineNumbers();
        makeEmptyBoard();
        setMinesPosition();
        markMines();
        markNumbersOfMinesAroundCell();
        printBoard();
    }


    public void setBoardRowAndMineNumbers() {
        // 게임보드 크기랑 지뢰개수 입력 받기
        try {
            setBoardRow();
            setMineNum();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void makeEmptyBoard() {
        board = new String[boardSize][boardSize];
        for (int row=0; row<boardSize; row++)
            for (int column=0; column<boardSize; column++)
                board[row][column] = EMPTY;
    }
    public void setMinesPosition() {
        positionOfMines = new int[mineNum];
        for (int target=0; target<mineNum; target++) {
            positionOfMines[target] = random.nextInt(boardSize,boardSize*boardSize);
            if (isMineDuplicated(target)) target--;

        }
    }
    public void markMines() {
        for (int mineCell: positionOfMines) {
            int row = mineCell/boardSize; int column = mineCell%boardSize;
            board[row][column] = MINE;
        }
    }
    public void markNumbersOfMinesAroundCell() {
        for (int row=0; row<boardSize; row++)
            for (int column=0; column<boardSize; column++) {
                if (board[row][column].equals(MINE)) continue;
                board[row][column] = Integer.toString(countMinesAroundCell(row, column));
        }
    }
    public void printBoard() {
        for (int row=0; row<boardSize; row++) {
            for (int column=0; column<boardSize; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    public void setMineNum() {
        while (true) {
            System.out.println("mine number : ");
            mineNum = scan.nextInt();
            if (Math.floor(boardSize*boardSize*0.1) > mineNum || Math.floor(boardSize*boardSize*0.2) < mineNum)
                System.out.println("invalid number");
            else break;
        }
    }
    public void setBoardRow() {
        while (true) {
            System.out.println("row number : ");
            boardSize = scan.nextInt();
            if (boardSize <= MIN_NUMBER_OF_MINES || boardSize >= MAX_NUMBER_OF_MINES)
                System.out.println("invalid number");
            else break;
        }
    }
    public int countMinesAroundCell(int row, int col) {
        numberOfMinesAroundCell = 0;
        for (int rowPattern: patternForCheckMines) {
            for (int colPattern: patternForCheckMines)
                if (row+rowPattern >= 0 &&
                    row+rowPattern < boardSize &&
                    col+colPattern >= 0 &&
                    col+colPattern < boardSize)
                    if (board[row+rowPattern][col+colPattern].equals(MINE)) numberOfMinesAroundCell++;
        }
        return numberOfMinesAroundCell;
    }
    public boolean isMineDuplicated(int target) {
        for (int check=0; check<target; check++)
            if (positionOfMines[target] == positionOfMines[check]) return true;
        return false;
    }

    public static void main(String[] args) {
        MineSweeperBoard m = new MineSweeperBoard();
        m.makeAndPrintBoard();
    }
}

