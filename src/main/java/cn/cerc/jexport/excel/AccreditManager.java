package cn.cerc.jexport.excel;

public interface AccreditManager {
	/**
	 * 返回是否可以通过本次权限
	 */
	public boolean isPass(Object handle);

	/**
	 * 返回需要授权的权限描述
	 */
	public String getDescribe();
}
