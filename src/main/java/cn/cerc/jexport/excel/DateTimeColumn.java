package cn.cerc.jexport.excel;

public class DateTimeColumn extends Column {

	@Override
	public Object getValue() {
		return getRecord().getDateTime(getCode()).getData();
	}
}
