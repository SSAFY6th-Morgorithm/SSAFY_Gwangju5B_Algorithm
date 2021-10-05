package week_10_1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간제한: 초
public class BOJ_B1_2차원배열의합 {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken())+1;
		int C = Integer.parseInt(st.nextToken())+1;
		int[][] map =new int[R][C];
		
		for(int r=1;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int k=1;k<=K;k++) {
			st = new StringTokenizer(br.readLine());
			int i= Integer.parseInt(st.nextToken());
			int j= Integer.parseInt(st.nextToken());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			int sum=0;
			for(int r=i;r<=x;r++) {
				for(int c=j;c<=y;c++) {
					sum+=map[r][c];
				}
			}
			System.out.println(sum);
		}
	}
}
