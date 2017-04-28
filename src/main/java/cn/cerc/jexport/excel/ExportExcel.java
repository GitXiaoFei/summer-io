package cn.cerc.jexport.excel;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.cerc.jdb.core.DataSet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportExcel {
	private static ApplicationContext app;
	private static String xmlFile = "classpath:export-excel.xml";
	private HttpServletResponse response;
	private String templateId;
	private Template template;
	private Object handle;

	public ExportExcel(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 是否有权限导出判断
	 * @author 李港归
	 * 2017年4月28日下午4:58:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void export() throws IOException, WriteException, AccreditException {
		if (this.handle == null)
			throw new RuntimeException("handle is null");

		template = this.getTemplate();

		AccreditManager manager = template.getAccreditManager();
		if (manager != null) {
			if (!manager.isPass(this.handle))
				throw new AccreditException(String.format("您没有导出[%s]的权限", manager.getDescribe()));
		}

		HistoryWriter writer = template.getHistoryWriter();
		if (writer != null) {
			writer.start(this.handle, template);
			exportDataSet();
			writer.finish(this.handle, template);
		} else {
			exportDataSet();
		}
	}

	/**
	 * excel导出
	 * @author 李港归
	 * 2017年4月28日下午4:58:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void exportDataSet() throws IOException, WriteException {
		template = this.getTemplate();
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流

		// 下面是对中文文件名的处理
		response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
		String fname = new String(template.getFileName().getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname + ".xls");
		response.setContentType("application/msexcel");// 定义输出类型

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

	/**
	 * 获取导出的内容
	 * @author 李港归
	 * 2017年4月28日下午4:58:06
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @param message 
	 */
	public void export(String message) throws WriteException, IOException {
		this.setTemplateId("ExportMessage");
		DataSet ds = new DataSet();
		ds.append();
		ds.setField("message_", message);
		this.getTemplate().setDataSet(ds);
		exportDataSet();
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取导出权限
	 * @return Template
	 * @author 李港归
	 * 2017年4月28日下午5:24:01
	 */
	public Template getTemplate() {
		if (template == null) {
			if (templateId == null)
				throw new RuntimeException("templateId is null");
			if (app == null)
				app = new FileSystemXmlApplicationContext(xmlFile);
			template = app.getBean(templateId, Template.class);
		}
		return template;
	}

	public void setTemplate(Template template) {

		this.template = template;
	}

	public String getTemplateId() {

		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Object getHandle() {
		return handle;
	}

	public void setHandle(Object handle) {
		this.handle = handle;
	}

}