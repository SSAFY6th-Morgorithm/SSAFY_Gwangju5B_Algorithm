import java.util.Arrays;
import java.util.Scanner;

/*
 * 시간초과 주의
 * 1) 일반적인 이중for문으로 비교 -> 시간초과
 * 2) 몇가지 다른 예시들로 계산해보니 가장 가까운 값은 모두 중앙에 있는 집이였음 
 * 		=> 정렬 후, 중앙에 있는 집만 비교
 * ++ 내장 sort 말고 quick sort 직접 구현 -> 시간초과
 *    Arrays.sort()는 Dual-Pivot Quick Sort, 
 *    1 피벗 Quick sort에 비해 많은 데이터 셋에서 O(n log n)의 빠른 속도를 보장
 */

public class BOJ_18310 {
	static int min=Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		int[] list=new int[N];
		int minj=0;
		
		// 위치 값 입력받기
		for (int i=0; i<N; i++) {
			list[i]=scan.nextInt();
		}
		
		// 가장 가까운 위치는 중앙에 있는 값
		int half=(N/2)-1;
				
		// 정렬
		Arrays.sort(list);
		// quick_sort(list,0,N-1); 

		// 중앙에 있는 두개의 값 확인
		for (int i=half; i<half+2; i++) {
			int distance=0;
			for (int j=0; j<N; j++) {
				distance+=Math.abs(list[i]-list[j]);
			}
			if (min>distance) {
				min=distance;
				// 가까운 위치 저장
				minj=i;
			}
		}
		System.out.println(list[minj]);
	}
	
//	public static void quick_sort(int[] list,int start,int end) {
//		if (start>=end) {
//			return;
//		}
//		int pivot=start;
//		int left=start+1;
//		int right=end;
//		
//		while(left<=right) {
//			while(left<=end && list[pivot]>=list[left]) {
//				left++;
//			}
//			while(right>start && list[pivot]<=list[right]) {
//				right--;
//			}
//			if (left>=end) {
//				int swap=list[pivot];
//				list[pivot]=list[left];
//				list[left]=swap;
//			}else {
//				int swap=list[left];
//				list[left]=list[right];
//				list[right]=swap;
//			}
//		}
//		quick_sort(list,start,left-1);
//		quick_sort(list,left+1,end);
//	}
}
