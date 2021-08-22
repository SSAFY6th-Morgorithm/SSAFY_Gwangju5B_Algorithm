package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 제한 :2초
public class BOJ_S2_종이의개수 {
	private static int[][] map;
	static int[] answer = new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map= new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		check(0,0,N);
		for(int data:answer) {
			System.out.println(data);
		}
	}

	private static void check(int r, int c, int len) {
		int temp=map[r][c];
		boolean flag=false;
		label: for(int i=r;i<r+len;i++) {
			for(int j=c;j<c+len;j++) {
				if(temp!=map[i][j]) {
					flag = true;
					break label;
				}
			}
		}
		if(!flag) {
			if(temp==-1) answer[0]++;
			else if(temp==0) answer[1]++;
			else answer[2]++;
		}
		else {
			len/=3;
			check(r,c,len);
			check(r,c+len,len);
			check(r,c+len*2,len);
			check(r+len,c,len);
			check(r+len,c+len,len);
			check(r+len,c+len*2,len);
			check(r+len*2,c,len);
			check(r+len*2,c+len,len);
			check(r+len*2,c+len*2,len);
		}
	}
}
