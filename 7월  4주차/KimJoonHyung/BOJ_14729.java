package com.java;

import java.util.Scanner;

public class BOJ_14729 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double[] score = new double[n];

		for(int i=0;i<n;i++) {
			score[i] = sc.nextDouble();		
		}
		quickSort(score);
	}
	
	public static void quickSort(double[] a) {
		pivot(a,0,a.length-1);
		
		for(int i=0;i<7;i++) {
			System.out.println(a[i]);
		}
	}
	
	
	public static void pivot(double[] a,int l,int h) {
		if(l>=h)
			return;
		
		int pivot = partition(a,l,h);
		
		pivot(a,l,pivot-1);
		pivot(a,pivot+1,h);
	}
	
	public static int partition(double[] a,int left,int right) {
		int l = left;
		int h = right;
		double pivot = a[left];
		
		while(l<h) {
			while(a[h] > pivot && l<h) {
				h--;
			}
			while(a[l] <= pivot && l<h) {
				l++;
			}
			Swap(a,l,h);
		}
		Swap(a,left,l);
		
		return l;
	}
	
	public static void Swap(double[] a,int i,int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
