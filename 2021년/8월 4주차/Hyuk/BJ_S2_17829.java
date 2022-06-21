package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S2_17829 {
	static int N;
	static int[][] data;
	
	static int[][] pooling(int[][] data) {
		if(data.length == 1) return data;
		
		int[][] resol = new int[data.length/2][data.length/2];
		int[] a = new int[4];
		int i=0,j=0;
		for (int r = 0; r < resol.length ; r++) {
			for (int c = 0; c < resol.length ; c++) {
				i = r*2;
				j = c*2;
				a = new int[]{data[i][j],data[i+1][j],data[i][j+1],data[i+1][j+1]};
				Arrays.sort(a);
				resol[r][c] = a[2];				
			}
		}
		
		return pooling(resol);
	}
	
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		N = Integer.parseInt(br.readLine());
		data = new int[N][N];
		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N ; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] a = pooling(data);
		System.out.println(a[0][0]);
	}
}
