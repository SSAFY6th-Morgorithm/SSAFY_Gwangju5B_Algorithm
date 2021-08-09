package day0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_2178 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] vector = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			char[] c_arr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c_arr[j] - '0';
			}
			
		}
		
// map 찍어보기
//		for (int arr[] : map) {
//			System.out.println(Arrays.toString(arr));
//		}

		bfs(0, 0);
		sb.append(map[N - 1][M - 1]);
		System.out.println(sb);
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { i, j });
		
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			visited[temp[0]][temp[1]] = true;

			for (int d = 0; d < 4; d++) {
				int nr = temp[0] + vector[d][0];
				int nc = temp[1] + vector[d][1];
				
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					q.offer(new int[] { nr, nc });
					map[nr][nc] = map[temp[0]][temp[1]] + 1;
				}
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
