package m11w3;

import java.io.*;
import java.util.*;

//시간제한
public class BOJ_G5_두용액 {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int front_idx = 0;
		int back_idx= arr.length-1;
		int gap=Integer.MAX_VALUE;
		int answer1=0,answer2=0;
		
		while(front_idx < back_idx) {
			int temp = arr[back_idx]+arr[front_idx];
			int toGoal = Math.abs(temp); 
			if(gap>toGoal) {
				answer1=arr[front_idx];
				answer2=arr[back_idx];
				gap=toGoal;
			}
			/*else {
				break;
			}*/
			if(temp>0) {
				back_idx--;
			}else {
				front_idx++;
			}
		}
		
		System.out.println(answer1+" "+answer2);
		
		
	}
}
