import java.util.Random;
import java.util.Scanner;

public class MineSweeperBoard {
    public static void main(String[] args) {
        int input = 0;

        try {
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("input Messages : ");
                input = scan.nextInt();
                if (input <= 4 || input >= 16) System.out.println("invalid number");
                else break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int mine_num = 2;

        int mine_pos[] = new int[mine_num];
        String board[][] = new String[input][input];

        Random random = new Random();
        for (int i=0; i<mine_num; i++) {
            mine_pos[i] = random.nextInt(input,input*input);
            System.out.println(mine_pos[i]);
        }

        for (int i=0; i<input; i++) {
            for (int j=0; j<input; j++) {
                board[i][j] = "O";
            }
        }

        for (int i=0; i<input; i++) {
            for (int j=0; j<input; j++) {
                for (int n=0; n<mine_num; n++) {
                    if (mine_pos[n]/input==i && mine_pos[n]%input==j)
                        board[i][j] = "X";
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
