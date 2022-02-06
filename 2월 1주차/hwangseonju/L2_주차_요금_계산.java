import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class L2_주차_요금_계산 {

	public static void main(String[] args) {
		// 주차 요금 계산
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		StringTokenizer st = null;
        
        HashMap<Integer,HashMap<Integer, String>> cars = new LinkedHashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();	//	자동차 번호 파악(오름차순)
        
        for(int r=0; r<records.length; r++){
            st = new StringTokenizer(records[r]);
            String time = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if(!cars.containsKey(num)){
                cars.put(num, new LinkedHashMap<>());
                pq.offer(num);
            }
            
            cars.get(num).put(cars.get(num).size()+1, time);
        }
        
        int[] answer = new int[pq.size()];
        int cnt = 0;
        while(!pq.isEmpty()){
            int num = pq.poll();
            if(cars.get(num).size()%2!=0){	// 입차 내역만 있는 경우
                cars.get(num).put(cars.get(num).size()+1, "23:59");
            }
            
            int total = 0;
            for(int i=cars.get(num).size(); i>0; i--){
                st = new StringTokenizer(cars.get(num).get(i), ":");
                int hhout = Integer.parseInt(st.nextToken());	// 출차 시
                int mmout = Integer.parseInt(st.nextToken());	// 출차 분
                
                st = new StringTokenizer(cars.get(num).get(--i), ":");
                int hhin = Integer.parseInt(st.nextToken());	// 입차 시
                int mmin = Integer.parseInt(st.nextToken());	// 입차 분
                
                if(mmout - mmin<0){
                    total += ((mmout+60) - mmin);
                    hhout--;
                }else {
                	total += (mmout - mmin);
                }
                total += (hhout - hhin)*60;
            }
            answer[cnt] = (total>fees[0])? (int) (fees[1] + Math.ceil((double) (total-fees[0])/fees[2])*fees[3]):fees[1];    
            cnt++;
        }
        
        for(int a=0; a<answer.length; a++) {
        	System.out.print(answer[a]+" ");
        }
	}
}
