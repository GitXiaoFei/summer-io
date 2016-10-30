package cn.cerc.jimport.excel;

public interface ImportError {
	/**
	 * 自定义错误处理器
	 * 
	 * @param e
	 * @return true: 表示错误已处理，允许继续导入，否则予以中止
	 * @throws Exception
	 */
	public boolean process(ColumnValidateException e) throws Exception;
}
