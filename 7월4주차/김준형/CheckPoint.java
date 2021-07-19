package com.java.first;

import java.util.Scanner;

public class CheckPoint {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int height = scan.nextInt();
		int weight = scan.nextInt();
		int BMI = weight + 100 - height;
		System.out.printf("비만수치는 %d입니다.\n",BMI);
		if(BMI>0)
			System.out.println("당신은 비만이군요?");
	}
}