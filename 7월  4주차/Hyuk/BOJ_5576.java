package Clear;

import java.io.*;

public class BJ_5576 {
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
			int[] score = new int[20];
			for (int j = 0; j < 20; j++) {
				score[j] = Integer.parseInt(br.readLine());
			}
			quickSort(score, 0, 9);
			quickSort(score, 10, 19);
			System.out.print(score[9] + score[8] + score[7]+" ");
			System.out.print(score[19] + score[18] + score[17]);

		} catch (Exception e) {
		}
	}
}
