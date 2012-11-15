package org.eweb4j.solidbase.resource.web;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.MIMEType;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.resource.model.Resource;
import org.eweb4j.solidbase.resource.model.ResourceCons;
import org.eweb4j.solidbase.resource.model.ResourceException;
import org.eweb4j.solidbase.resource.model.ResourceService;

/**
 * 资源 控制器
 * 
 * @author weiwei
 * 
 */
@Path("${ResourceConstant.MODEL_NAME}")
public class ResourceController {

	/**
	 * 资源服务接口，从IOC容器实例化
	 */
	private ResourceService resourceService = IOC.getBean(ResourceCons.IOC_SERVICE_BEAN_ID());

	/**
	 * DWZ服务接口，从IOC容器实例化
	 */
	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	/**
	 * 资源实体，数据载体
	 */
	private Resource resource = null;

	private int pageNum = 1;

	private int numPerPage = 20;

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@Path("/")
	@POST
	public String doPOST() {
		try {
			resourceService.createResourceInfo(resource);
			return ResourceCons.DWZ_SUCCESS_JSON("添加资源操作成功!");
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	/**
	 * 访问添加资源页面
	 * 
	 * @param request
	 * @return
	 */
	@Path("/new")
	@POST
	@GET
	public String doNew(Map model) {
		model.put("openType", ResourceCons.OPEN_TYPE());
		model.put("model", ResourceCons.MODEL_NAME());

		return ResourceCons.NEW_ACTION_RESULT();
	}

	/**
	 * 修改某资源信息
	 * 
	 * @param resId
	 * @return
	 */
	@Path("/{resId}")
	@PUT
	public String doPUT(@PathParam("resId") long resId) {
		try {
			this.resourceService.updateResourceInfo(resource);
			return ResourceCons.DWZ_SUCCESS_JSON("更新资源信息成功!");
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@Path("/{resId}/edit")
	@GET
	@POST
	public String doEdit(@PathParam("resId") long resId, Map model) {
		try {
			model.put("openType", ResourceCons.OPEN_TYPE());
			model.put("editPage", resourceService.getEditPage(resId));

			return ResourceCons.EDIT_ACTION_RESULT();
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@Path("/{resId}")
	@DELETE
	public String doDELETE(@PathParam("resId") long resId) {
		try {
			resourceService.removeResourceInfo(resId);
			return ResourceCons.DWZ_SUCCESS_JSON("删除资源信息成功!");
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@Path("/batchRemove")
	@DELETE
	public String doDELETE(@QueryParam("ids") long[] ids) {
		try {
			resourceService.batchRemoveResourceInfo(ids);
			return ResourceCons.DWZ_SUCCESS_JSON("删除资源信息成功!");
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	/**
	 * 获取所有资源，并且渲染成JSON格式
	 * 
	 * @return
	 */
	@Path("/json")
	@GET
	@POST
	@Produces(MIMEType.JSON)
	public Object doGetResJson() {
		try {
			return resourceService.getAllResource();
		} catch (ResourceException e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@Path("/list")
	@GET
	@POST
	public String doList(Map model) {
		try {
			PageMod<Resource> pageMod = resourceService.getPageDepartInfo(pageNum, numPerPage);
			long allCount = pageMod.getAllCount();
			List<Resource> pojos = pageMod.getPojos();

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage,
					allCount - 1, 8);

			SearchForm searchForm = new SearchForm(ResourceCons.MODEL_NAME() + "/list", "");

			ListPage listPage = new ListPage(ResourceCons.MODEL_NAME(), searchForm, pojos, dpc);
			listPage = DataAssemUtil.assemHead(listPage, pojos, ResourceCons.getMap());

			model.put("random", Math.random());
			model.put("listPage", listPage);

			return ResourceCons.PAGING_ACTION_RESULT();
		} catch (ResourceException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.toString()).toString();
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}
