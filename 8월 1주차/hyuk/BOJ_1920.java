package BJ_Practice.Silver;

import java.util.*;

public class BJ_S4_1920 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Set<Long> set1 = new HashSet<>();
		for(int i = 0; i< n ; i++) {
			set1.add(sc.nextLong());
		}
		n = sc.nextInt();

		for(int i =0 ; i<n; i++) {
			if(set1.contains(sc.nextLong())) System.out.println(1);
			else System.out.println(0);
		}
	}
}
