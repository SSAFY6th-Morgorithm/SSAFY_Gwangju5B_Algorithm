package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_별찍기10 {
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
//		for(int r=0;r<N; r++) {
//			for(int c=0;c<N;c++) {
//				map[r][c]=' ';
//			}
//		}
		makestar(0,0,N);
		for(int r=0;r<N; r++) {
			for(int c=0;c<N;c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void makestar(int r, int c,int len) {
		int l = len/3;
		
		
//		if(len==1){
//			map[r][c]='*';
//			return;
//		}                                       // 492ms
		
		
		//348ms
		if(len==3){
			for(int i=r;i<r+len;i++) {
				for(int j=c;j<c+len;j++) {
					if(i==r+1 && j ==c+1) map[i][j]=' ';
					else map[i][j]='*';
				}
			}
			return;
		}
		
		
		makestar(r,c,l);
		makestar(r,c+l,l);
		makestar(r,c+l*2,l);
		makestar(r+l,c,l);
		makespace(r+l,c+l,l);
		makestar(r+l,c+l*2,l);
		makestar(r+l*2,c,l);
		makestar(r+l*2,c+l,l);
		makestar(r+l*2,c+l*2,l);
		
		}

	private static void makespace(int r, int c, int len) {
		for(int i=r;i<r+len;i++) {
			for(int j=c;j<c+len;j++) {
				map[i][j]=' ';
			}
		}
	}
	
}
