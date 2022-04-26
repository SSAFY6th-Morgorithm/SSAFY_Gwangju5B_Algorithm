package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj_1756 {
	
	static int N;
	static int D;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 피자 굽기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		Stack<Integer> oven = new Stack<>();
		st = new StringTokenizer(br.readLine());
		
		int before = Integer.parseInt(st.nextToken());
		oven.add(before);
		for(int n=1; n<N; n++) {
			int current = Integer.parseInt(st.nextToken());
			if(before>current) {
				oven.add(current);
				before = current;
			} else {
				oven.add(before);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int result = 0;
		for(int d=0; d<D; d++) {
			int size = Integer.parseInt(st.nextToken());
			
			while(!oven.isEmpty()) {
				int ovenpop = oven.pop();
				cnt++;
				
				if(ovenpop>=size) {
					result++;
					break;
				}
			}
		}
		
		if(result==D)
			System.out.print(N-cnt+1);
		else
			System.out.print(0);
	}

}
