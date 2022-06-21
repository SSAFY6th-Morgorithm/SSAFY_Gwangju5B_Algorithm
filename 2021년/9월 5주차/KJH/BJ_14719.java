package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14719 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H,W;
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		for(int i=0;i<W;i++) {
			int leftMax=arr[i];
			for(int left=i;left>=0;left--) {
				leftMax = Math.max(arr[left], leftMax);
			}
			int rightMax = arr[i];
			for(int right=i;right<W;right++) {
				rightMax = Math.max(arr[right], rightMax);
			}
			res += Math.min(leftMax, rightMax)-arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(res);
		System.out.println(sb);
	}

}
