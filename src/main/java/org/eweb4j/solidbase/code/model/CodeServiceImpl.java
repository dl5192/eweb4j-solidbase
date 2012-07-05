package org.eweb4j.solidbase.code.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.code.dao.CodeDAO;
import org.eweb4j.util.StringUtil;

public class CodeServiceImpl implements CodeService {
	private CodeDAO codeDAO;
	List<Code> pojos = null;
	long allCount = 0;

	public PageMod<Code> queryByCodeTypeIdAndParentId(long codeTypeId,
			long parentId, int pageNum, int numPerPage) throws CodeException {

		// 如果代码类型忽略,则查询所有的代码信息
		if (codeTypeId < 0) {
			pojos = codeDAO.selectPage(pageNum, numPerPage);
			allCount = codeDAO.countAll();
		} else if (codeTypeId == 0) {
			// 元类型
			pojos = codeDAO.selectPageByCodeTypeId(CodeCons.META_CODE_ID(),
					pageNum, numPerPage);
			allCount = codeDAO.countByCodeTypeId(CodeCons.META_CODE_ID());
		} else {
			Code db_type = codeDAO.selectOneByCodeId(codeTypeId);
			if (db_type == null)
				throw new CodeException(CodeCons.TYPE_NOT_FOUND_MESS());

			if (parentId < 0) {
				// 某类型所有代码
				pojos = codeDAO.selectPageByCodeTypeId(codeTypeId, pageNum,
						numPerPage);
				allCount = codeDAO.countByCodeTypeId(codeTypeId);

			} else {
				// 某类型所有顶级类型 或 某类型某父级下的所有子级
				if (parentId == 0)
					parentId = CodeCons.TOP_CODE_ID();

				Code db_parent = codeDAO.selectOneByCodeId(parentId);
				if (db_parent == null)
					throw new CodeException(CodeCons.PARENT_NOT_FOUND_MESS());

				pojos = codeDAO.selectPageByCodeTypeIdAndParentId(codeTypeId,
						parentId, pageNum, numPerPage);

				allCount = codeDAO.countByCodeTypeIdAndParentId(codeTypeId,
						parentId);
			}
		}

		if (pojos != null)
			codeDAO.cascadeSelect(pojos.toArray(new Code[] {}));

		return new PageMod<Code>(pojos, allCount);
	}

	public List<Code> getTypes(long typeId, String inputValue)
			throws CodeException {
		if (typeId <= 0)
			typeId = CodeCons.META_CODE_ID();

		Code[] codes = handleCodes(typeId, -1, inputValue);

		Arrays.sort(codes, new ByInputValIndexComparator());

		return new ArrayList<Code>(Arrays.asList(codes));
	}

	private Code[] handleCodes(long typeId, long parentId, String inputValue)
			throws CodeException {
		List<Code> db_codes = null;
		if (parentId >= 0 || parentId == CodeCons.TOP_CODE_ID()) {
			db_codes = codeDAO.selectByCodeTypeIdAndParentIdAndCodeValue(typeId, parentId, inputValue);
		} else {
			db_codes = codeDAO.selectByCodeTypeIdAndCodeVal(typeId, inputValue);
		}

		if (db_codes == null)
			db_codes = new ArrayList<Code>();

		final List<Code> pojos = new ArrayList<Code>();
		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws CodeException {
				Code metaType = codeDAO.selectOneByCodeId(CodeCons
						.META_CODE_ID());
				if (metaType == null) {
					metaType = new Code();
					metaType.setCodeId(CodeCons.META_CODE_ID());
					metaType.setCodeValue(CodeCons.META_TYPE());
					metaType.setRemark(CodeCons.META_TYPE());
					String[] fields = { "codeId", "codeValue", "remark" };
					Object[] values = { CodeCons.META_CODE_ID(),
							CodeCons.META_TYPE(), CodeCons.META_TYPE() };
					Code db_metaType = codeDAO.selectOneByCodeValue(CodeCons
							.META_TYPE());
					if (db_metaType != null) {
						metaType.setCodeValue(CodeCons.META_TYPE() + "_");
						values[1] = metaType.getCodeValue();
					}

					Code db_metaType2 = codeDAO.selectOneByRemark(CodeCons
							.META_TYPE());
					if (db_metaType2 != null) {
						metaType.setRemark(CodeCons.META_TYPE() + "_");
						values[2] = metaType.getRemark();
					}

					long id = codeDAO.insert(fields, values);
					if (id != CodeCons.META_CODE_ID()) {

						codeDAO.update(new String[] { "codeId" },
								new Object[] { CodeCons.META_CODE_ID() });
					}

					pojos.add(metaType);
				}
			}
		}, "");

		pojos.addAll(db_codes);

		Code[] codes = pojos.toArray(new Code[] {});

		for (Code c : codes)
			c.setInputValue(inputValue);

		return codes;
	}

	public List<Code> getParents(long codeTypeId, String inputValue)
			throws CodeException {
		if (codeTypeId <= 0)
			codeTypeId = CodeCons.META_CODE_ID();

		Code db_type = codeDAO.selectOneByCodeId(codeTypeId);
		if (db_type == null)
			throw new CodeException(CodeCons.TYPE_NOT_FOUND_MESS());

		Code[] codes = this.handleCodes(codeTypeId, CodeCons.TOP_CODE_ID(),
				inputValue);

		Arrays.sort(codes, new ByInputValIndexComparator());

		return new ArrayList<Code>(Arrays.asList(codes));
	}

	public long addCodeInfo(long codeTypeId, long parentId, Code code)
			throws CodeException {
		if (code == null)
			throw new CodeException(CodeCons.INVALID_CODE());

		Code db_type = codeDAO.selectOneByCodeId(codeTypeId);
		if (db_type == null)
			throw new CodeException(CodeCons.TYPE_NOT_FOUND_MESS());

		Code db_parent = codeDAO.selectOneByCodeId(parentId);
		if (db_parent == null)
			throw new CodeException(CodeCons.PARENT_NOT_FOUND_MESS());

		Code db_code = codeDAO.selectOneByCodeValue(code.getCodeValue());
		if (db_code != null)
			throw new CodeException(CodeCons.DUP_CODE_VALUE_MESS());

		Code type = new Code();
		type.setCodeId(codeTypeId);
		code.setType(type);

		Code parent = new Code();
		parent.setCodeId(parentId);
		code.setParent(parent);

		String now = StringUtil.getNowTime();
		code.setAddTime(now);
		code.setModifyTime(now);

		return codeDAO.insert(code);
	}

	public void removeOneCodeInfo(long codeId) throws CodeException {
		if (codeId == CodeCons.META_CODE_ID()
				|| codeId == CodeCons.TOP_CODE_ID())
			throw new CodeException("该代码不允许删除");

		Code db_code = codeDAO.selectOneByCodeId(codeId);
		if (db_code == null)
			throw new CodeException(CodeCons.CODE_NOT_FOUND_MESS());

		codeDAO.deleteByCodeId(codeId);
	}

	public void removeCodes(Long[] ids) throws CodeException {
		for (long id : ids) {
			removeOneCodeInfo(id);
		}
	}

	public CodeDAO getCodeDAO() {
		return codeDAO;
	}

	public void setCodeDAO(CodeDAO codeDAO) {
		this.codeDAO = codeDAO;
	}

	public EditPage<Code> getEditPage(long codeId) throws CodeException {
		Code db_code = codeDAO.selectOneByCodeId(codeId);
		if (db_code == null)
			throw new CodeException(CodeCons.CODE_NOT_FOUND_MESS());

		String model = CodeCons.MODEL_NAME();
		String action = model + "/" + codeId;
		Code pojo = codeDAO.selectOneByCodeId(codeId);
		if (pojo != null) {
			codeDAO.cascadeSelect(new Code[] { pojo });
		}

		return new EditPage<Code>(model, action, pojo);
	}

	public void updateCodeInfo(Code code) throws CodeException {
		if (code == null)
			throw new CodeException(CodeCons.CODE_NOT_FOUND_MESS());

		long codeId = code.getCodeId();
		if (codeId == 0)
			throw new CodeException(CodeCons.CODE_NOT_FOUND_MESS());

		Code db_code = codeDAO.selectOneByCodeId(codeId);
		if (db_code == null)
			throw new CodeException(CodeCons.CODE_NOT_FOUND_MESS());

		Code db_code_dup_value = codeDAO.selectOneByCodeValue(code
				.getCodeValue());
		if (db_code_dup_value != null
				&& db_code_dup_value.getCodeId() != codeId)
			throw new CodeException(CodeCons.DUP_CODE_VALUE_MESS());

		String now = StringUtil.getNowTime();
		code.setModifyTime(now);

		codeDAO.update(code);
	}

	public List<Code> getChildren(long codeTypeId, long parentId,
			String inputValue) throws CodeException {
		List<Code> pojos = codeDAO.selectByCodeTypeIdAndParentIdAndCodeValue(
				codeTypeId, parentId, inputValue);
		if (pojos == null || pojos.size() == 0)
			return pojos;

		Code[] codes = pojos.toArray(new Code[] {});
		pojos = null;

		for (Code c : codes)
			c.setInputValue(inputValue);

		Arrays.sort(codes, new ByInputValIndexComparator());

		return new ArrayList<Code>(Arrays.asList(codes));
	}

	public Code findByCodeValue(String codeValue) throws CodeException {
		Code code = codeDAO.selectOneByCodeValue(codeValue);
		if (code == null)
			throw new CodeException("code value " + codeValue + " invalid");
		return code;
	}

	public List<Code> queryByCodeValueAndParentId(String codeValue,
			long parentId, int pageNum, int numPerPage) throws CodeException {
		Code code = codeDAO.selectOneByCodeValue(codeValue);
		if (code == null)
			throw new CodeException("code value " + codeValue + " invalid ");

		return this.queryByCodeTypeIdAndParentId(code.getCodeId(), parentId,
				pageNum, numPerPage).getPojos();
	}

	public Code getAndCreateCodeByCodeValue(String codeValue)
			throws CodeException {
		Code type = codeDAO.selectOneByCodeValue(codeValue);
		if (type == null) {
			type = new Code();
			type.setCodeValue(codeValue);
			type.setRemark(type.getCodeValue());
			type.setCodeId(codeDAO.insert(type));
		}

		return type;
	}
}
