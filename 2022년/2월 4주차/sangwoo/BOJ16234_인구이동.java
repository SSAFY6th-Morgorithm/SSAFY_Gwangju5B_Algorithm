import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_인구이동 {
    final static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int N, L, R, answer;
    static int[][] populations;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        populations = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                populations[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        boolean flag;
        do {
            flag = false;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        flag = flag | bfs(r, c);
                    }
                }
            }
            if(!flag) break;
            answer++;

        } while (flag);

        System.out.println(answer);
    }

    static boolean bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> positions = new LinkedList<>();
        visited[r][c] = true;
        q.offer(new int[]{r, c});
        positions.offer(new int[]{r, c});
        int count = 1;
        int sum = populations[r][c];
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            for (int d = 0; d < deltas.length; d++) {
                int nr = tmp[0] + deltas[d][0], nc = tmp[1] + deltas[d][1];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int sub = Math.abs(populations[tmp[0]][tmp[1]] - populations[nr][nc]);
                    if (sub >= L && sub <= R) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        positions.offer(new int[]{nr, nc});
                        count++;
                        sum += populations[nr][nc];
                    }
                }
            }
        }
        if (count == 1) return false;
        int population = sum / count;
        while (!positions.isEmpty()){
            int[] tmp = positions.poll();
            populations[tmp[0]][tmp[1]] = population;
        }
        return true;
    }
}

