package M12W3;

import java.io.*;
import java.util.*;

public class BOJ_G4_주사위굴리기 {
	private static int[] dice;
	private static int[][] map;
	static int[][] vector = {{0,1},{0,-1},{-1,0},{1,0}};
	private static int R;
	private static int C;
	private static int startR;
	private static int startC;
	private static StringBuilder sb;  
	public static void main(String[] args) throws IOException {
		//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dice = new int[7];
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		int cmd = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int r=0;r<R;r++) {
			st= new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st= new StringTokenizer(br.readLine());
		for(int i=0;i<cmd;i++) {
			int d = Integer.parseInt(st.nextToken());
			startR = startR+vector[d-1][0];
			startC = startC+vector[d-1][1];
			if(startR<0||startR>=R||startC<0||startC>=C) {
				startR = startR-vector[d-1][0];
				startC = startC-vector[d-1][1];
				continue;
			}
			goDice(d);
			if(map[startR][startC]==0) map[startR][startC] = dice[6];
			else {
				dice[6] = map[startR][startC];
				map[startR][startC] = 0;
			}
			
		}
		System.out.println(sb);
	}

	private static void goDice(int d) {
		int[] temp = dice.clone();
		//동쪽
		switch(d) {
			case 1:
				dice[1] = temp[4];
				dice[4] = temp[6];
				dice[3] = temp[1];
				dice[6] = temp[3];
				break;
			case 2:
				dice[1] = temp[3];
				dice[4] = temp[1];
				dice[3] = temp[6];
				dice[6] = temp[4];
				break;
			case 3:
				dice[1] = temp[2];
				dice[2] = temp[6];
				dice[5] = temp[1];
				dice[6] = temp[5];
				break;
			case 4:
				dice[1] = temp[5];
				dice[2] = temp[1];
				dice[5] = temp[6];
				dice[6] = temp[2];
				break;
		}
	
		sb.append(dice[1]+"\n");  //굴리고 나서 주사위 윗면 출력
	}
}