package ProxyFactory;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

//public class container{
//	public static void main(String[] args){
//		String fact= "wx is studying";
//		PriorityQueue<String> pq=new PriorityQueue<String>(Arrays.asList(fact.split("")));//以字母分开
//		System.out.println("pq= " + pq);
//		while(pq.peek()!=null){
//			System.out.print(pq.remove() + " ");
//		}
//		System.out.println();
//	}
//}

class testintern{
	String s ="abc";
	String m1 = new StringBuilder().append("abcd").toString();
	String m =m1.substring(0,3);
	String m2=new String("abc");
	String m3=new String("abcd");
}

public class container {
	public static void main(String[] args) {
//		Set<Entry<String, String>> myset = System.getenv().entrySet();
//		for(Entry<String, String> entry: myset) {
//			System.out.println(entry.getKey() + ": " +entry.getValue());
//		}
		
		/*再补充介绍一点：存在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。
		 * String的intern()方法就是扩充常量池的一个方法；
		 * 当一个String实例str调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，
		 * 如果有，则返回其的引用，如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用
		 */
		//关于equals()和== 
		//这个对于String简单来说就是比较两字符串的Unicode序列是否相当，如果相等返回true;
		//而==是比较两字符串的地址是否相同，也就是是否是同一个字符串的引用。 
		System.out.println("test intern()");
		testintern s = new testintern();
		System.out.println("m2==s:"+ (s.m2==s.s)); //false  new String 与　＝“abc”这种常量值初始化的区别
		s.m2 = s.m2.intern();
		System.out.println("after m2.intern() " + s.m2.getClass() + "@" + s.m2.hashCode());
		System.out.println("m2==s:"+ (s.m2==s.s)); //true
		System.out.println("m3==m3.intern():"+ (s.m3==s.m3.intern())); //false 说明常量池新加了一个String,但不是原来new的String:s.m3!!!!!!!!!!
	}
} 