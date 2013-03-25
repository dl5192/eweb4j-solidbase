package org.eweb4j.solidbase.filecate.web;

import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eweb4j.cache.Props;
import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.filecate.model.FileCate;
import org.eweb4j.solidbase.filecate.model.FileCateException;
import org.eweb4j.solidbase.filecate.model.FileCateService;
import org.eweb4j.solidbase.filecate.model.FileCateServiceImpl;

@Path("file_cate")
public class FileCateControl {
	
	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private FileCateService service = new FileCateServiceImpl();
	private int pageNum = 1;
	private int numPerPage = 20;
	private FileCate fileCate = null;
	private Long id = null;
	private Long[] ids = null;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public void setFileCate(FileCate cate){
		this.fileCate = cate;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setIds(Long[] ids){
		this.ids = ids;
	}

	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {

		try {
			PageMod<FileCate> pageMod = service.getPage(pageNum, numPerPage);

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, pageMod.getAllCount() - 1, 8);
			SearchForm searchForm = new SearchForm("file_cate/list", "");
			ListPage listPage = new ListPage("file_cate", searchForm, pageMod.getPojos(), dpc);
			listPage = DataAssemUtil.assemHead(listPage, pageMod.getPojos(), Props.getMap("FileCateConstant"));

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:files/view/cate/list.jsp";
	}
	
	@Path("/new")
	@GET
	@POST
	public String doGet(Map<String, Object> model) {
		return "jsp:files/view/cate/add.jsp";
	}
	
	@Path("/")
	@POST
	public String doPost() {
		try {
			service.create(fileCate);
			return dwz.getSuccessJson("create ok", "xssywjfl", MVCConfigConstant.BASE_URL+"file_cate/list", "dialog", "文件分类管理").toString();
		} catch (FileCateException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}/edit")
	@GET
	@POST
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("pojo", service.get(id));
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:files/view/cate/edit.jsp";
	}
	
	@Path("/{id}")
	@PUT
	public String doPut() {
		try {
			fileCate.setId(id);
			service.update(fileCate);
			return dwz.getSuccessJson("update ok", "xssywjfl", MVCConfigConstant.BASE_URL+"file_cate/list", "dialog", "文件分类管理").toString();
		} catch (FileCateException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}")
	@DELETE
	public String doDelete() {
		try {
			service.remove(id);
			return dwz.getSuccessJson("delete ok", "xssywjfl", MVCConfigConstant.BASE_URL+"file_cate/list", "dialog", "文件分类管理").toString();
		} catch (FileCateException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/batchRemove")
	@DELETE
	public String doBatchDelete() {
		try {
			service.batchRemove(ids);
			return dwz.getSuccessJson("batch delete ok", "xssywjfl", MVCConfigConstant.BASE_URL+"file_cate/list", "dialog", "文件分类管理").toString();
		} catch (FileCateException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
}
