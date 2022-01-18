package jan11_string;

import java.io.*;
import java.util.*;

public class BJ_G5_20437_문자열게임2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int arr[] = new int[26];
			String s = br.readLine();
			
			for (int i = 0; i < s.length(); i++) {
				arr[s.charAt(i)-'a']++;
			}
			
			int K = Integer.parseInt(br.readLine());
			
			int min = Integer.MAX_VALUE;
			int max = 0;
			
			for (int i = 0; i < s.length(); i++) {
				if(arr[s.charAt(i)-'a'] >= K) {
					int cnt = 0;
					for (int j = i; j < s.length(); j++) {
						if(s.charAt(i) == s.charAt(j)) cnt++;
						if(cnt == K) {
							min = Math.min(min, j-i+1);
							max = Math.max(max, j-i+1);
							break;
						}
					}
				}
			}
			
			if(min != Integer.MAX_VALUE) {
				sb.append(min+" ").append(max).append("\n");
			}else {
				sb.append("-1").append("\n");
			}
			
		}//tc
		
		System.out.println(sb);
		
	}

}
