package cn.cerc.jexport.excel;

public class ProfitRateColumn extends Column {

	/**
	 *取得数据
	 *@author  李港归 
	 *@Time  2017-4-28 16：37
	 *@return  数据集字段对象           
	 */
	@Override
	public Object getValue() {
		return getRecord().getDouble(getCode()) + "%";
	}

}
