import java.util.Random;
import java.util.Scanner;

public class MineSweeperBoard {
    int BOARD_ROW = 0;
    int MINE_NUM = 0;
    final String MINE = "X";
    final String EMPTY = "0";
    final int MIN_NUMBER_OF_MINES = 4;
    final int MAX_NUMBER_OF_MINES = 16;
    int patternForCheckMines[] = {-1,0,1};
    int NUMBER_OF_MINES_AROUND_CELL = 0;
    String board[][];
    int positionOfMines[];
    Scanner scan = new Scanner(System.in);
    Random random = new Random();

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
        board = new String[BOARD_ROW][BOARD_ROW];
        for (int row=0; row<BOARD_ROW; row++)
            for (int column=0; column<BOARD_ROW; column++)
                board[row][column] = EMPTY;
    }
    public void setMinesPosition() {
        positionOfMines = new int[MINE_NUM];
        for (int target=0; target<MINE_NUM; target++) {
            positionOfMines[target] = random.nextInt(BOARD_ROW,BOARD_ROW*BOARD_ROW);
            if (isMineDuplicated(target)) target--;

        }
    }
    public void markMines() {
        for (int mineCell: positionOfMines) {
            int row = mineCell/BOARD_ROW; int column = mineCell%BOARD_ROW;
            board[row][column] = MINE;
        }
    }
    public void markNumbersOfMinesAroundCell() {
        for (int row=0; row<BOARD_ROW; row++)
            for (int column=0; column<BOARD_ROW; column++) {
                if (board[row][column].equals(MINE)) continue;
                board[row][column] = Integer.toString(countMinesAroundCell(row, column));
        }
    }
    public void printBoard() {
        for (int row=0; row<BOARD_ROW; row++) {
            for (int column=0; column<BOARD_ROW; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    public void setMineNum() {
        while (true) {
            System.out.println("mine number : ");
            MINE_NUM = scan.nextInt();
            if (Math.floor(BOARD_ROW*BOARD_ROW*0.1) > MINE_NUM || Math.floor(BOARD_ROW*BOARD_ROW*0.2) < MINE_NUM)
                System.out.println("invalid number");
            else break;
        }
    }
    public void setBoardRow() {
        while (true) {
            System.out.println("row number : ");
            BOARD_ROW = scan.nextInt();
            if (BOARD_ROW <= MIN_NUMBER_OF_MINES || BOARD_ROW >= MAX_NUMBER_OF_MINES)
                System.out.println("invalid number");
            else break;
        }
    }
    public int countMinesAroundCell(int row, int col) {
        NUMBER_OF_MINES_AROUND_CELL = 0;
        for (int rowPattern: patternForCheckMines) {
            for (int colPattern: patternForCheckMines)
                if (row+rowPattern >= 0 && row+rowPattern < BOARD_ROW &&
                        col+colPattern >= 0 && col+colPattern < BOARD_ROW)
                    if (board[row+rowPattern][col+colPattern].equals(MINE)) NUMBER_OF_MINES_AROUND_CELL++;
        }
        return NUMBER_OF_MINES_AROUND_CELL;
    }
    public boolean isMineDuplicated(int target) {
        for (int check=0; check<target; check++)
            if (positionOfMines[target] == positionOfMines[check]) return true;
        return false;
    }
    public void makeAndPrintBoard() {
        setBoardRowAndMineNumbers();
        makeEmptyBoard();
        setMinesPosition();
        markMines();
        markNumbersOfMinesAroundCell();
        printBoard();
    }

    public static void main(String[] args) {
        MineSweeperBoard m = new MineSweeperBoard();
        m.makeAndPrintBoard();
    }
}

