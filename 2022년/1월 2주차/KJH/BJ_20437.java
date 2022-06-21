package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BJ_20437 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			String W = br.readLine();
			char[] c = W.toCharArray();
			int K = Integer.parseInt(br.readLine());
			int min = Integer.MAX_VALUE;
			int max = 0;
			int[] alpArr = new int[26];
			
			for(int j=0;j<c.length;j++)
				alpArr[c[j]-'a']++;
			for(int j=0;j<c.length;j++) {
				if(K==1) {
					min = 1;
					max = 1;
					break;
				}
				if(alpArr[c[j]-'a']>=K) {
					int cnt = 1;
					for(int n=j+1;n<c.length;n++) {
						if(c[n]==c[j])
							cnt++;
						if(cnt==K) {
							min = Math.min(min, n-j+1);
							max = Math.max(max, n-j+1);
							alpArr[c[j]-'a']--;
							break;
						}
					}
				}
			}
			
			if(min==Integer.MAX_VALUE && max==0)
				sb.append(-1).append("\n");
			else
				sb.append(min).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);

	}

}
