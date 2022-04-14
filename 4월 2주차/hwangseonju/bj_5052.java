package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_5052 {
	
	static int T;	// 테케
	static int N;	// 전화번호 수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 전화번호 목록
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			String[] phones = new String[N];
			boolean flag = false;
			for(int n=0; n<N; n++) {
				phones[n] = br.readLine();
			}
			
			Arrays.sort(phones);
			
			for(int n=1; n<N; n++) {
				if(phones[n].startsWith(phones[n-1])) {
					flag = true;
					sb.append("NO");
					break;
				}
			}
			
			if(!flag) {
				sb.append("YES");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
