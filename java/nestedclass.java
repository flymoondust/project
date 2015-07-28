interface Bird{
	void eat();
	static class littlebird{
		static void move(Bird bird){
			bird.eat();
		}
	}
}

public class nestedclass implements Bird{
	public void eat(){
		System.out.print("little bird is eatting\n");
	}
	public static void main(String[] args){
		littlebird.move(new nestedclass());
	}	
}
