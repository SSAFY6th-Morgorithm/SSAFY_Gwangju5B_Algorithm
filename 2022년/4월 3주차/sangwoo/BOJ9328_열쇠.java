import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9328_열쇠 {
    private static final int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(input.readLine());
            int R = Integer.parseInt(st.nextToken()) + 2, C = Integer.parseInt(st.nextToken()) + 2;
            char[][] map = new char[R][C];
            for (int r = 0; r < R; r++) {
                Arrays.fill(map[r], '.');
            }
            for (int r = 1; r <= R - 2; r++) {
                String line = input.readLine();
                for (int c = 1; c <= C - 2; c++) {
                    map[r][c] = line.charAt(c - 1);
                }
            }
            boolean[] key = new boolean[26];
            String keys = input.readLine();
            if (!"0".equals(keys)) {
                for (int i = 0; i < keys.length(); i++)
                    key[keys.charAt(i) - 'a'] = true;
            }
            int count = 0;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] v = new boolean[R][C];
            v[0][0] = true;
            q.offer(new int[]{0, 0});
            while (!q.isEmpty()) {
                int[] tmp = q.poll();
                int r = tmp[0], c = tmp[1];
                for(int d = 0; d < deltas.length; d++) {
                    int nr = r + deltas[d][0], nc = c + deltas[d][1];
                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && map[nr][nc] != '*') {
                        if(map[nr][nc] == '.') {
                            v[nr][nc] = true;
                            q.offer(new int[]{nr, nc});
                        } else if(map[nr][nc] == '$') {
                            v[nr][nc] = true;
                            map[nr][nc] = '.';
                            q.offer(new int[]{nr, nc});
                            count++;
                        } else if(Character.isUpperCase(map[nr][nc])) {
                            if(key[Character.toLowerCase(map[nr][nc]) - 'a']) {
                                v[nr][nc] = true;
                                map[nr][nc] = '.';
                                q.offer(new int[]{nr, nc});
                            }
                        } else {
                            v = new boolean[R][C];
                            key[map[nr][nc] - 'a'] = true;
                            v[nr][nc] = true;
                            map[nr][nc] = '.';
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            answer.append(count).append("\n");
        }
        System.out.println(answer);
    }
}
