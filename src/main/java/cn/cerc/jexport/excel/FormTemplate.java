package cn.cerc.jexport.excel;

import java.util.List;

import cn.cerc.jdb.core.Record;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class FormTemplate extends Template {
	private List<Column> heads;
	private OutputExcel footer;

	public List<Column> getHeads() {
		return heads;
	}

	public void setHeads(List<Column> heads) {
		this.heads = heads;
	}


	/**
	 * 输出数据以及设置新的起点行号
	 * @author 李港归
	 * 2017年4月28日下午4:53:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @param sheet 工作表
	 */
	@Override
	public void output(WritableSheet sheet) throws RowsExceededException, WriteException {
		// 输出列头
		Record head = this.getDataSet().getHead();
		if (heads != null) {
			for (int lineNo = 0; lineNo < heads.size(); lineNo++) {
				Column column = heads.get(lineNo);
				int row = this.getRow() + lineNo;
				Label item1 = new Label(0, row, column.getName());
				sheet.addCell(item1);
				// Label item2 = new Label(1, lineNo,
				// head.getString(column.getCode()));
				// sheet.addCell(item2);
				column.setRecord(head);
				writeColumn(sheet, 1, row, column);
			}
			// 设置新的起点行号
			this.setRow(this.getRow() + heads.size());
		}
		// 输出原来的表格
		super.output(sheet);

		// 输出合计
		if (footer != null)
			footer.output(this, sheet);
	}

	public OutputExcel getFooter() {
		return footer;
	}

	public void setFooter(OutputExcel footer) {
		this.footer = footer;
	}
}
