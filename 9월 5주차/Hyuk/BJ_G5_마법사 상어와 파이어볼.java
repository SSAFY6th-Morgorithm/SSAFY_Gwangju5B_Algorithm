package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_20056 {
	static int N, M, K;
	static int deltas[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static StringTokenizer st;
	static List<FireBall> list = new ArrayList<>();

	static class FireBall {
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "\n[r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(M == 0) return;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}
		
		for (int i = 0; i < K ; i++) {
//			System.out.println(list);
			move();
			checkMap();
		}
//		System.out.println(list);
		int answer = 0;
		for (int i = 0; i < list.size() ; i++) {
			answer += list.get(i).m;
		}
		System.out.println(answer);
	}

	static void move() {
		for (int i = 0; i < list.size(); i++) {
			FireBall temp = list.get(i);
			temp.r = (temp.r + deltas[temp.d][0] * temp.s + N*1001) % N;
			temp.c = (temp.c + deltas[temp.d][1] * temp.s + N*1001) % N;
		}
	}

	static void checkMap() {
		int[][] map = new int[N][N];
		for (int i = 0; i < list.size(); i++) {
			FireBall temp = list.get(i);
			map[temp.r][temp.c]++;
		}
		for (int i = 0; i < N ; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(list);
		System.out.println();
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				if(map[i][j] > 1)
					diffuse(i,j);
			}
		}
	}

	static void diffuse(int r, int c) {
		List<Integer> same = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			FireBall temp = list.get(i);
			if (temp.r == r && temp.c == c) {
				same.add(i);
			}
		}

		int m = 0, s = 0;
		int odd = 0, even = 0;
		for (int i = 0; i < same.size(); i++) {
			int index = same.get(i);
			FireBall temp = list.get(index);
			m += temp.m;
			s += temp.s;
			if (temp.d % 2 == 0)
				even++;
			else
				odd++;
		}
//		System.out.println("entire : " + same);
		for (int i = same.size() - 1; i >= 0; i--) {
			int index = same.get(i);
			list.remove(index);
		}
		if (m < 5)
			return;
		m /= 5;
		s /= same.size();
		if (odd == same.size() || even == same.size()) {
			for (int i = 0; i < 4; i++) {
				list.add(new FireBall(r, c, m, s, i * 2));
			}
		} else {
			for (int i = 0; i < 4 ; i++) {
				list.add(new FireBall(r,c,m,s,i*2+1));
			}
		}

	}
}
