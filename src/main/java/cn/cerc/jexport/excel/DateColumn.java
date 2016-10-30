package cn.cerc.jexport.excel;

public class DateColumn extends Column {

	@Override
	public Object getValue() {
		return getRecord().getDate(getCode());
	}
}
