package org.eweb4j.solidbase.code.model;

import java.util.Collection;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;

/**
 * 迫于用户使用上的习惯,只能把原本叫做"类别管理"的东西变成"代码管理". Category-->Code
 * 
 * @author weiwei
 * 
 */
public interface CodeService {

	/**
	 * 查询某个类型下的代码信息
	 * 
	 * @param codeTypeId
	 * @param parentId
	 *            父类别ID 0表示没有父类的顶级类
	 * @param pageNum
	 *            -1 表示查询所有
	 * @param numPerPage
	 *            -1 表示查询所有
	 * @return ListPage
	 * @throws CodeException
	 */
	public PageMod<Code> queryByCodeTypeIdAndParentId(long codeTypeId, long parentId, int pageNum, int numPerPage) throws CodeException;

	/**
	 * 
	 * @param codeValue
	 * @param parentId
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 * @throws CodeException
	 */
	public Collection<Code> queryByCodeValueAndParentId(String codeValue, long parentId, int pageNum, int numPerPage) throws CodeException;

	/**
	 * get the codetype list
	 * 
	 * @param inputValue
	 * @param typeId
	 * @return
	 * @throws CodeException
	 */
	public Collection<Code> getTypes(long typeId, String inputValue) throws CodeException;

	/**
	 * get the parent codes list
	 * 
	 * @param inputValue
	 * 
	 * @return
	 * @throws CodeException
	 */
	public Collection<Code> getParents(long codeTypeId, String inputValue) throws CodeException;

	/**
	 * 添加代码信息
	 * 
	 * @param codeTypeId
	 * @param parentId
	 * @param code
	 * @return 
	 * @throws CodeException
	 */
	public long addCodeInfo(long codeTypeId, long parentId, Code code) throws CodeException;

	/**
	 * 删除
	 * 
	 * @param codeId
	 */
	public void removeOneCodeInfo(long codeId) throws CodeException;

	/**
	 * 
	 * @param ids
	 * @throws CodeException
	 */
	public void removeCodes(Long[] ids) throws CodeException;

	/**
	 * query by id
	 * 
	 * @param codeId
	 * @return
	 * @throws CodeException
	 */
	public EditPage<Code> getEditPage(long codeId) throws CodeException;

	/**
	 * 只能修改名字,不能修改类型和父类
	 * 
	 * @param code
	 * @throws CodeException
	 */
	public void updateCodeInfo(Code code) throws CodeException;

	/**
	 * 
	 * @param bkTypeCodeTypeId
	 * @param pid
	 * @param inputValue
	 * @return
	 */
	public Collection<Code> getChildren(long bkTypeCodeTypeId, long pid, String inputValue) throws CodeException;

	/**
	 * 
	 * @param codeValue
	 * @return
	 * @throws CodeException
	 */
	public Code findByCodeValue(String codeValue) throws CodeException;

	/**
	 * 
	 * @param codeValue
	 * @return
	 * @throws CodeException
	 */
	public Code getAndCreateCodeByCodeValue(String codeValue) throws CodeException;
}
