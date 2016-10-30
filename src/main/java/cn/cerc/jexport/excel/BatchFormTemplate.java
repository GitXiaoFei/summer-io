package cn.cerc.jexport.excel;

import java.util.List;

import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.other.utils;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * 批次导出单据明细
 * @author weish
 *
 */
public class BatchFormTemplate extends FormTemplate {
	List<DataSet> items;

	@Override
	public void output(WritableSheet sheet) throws RowsExceededException, WriteException {
		int newRow = 0;
		for (DataSet ds : items) {
			this.setDataSet(ds);
			this.setFooter((template, sheet1) -> {
				Record footer = new Record();
				for (Record item : ds) {
					footer.setField("合计数量", footer.getDouble("合计数量") + item.getDouble("Num_"));
					footer.setField("合计金额", footer.getDouble("合计金额") + item.getDouble("OriAmount_"));
				}
				int row = template.getRow();
				for (String field : footer.getItems().keySet()) {
					row++;
					Object val = footer.getItems().get(field);
					sheet1.addCell(new Label(0, row, field));
					sheet1.addCell(new Label(1, row, Double.toString(utils.roundTo((Double) val, -2))));
				}
			});

			// 输出原来的表格
			super.output(sheet);
			newRow += this.getHeads().size() + ds.size() + 6;
			this.setRow(newRow);
		}
	}

	public List<DataSet> getItems() {
		return items;
	}

	public void setItems(List<DataSet> items) {
		this.items = items;
	}

}
