package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_G5_6198_옥상정원꾸미기_스택 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<>();
		long res = 0;
		for(int n=0;n<N;n++) {
			int tmp = Integer.parseInt(br.readLine());
			while(!st.isEmpty() && st.peek()<=tmp) {
				st.pop();
			}
			res += st.size();
			st.push(tmp);
		}
		System.out.println(res);

	}

}
