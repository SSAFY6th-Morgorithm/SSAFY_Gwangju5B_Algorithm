package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_12764_sort {

	static int N; // 사람
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// 싸지방에 간 준하
		N = Integer.parseInt(br.readLine());

		List<PQTime> P = new ArrayList<>();
		List<QTime> Q = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int ptime = Integer.parseInt(st.nextToken());
			int qtime = Integer.parseInt(st.nextToken());
			P.add(new PQTime(ptime, qtime, 0));
		}
		Collections.sort(P); // 시작시간을 기준으로 오름차순 정렬

		for (int n = 0; n < N; n++) {
			Q.add(new QTime(n, P.get(n).q));
		}
		Collections.sort(Q); // 종료시간을 기준으로 오름차순 정렬

		PriorityQueue<Integer> com = new PriorityQueue<>();	// 비어있는 자리 관리
		int computer = 0;
		int end = 0;
		for (int start = 0; start < N; start++) {
			if (P.get(start).p < Q.get(end).q) {
				if (!com.isEmpty()) {
					P.get(start).num = com.poll();
				} else {
					P.get(start).num = ++computer;
				}
			} else {
				while (P.get(start).p > Q.get(end).q) {
					com.offer(P.get(Q.get(end).idx).num);
					++end;
				}
				P.get(start).num = com.poll();
			}
		}

		int[] count = new int[computer + 1];
		for (int c = 0; c < N; c++) {
			count[P.get(c).num]++;
		}

		sb.append(computer).append("\n");
		for (int i = 1; i <= computer; i++) {
			sb.append(count[i]).append(" ");
		}

		System.out.println(sb);

	}

	static class PQTime implements Comparable<PQTime> {
		int p, q, num; // p:시작시간, q:종료시간, num:컴퓨터 자리

		public PQTime(int p, int q, int num) {
			super();
			this.p = p;
			this.q = q;
			this.num = num;
		}

		@Override
		public int compareTo(PQTime o) {
			return Integer.compare(this.p, o.p);
		}
	}

	static class QTime implements Comparable<QTime> {
		int idx, q; // idx : PQTime의 인덱스, q : 종료시간

		public QTime(int idx, int q) {
			super();
			this.idx = idx;
			this.q = q;
		}

		@Override
		public int compareTo(QTime o) {
			return Integer.compare(this.q, o.q);
		}
	}
}