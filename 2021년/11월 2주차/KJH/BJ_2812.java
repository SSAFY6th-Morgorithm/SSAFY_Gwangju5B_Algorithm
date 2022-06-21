package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2812 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> s = new Stack<>();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String[] str = br.readLine().split("");
		int[] arr = new int[N];
		for(int i=0;i<str.length;i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		for(int i=0;i<N;i++) {
			while(K>0 && !s.isEmpty() && s.peek()<arr[i]) {
				s.pop();
				K--;
			}
			s.push(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		int tmp = s.size();
		for(int i=0;i<tmp-K;i++) {
			sb.append(s.elementAt(i));
		}
		System.out.println(sb);
	}

}
