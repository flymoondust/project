class outer{
   int count=0;
   class inter{
   	inter(int i){
       count=i;
       System.out.printf("inter :count=%d\n",count);
	}
   }
}

public class test extends outer{
    class innertest extends outer.inter{
    	innertest(outer o,int i){
	  		 o.super(i);
		}
    }
    public static void main(String[] args){
		outer o=new outer();
   		test t=new test();
		System.out.printf("count=%d\n",t.count);
    	test.innertest it=t.new innertest(t,5);	
		System.out.printf("count=%d\n",t.count);
    }
}
