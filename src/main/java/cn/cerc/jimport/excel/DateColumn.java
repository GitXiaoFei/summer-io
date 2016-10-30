package cn.cerc.jimport.excel;

import cn.cerc.jdb.core.TDateTime;

public class DateColumn extends Column {

	@Override
	public Object getValue() {
		return getRecord().getDate(getCode()).toString();
	}

	@Override
	public boolean validate(int row, int col, String value){
		TDateTime obj = TDateTime.fromDate(value);
		return obj != null;
	}
}
