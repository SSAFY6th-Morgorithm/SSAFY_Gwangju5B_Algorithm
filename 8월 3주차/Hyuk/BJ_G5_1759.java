package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G5_1759 {
	static int L, C;
	static char[] data;
	static boolean[] selected;
	static String ahdma2 = "aeiou";
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		selected = new boolean[C];
		data = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			data[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(data);
		combination(0, 0);
	}

	
	static void combination(int cnt, int start) {
		if (cnt == L) {
			String s = "";
			int cnt2 = 0;
			for (int i = 0; i < C; i++) {
				if (selected[i] == true) {
					s = s.concat(String.valueOf(data[i]));
					if (ahdma2.contains(String.valueOf(data[i])))
						cnt2++;				
				}
			}
			if (cnt2 == 0 || cnt2 >= L - 1)
				return;
			
			System.out.println(s);
		}
		
		for (int i = start; i < C; i++) {
			selected[i] = true;
			combination(cnt + 1, i + 1);
			selected[i] = false;
		}

	}
}
