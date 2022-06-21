package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_10989 {

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int start, int end) {
		int part = partition(arr, start, end);
		if (start < part - 1) {
			quickSort(arr, start, part - 1);
		}
		if (part < end) {
			quickSort(arr, part, end);
		}
	}

	public static int partition(int[] arr, int start, int end) {
		int pivot = arr[(start + end) / 2];
		int temp;
		while (start <= end) {
			while (arr[start] < pivot)
				start++;
			while (pivot < arr[end])
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
		try {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[N + 1];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			arr[N] = 1001;
			quickSort(arr);
			for (int i = 1; i < arr.length; i++) {
				if (arr[i - 1] != arr[i])
					System.out.print(arr[i - 1] + " ");
			}
		} catch (Exception e) {
		}

	}
}
