package cn.cerc.jexport.excel;

import java.util.LinkedHashMap;
import java.util.Map;

public class OptionColumn extends Column {
	private Map<String, String> items = new LinkedHashMap<>();

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	/**
	 *取得数据
	 *@author  李港归 
	 *@Time  2017-4-28 16：35
	 *@return  数据集字段对象           
	 */
	@Override
	public Object getValue() {
		String key = this.getString();
		if (key == null)
			key = "";
		String val = items.get(key);
		return val != null ? val : key;
	}
}
