package org.eweb4j.solidbase.code.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.orm.Models;
import org.eweb4j.orm.dao.DAO;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

public class CodeDAOImpl implements CodeDAO {
	private DivPageDAO divPageDAO = null;
	private SelectDAO selectDAO = null;
	private DeleteDAO deleteDAO = null;
	private InsertDAO insertDAO = null;
	private UpdateDAO updateDAO = null;
	private CascadeDAO cascadeDAO = null;
	private DAO dao = null;

	public void setDsName(String dsName) {
		divPageDAO = DAOFactory.getDivPageDAO(dsName);
		selectDAO = DAOFactory.getSelectDAO(dsName);
		deleteDAO = DAOFactory.getDeleteDAO(dsName);
		insertDAO = DAOFactory.getInsertDAO(dsName);
		updateDAO = DAOFactory.getUpdateDAO(dsName);
		cascadeDAO = DAOFactory.getCascadeDAO(dsName);
		dao = DAOFactory.getDAO(Code.class, dsName);
	}

	public List<Code> selectPage(int pageNum, int numPerPage) throws CodeException {
		try {
			String orderField = "codeValue";
			int orderType = 1;
			return divPageDAO.divPage(Code.class, pageNum, numPerPage, orderField, orderType);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}

	}

	public long countAll() throws CodeException {
		return selectDAO.selectCount(Code.class);
	}

	public long countByCodeTypeId(long codeTypeId) throws CodeException {
		try {
			String condition = "type_id = ?";
			Object[] args = { codeTypeId };

			return selectDAO.selectCount(Code.class, condition, args);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}
	}

	public long countByCodeTypeIdAndParentId(long codeTypeId, long parentId) throws CodeException {
		String condition = "type_id = '%s' and parent_id = '%s'";
		return selectDAO.selectCount(Code.class,
				String.format(condition, codeTypeId, parentId));
	}

	public Code selectOneByCodeId(long codeId) {
		return Models.inst(Code.class).findById(codeId);
	}

	public void deleteByCodeId(long codeId) throws CodeException {
		try {
			deleteDAO.deleteById(Code.class, codeId);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}
	}

	public long insert(Code code) throws CodeException {
		long codeId = -1;
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
			codeId = Long.parseLong(String.valueOf(insertDAO.insert(code)));
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}

		return codeId;
	}

	public Code selectOneByCodeValue(String codeValue) throws CodeException {
		try {
			Code code = new Code();
			code.setCodeValue(codeValue);
			return selectDAO.selectOne(code, "codeValue");
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}
	}

	public Code selectOneByRemark(String remark) throws CodeException {
		try {
			Code code = new Code();
			code.setRemark(remark);
			return selectDAO.selectOne(code, "remark");
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
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

			updateDAO.update(code);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}

	}

	public void cascadeSelect(Code[] codes, String... fields) throws CodeException {
		try {
			cascadeDAO.select(codes, fields);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | "
					+ e.getMessage());
		}
	}

	public List<Code> selectPageByCodeTypeIdAndParentId(long codeTypeId, long parentId, int pageNum, int numPerPage) throws CodeException {
		try {
			Collection<Code> codes = 
				dao.clear()
					.selectAll()
					.where()
						.field("type").equal(codeTypeId)
						.and("parent").equal(parentId)
					.asc("codeValue")
					.query(pageNum, numPerPage);
			
			if (codes == null) return null;
			
			return new ArrayList<Code>(codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public List<Code> selectPageByCodeTypeId(long codeTypeId, int pageNum, int numPerPage) throws CodeException {
		try {
			Collection<Code> codes = 
				dao.clear()
				.selectAll()
				.where()
					.field("type").equal(codeTypeId)
					.asc("codeValue")
				.query(pageNum, numPerPage);
			
			if (codes == null) return null;
			
			return new ArrayList<Code>(codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public List<Code> selectByCodeTypeIdAndCodeVal(long codeTypeId, String inputValue) throws CodeException {
		try {
			// select codeId, codeValule, remark from t_code where code_value
			// like '%{inputValue}%' and
			// type_id = '{typeId}' order by code_value asc ;
			Collection<Code> codes = dao.clear()
										.select("codeId", "codeValue", "remark")
										.where()
											.field("codeValue").like(inputValue)
											.and("type").equal(codeTypeId)
										.asc("codeValue")
										.query();
			
			if (codes == null) return null;
			
			return new ArrayList<Code>(codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public List<Code> selectByCodeTypeIdAndIdInPIdsAndCodeVal(long codeTypeId, String inputValue) throws CodeException {
		try {
			// select codeId, codeValule, remark from Code where codeValue
			// like '%{inputValue}%' and type_id = '{typeId}' and codeId
			// not in (select codeId from Code where parentId > '0' ) order by
			// codeValue asc ;
			
			String notInSql = dao.clear()
				.select("codeId")
				.where()
					.field("parent").moreThan(CodeCons.TOP_CODE_ID())
				.toSql();
			
			Collection<Code> codes = 
				dao.clear()
				.select("codeId", "codeValue", "remark")
				.where()
					.field("codeValue").like(inputValue)
					.and("type").equal(codeTypeId).and("codeId")
					.notInSql(notInSql)
				.asc("codeValue")
				.query();
			
			if (codes == null) return null;
			
			return new ArrayList<Code>(codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public List<Code> selectByCodeTypeIdAndParentIdAndCodeValue(long codeTypeId, long parentId, String inputValue) throws CodeException {
		try {
			Collection<Code> codes = 
				dao.clear()
				.select("codeId", "codeValue", "remark")
				.where()
					.field("codeValue").like(inputValue)
					.and("type").equal(codeTypeId)
					.and("parent").equal(parentId)
				.asc("codeValue")
				.query();
			
			if (codes == null) return null;
			
			return new ArrayList<Code>(codes);
		} catch (DAOException e) {
			throw new CodeException(CodeCons.DATA_ACCESS_ERR() + " | " + e.getMessage());
		}
	}

	public long insert(String[] fields, Object... values) throws CodeException {
		return dao.clear().insert(fields).values(values).execute().longValue();
	}

	public void update(String[] fields, Object[] values) throws CodeException {
		dao.clear()
			.update(fields).set(values)
			.execute();
	}
}
