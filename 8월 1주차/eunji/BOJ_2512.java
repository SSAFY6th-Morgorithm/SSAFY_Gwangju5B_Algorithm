import java.util.Scanner;

public class BOJ_2512 {
	public static int M;

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		int[] array=new int[N];
		int sum=0;
		int max=0;
		for (int i=0; i<N; i++) {
			array[i]=scan.nextInt();
			sum+=array[i];
			if (max<array[i]) {
				max=array[i];
			}
		}
		M=scan.nextInt();
		if (sum<=M) {
			// 모든 요청이 배정될 수 있는 경우
			System.out.println(max);
		}else {
			//모든 요청이 배정될 수 없는 경우
			//정수 상한액 정해야 함
			System.out.println(bs(array,1,M,0));
		}
	}
	
	public static int bs(int[] array,int start,int end,int result) {
		if (start>end) {
			return result;
		}
		int mid=(start+end)/2; //정수 상한액
		int s=0;//정수 상한액으로 계산한 예산
		for (int i=0; i<array.length; i++) {
			if (mid<=array[i]) {
				//정수 상한액보다 이상인 예산요청은 정수 상한액 배정
				s+=mid;
			}else {
				//상한액 이하의 예산요청은 요청한 금액 배정
				s+=array[i];
			}
		}
		if (s<=M) {
			//정수상한액으로 계산한 예산이 제시된 예산보다 작거나 같으면
			//정수상한액을 더 올려도 된다는 의미
			start=mid+1;
			result=mid;
		}else {
			end=mid-1;
		}
		return bs(array,start,end,result);
	}

}
