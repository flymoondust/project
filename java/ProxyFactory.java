package ProxyFactory;

import java.io.*;
import java.util.*;
 /*
interface fruit{
    public abstract void eat();
}
 
class Apple implements fruit{
    public void eat(){
        System.out.println("Apple");
    }
}
 
class Orange implements fruit{
    public void eat(){
        System.out.println("Orange");
    }
}
 
//操作属性文件类
class init{
    public static Properties getPro() throws FileNotFoundException, IOException{
        Properties pro=new Properties();
        File f=new File("fruit.properties");
        if(f.exists()){
            pro.load(new FileInputStream(f));
        }else{
            pro.setProperty("apple", "ProxyFactory.Apple");
            pro.setProperty("orange", "ProxyFactory.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}

class Factory{
    public static fruit getInstance(String ClassName){
        fruit f=null;
        try{
            f=(fruit)Class.forName(ClassName).newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}

public class ProxyFactory {
	public static void main(String[] a) throws FileNotFoundException, IOException{
        Properties pro=init.getPro();
        fruit f=Factory.getInstance(pro.getProperty("apple"));
        if(f!=null){
            f.eat();
        }
    }
}
*/

///*
import java.lang.reflect.*;
 
//定义项目接口
interface Subject {
    public String say(String name, int age);
}
 
// 定义真实项目
class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        return name + "  " + age;
    }
}
 
class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;
 
    public Object bind(Object obj) {
        this.obj = obj;
        //return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
//                .getInterfaces(), this);
        return Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{ Subject.class },this);//getClassLoader()没什么影响
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
    	System.out.println("before invoke: "+proxy.getClass().getName()+ " method : "+method);
        Object temp = method.invoke(this.obj, args);
        return temp;
    }
}

public class ProxyFactory {
    public static void main(String[] args) {
        MyInvocationHandler demo = new MyInvocationHandler();
        Subject sub = (Subject) demo.bind(new RealSubject());
        System.out.println(sub.getClass().getName());
        
        Class[] interfaces = sub.getClass().getInterfaces();
        String inters = "";
        for(int i=0; i<interfaces.length; i++){
         if(i==0){
          inters += "implements ";
         }
         inters += interfaces[i].getName();
        }
        System.out.println("interfaces[0]= "+interfaces[0]+" interfaces.length = "+interfaces.length);//interfaces[0]= interface ProxyFactory.Subject interfaces.length = 1
        System.out.println(inters);																					  //注意这个“interface”，这就是没有调用getname()的区别
        String info = sub.say("Rollen", 20);
        System.out.println(info);
    }
}
//*/
