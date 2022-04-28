package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_17232 {
	
	static int N;	// 세로
	static int M;	// 가로
	static int T;	// 시간
	static int K;	// 기준
	static int a;
	static int b;
	static int[][] map;
	static int[][] count;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// 생명 게임
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		count = new int[N+1][M+1];
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		for(int n=1; n<=N; n++) {
			String str = br.readLine();
			for(int m=1; m<=M; m++) {
				char data = str.charAt(m-1);
				if(data=='*')
					map[n][m] = 1;
			}
		}
		
		for(int t=0; t<T; t++) {
				// 주위 생명 개수 파악하기
				for(int n=1; n<=N; n++) {
					for(int m=1; m<=M; m++) {
						count[n][m] = count[n - 1][m] + count[n][m - 1] - count[n - 1][m - 1] + map[n][m];
					}
				}
				
				for(int n=1; n<=N; n++) {
					for(int m=1; m<=M; m++) {
						int sr = n-K<1 ? 1 : n-K;	// 처음
						int fr = n+K>N ? N : n+K;
						int sc = m-K<1 ? 1 : m-K;
						int fc = m+K>M ? M : m+K;	// 끝
						
						int cnt = count[fr][fc] - count[fr][sc-1] - count[sr-1][fc] + count[sr-1][sc-1];
						
						if(map[n][m]==1)
							cnt--;
						
						if (map[n][m]==1 && cnt >= a && cnt <= b)
							 map[n][m] = 1;
						else if (map[n][m]==1 && cnt < a)
							map[n][m] = 0;
						else if (map[n][m]==1 && cnt > b)
							map[n][m] = 0;
						else if (map[n][m]==0 && cnt>a && cnt <=b)
							map[n][m] = 1;
					}
				}
		}		
		
		for(int n=1; n<=N; n++) {
			for(int m=1; m<=M; m++) {
				if(map[n][m]==1) {
					sb.append("*");
				}else
					sb.append(".");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}