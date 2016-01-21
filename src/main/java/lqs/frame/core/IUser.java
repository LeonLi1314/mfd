package lqs.frame.core;

/**
 * 用户信息
 * 
 * @author liqingshan 2016-01-20
 *
 */
public interface IUser {
	/**
	 * 获取用户的身份标识
	 * 
	 * @return 身份标识
	 */
	String getToken();

	/**
	 * 获取登录用户编号
	 * 
	 * @return 用户编号
	 */
	String getUserCd();

	/**
	 * 获取用户真实姓名
	 * 
	 * @return 用户真实姓名
	 */
	String getRealName();

	/**
	 * 获取用户角色串（英文逗号分割）
	 * 
	 * @return
	 */
	String getRoleCodes();
}
