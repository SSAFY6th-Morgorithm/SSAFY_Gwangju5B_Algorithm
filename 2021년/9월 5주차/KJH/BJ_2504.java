package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		Stack<String> st = new Stack<>();
		boolean check = true;
		for(int i=0;i<input.length();i++) {
			String t = input.substring(i, i+1);
			if(t.equals("[")) {
				st.push("]");
				continue;
			}
			if(t.equals("(")) {
				st.push(")");
				continue;
			}
			
			int temp = 0;
			while(true) {
				if(st.isEmpty()) {
					check = false;
					break;
				}
				
				if(!st.peek().equals(")") && !st.peek().equals("]")) {
					temp += Integer.parseInt(st.pop());
				}
				else {
					if(st.peek().equals(t)) {
						st.pop();
						int n = t.equals(")")?2:3;
						if(temp==0) {
							st.push(String.valueOf(n));
						}
						else {
							st.push(String.valueOf(temp*n));
						}
						break;
					}
					else {
						check = false;
						break;
					}
				}
			}
			if(!check)
				break;
		}
		
		int res = 0;
		while(!st.isEmpty()) {
			if(!st.peek().equals(")") && !st.peek().equals("]"))
				res += Integer.parseInt(st.pop());
			else {
				check = false;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(check)
			sb.append(res);
		else
			sb.append(0);
		System.out.println(sb);

	}

}
