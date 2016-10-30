package cn.cerc.jimport.excel;

public class NumberColumn extends Column {
	@Override
	public Object getValue() {
		return getRecord().getDouble(getCode());
	}

	@Override
	public boolean validate(int row, int col, String value){
		return cn.cerc.jdb.other.utils.isNumeric(value);
	}
}
