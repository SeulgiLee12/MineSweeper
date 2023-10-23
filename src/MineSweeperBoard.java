import java.util.Random;
import java.util.Scanner;

public class MineSweeperBoard {
    public static void main(String[] args) {
        int input = 0;
        int mine_num = 0;

        // 게임보드 크기랑 지뢰개수 입력 받기
        try {
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("input row number : ");
                input = scan.nextInt();
                if (input <= 4 || input >= 16) System.out.println("invalid number");
                else break;
            }
            while (true) {
                System.out.println("input mine number : ");
                mine_num = scan.nextInt();
                if (Math.floor(input*input*0.1) > mine_num || Math.floor(input*input*0.2) < mine_num)
                    System.out.println("invalid number");
                else break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 지뢰찾기 보드 만들기
        String board[][] = new String[input][input];
        for (int i=0; i<input; i++) {
            for (int j=0; j<input; j++) {
                board[i][j] = "O";
            }
        }

        // 지뢰 랜덤 배치
        int mine_list[] = new int[mine_num];
        Random random = new Random();
        for (int i=0; i<mine_num; i++) {
            mine_list[i] = random.nextInt(input,input*input);
            for (int j=0; j<i; j++) { // 중복검사
                if (mine_list[i] == mine_list[j]) {
                    mine_list[i] = random.nextInt(input,input*input);
                    break;
                }
            }
        }

        // 지뢰 위치 표시
        for (int n: mine_list) {
            int a = n/input; int b = n%input;
            board[a][b] = "X";
        }

        // 주변 지뢰 개수 표시
        int mine_pos[] = {-1,0,1};
        int mine_around = 0;
        for (int i=0; i<input*input; i++) {
            if (board[i/input][i%input] == "X") continue;
            for (int n: mine_pos) {
                for (int m: mine_pos) {
                    if (i/input+n >= 0 && i/input+n < input && i%input+m >= 0 && i%input+m < input) {
                        if (board[i/input+n][i%input+m] == "X") mine_around++;
                    }
                }
            }
            board[i/input][i%input] = Integer.toString(mine_around);
            mine_around = 0;
        }

        // 게임보드 출력
        for (int i=0; i<input; i++) {
            for (int j=0; j<input; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }
}

