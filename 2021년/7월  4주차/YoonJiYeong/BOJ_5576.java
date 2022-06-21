package BJ.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 콘테스트*/

// 선택정렬
public class BJ_B2_5576 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
 
        for(int i=0; i<10; i++){
            arr1[i] = sc.nextInt();
        }
        
        for(int i=0; i<10; i++){
            arr2[i] = sc.nextInt();
        }
        
        int cnt=0;

		for (int i = 0; i < arr1.length - 1; i++) {
			if(cnt == 3) break;
			for (int j = i + 1; j < arr1.length; j++) {
				if (arr1[i] < arr1[j])
					swap(arr1,i,j);
			}
			cnt++;
		}
        
        cnt =0;
        for (int i = 0; i < arr2.length - 1; i++) {
			if(cnt == 3) break;
			for (int j = i + 1; j < arr2.length; j++) {
				if (arr2[i] < arr2[j])
					swap(arr2,i,j);
			}
			cnt++;
		}        
        
        System.out.printf("%d %d",arr1[0]+arr1[1]+arr1[2],arr2[0]+arr2[1]+arr2[2]);
 
        // Arrays 사용
        /*
        Arrays.sort(arr1);
        Arrays.sort(arr2);
 
        System.out.print(arr1[9]+arr1[8]+arr1[7]+" ");                                                   
        System.out.print(arr2[9]+arr2[8]+arr2[7]);*/

	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j]= temp;
	}

}
