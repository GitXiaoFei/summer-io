package cn.cerc.jimport.excel;

public class StringColumn extends Column {

	// 取得数据
	@Override
	public Object getValue() {
		return this.getString();
	}

	@Override
	public boolean validate(int row, int col, String value) {
		return true;
	}

}
