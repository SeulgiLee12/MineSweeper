import java.util.Random;

public class MineSweeperBoard {
    public static void main(String[] args) {
        int num = 5;
        int mine_num = 2;

        int mine_pos[] = new int[mine_num];

        String board[][] = new String[num][num];

        Random random = new Random();
        for (int i=0; i<mine_num; i++) {
            mine_pos[i] = random.nextInt(num,num*num);
        }

        for (int i=0; i<num; i++) {
            for (int j=0; j<num; j++) {
                board[i][j] = "O";
            }
        }

        for (int i=0; i<num; i++) {
            for (int j=0; j<num; j++) {
                for (int n=0; n<mine_num; n++) {
                    if (mine_pos[n]/num==i && mine_pos[n]%num==j)
                        board[i][j] = "X";
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
