package Top10;

public class Fibonanci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//0,1,1,2,3,5,8,13
		int num =6;
		int a=0;
		int b=1;
		while(a < num) {
			System.out.println(a+" ");
			int next=a+b;
			a=b;
			b=next;
		}
		
	}

}
