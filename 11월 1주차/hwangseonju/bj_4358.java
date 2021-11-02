package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class bj_4358 {
	
	static HashMap<String, Double> trees;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 생태학
		trees = new HashMap<String, Double>();
		
		int total = 0;	// 전체 나무 수
		while(true) {
			String tree = br.readLine();
			if(tree==null || tree.length()==0) break;	// 입력값 없으면 멈추기
			total++;
			
			if(trees.get(tree)==null) {		// 나무가 존재하지 않으면 새로 추가
				trees.put(tree, 1.0);
			}else {							// 나무가 존재하면 +1
				trees.put(tree, trees.get(tree)+1.0);
			}
		}
		
		Object[] sort = trees.keySet().toArray();	// 키(나무이름)를 기준으로 오름차순 정렬
		Arrays.sort(sort);
		
		for(int j=0; j<sort.length; j++) {
			double result = (trees.get(sort[j])/total)*100;	// 백분율 계산
			sb.append(sort[j]).append(" ").append(String.format("%.4f", result)).append("\n");
		}

		System.out.print(sb);
	}
}