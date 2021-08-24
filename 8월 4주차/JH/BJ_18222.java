package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_18222 {
	static String X;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		X = "0";
		
		solve();
	}
	
	static void solve() {
		int len = X.length();
		if(len>=n) {
			StringBuilder sb = new StringBuilder();
			sb.append(X.charAt(n-1));
			System.out.println(sb);
			return;
		}
		for(int i=0;i<len;i++) {
			if(X.charAt(i)=='0')
				X += "1";
			else
				X += "0";
		}
		solve();
	}
}
