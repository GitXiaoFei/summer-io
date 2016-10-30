package cn.cerc.jimport.excel;

import cn.cerc.jdb.core.Record;

public interface ImportRecord {
	public boolean process(Record rs) throws Exception;
}
