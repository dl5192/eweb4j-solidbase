package org.eweb4j.solidbase.test;

import org.eweb4j.config.EWeb4JConfig;
import org.eweb4j.orm.Models;
import org.eweb4j.orm.config.ORMConfigBeanUtil;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.util.CommonUtil;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2012-11-15 上午11:20:03
 */

public class TestDAO {

	@BeforeClass
	public static void prepare(){
		String err = EWeb4JConfig.start("start.xml");
		if (err != null)
			System.out.println(err);
	}
	
	@Test
	public void test() throws Exception{
//		Department d = DAOFactory.getDAO(Department.class).selectAll().where().field("departId").equal(18).queryOne();
//		if (d == null)
//			return ;
//		System.out.println("depart===>\n\t"+ d.getCode().getCodeId());
//		
//		DAOFactory.getDeleteDAO().deleteById(Department.class, d.getDepartId());
//		DAOFactory.getDeleteDAO().deleteById(Code.class, d.getCode().getCodeId());
//		
//		
//		
		String field = ORMConfigBeanUtil.getField(Department.class, "id");
		System.out.println("field------->" + field);
	}
}
