package simulation;

import java.io.*;
import java.util.*;

public class BJ_G5_14891_톱니바퀴 {

	static int[][] wheel = new int[4][8];
	static int turn[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = s.charAt(j) - '0'; // N극0 S극1
			}
		}


		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			turn = new int[4]; // 회전방향
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken())-1; // 선택된 톱니바퀴(0부터로 바꾸기)
			int dir = Integer.parseInt(st.nextToken()); // 1시계 -1반시계
			
			checkWheels(N,dir);
			
			// 바퀴 돌리기
			for (int d = 0; d < 4; d++) {
				if(turn[d] != 0) {
					int[] changedWheel = new int[8];
					
					int idx;
					for (int j = 0; j < 8; j++) {
						idx = j+turn[d];
						if(idx<0) idx = 7;
						if(idx>7) idx = 0;

						changedWheel[idx] = wheel[d][j];
					}
					wheel[d] = changedWheel;
				}
			}
			
		}
		
		int sum=0;
		for (int i = 0; i < 4; i++) {
			int grade = wheel[i][0];
			
			if(grade == 1) {
				if(i==0) sum+=1;
				else if(i==1) sum+=2;
				else if(i==2) sum+=4;
				else sum+=8;
			}
		}
		
		System.out.println(sum);

	}
	
	private static void checkWheels(int N, int dir) {
		turn[N] = dir;
		
		int prevW = N-1;
		int nextW = N+1;
		
		if(prevW >= 0 && turn[prevW] == 0) {
			if(wheel[prevW][2] != wheel[N][6]) {
				checkWheels(prevW,dir*(-1)); // 앞 바퀴 반대
			}
		}
		
		if(nextW < 4 && turn[nextW] == 0) {
			if(wheel[N][2] != wheel[nextW][6]) {
				checkWheels(nextW,dir*(-1)); // 뒷 바퀴 반대
			}
		}
	}
	
}
