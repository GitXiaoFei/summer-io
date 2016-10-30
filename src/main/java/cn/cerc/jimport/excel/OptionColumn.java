package cn.cerc.jimport.excel;

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

	@Override
	public Object getValue() {
		String key = this.getString();
		if (key == null)
			key = "";
		String val = items.get(key);
		return val != null ? val : key;
	}

	@Override
	public boolean validate(int row, int col, String value) {
		return items.containsKey(value);
	}
}
