import java.util.Random;
import java.util.Scanner;

public class MineSweeperBoard {
    int BOARD_ROW = 0;
    int MINE_NUM = 0;
    int patternForCheckMines[] = {-1,0,1};
    int MINE_AROUND = 0;
    String board[][];
    int minePositions[];
    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    public void initialize() {
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
        for (int i=0; i<BOARD_ROW; i++) {
            for (int j=0; j<BOARD_ROW; j++) {
                board[i][j] = "O";
            }
        }
    }
    public void assignMinesPosition() {
        minePositions = new int[MINE_NUM];
        for (int i=0; i<MINE_NUM; i++) {
            minePositions[i] = random.nextInt(BOARD_ROW,BOARD_ROW*BOARD_ROW);
            for (int j=0; j<i; j++) // 중복검사
                if (minePositions[i] == minePositions[j]) i--;
        }
    }
    public void markMines() {
        for (int mineCell: minePositions) {
            int row = mineCell/BOARD_ROW; int column = mineCell%BOARD_ROW;
            board[row][column] = "X";
        }
    }
    public void markNumOfMinesAroundCell() {
        for (int row=0; row<BOARD_ROW; row++) {
            for (int column=0; column<BOARD_ROW; column++) {
                if (board[row][column] == "X") continue;
                board[row][column] = Integer.toString(countMinesAroundCell(row, column));

            }
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
        System.out.println("BOARD_ROW mine number : ");
        MINE_NUM = scan.nextInt();
        if (Math.floor(BOARD_ROW*BOARD_ROW*0.1) > MINE_NUM || Math.floor(BOARD_ROW*BOARD_ROW*0.2) < MINE_NUM)
            System.out.println("invalid number");
        else break;
        }
    }
    public void setBoardRow() {
        while (true) {
            System.out.println("BOARD_ROW row number : ");
            BOARD_ROW = scan.nextInt();
            if (BOARD_ROW <= 4 || BOARD_ROW >= 16) System.out.println("invalid number");
            else break;
        }
    }
    public int countMinesAroundCell(int row, int col) {
        MINE_AROUND = 0;
        for (int prow: patternForCheckMines) {
            for (int pcolumn: patternForCheckMines) {
                if (row+prow >= 0 && row+prow < BOARD_ROW && col+pcolumn >= 0 && col+pcolumn < BOARD_ROW) {
                    if (board[row+prow][col+pcolumn] == "X") MINE_AROUND++;
                }
            }
        }
        return MINE_AROUND;
    }

    public void makeAndPrintBoard() {
        initialize();
        makeEmptyBoard();
        assignMinesPosition();
        markMines();
        markNumOfMinesAroundCell();
        printBoard();
    }

    public static void main(String[] args) {
        MineSweeperBoard m = new MineSweeperBoard();
        m.makeAndPrintBoard();
    }
}

