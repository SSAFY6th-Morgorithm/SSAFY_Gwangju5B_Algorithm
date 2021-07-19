package com.ssafy.ws01.step3;

public class DigitTest1 {
	public static void main(String[] args) {
		int num=0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<i; j++) {
				System.out.print("\t");
			}
			for(int k=0; k<5-i;k++) {
				System.out.print( ++num + "\t");
			}			
			System.out.println();
		}
	}
}
