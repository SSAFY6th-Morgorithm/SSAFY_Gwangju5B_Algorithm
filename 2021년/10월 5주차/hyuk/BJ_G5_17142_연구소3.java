package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G5_17142_연구소3 {
	static int N, M, size;
	static int[][] map;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static List<RC> list = new ArrayList<>();
	static Queue<RC> queue = new LinkedList<>();
	static StringTokenizer st;

	static class RC {
		int r, c;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
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
				if (map[i][j] == 2)
					list.add(new RC(i, j));
				else if(map[i][j] == 1) {
					map[i][j] = -5;
				}
			}
		}
		size = list.size();
		int[] arr = new int[size];

		for (int i = 0; i < M; i++) {
			arr[size - i - 1] = 1;
		}
		int answer = 99999;
		do {
			queue.clear();
			int[][] tempMap = new int[N][N];

			for (int i = 0; i < N; i++) {
				tempMap[i] = Arrays.copyOf(map[i], N);
			}
			for (int i = 0; i < size; i++) {
				RC temp = list.get(i);
				tempMap[temp.r][temp.c] = -2;

				if (arr[i] == 1) {
					queue.offer(temp);
					tempMap[temp.r][temp.c] = -1;
				}
			}
			int sub = bfs(tempMap);
			if (sub != -1)
				answer = Math.min(answer, sub);
//			System.out.println(Arrays.toString(arr));
//			for (int i = 0; i < N ; i++) {
//				System.out.println(Arrays.toString(tempMap[i]));
//			}
//			System.out.println();
		} while (np(arr));
		System.out.println(answer = answer == 99999 ? -1 : answer);

	}

	public static int bfs(int[][] map) {
		int size = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			cnt++;
			for (int step = 0; step < size; step++) {
				RC temp = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nr = temp.r + deltas[i][0];
					int nc = temp.c + deltas[i][1];
					if (isIn(nr, nc)) {
						if (map[nr][nc] == 0) {
							queue.offer(new RC(nr, nc));
							map[nr][nc] = cnt;
						}else if(map[nr][nc] == -2) {
							queue.offer(new RC(nr, nc));
							map[nr][nc] = -1;
						}
					}
				}
			}
		}
		int maxval = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					return -1;
				maxval = Math.max(maxval, map[i][j]);
			}
		}
		if(maxval == -1) {
			return 0;
		}
		return maxval;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

	public static boolean np(int[] numbers) {
		int i, j, k;
		i = j = k = numbers.length - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i])
			--i;

		if (i == 0)
			return false;

		while (numbers[i - 1] >= numbers[j])
			--j;

		swap(numbers, i - 1, j);

		while (i <= k) {
			swap(numbers, i++, k--);
		}

		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
