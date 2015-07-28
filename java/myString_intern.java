/*
 * 在这里控制台输出的结果都是true  true，原因在于 intern 这个方法返回的是 返回字符串对象的规范化表示形式，
 * 当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（该对象由 equals(Object) 方法确定），
 * 则返回池中的字符串。否则，将此 String 对象添加到池中，并且返回此 String 对象的引用。这时候c和d就是相等的。
 */

//假定要加载许多数据库数据到内存，这些数据有很多是重复的。在反复测试之后,发现intern() 省了好多内存。
package myString_intern;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
/*
public class myString_intern {
	public static void main (String []argv) {
		
		String a =  "b" ;   
	    String b =  "b" ;   
	    System.out.println( a == b);   
	    String c = "d" ;  
	    String d = new String( "d" ).intern() ;   
	    System.out.println( c == d); 
	    
		String s1 = "ab123" ;  
		String s2 = new String( "ab123" ) ;  
		System.out.println( s1 == s2 );   
		String s3 = s2.intern() ;   
		System.out.println( s1 == s3 ) ; //和常亮的实例是同一块内存
		System.out.println( s2 == s3 ) ;//和new 出来的对象不是同一块内存
	}
}
*/
//其实还可以通过反射调用其他类中的方法：
interface China{
    public static final String name="Rollen";
    public static  int age=20;
    public void sayChina();
    public void sayHello(String name, int age);
}
 
class Person implements China{
    public Person() {
         
    }
    public Person(String sex){
        this.sex=sex;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public void sayChina(){
        System.out.println("hello ,china");
    }
    @Override
    public void sayHello(String name, int age){
        System.out.println(name+"  "+age);
    }
    private String sex;
}



class myString_intern {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("myString_intern.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            //调用Person类中的sayChina方法
            Method method=demo.getMethod("sayChina");
            method.invoke(demo.newInstance());
            //调用Person的sayHello方法
            method=demo.getMethod("sayHello", String.class,int.class);
            method.invoke(demo.newInstance(),"Rollen",20);
             
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
class myString_intern{
    public static void main(String[] args) {
        int[] temp={1,2,3,4,5};
        Class<?>demo=temp.getClass().getComponentType();
        System.out.println("数组类型： "+demo.getName());
        System.out.println("数组长度  "+Array.getLength(temp));
        System.out.println("数组的第一个元素: "+Array.get(temp, 4));
        Array.set(temp, 0, 100);
        System.out.println("修改之后数组第一个元素为： "+Array.get(temp, 0));
    }
}
*/
