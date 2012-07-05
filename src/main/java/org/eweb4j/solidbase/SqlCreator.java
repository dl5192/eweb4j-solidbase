package org.eweb4j.solidbase;

import org.eweb4j.config.EWeb4JConfig;
import org.eweb4j.orm.sql.Model2Table;

public class SqlCreator {

	public static void main(String[] args){
		EWeb4JConfig.start("start.xml");
		Model2Table.write("solidbase.sql");
	}
	
}
