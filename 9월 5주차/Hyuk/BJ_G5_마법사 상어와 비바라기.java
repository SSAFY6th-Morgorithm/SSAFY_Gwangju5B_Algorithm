package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_21610 {
	static int N, M;
	static int map[][];
	static int deltas[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static StringTokenizer st;

	static List<RC> clouds = new ArrayList<>();

	static class RC {
		int r, c;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "\n[r=" + r + ", c=" + c + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clouds.add(new RC(N - 1, 0));
		clouds.add(new RC(N - 1, 1));
		clouds.add(new RC(N - 2, 0));
		clouds.add(new RC(N - 2, 1));

		for (int i = 0; i < M; i++) {
//			System.out.println("---------이동 전 ---------");
//			System.out.println(clouds);
//			for (int j = 0; j < N ; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			int size = clouds.size();
			
			for (int j = 0; j < size; j++) {
				RC temp = clouds.get(j);
				temp.r = (temp.r + deltas[d][0] * s + N * 51) % N;
				temp.c = (temp.c + deltas[d][1] * s + N * 51) % N;
				map[temp.r][temp.c]++;
			}

			for (int j = 0; j < size; j++) {
				RC temp = clouds.get(j);
				int cnt = 0;
				for (int delta = 0; delta < 4; delta++) {
					int nr = temp.r + deltas[delta * 2 + 1][0];
					int nc = temp.c + deltas[delta * 2 + 1][1];
					if (isIn(nr, nc) && map[nr][nc] > 0) {
						cnt++;
					}
				}
				map[temp.r][temp.c] += cnt;
			}
			
			for (int j = 0; j < size; j++) {
				RC temp = clouds.get(j);
				map[temp.r][temp.c] -= 1000;
			}
			
			clouds.clear();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] >= 2) {
						clouds.add(new RC(r, c));
						map[r][c] -= 2;
					} else if (map[r][c] < -500) {
						map[r][c] += 1000;
					}
				}
			}
		}
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				answer += map[r][c];
			}
		}
		System.out.println(answer);
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
