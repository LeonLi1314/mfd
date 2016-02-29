package rtmap.frame.util;

/**
 * 字符串工具类
 * 
 * @author liqingshan 2015-12-03
 *
 */
public class StringUtils {
	/**
	 * 判断是否为空或者空字符串
	 * 
	 * @param str
	 *            待判断的字符串
	 * @return true：空或空字符串；false：非空
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null)
			return true;
		if (str.trim().length() == 0)
			return true;

		return false;
	}
	
	/**
	 * 对比两个字符串是否相等
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 当两个字符串为空或者空字符串时相等；对比两个字符串的值是否相等
	 */
	public static boolean compareIngoreEmpty(String str1,String str2){
		if(isNullOrEmpty(str1) && isNullOrEmpty(str2))
			return true;
		
		if((!isNullOrEmpty(str1) && isNullOrEmpty(str2)) || (isNullOrEmpty(str1) && !isNullOrEmpty(str2)))
			return false;
		
		return str1.trim().equals(str2.trim());
	}
}
