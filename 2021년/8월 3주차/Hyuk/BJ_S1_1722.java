package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S1_1722 {
	static int N;
	static int[] input;
//	static int[] numbers;

	static List<Integer> list = new LinkedList<>();
	static StringTokenizer st;

	static int[] extraction(int N, long num, int[] arr, int index) {
		if (N == 0) {
			return arr;
		}
		long factorial = 1;
		for (int s = 1; s < N; s++) {
			factorial *= s;
		}
		long value = num / factorial;

		arr[index] = list.get((int) value);
		list.remove((int) value);
		extraction(N - 1, num - (value * factorial), arr, index + 1);
		return arr;
	}

	static long backpro(int N, int index, long cnt) {

		if (N == 0)
			return cnt;
		long factorial = 1;
		for (int s = 1; s < N; s++) {
			factorial *= s;
		}
		int i = list.indexOf(input[index]);
		cnt += factorial * i;
		list.remove(i);
		return backpro(N - 1, index + 1, cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		long a = 0;
		if (M == 1) {
			a = Long.parseLong(st.nextToken());
		} else {
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		switch (M) {
		case 1:
			int[] arr = new int[N];
			arr = extraction(N, a - 1, arr, 0);
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			System.out.println(sb);
			break;
		case 2:
			sb.append(backpro(N, 0, 0) + 1);
			System.out.println(sb);
			break;
		}
	}
}

//	static boolean nextPermutation(int[] numbers) {
//		int n = numbers.length;
//
//		int i = n - 1;
//		while (i > 0 && numbers[i - 1] >= numbers[i])
//			--i;
//		if (i == 0)
//			return false;
//
//		int j = n - 1;
//		while (numbers[i - 1] >= numbers[j])
//			--j;
//		swap(i-1,j,numbers);
//		
//		int k = n-1;
//		while(k>i)
//			swap(i++,k--,numbers);
//		
//		return true;
//	}
//
//	static void swap(int i, int j, int[] numbers) {
//		int temp = numbers[i];
//		numbers[i] = numbers[j];
//		numbers[j] = temp;
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		N = Integer.parseInt(br.readLine());
//		st = new StringTokenizer(br.readLine());
//		
//		numbers = new int[N];
//
//		int M = Integer.parseInt(st.nextToken());
//		if( M == 1) {
//			input = new int[1];
//			input[0] = Integer.parseInt(st.nextToken());
//		}
//		else {
//			input = new int[N];
//			for (int i = 0; i < N ; i++) {
//				input[i] = Integer.parseInt(st.nextToken());
//			}
//		}
//		for (int i = 1; i <= N ; i++) {
//			numbers[i-1] = i;
//		}
//		
//		int cnt=0;
//		Arrays.sort(numbers);
//		do {
//			if(M == 1) {
//				cnt++;
//				if(input[0] == cnt) {
//					System.out.println(Arrays.toString(numbers));
//					break;
//				}
//			}
//			else {
//				cnt++;
//				if(Arrays.equals(input, numbers)){
//					System.out.println(cnt);
//				}
//			}			
//		}while(nextPermutation(numbers));
//	}
//}
