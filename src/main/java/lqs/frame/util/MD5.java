package lqs.frame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 加密算法
 * 
 * @author liqingshan 2015-12-21
 *
 */
public class MD5 {
	private static final Logger logger = LoggerFactory.getLogger(MD5.class);
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 返回形式为数字跟字符串
	 * 
	 * @param bByte
	 *            一个字节
	 * @return 加密后的值
	 */
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 加密后的字符串
	 */
	private static String byteToString(byte[] bytes) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sBuffer.append(byteToArrayString(bytes[i]));
		}

		return sBuffer.toString();
	}

	/**
	 * 获取字符串的MD5值
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 返回加密后的字符串
	 */
	public static String getMD5Code(String str) {
		String resultString = null;
		try {
			resultString = new String(str);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			logger.error(ex.toString());
		}

		return resultString;
	}

	/**
	 * 校验密码是否一致
	 * 
	 * @param password
	 *            明文密码
	 * @param md5PwdStr
	 *            加密后的密码字符串
	 * @return true:密码一致；false:密码不一致
	 */
	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5Code(password);
		return s.equalsIgnoreCase(md5PwdStr);
	}
}
