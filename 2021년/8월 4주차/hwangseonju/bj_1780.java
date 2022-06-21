package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1780 {

	static int N;	// 배열의 크기
	static int [][] paper;	// 색종이
	static int first =0;	// -1 색종이 개수
	static int second=0;	// 0 색종이 개수
	static int third=0;		// 1색종이 개수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 종이의 개수
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		div(0, 0, N);
		
		sb.append(first).append("\n").append(second).append("\n").append(third);
		System.out.print(sb);
	}
	
	static void div(int x, int y, int n) {	// 9등분해서 탐색하기
		boolean diff = false;		// 다른 색종이가 있는지에 대한 여부
		int temp = paper[x][y];		// 탐색 시작한 첫번째 색종이
		
		outer:for(int r=x; r<x+n; r++) {
			for(int c=y; c<y+n; c++) {
				if(paper[r][c]!=temp) {
					diff = true;
					break outer;	// 라벨 추가함 -> 시간이 조금 줄어듦.
				}
			}
		}
		
		// 색종이가 같은 경우
		if(diff==false) {
			if(temp == -1) {
				first++;
			}else if(temp == 0) {
				second++;
			}else {
				third++;
			}
			return;
		}
		
		n = n/3;
		
		// 9등분 구간
		// 1 2 3
		// 4 5 6
		// 7 8 9
		div(x, y, n);			// 1구간 탐색				
		div(x, y+n, n);			// 2구간 탐색
		div(x, y+(2*n), n);		// 3구간 탐색
		div(x+n, y, n);			// 4구간 탐색
		div(x+n, y+n, n);		// 5구간 탐색
		div(x+n, y+(2*n), n);	// 6구간 탐색
		div(x+(2*n), y, n);		// 7구간 탐색
		div(x+(2*n), y+n, n);	// 8구간 탐색
		div(x+(2*n), y+(2*n), n);	//9구간 탐색
	}
}
