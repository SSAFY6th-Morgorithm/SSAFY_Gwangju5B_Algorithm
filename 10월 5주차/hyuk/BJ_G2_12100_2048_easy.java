package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G2_12100_2048_easy {
	static int N, maxNumber = 0;
	static int[][] map;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static Stack<Integer> stack = new Stack<>();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 4; i++) {
			move(0, i, map);
		}
		System.out.println(maxNumber);
//		move(0,3,map);

	}

	static void move(int cnt, int dir, int[][] map) {

		if (cnt == 5) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maxNumber = Math.max(maxNumber, map[i][j]);
				}
			}
			return;
		}

		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			tempMap[i] = Arrays.copyOf(map[i], N);
		}
		int r = 0, c = 0;
		if (dir >= 2) {
			r = N - 1;
			c = N - 1;
		}
		int direction = dir % 2 == 0 ? dir + 1 : dir - 1;

		for (int i = 0; i < N; i++) {
			int nr = r;
			int nc = c;
			while (isIn(nr, nc)) {
				if (tempMap[nr][nc] != 0) {
					stack.push(tempMap[nr][nc]);
				}
				nr += deltas[dir][0];
				nc += deltas[dir][1];
			}
			nr -= deltas[dir][0];
			nc -= deltas[dir][1];

			while (isIn(nr, nc)) {
				int number=0;
				if (!stack.isEmpty()) {
					number = stack.pop();
					if(!stack.isEmpty() && stack.peek() == number) {
						number += stack.pop();
					}
				}
				tempMap[nr][nc] = number;
				nr -= deltas[dir][0];
				nc -= deltas[dir][1];
			}
			r += deltas[direction][0];
			c += deltas[direction][1];
		}

		for (int i = 0; i < 4; i++) {
				move(cnt + 1, i, tempMap);
		}
//		for (int i = 0; i < N ; i++) {
//			System.out.println(Arrays.toString(tempMap[i]));
//		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}
