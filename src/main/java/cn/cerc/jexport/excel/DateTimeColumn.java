package cn.cerc.jexport.excel;

public class DateTimeColumn extends Column {

	/**
	 *获取数据源获取的时间
	 *@author  李港归 
	 *@Time  2017-4-28 16：40
	 *@return  时间对象
	 */
	@Override
	public Object getValue() {
		return getRecord().getDateTime(getCode()).getData();
	}
}
