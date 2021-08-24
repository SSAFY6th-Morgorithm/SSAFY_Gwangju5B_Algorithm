package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10830_행렬제곱 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	static int N;
	static long B;
	static int[][] data,next,map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		B=Long.parseLong(st.nextToken());
		data=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				data[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		map=new int[N][N];
//		next=data;
		
		for (int i=0; i<N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=data[i][j];
			}
		}
		
		
		for (int i = 1; i < B; i++) {
			map=cal(map);
		}

		for (int i=0; i<N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static int[][] cal(int[][] map) {
		int next[][]=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int n=0; n<N; n++) {
					next[i][j]+=(map[i][n]*data[n][j]);
				}
				next[i][j]%=1000;
			}
		}
		
		return next;
	}

}
