package cn.cerc.jexport.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import cn.cerc.jdb.core.DataSet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataSetFile {
	private Template template;
	private DataSet dataSet;
	private String fileName;

	public DataSetFile(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public void save() throws IOException, RowsExceededException, WriteException {
		save(this.fileName);
	}

	/**
	 * 输出文件
	 * @author 李港归
	 * 2017年4月28日下午5:43:50
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @param fileName 文件名
	 */
	public void save(String fileName) throws IOException, RowsExceededException, WriteException {
		setFileName(fileName);
		OutputStream os = new FileOutputStream(fileName);
		Template template = this.getTemplate();
		template.setFileName(fileName);
		template.setDataSet(dataSet);
		if (template.getColumns().size() == 0) {
			for (String field : dataSet.getFieldDefs().getFields()) {
				StringColumn column = new StringColumn();
				column.setCode(field);
				column.setName(field);
				template.addColumn(column);
			}
		}
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		template.output(sheet);
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

	public Template getTemplate() {
		if (template == null) {
			template = new Template();
			template.setColumns(new ArrayList<>());
		}
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
