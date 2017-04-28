package cn.cerc.jexport.excel;

public class ProfitRateColumn extends Column {

	@Override
	public Object getValue() {
		return getRecord().getDouble(getCode()) + "%";
	}

}
