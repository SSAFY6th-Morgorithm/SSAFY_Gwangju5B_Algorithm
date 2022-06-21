package m11w3;

import java.io.*;
import java.util.*;


//시간제한
public class BOJ_G5_LCS {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for(int r=1;r<dp.length;r++) {
			for (int c = 1; c < dp[r].length; c++) {
				if(s1.charAt(r-1)==s2.charAt(c-1)) {
					dp[r][c]=dp[r-1][c-1]+1;
				}else {
					dp[r][c]=Math.max(dp[r-1][c], dp[r][c-1]);
				}
			}
		}
		System.out.println(dp[s1.length()][s2.length()]);
		
		
	}
}
