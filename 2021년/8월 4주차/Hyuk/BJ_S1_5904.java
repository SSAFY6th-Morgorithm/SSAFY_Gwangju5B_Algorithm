package BJ_Practice.Silver;

import java.util.*;

public class BJ_S1_5904 {
	static int N, step;
	static String s = "moo";

	static String Moo(String str, int cnt) {
		if (cnt == step-1)
			return str;

		s += "o";
		str += s + str;

		return Moo(str, cnt + 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		step = 1;
		int before = 3;
		while (N > step+2) {
			int a = 3;
			while(a < N) {
				before = a;
				a = before * 2 + step+3;
				step++;
			}
			
			N -= before;
			if (N <= step + 2) {
				continue;
			} else {
				N -= step + 2;
				step = 1;
				before = 3;
			}
		}
		
//		System.out.println(N);
		if (N == 1)
			System.out.println("m");
		else
			System.out.println("o");
		
		
//		int a = 3;
//		while(a < N) {
//			before = a;
//			a = before * 2 + step+3;
//			step++;
//		}
//
//		System.out.println(Moo(s, 0));
	}
}
