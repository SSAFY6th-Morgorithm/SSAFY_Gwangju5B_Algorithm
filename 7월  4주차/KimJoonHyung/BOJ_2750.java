package com.java;

import java.util.Scanner;

public class BOJ_2750 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        sort(arr);
        
        for(int data:arr){
            System.out.println(data);
        }
	}
	
	public static void sort(int a[]) {
		for(int i = 0; i < a.length - 1; i++) {
			int min = i;	
			
			for(int j = i + 1; j < a.length; j++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			
			swap(a, min, i);
		}
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
