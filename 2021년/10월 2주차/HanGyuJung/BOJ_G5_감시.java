package week1002;

import java.util.*;
import java.io.*;

//순열로 방향정해주고 그때마다 사각지대 구하자
class CCTV {
	int r;
	int c;

	public CCTV(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BOJ_G5_감시 {

	private static int R;
	private static int C;
	private static int answer = Integer.MAX_VALUE;
	private static int[][] map, copymap;
	static ArrayList<CCTV> cctvs = new ArrayList<>();
	static int[] directions;
	static int[][] vector = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] >= 1 && map[r][c] <= 5) {
					cctvs.add(new CCTV(r, c));
				}
			}
		}
		directions = new int[cctvs.size()];
		permutation(0);
		System.out.println(answer);

	}

	private static void permutation(int count) {
		if (count == cctvs.size()) {
			System.out.println(Arrays.toString(directions));
			copymap = new int[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					copymap[r][c] = map[r][c];
				}
			}
			watchCCTV();
			calcSagak();
			return;
		}
		for (int i = 0; i < 4; i++) {
			directions[count] = i;
			permutation(count + 1);
		}
	}

	private static void calcSagak() {
		int sumSagak = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (copymap[r][c] == 0)
					sumSagak++;
			}
		}
		answer = Math.min(answer, sumSagak);
	}

	private static void watchCCTV() {

		for (int i = 0; i < cctvs.size(); i++) {
			CCTV cctv = cctvs.get(i);
			int level = copymap[cctv.r][cctv.c];
			switch (level) {
			case 1: {
				see(directions[i], cctv);
				break;
			}
			case 2: {
				see(directions[i], cctv);
				see((directions[i] + 2) % 4, cctv);
				break;

			}
			case 3: {
				see(directions[i], cctv);
				see((directions[i] + 1) % 4, cctv);
				break;

			}
			case 4: {
				see(directions[i], cctv);
				see((directions[i] + 1) % 4, cctv);
				see((directions[i] + 2) % 4, cctv);
				break;

			}
			case 5: {
				see(directions[i], cctv);
				see((directions[i] + 1) % 4, cctv);
				see((directions[i] + 2) % 4, cctv);
				see((directions[i] + 3) % 4, cctv);
			}

			}
		}
	}

	private static void see(int d, CCTV cctv) {
		int nr = cctv.r;
		int nc = cctv.c;
		while (true) {
			nr += vector[d][0];
			nc += vector[d][1];
			if (!isIn(nr, nc) || map[nr][nc] == 6) {
				return;
			}
			if(copymap[nr][nc]>0) continue;
			copymap[nr][nc] = -1;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
