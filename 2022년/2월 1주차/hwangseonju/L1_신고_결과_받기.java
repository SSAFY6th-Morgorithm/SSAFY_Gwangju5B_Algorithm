import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class L1_신고_결과_받기 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st =null;

	public static void main(String[] args) {
		// 신고 결과 받기
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k = 2;
		
		HashMap<String, HashMap<String, Integer>> cnt = new LinkedHashMap<>();
		
		for(int i=0; i<id_list.length; i++){
            cnt.put(id_list[i], new HashMap<>());
        }
		
		// 신고 횟수 count
        for(int r=0; r<report.length; r++){
            st = new StringTokenizer(report[r]);
            String user = st.nextToken();
            String caution = st.nextToken();
            
            cnt.get(caution).put(user, cnt.get(caution).size()+1);
        }
        
        int[] answer = new int[id_list.length];
        for(int i=0; i<id_list.length; i++){
            if(cnt.get(id_list[i]).size()>=k){
                for(int u=0; u<id_list.length; u++){
                    if(cnt.get(id_list[i]).containsKey(id_list[u])){
                        answer[u]++;
                    }
                }
            }
        }
        
        for(int a=0; a<answer.length; a++) {
        	System.out.print(answer[a]+" ");
        }
	}
}
