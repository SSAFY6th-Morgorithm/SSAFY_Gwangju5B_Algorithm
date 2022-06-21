package week20.day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1022 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int r1, c1, r2, c2, MAX;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine()," ");
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
		map = new int[r2-r1+1][c2-c1+1];
		//r2-r1과 c2-c1의 최소가 0,0일 때 1이 답이기 때문에 +1을 해준다.
		
		play();
		
		sb.append("%");
		sb.append(String.valueOf(MAX).length());
		sb.append("d ");
		//%nd 형태로 만들어서 공백을 자릿수에 맞춰 출력하고자 함
//		System.out.println(sb.toString());
		for(int i=0; i<= r2-r1; i++) {
			for(int j=0; j<= c2-c1; j++) {
				System.out.printf(sb.toString(),map[i][j]);
			}
			System.out.println();
		}
		
	}

	private static void play() {
		// r1, c1은 가장 왼쪽 위 칸이고, r2, c2는 가장 오른쪽 아래 칸이다
		// r-r1, c-c1은 0부터 시작
		for(int r=r1; r<= r2; r++) {
			for(int c=c1; c<= c2; c++) {
				map[r-r1][c-c1] = find(r,c);
				MAX = Math.max(MAX, map[r-r1][c-c1]);
			}
		}
	}

	private static int find(int r, int c) {
		//몇번째 껍질인지 찾아내야 함.
		
		//1^2+1 ~ 3^2 이면 (2*2 -3)^2+1 ~ (2*2-1)^2
		//이므로  2번째 껍질
		//첫번째 껍질은 1
		int round = Math.max(Math.abs(r), Math.abs(c));
		int max = (int) Math.pow(2*(round+1) -1, 2);
		//껍질의 최솟값을 구함
		if(c==round && r==round) {
			return max;
		}//(r,c)면 max값
		if(c==round) {
			return max - (round * 7) - r;
		}//c와 같으면 공식이 max값에서 껍질 층*7을 뺀 후 거기서 r뺀다.
		if((r*-1)==round) {
			return max - (round * 5) - c;
		}//r*-1과 같으면 공식이 max값에서 껍질 층 * 5를 뺀 후 거기서 c를 뺀다. 
		if((c*-1) == round) {
			return max - (round * 3) + r;
		}//c*-1과 같으면 공식이 max값에서 껍질 층 * 3을 뺀 후 거기서 r더한다.
//		if(r==round) {
//			return max - r + c;
//		}
		return max - r + c;
		//r과 같으면 공식이 max값에서
		
	}
}
