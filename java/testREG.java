import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testREG{
	static Pattern getpattern(String reg){
		return Pattern.compile(reg);
	}
	static Pattern getpattern(String reg,int flag){
		return Pattern.compile(reg,flag);
	}
	public static void main(String[] args){
		Pattern p = getpattern("Never.*!");
		Matcher m = p.matcher("Never be so 2! Never be so silly!");
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());//end()返回匹配位置+1
		}
		p=getpattern("Never.*?!"); //“?” 懒惰型
		m = p.matcher("Never be so 2! Never be so silly!");
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());
		}
		p=getpattern("\\(Never.*?!\\)"); //“?” 懒惰型 在正则表达式中必须用两个"\\"来表达字符串字面值！！！
		m = p.matcher("(Never be so 2! Never be so silly!)");
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());
		}
		p=getpattern("\\(Never.*?a\\\\u030A\\)",Pattern.CANON_EQ); //“?” 懒惰型 在正则表达式中必须用两个"\\"来表达字符串字面值！！！
		m = p.matcher("(Never be so 2! Never be so silly!\u00E5)"); //指定此标志后，当且仅当其完整规范分解匹配时，两个字符才可视为匹配。
																	//例如，当指定此标志时，表达式 "a\u030A" 将与字符串 "\u00E5" 匹配。默认情况下，匹配不考虑采用规范等价。
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());
		}
		String test=Pattern.quote("(Never.*?a\u030A)");
		System.out.format("char=%s\n",  "\u5f53\u7b2c\u4e00");
		System.out.format("char=%s\n", "(Never be so 2! Never be so silly!\u00E5)\n" + test);
		
		p=getpattern(test,Pattern.CANON_EQ); 
		m = p.matcher("(Never be so 2! Never be so silly!\u00E5)"); 
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());
		}
		
		p=getpattern("\\Breg.*"); /*\bword\b \b 就是用在你匹配整个单词的时候  */
		m = p.matcher("Java now hasregular expressions");
		while(m.find()){
			System.out.println(m.group() + " start:" +m.start() + " end:" + m.end());
		}
		
		 p = Pattern.compile("(cat)");
		 m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
			 System.out.println(m.group(1));  //选择组号
		     m.appendReplacement(sb, "$1dog");//$g表可以选择组号
		 }
		 m.appendTail(sb); //加入剩余的字符串
		 System.out.println(sb.toString());
	}
}