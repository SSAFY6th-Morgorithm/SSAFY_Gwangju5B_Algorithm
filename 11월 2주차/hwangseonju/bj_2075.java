package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_2075 {

	static int N;	// N번째 숫자
	static int[][] num;
	static PriorityQueue<Integer> pq;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// N번째 큰 수
		N = Integer.parseInt(br.readLine());
		num = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				num[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	
		pq = new PriorityQueue<>(Collections.reverseOrder());	// 내림차순
		int last = 0;
		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				pq.add(num[i][j]);
			}
			last = pq.poll();	// NxN크기의 배열에서 N번째 큰 수이므로 각 행마다 큰 수를 poll하면 마지막으로 poll된 수가 답이다.
		}

		System.out.println(last);
	}
}