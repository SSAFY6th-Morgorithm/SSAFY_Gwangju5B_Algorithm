package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_16922 {
	static int N, res;
	static int[] num;
	static boolean[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		br.close();
		num = new int[] {1,5,10,50};
		check = new boolean[1001];
		res = 0;
		solve(N,0,0);
		sb.append(res);
		System.out.println(sb);
	}
	
	static void solve(int cnt, int idx, int s) {
		if(cnt==0) {
			if(!check[s]) {
				check[s] = true;
				res++;
			}
			return;
		}
		
		for(int i=idx;i<num.length;i++) {
			solve(cnt-1,i,s+num[i]);
		}
	}
}
