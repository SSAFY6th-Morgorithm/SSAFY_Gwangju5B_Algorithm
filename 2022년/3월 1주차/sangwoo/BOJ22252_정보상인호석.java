import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(input.readLine());
        Map<String, Queue<Integer>> map = new HashMap<>();
        StringTokenizer st;
        long cost = 0;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(input.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command == 1) {
                String key = st.nextToken();
                int size = Integer.parseInt(st.nextToken());
                Queue<Integer> values = map.getOrDefault(key, new PriorityQueue<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1, o2) * -1;
                    }
                }));
                map.put(key, values);
                for(int j = 0; j < size; j++) {
                    values.offer(Integer.parseInt(st.nextToken()));
                }
            } else {
                String key = st.nextToken();
                int size = Integer.parseInt(st.nextToken());
                if(map.get(key) == null) continue;
                Queue<Integer> values = map.get(key);
                while(!values.isEmpty() && size > 0) {
                    cost += values.poll();
                    size--;
                }
            }
        }
        System.out.println(cost);
    }
}
