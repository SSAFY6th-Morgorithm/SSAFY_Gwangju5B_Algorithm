package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919 {
	static String S,T;
	static StringBuffer tmp;
	static boolean isOk = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		solve(T);
		
		if(isOk)
			sb.append(1);
		else
			sb.append(0);
		System.out.println(sb);
	}
	
	static void solve(String t) {
		if(S.length()==t.length()) {
			if(S.equals(t))
				isOk = true;
			return;
		}
		if(t.charAt(t.length()-1)=='A') {
			String n = t;
			n = n.substring(0,n.length()-1);
			solve(n);
		}
		if(t.charAt(0)=='B') {
			String n = t;
			n = n.substring(1);
			tmp = new StringBuffer(n);
			n = tmp.reverse().toString();	
			solve(n);
		}
	}
}
