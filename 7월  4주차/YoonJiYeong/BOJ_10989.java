package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_S5_10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[] arr = new int[10001];
		int N = Integer.parseInt(br.readLine());
				
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			arr[val]++;
		}
		
		for (int i = 0; i < arr.length; i++) {
			while(arr[i] != 0) {
				sb.append(i).append('\n');
				arr[i]--;
			}
		}
		System.out.println(sb);
		br.close();
	}

}
