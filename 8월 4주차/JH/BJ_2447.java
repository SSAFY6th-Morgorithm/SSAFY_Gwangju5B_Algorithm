package BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_2447 {
	static int N;
	static String[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = " ";
			}
		}
		
		solve(N,0,0);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	static void solve(int n, int y, int x) {
		if(n==1) {
			map[y][x] = "*";
			return;
		}
		else {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(!(i==1&&j==1)) {
						solve(n/3, y+(n/3*i), x+(n/3*j));
					}
				}
			}
		}
	}
}
