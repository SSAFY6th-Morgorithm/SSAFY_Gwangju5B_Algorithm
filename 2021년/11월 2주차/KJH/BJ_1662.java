package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1662 {
	static String[] S;
	static char[] sArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> s = new Stack<>();
		S = br.readLine().split("");
		for(int i=0;i<S.length;i++) {
			if(S[i].equals(")")) {
				int cnt = 0;
				while(!s.peek().equals("(")) {
					if(s.peek().equals("*")) {
						s.pop();
						cnt += Integer.parseInt(s.pop());
					}
					else {
						s.pop();
						cnt++;
					}
				}		
				s.pop();
				cnt *= Integer.parseInt(s.pop());
				s.push(String.valueOf(cnt));
				s.push("*");
			}
			else 
				s.push(S[i]);
		}
		StringBuilder sb = new StringBuilder();
		int res = 0;
		while(!s.isEmpty()) {
			if(s.peek().equals("*")) {
				s.pop();
				res+=Integer.parseInt(s.pop());
			}
			else {
				s.pop();
				res++;
			}
		}
		sb.append(res);
		System.out.println(sb);
	}

}
