package day0820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_행렬제곱 {
	static int[][] map, answer;
	private static int N;
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		map = new int[N][N];
		answer = new int[N][N];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
//				answer[r][c] = map[r][c];
			}
		}
		
		
		answer = zecob(B);
		 for (int[] arr : answer) {
            for (int data : arr) {
            	sb.append(data+" ");
            }
            sb.append("\n");
        }
		System.out.println(sb);
	}
	private static int[][] zecob(long b) {
		
		if(b==1) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j]%=1000;
				}
			}
			return map;
		}
		int[][] temp = zecob(b/2);
		
		if(b%2==0) return multiple(temp,temp);
		else return multiple(multiple(temp,temp),map);
	}
	private static int[][] multiple(int[][] temp, int[][] temp2) {
		int[][] temp3 = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					temp3[i][j]+= temp[i][k]*temp2[k][j];
				}
				temp3[i][j]%=1000;
			}
		}
		return temp3;
	}
}
