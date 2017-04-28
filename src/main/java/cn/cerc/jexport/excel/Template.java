package cn.cerc.jexport.excel;

import java.util.Date;
import java.util.List;

import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.TDate;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Template {
	private String fileName;
	private List<Column> columns;
	private AccreditManager accreditManager;
	private HistoryWriter historyWriter;
	private DataSet dataSet;
	private int row = 0;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void addColumn(Column column) {
		columns.add(column);
	}

	public AccreditManager getAccreditManager() {
		return accreditManager;
	}

	public void setAccreditManager(AccreditManager accredit) {
		this.accreditManager = accredit;
	}

	public HistoryWriter getHistoryWriter() {
		return historyWriter;
	}

	public void setHistoryWriter(HistoryWriter historyWriter) {
		this.historyWriter = historyWriter;
	}

	/**
	 * 输出数据
	 * @author 李港归
	 * 2017年4月28日下午4:53:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @param sheet 工作表
	 */
	public void output(WritableSheet sheet) throws RowsExceededException, WriteException {
		// 输出列头
		for (int col = 0; col < columns.size(); col++) {
			Column column = columns.get(col);
			Label item = new Label(col, row, column.getName());
			sheet.addCell(item);
		}

		// 输出列数据
		if (dataSet != null) {
			dataSet.first();
			while (dataSet.fetch()) {
				row++;
				for (int col = 0; col < columns.size(); col++) {
					Column column = columns.get(col);
					column.setRecord(dataSet.getCurrent());
					writeColumn(sheet, col, row, column);
				}
			}
		}
	}


	/**
	 * 写入数据
	 * @author 李港归
	 * 2017年4月28日下午4:53:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @param sheet 工作表
	 * @param col 列
	 * @param row 行
	 * @param column 数据对象
	 */
	protected void writeColumn(WritableSheet sheet, int col, int row, Column column)
			throws WriteException, RowsExceededException {
		if (column instanceof NumberColumn) {
			jxl.write.Number item = new jxl.write.Number(col, row, (double) column.getValue());
			sheet.addCell(item);
		} else if (column instanceof DateColumn) {
			TDate day = (TDate) column.getValue();
			DateFormat df = new DateFormat("yyyy-MM-dd");
			DateTime item = new DateTime(col, row, day.getData(), new WritableCellFormat(df));
			sheet.addCell(item);
		} else if (column instanceof DateTimeColumn) {
			DateFormat df = new DateFormat("yyyy-MM-dd HH:mm:ss");
			DateTime item = new DateTime(col, row, (Date) column.getValue(),new WritableCellFormat(df));
			sheet.addCell(item);
		} else {
			Label item = new Label(col, row, column.getValue().toString());
			sheet.addCell(item);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
}
