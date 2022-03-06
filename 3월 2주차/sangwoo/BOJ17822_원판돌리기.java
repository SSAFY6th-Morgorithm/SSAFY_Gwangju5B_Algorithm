import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17822_원판돌리기 {
    static int[][] roundPanel;
    static int N, M, T;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        roundPanel = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                roundPanel[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(input.readLine());
            int xi = Integer.parseInt(st.nextToken()), di = Integer.parseInt(st.nextToken()), ki = Integer.parseInt(st.nextToken());
            rotate(xi, di, ki);
        }
        int answer = getTotal();
        System.out.println(answer);
    }

    private static int getTotal() {
        int sum = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < M; j++)
                sum += roundPanel[i][j];
        return sum;
    }

    private static void rotate(int xi, int di, int ki) {
        for (int i = 1; i <= N; i++) {
            if (i % xi == 0) {
                for(int k = 0; k < ki; k++) {
                    if (di == 0) {
                        int tmp = roundPanel[i][M - 1];
                        for (int j = M - 1; j >= 1; j--) {
                            roundPanel[i][j] = roundPanel[i][j - 1];
                        }
                        roundPanel[i][0] = tmp;
                    } else {
                        int tmp = roundPanel[i][0];
                        for (int j = 0; j < M - 1; j++) {
                            roundPanel[i][j] = roundPanel[i][j + 1];
                        }
                        roundPanel[i][M - 1] = tmp;
                    }
                }
            }
        }
        // 오른쪽, 바깥쪽 원판만 확인하면 중복 제거
        boolean[][] deleted = new boolean[N + 1][M];
        // 오른쪽 확인
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (roundPanel[i][j] != 0 && roundPanel[i][j] == roundPanel[i][(j + 1) % M]) {
                    deleted[i][j] = true;
                    deleted[i][(j + 1) % M] = true;
                }
            }
        }
        // 바깥쪽 확인
        for (int j = 0; j < M; j++) {
            for (int i = 1; i <= N - 1; i++) {
                if (roundPanel[i][j] != 0 && roundPanel[i][j] == roundPanel[i + 1][j]) {
                    deleted[i][j] = true;
                    deleted[i + 1][j] = true;
                }
            }
        }
        int count = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(deleted[i][j]) {
                    roundPanel[i][j] = 0;
                    count++;
                }
            }
        }
        if(count != 0) return;
        double sum = 0;
        count = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(roundPanel[i][j] != 0) {
                    sum += roundPanel[i][j];
                    count++;
                }
            }
        }
        double avg = sum / count;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(roundPanel[i][j] != 0) {
                    if(roundPanel[i][j] > avg)
                        roundPanel[i][j]--;
                    else if(roundPanel[i][j] < avg)
                        roundPanel[i][j]++;
                }
            }
        }
    }
}
