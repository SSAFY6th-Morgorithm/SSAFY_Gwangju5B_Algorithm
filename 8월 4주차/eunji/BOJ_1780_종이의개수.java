package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author CHO
 * @see https://www.acmicpc.net/problem/1780
 * 분할정복, 재귀
 */

public class BOJ_1780_종이의개수 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	static int N;
	static int[][] map;
	static int result[];
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		result=new int[3]; // 결과값 저장할, index 0일때 -1, 1일때 0, 2일때 1 개수
		
		divide(0,0,N);
		
		for (int i = 0; i < 3; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb);

	}
	private static void divide(int x, int y,int n) {
		// 어떤 숫자가 있는지 횟수 세기 -1->index 0 / 0-> index 1 / 1-> index2
		int[] num=new int[3]; 
		
		for (int i = x; i < x+n; i++) {
			for (int j = y; j <y+n; j++) {
				num[map[i][j]+1]+=1;
			}
		}
		
		if(num[0]==n*n) result[0]+=1; // -1
		else if(num[1]==n*n) result[1]+=1; // 0
		else if(num[2]==n*n) result[2]+=1; // 1
		else {
			int len=n/3;
			divide(x,y,len);
			divide(x,y+len,len);
			divide(x,y+(len*2),len);
			divide(x+len,y,len);
			divide(x+len,y+len,len);
			divide(x+len,y+(len*2),len);
			divide(x+(len*2),y,len);
			divide(x+(len*2),y+len,len);
			divide(x+(len*2),y+(len*2),len);
		}
		
	}

}
