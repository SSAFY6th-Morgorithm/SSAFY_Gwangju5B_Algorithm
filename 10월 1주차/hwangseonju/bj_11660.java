package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11660 {
	
	static int N;	// 배열의 크기
	static int M;	// 횟수
	static int[][] num;	// 숫자 저장
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// 구간 합 구하기5(누적합 사용)
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N][N+1];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=N; c++) {
				num[r][c] = Integer.parseInt(st.nextToken());
			}
			
			for(int c=1; c<=N; c++) {	// 누적합 배열 만들기
				num[r][c] = num[r][c-1] + num[r][c];
			}
		}
		
		/*for(int r=0; r<N; r++) {
			for(int c=0; c<=N; c++) {
				System.out.print(num[r][c]+" ");
			}
			System.out.println();
		}*/
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for(int r=x1; r<=x2; r++) {		// 각 행을 [y1, y2]로 구간을 지정 -> ps[y2] - ps[y1-1]로 구간합 구하기 
				sum += num[r][y2] - num[r][y1-1];
			}
			
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
	}
}
