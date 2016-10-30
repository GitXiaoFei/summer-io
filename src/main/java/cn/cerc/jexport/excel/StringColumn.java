package cn.cerc.jexport.excel;

public class StringColumn extends Column {

	// 取得数据
	@Override
	public Object getValue() {
		return this.getString();
	}

}
