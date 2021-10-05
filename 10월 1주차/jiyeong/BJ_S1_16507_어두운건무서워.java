package prefix_sum;

import java.io.*;
import java.util.*;

public class BJ_S1_16507_어두운건무서워 {

	static int R, C, Q;
	static int[][] pic;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		pic = new int[R + 1][C + 1];
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int j = 1; j <= C; j++) {
				sum += Integer.parseInt(st.nextToken());
				pic[i][j] = pic[i - 1][j] + sum;
			}
		}
		
//		for(int[] r:pic)
//			System.out.println(Arrays.toString(r));
		
		for (int x = 0; x < Q; x++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			sb.append((pic[r2][c2] - pic[r1 - 1][c2] - pic[r2][c1 - 1] + pic[r1 - 1][c1 - 1])/ ((r2 - r1 + 1) * (c2 - c1 + 1))).append("\n");
//			int avg = ((pic[r2][c2] - pic[r1-1][c2] - pic[r2][c1-1] + pic[r1-1][c1-1]) / ((r2-r1+1)*(c2-c1+1)));
//			System.out.println(avg);
		}
		System.out.print(sb);
	}
}