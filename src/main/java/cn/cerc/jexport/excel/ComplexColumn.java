package cn.cerc.jexport.excel;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jdb.core.Record;

public class ComplexColumn extends Column {
	private List<String> fields = new ArrayList<>();

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	/**
	 *取得数据
	 *@author  李港归 
	 *@Time  2017-4-28 16：40
	 *@return  
	 */
	public String getValue() {
		Record record = this.getRecord();
		StringBuffer buff = new StringBuffer();
		for (String field : fields) {
			if (record.hasValue(field)) {
				if (buff.length() > 0)
					buff.append(",");
				buff.append(record.getString(field));
			}
		}
		return buff.toString();
	}
}
