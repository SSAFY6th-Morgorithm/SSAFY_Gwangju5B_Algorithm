package day0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BOJ_S3_15649 {
	static int[] num_arr;
	static boolean[] visited;
	static int N,R;
	static ArrayList<Integer> modify_list;
	static int init_point, cmd_num;
	static StringBuilder sb; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		R = Integer.parseInt(st.nextToken());
		num_arr = new int[R];
		
		permutation(0);
		System.out.println(sb);
	}
	private static void permutation(int count) {

		if(count==R) {
			for(int data:num_arr) {
				sb.append(data+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]= true;
				num_arr[count] = i+1;
				permutation(count+1);
				visited[i]=false;
			}
		}
		
	}
}
