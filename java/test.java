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
	inter getinner(test o,int input){
    	class innertest extends outer.inter{
    		innertest(outer o,int i){
	  			o.super(i);
			}
    	}
		return new innertest(o,input);
	}
	inter getinner2(test o,int input){
			return o.new inter(input){
		};
	}
    public static void main(String[] args){
		outer o=new outer();
   		test t=new test();
		System.out.printf("count=%d\n",t.count);
    	//test.innertest it=t.new innertest(t,5);	
		t.getinner(t,5);
		System.out.printf("count=%d\n",t.count);
		t.getinner2(t,6);
		System.out.printf("count=%d\n",t.count);
    }
}
