package Clear;

import java.io.*;
import java.util.*;

public class BJ_11004 {
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int start, int end) {
		int part2 = partition(arr, start, end);
		if (start < part2 - 1) {
			quickSort(arr, start, part2 - 1);
		}
		if (part2 < end) {
			quickSort(arr, part2, end);
		}
	}

	public static int partition(int[] arr, int start, int end) {
		int pivot = arr[(start + end) / 2];
		int temp;
		while (start <= end) {
			while (arr[start] < pivot)
				start++;
			while (arr[end] > pivot)
				end--;
			if (start <= end) {
				temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				start++;
				end--;
			}

		}
		return start;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			int[] A = new int[N];
			for(int i=0; i<N; i++)
				A[i] = Integer.parseInt(st.nextToken());
			quickSort(A);
			bw.write(Integer.valueOf(A[K-1])+" ");
			bw.flush();
			bw.close();
			br.close();
		
		} catch (Exception e) {
		}
	}
}
