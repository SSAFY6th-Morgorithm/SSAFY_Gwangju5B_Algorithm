package day0817;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_다리놓기 {
	static int N,R;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			N= Integer.parseInt(st.nextToken());
			
			System.out.printf("%.0f\n",com(N,R));
		
		}
	}

	private static double com(int n, int r) {
		double sum_top=1;
		double sum_bottom=1;
		
		for(double a=n;a>n-r;a--) {
			sum_top*=a;
		}
		for(double b=r;b>=1;b--) {
			sum_bottom*=b;
		}
		return sum_top/sum_bottom;
	}
}
