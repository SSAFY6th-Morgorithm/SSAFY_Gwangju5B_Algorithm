package algo;

import java.io.*;
import java.util.*;

public class bj_11404 {

	static int N;	// 도시
	static int M;	// 버스
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 플로이드
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		int[][] result = new int[N][N];
		for(int r=0; r<N; r++) {
			Arrays.fill(result[r], 10000001);
			result[r][r] = 0;	// 자기자신으로 가는 비용 = 0
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;	// 시작
			int b = Integer.parseInt(st.nextToken())-1;	// 도착
			int c = Integer.parseInt(st.nextToken());	// 비용
			
			result[a][b] = Math.min(result[a][b], c);	// 중복된 버스 노선에서 비용이 작은값으로 저장
		}
		
		for(int n=0; n<N; n++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					result[r][c] = Math.min(result[r][c], result[r][n] + result[n][c]);
				}
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(result[r][c] >= 10000001) {
					sb.append("0 ");
				}else {
					sb.append(result[r][c]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}
