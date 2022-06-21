import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int to, weight;
        String type;
        Node link;

        Node(int to, int weight, String type, Node link) {
            this.to = to;
            this.weight = weight;
            this.type = type;
            this.link = link;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        st = new StringTokenizer(input.readLine());
        while (st.hasMoreTokens()) {
            map.put(st.nextToken(), 0);
        }
        int index = 0;
        for (String key : map.keySet()) {
            map.put(key, index++);
        }
        N = map.size();
        int M = Integer.parseInt(input.readLine());
        int[][] routes = new int[M - 1][2];
        String[] cities = input.readLine().split(" ");
        for (int i = 0; i < cities.length - 1; i++) {
            int from = map.get(cities[i]);
            int to = map.get(cities[i + 1]);
            routes[i] = new int[]{from, to};
        }
        int K = Integer.parseInt(input.readLine());
        Node[] adjList = new Node[N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(input.readLine());
            String type = st.nextToken();
            int from = map.get(st.nextToken());
            int to = map.get(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, type, adjList[from]);
            adjList[to] = new Node(from, weight, type, adjList[to]);
        }
        double[][][] costs = new double[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) Arrays.fill(costs[i][j], Double.MAX_VALUE);
            }
        }
        for (String key : map.keySet()) {
            int start = map.get(key);
            boolean[] visited = new boolean[N];
            while (true) {
                int v = -1;
                double min = Double.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && costs[start][i][0] < min) {
                        v = i;
                        min = costs[start][i][0];
                    }
                }
                if (v == -1) break;
                visited[v] = true;
                for (Node tmp = adjList[v]; tmp != null; tmp = tmp.link) {
                    if(!visited[tmp.to] && costs[start][tmp.to][0] > min + tmp.weight){
                        costs[start][tmp.to][0] = min + tmp.weight;
                    }
                }
            }
            visited = new boolean[N];
            while (true) {
                int v = -1;
                double min = Integer.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && costs[start][i][1] < min) {
                        v = i;
                        min = costs[start][i][1];
                    }
                }
                if (v == -1) break;
                visited[v] = true;
                for (Node tmp = adjList[v]; tmp != null; tmp = tmp.link) {
                    double discounted = tmp.weight;
                    if(tmp.type.equals("Mugunghwa") || tmp.type.equals("ITX-Cheongchun") || tmp.type.equals("ITX-Saemaeul")){
                        discounted = 0;
                    } else if(tmp.type.equals("V-Train") || tmp.type.equals("S-Train")){
                        discounted /= 2.0;
                    }
                    if(!visited[tmp.to] && costs[start][tmp.to][1] > min + discounted){
                        costs[start][tmp.to][1] = min + discounted;
                    }
                }
            }
        }
        double costWithout = 0, costWith = R;
        for(int i = 0; i < routes.length; i++){
            costWithout += costs[routes[i][0]][routes[i][1]][0];
            costWith += costs[routes[i][0]][routes[i][1]][1];
        }
        System.out.println(costWith < costWithout ? "Yes" : "No");
    }
}
