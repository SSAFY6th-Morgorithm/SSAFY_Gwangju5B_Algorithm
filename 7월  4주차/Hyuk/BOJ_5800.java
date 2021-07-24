package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_5800 {
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
			int T = Integer.parseInt(br.readLine());
			for (int i = 0; i < T; i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st1.nextToken());
				int[] score = new int[n];

				for (int j = 0; j < n; j++)
					score[j] = Integer.parseInt(st1.nextToken());
				quickSort(score);
			int gap_MAX = Integer.MIN_VALUE;
				for (int j = 1; j < n; j++) {
					int gap = score[j] - score[j - 1];
					gap_MAX = Math.max(gap_MAX, gap);
				}
				System.out.println("Class " + (i + 1));
				System.out.println("Max " + score[n - 1] + ", Min " + score[0] + ", Largest gap "+ gap_MAX);
			}

		} catch (Exception e) {
		}
	}
}
