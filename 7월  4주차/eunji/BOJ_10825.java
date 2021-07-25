import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Students implements Comparable<Students>{
	String name;
	int ko;
	int eng;
	int math;
	
	public Students(String name,int ko, int eng, int math) {
		this.name=name;
		this.ko=ko;
		this.eng=eng;
		this.math=math;
	}
	
	/*
	 * 국어점수 내림차순
	 * 영어점수 오름차순
	 * 수학점수 내림차순
	 * 이름 사전순으로 (오름차순) 정렬
	 */
	
	@Override
	public int compareTo(Students o) {
		// TODO Auto-generated method stub
		
		/*
		 * compareTo 메서드로 자리바꿈 여부를 결정함
		 * return이 0이나 음수이면, 자리바꿈 x 
		 * 양수이면 자리바꿈 o
		 * (x,y) ==> x<y면 -1, x==y면 0, x>y면 1
		 */
		
		if (this.ko==o.ko && this.eng==o.eng && this.math==o.math) {
			/* 만약, this.name>o.name => this.name이 사전순으로 더 크다! 
			* => 따라서 this.name과 o.name 순서를 바꿈 (this.name이 뒤로 가야함)
			* */ 
			return this.name.compareTo(o.name);
		}
		if (this.ko==o.ko && this.eng==o.eng) {
			/* 만약, o.name>this.name => o.name이 더 큰 값이다! 
			* => 내림차순이므로 o.math와 this.math 순서를 바꿈 (this.math(작은값)이 뒤로 가야함)
			* */ 
			return Integer.compare(o.math,this.math);
		}
		if (this.ko==o.ko) {
			return Integer.compare(this.eng,o.eng);
		}
		return Integer.compare(o.ko,this.ko);
	}

}
public class BOJ_10825 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int T=scan.nextInt();
		
		ArrayList<Students> score=new ArrayList<Students>();
		
		// score 입력받기
		for (int i=0; i<T; i++) {
			String name=scan.next();
			int ko=scan.nextInt();
			int eng=scan.nextInt();
			int math=scan.nextInt();
			Students st=new Students(name,ko,eng,math);
			score.add(st);
		}
		
		// 기본적으로 Collections.sort는 오름차순
		Collections.sort(score);
		
		// 이름만 출력해야하므로 
		for (int i=0; i<T; i++) {
			System.out.println(score.get(i).name);
		}
	}

}
