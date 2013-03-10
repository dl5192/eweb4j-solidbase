package org.eweb4j.solidbase.code.dao;

import java.util.Collection;

import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeException;

public interface CodeDAO {

	public void batchDelete(Code... codes) throws CodeException;
	
	public Collection<Code> selectPage(int pageNum, int numPerPage) throws CodeException;

	public long countAll() throws CodeException;

	public Collection<Code> selectPageByCodeTypeId(long codeTypeId, int pageNum, int numPerPage) throws CodeException;

	public long countByCodeTypeId(long codeTypeId) throws CodeException;

	public Code selectOneByCodeId(long codeId) throws CodeException;

	public void deleteByCodeId(long codeId) throws CodeException;

	public long insert(Code code) throws CodeException;

	public Code selectOneByCodeValue(String codeValue) throws CodeException;

	public Code selectOneByRemark(String remark) throws CodeException;

	public void update(Code code) throws CodeException;

	public void cascadeSelect(Code[] codes, String... fields) throws CodeException;

	public Collection<Code> selectPageByCodeTypeIdAndParentId(long codeTypeId, long parentId, int pageNum, int numPerPage) throws CodeException;

	public Collection<Code> selectByCodeTypeIdAndParentIdAndCodeValue(long codeTypeId, long parentId, String codeValue) throws CodeException;

	public long countByCodeTypeIdAndParentId(long codeTypeId, long parentId) throws CodeException;

	public Collection<Code> selectByCodeTypeIdAndCodeVal(long codeTypeId, String inputValue) throws CodeException;

	public Collection<Code> selectByCodeTypeIdAndIdInPIdsAndCodeVal(long codeTypeId, String inputValue) throws CodeException;

	public long insert(String[] fields, Object... values) throws CodeException;

	public void update(String[] fields, Object[] values) throws CodeException;

}
