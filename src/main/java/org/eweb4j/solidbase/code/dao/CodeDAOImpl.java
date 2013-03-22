package org.eweb4j.solidbase.code.dao;

import java.util.Collection;

import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

public class CodeDAOImpl implements CodeDAO {

	private String dsName;
	private Class<Code> clazz;
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
		clazz = Code.class;
	}

	public void batchDelete(Code... codes) throws CodeException{
		try {
			Db.batchDelete(dsName, codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}
	
	public Collection<Code> selectPage(int pageNum, int numPerPage) throws CodeException {
		try {
			return Db.ar(clazz,dsName)
					.dao()
					.selectAll()
					.asc("codeValue")
					.query(pageNum, numPerPage);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long countAll() throws CodeException {
		try {
			return Db.ar(clazz,dsName).count();
		} catch (DAOException e){
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long countByCodeTypeId(long codeTypeId) throws CodeException {
		try {

			return Db.ar(clazz,dsName).count("byType", codeTypeId);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long countByCodeTypeIdAndParentId(long codeTypeId, long parentId) throws CodeException {
		try {
			return Db.ar(clazz,dsName).count("byTypeAndParent", codeTypeId, parentId);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Code selectOneByCodeId(long codeId) throws CodeException{
		try {
			return Db.ar(clazz,dsName).findById(codeId);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public void deleteByCodeId(long codeId) throws CodeException {
		try {
			Db.ar(clazz,dsName).delete("byCodeId", codeId);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long insert(Code code) throws CodeException {
		try {
			Code type = code.getType();
			if (type == null || type.getCodeId() <= 0) {
				type = new Code();
				type.setCodeId(CodeCons.META_CODE_ID());
				code.setType(type);
			}

			Code parent = code.getParent();
			if (parent == null || parent.getCodeId() <= 0) {
				code.setParent(type);
			}
			
			Db.ar(code,dsName).create();
			
			return code.getCodeId();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Code selectOneByCodeValue(String codeValue) throws CodeException {
		try {
			return Db.ar(clazz,dsName).find("byCodeValue", codeValue).first();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Code selectOneByRemark(String remark) throws CodeException {
		try {
			return Db.ar(clazz,dsName).find("byRemark",remark).first();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public void update(Code code) throws CodeException {
		try {
			Code type = code.getType();
			if (type == null || type.getCodeId() <= 0) {
				new Code();
				type.setCodeId(CodeCons.META_CODE_ID());
				code.setType(type);
			}

			Code parent = code.getParent();
			if (parent == null || parent.getCodeId() <= 0) {
				code.setParent(type);
			}

			Db.ar(code,dsName).save();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}

	}

	public void cascadeSelect(Code[] codes, String... fields) throws CodeException {
		try {
			DAOFactory.getCascadeDAO(dsName).select(codes, fields);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Collection<Code> selectPageByCodeTypeIdAndParentId(long codeTypeId, long parentId, int pageNum, int numPerPage) throws CodeException {
		try {
			return Db.ar(clazz, dsName).dao()
					.selectAll()
					.where()
						.field("type").equal(codeTypeId)
						.and("parent").equal(parentId)
					.asc("codeValue")
					.query(pageNum, numPerPage);
			
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Collection<Code> selectPageByCodeTypeId(long codeTypeId, int pageNum, int numPerPage) throws CodeException {
		try {
			return Db.ar(clazz, dsName).dao()
					.selectAll()
					.where()
						.field("type").equal(codeTypeId)
					.asc("codeValue")
					.query(pageNum, numPerPage);
			
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Collection<Code> selectByCodeTypeIdAndCodeVal(long codeTypeId, String inputValue) throws CodeException {
		try {
			return Db.ar(clazz, dsName).dao()
					.select("codeId", "codeValue", "remark")
					.where()
						.field("codeValue").like(inputValue)
						.and("type").equal(codeTypeId)
					.asc("codeValue")
					.query();
			
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Collection<Code> selectByCodeTypeIdAndIdInPIdsAndCodeVal(long codeTypeId, String inputValue) throws CodeException {
		try {
			// select codeId, codeValule, remark from Code where codeValue
			// like '%{inputValue}%' and type_id = '{typeId}' and codeId
			// not in (select codeId from Code where parentId > '0' ) order by
			// codeValue asc ;
			
			String notInSql = 
				Db.ar(clazz,dsName).dao()
					.select("codeId")
					.where()
						.field("parent").moreThan(CodeCons.TOP_CODE_ID())
					.toSql();
			
			return Db.ar(clazz,dsName).dao()
					.select("codeId", "codeValue", "remark")
					.where()
						.field("codeValue").like(inputValue)
						.and("type").equal(codeTypeId)
						.and("codeId").notInSql(notInSql)
					.asc("codeValue")
					.query();
			
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public Collection<Code> selectByCodeTypeIdAndParentIdAndCodeValue(long codeTypeId, long parentId, String inputValue) throws CodeException {
		try {
			return Db.ar(clazz, dsName).dao()
					.select("codeId", "codeValue", "remark")
					.where()
						.field("codeValue").like(inputValue)
						.and("type").equal(codeTypeId)
						.and("parent").equal(parentId)
					.asc("codeValue")
					.query();
			
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long insert(String[] fields, Object... values) throws CodeException {
		try {
			return Db.ar(clazz,dsName).dao()
					.insert(fields).values(values)
					.execute()
					.longValue();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public void update(String[] fields, Object[] values) throws CodeException {
		try {
			Db.ar(clazz,dsName).dao()
				.update(fields).set(values)
				.execute();
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}
}
