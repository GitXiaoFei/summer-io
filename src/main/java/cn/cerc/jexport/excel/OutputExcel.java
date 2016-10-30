package cn.cerc.jexport.excel;

import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public interface OutputExcel {
	public void output(FormTemplate formTemplate, WritableSheet sheet) throws RowsExceededException, WriteException;
}
