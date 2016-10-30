package cn.cerc.jexport.excel;

public class NumberColumn extends Column {
	@Override
	public Object getValue() {
		return getRecord().getDouble(getCode());
	}
}
