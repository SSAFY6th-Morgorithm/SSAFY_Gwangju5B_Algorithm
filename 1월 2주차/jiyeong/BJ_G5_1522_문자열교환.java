package jan11_string;

import java.io.*;
import java.util.*;

public class BJ_G5_1522_문자열교환 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String s = br.readLine();
		int acnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='a') acnt++;
		}
		
		int ans = s.length();
		int bcnt;
		
		for (int i = 0; i < s.length(); i++) {
			bcnt = 0;
			for (int j = i; j < acnt+i; j++) {
				int idx = j % s.length();
				if(s.charAt(idx) == 'b') {
					bcnt++;
				}
			}
			ans = ans < bcnt? ans:bcnt;
		}

		System.out.println(ans);
	}

}
