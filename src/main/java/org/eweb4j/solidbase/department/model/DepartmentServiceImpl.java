package org.eweb4j.solidbase.department.model;

import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.code.dao.CodeDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeException;
import org.eweb4j.solidbase.code.model.CodeService;
import org.eweb4j.solidbase.department.dao.DepartmentDAO;
import org.eweb4j.util.CommonUtil;

import freemarker.template.utility.StringUtil;

public class DepartmentServiceImpl implements DepartmentService {
	private CodeService codeService;

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	private DepartmentDAO departmentDAO;
	private CodeDAO codeDAO;

	private Code code;

	private final static String format = "<li><a tname='%s', tvalue='%s' checked='%s' >%s</a>%s</li>";

	private void checkDepartExist(long departId) throws DepartmentException {
		Department db_depart = departmentDAO.selectOneByDepartId(departId);
		if (db_depart == null)
			throw new DepartmentException(
					DepartmentCons.DEPARTMENT_NOT_FOUND_MESS());
	}

	private void departCodeLogic(final Department department)
			throws DepartmentException {
		if (department == null)
			throw new DepartmentException(
					DepartmentCons.IN_COMPLETE_DEPARTMENT_INFO_MESS());

		code = department.getCode();

		Code level = department.getLevel();
		if (level == null)
			throw new DepartmentException(DepartmentCons.LEVEL_NOT_FOUND_MESS());

		Code db_level = null;
		try {
			db_level = codeDAO.selectOneByCodeId(level.getCodeId());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}
		if (db_level == null)
			throw new DepartmentException(DepartmentCons.LEVEL_NOT_FOUND_MESS());

		Code parent = department.getCode().getParent();
		if (parent != null && parent.getCodeId() > 0) {
			Code db_parent = null;
			try {
				db_parent = codeDAO.selectOneByCodeId(parent.getCodeId());
			} catch (CodeException e) {
				throw new DepartmentException(
						DepartmentCons.CODE_DATA_OP_EXP(), e);
			}
			if (db_parent == null)
				throw new DepartmentException(
						DepartmentCons.PARENT_DEPARTMENT_NOT_FOUND_MESS());
		}

		Code cate = department.getDepartCate();
		if (cate == null)
			throw new DepartmentException(
					DepartmentCons.DEPARTMENT_CATE_NOT_FOUND_MESS());

		Code db_cate = null;
		try {
			db_cate = codeDAO.selectOneByCodeId(cate.getCodeId());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}

		if (db_cate == null)
			throw new DepartmentException(
					DepartmentCons.DEPARTMENT_CATE_NOT_FOUND_MESS());

	}

	public void createDepartInfo(final Department department)
			throws DepartmentException {
		this.departCodeLogic(department);

		Code db_code = null;
		try {
			db_code = codeDAO.selectOneByRemark(code.getRemark());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}
		if (db_code != null)
			throw new DepartmentException(
					DepartmentCons.DUP_DEPARTMENT_NAME_MESS());

		Code db_code2 = null;
		try {
			db_code2 = codeDAO.selectOneByCodeValue(code.getCodeValue());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}
		if (db_code2 != null)
			throw new DepartmentException(
					DepartmentCons.DUP_DEPARTMENT_CODE_VALUE_MESS());

		// 事务模板,保证代码和部门信息的同步插入
		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				code.setType(getTypeCodeByCodeValue(DepartmentCons
						.DEPARTMENT_TYPE_CODE_VALUE()));
				long codeId = codeDAO.insert(code);
				Code _code = new Code();
				_code.setCodeId(codeId);
				department.setCode(_code);
				String now = CommonUtil.getNowTime();
				department.setAddTime(now);
				department.setModifyTime(now);

				department.setDepartId(departmentDAO.insert(department));
			}
		}, "");

	}

	public static void co(Long id) {
		id = 10l;
	}

	public void updateDepartInfo(final Department department)
			throws DepartmentException {

		this.departCodeLogic(department);

		Code db_code = null;
		try {
			db_code = codeDAO.selectOneByRemark(code.getRemark());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}

		if (db_code != null && db_code.getCodeId() != code.getCodeId())
			throw new DepartmentException(
					DepartmentCons.DUP_DEPARTMENT_NAME_MESS());

		Code db_code2 = null;
		try {
			db_code2 = codeDAO.selectOneByCodeValue(code.getCodeValue());
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}

		if (db_code2 != null && db_code2.getCodeId() != code.getCodeId())
			throw new DepartmentException(
					DepartmentCons.DUP_DEPARTMENT_CODE_VALUE_MESS());

		Code parent = code.getParent();
		if (parent.getCodeId() == code.getCodeId())
			throw new DepartmentException(
					DepartmentCons.PARENT_CAN_NOT_SAME_TO_SELFT_MESS());

		// 事务模板,保证代码和部门信息的同步更新
		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				code.setType(getTypeCodeByCodeValue(DepartmentCons
						.DEPARTMENT_TYPE_CODE_VALUE()));
				codeDAO.update(code);
				department.setCode(null);
				String now = CommonUtil.getNowTime();
				department.setModifyTime(now);
				departmentDAO.update(department);
			}
		}, "");

	}

	public PageMod<Department> getPageDepartInfo(int pageNum, int numPerPage)
			throws DepartmentException {
		List<Department> pojos = departmentDAO.divPage(pageNum, numPerPage);
		if (pojos != null) {
			departmentDAO.cascadeSelect(pojos.toArray(new Department[] {}));
			for (Department depart : pojos) {
				try {
					codeDAO.cascadeSelect(new Code[] { depart.getCode() });
				} catch (CodeException e) {
					throw new DepartmentException(
							DepartmentCons.CODE_DATA_OP_EXP(), e);
				}
			}
		}
		long allCount = departmentDAO.countAll();

		return new PageMod<Department>(pojos, allCount);
	}

	public EditPage<Department> getEditPage(long departId)
			throws DepartmentException {

		Department pojo = departmentDAO.selectOneByDepartId(departId);
		if (pojo == null)
			throw new DepartmentException(
					DepartmentCons.DEPARTMENT_NOT_FOUND_MESS());

		departmentDAO.cascadeSelect(pojo);
		String model = DepartmentCons.MODEL_NAME();
		String action = model + "/" + departId;

		return new EditPage<Department>(model, action, pojo);
	}

	public void removeDepartInfo(final long departId)
			throws DepartmentException {

		this.checkDepartExist(departId);

		Transaction.execute(new Trans() {
			@Override
			public void run(Object... args) throws Exception {
				Department depart = departmentDAO.selectOneByDepartId(departId);
				departmentDAO.delete(departId);
				codeDAO.deleteByCodeId(depart.getCode().getCodeId());
			}
		}, "");

	}

	public void batchRemoveDepartInfo(long[] departIds)
			throws DepartmentException {
		for (long departId : departIds)
			this.removeDepartInfo(departId);
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public List<Code> queryParentDeparts() throws DepartmentException {
		Code type = getTypeCodeByCodeValue(DepartmentCons
				.DEPARTMENT_TYPE_CODE_VALUE());
		return departmentDAO.joinCodeSelectByCodeTypeId(type.getCodeId());
	}

	public CodeDAO getCodeDAO() {
		return codeDAO;
	}

	public void setCodeDAO(CodeDAO codeDAO) {
		this.codeDAO = codeDAO;
	}

	public List<Department> getTopDepartments() throws DepartmentException {
		// 表示顶级部门
		List<Department> pojos = null;

		try {
			Code top = codeService.getAndCreateCodeByCodeValue(DepartmentCons
					.DEPARTMENT_TYPE_CODE_VALUE());

			Code type = getTypeCodeByCodeValue(DepartmentCons
					.DEPARTMENT_TYPE_CODE_VALUE());
			pojos = departmentDAO.selectDepartmentByParentId(top.getCodeId(),
					type.getCodeId());

			if (pojos != null)
				departmentDAO.cascadeSelect(pojos.toArray(new Department[] {}));

		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}

		return pojos;
	}

	public List<Department> getSubDepartments(long codeParentId)
			throws DepartmentException {
		Code type = getTypeCodeByCodeValue(DepartmentCons
				.DEPARTMENT_TYPE_CODE_VALUE());
		List<Department> pojos = departmentDAO.selectDepartmentByParentId(
				codeParentId, type.getCodeId());
		if (pojos != null)
			departmentDAO.cascadeSelect(pojos.toArray(new Department[] {}));

		return pojos;
	}

	public String getDepartmentDWZTree(List<Department> departments,
			String ulStyle, String ulOncheckHandler) throws DepartmentException {

		if (departments == null)
			departments = this.getTopDepartments();

		if (departments == null)
			throw new DepartmentException(
					DepartmentCons.DEPARTMENT_NOT_FOUND_MESS());

		if (ulStyle == null)
			ulStyle = "";
		if (ulOncheckHandler == null)
			ulOncheckHandler = "";

		StringBuilder ul = new StringBuilder();
		ul.append(String.format("<ul class='%s' oncheck='%s'>", ulStyle,
				ulOncheckHandler));
		for (Department depart : departments) {
			long parentId = depart.getCode().getCodeId();
			StringBuilder li = new StringBuilder();

			List<Department> children = getSubDepartments(parentId);

			if (children != null && children.size() > 0) {
				// 递归
				String subUl = getDepartmentDWZTree(children, null, null);

				li.append(String.format(format, "", depart.getDepartId(),
						"false", depart.getCode().getRemark(), subUl));

			} else {
				li.append(String.format(format, "departmentIds", depart
						.getDepartId(), "false", depart.getCode().getRemark(),
						""));
			}

			ul.append(li.toString());
		}

		ul.append("</ul>");

		return ul.toString();
	}

	private Code getTypeCodeByCodeValue(String codeValue)
			throws DepartmentException {
		Code code = null;
		try {
			code = codeService.getAndCreateCodeByCodeValue(codeValue);
		} catch (CodeException e) {
			throw new DepartmentException(DepartmentCons.CODE_DATA_OP_EXP(), e);
		}

		return code;
	}

}
