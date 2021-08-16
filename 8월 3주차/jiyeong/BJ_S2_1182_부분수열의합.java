package CombAndPerm;

import java.io.*;
import java.util.*;

/**
 * 
 *  == 입력 ==
 *  5 0
 *  -7 -3 -2 5 8
 *  
 *  == 출력 ==
 *  1
 *  
 *  */

public class BJ_S2_1182_부분수열의합 {

	static int N, S, cnt = 0;
	static int[] arr;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		isSelected = new boolean[N];
		powerSet(N, isSelected, 0);
		
		if(S == 0) // 합이 0일때 공집합은 제거
			cnt--;
		
		System.out.println(cnt);

	}

	private static void powerSet(int toChoose, boolean[] isSelected, int sum) {
		
		if (toChoose == 0) {
			for (int i = 0; i < isSelected.length; i++) {
				if (isSelected[i]) {
					sum += arr[i];
					//System.out.print(arr[i] + " "); // 부분집합 확인
				}
			}
			//System.out.println();
			//System.out.println("합: "+sum);
			if(sum == S) {
				cnt++;
			}
			return;
		}

		isSelected[isSelected.length - toChoose] = true;
		powerSet(toChoose - 1, isSelected, sum);
		isSelected[isSelected.length - toChoose] = false;
		powerSet(toChoose - 1, isSelected, sum);
	}


}
