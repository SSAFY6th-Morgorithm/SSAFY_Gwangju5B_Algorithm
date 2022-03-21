import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1722_순열의순서 {

    static long[] fact;
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());
        fact = new long[N];
        fact[0] = 1;
        for(int i = 1; i < N; i++) {
            fact[i] = i * fact[i - 1];
        }
        StringTokenizer st = new StringTokenizer(input.readLine());
        int problem = Integer.parseInt(st.nextToken());
        List<Integer> sequence = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            sequence.add(i);
        }
        if(problem == 1) {
            long k = Long.parseLong(st.nextToken());

            answer = "";
            getNthList(N, k, sequence);
        } else {
            List<Integer> list = new ArrayList<>();
            while(st.hasMoreTokens()){
                list.add(Integer.parseInt(st.nextToken()));
            }
            answer = "";
            getNthWithList(N, 1, list, sequence);
        }
        System.out.println(answer);
    }
    static void getNthList(int n, long k, List<Integer> sequence) {
        for(int i = 0; i < sequence.size(); i++) {
            if(k > i * fact[n - 1] && k <= (i + 1) * fact[n - 1]) {
                answer += sequence.remove(i);
                answer += " ";
                getNthList(n - 1, k - i * fact[n - 1], sequence);
                break;
            }
        }
    }
    static void getNthWithList(int n, long count, List<Integer> list, List<Integer> sequence) {
        if(list.isEmpty()) {
            answer = count + "";
            return;
        }
        int current = list.remove(0);
        int index = sequence.indexOf(current);
        count += index * fact[n - 1];
        sequence.remove(index);
        getNthWithList(n - 1, count, list, sequence);
    }
}
