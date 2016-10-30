package cn.cerc.jexport.excel;

public interface HistoryWriter {
	public void start(Object handle, Template template);

	public void finish(Object handle, Template template);
}
