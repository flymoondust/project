//static class test{
//	int i;
//	void f(){
//		System.out.printf("f() called: i=%d\n",i);
//	}
//}
public class statictest{
	int i;
	void f(){
		System.out.printf("f() called: i=%d\n",i);
	}
	static class test{
		int i;
		void f(){
			System.out.printf("f() called: i=%d\n",i);
		}
	}
	public static void main(String[] args){
		test.i=5;
		test.f();
	}
}
