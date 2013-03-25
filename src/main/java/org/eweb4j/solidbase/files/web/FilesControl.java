package org.eweb4j.solidbase.files.web;

import java.io.File;
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
import org.eweb4j.mvc.upload.UploadFile;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.filecate.model.FileCateException;
import org.eweb4j.solidbase.filecate.model.FileCateService;
import org.eweb4j.solidbase.filecate.model.FileCateServiceImpl;
import org.eweb4j.solidbase.files.model.Files;
import org.eweb4j.solidbase.files.model.FilesException;
import org.eweb4j.solidbase.files.model.FilesService;
import org.eweb4j.solidbase.files.model.FilesServiceImpl;
import org.eweb4j.util.CommonUtil;

@Path("/files")
public class FilesControl {

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private FilesService service = new FilesServiceImpl();
	private FileCateService cateService = new FileCateServiceImpl();
	private int pageNum = 1;
	private int numPerPage = 20;
	private Files files = null;
	private Long id = null;
	private Long[] ids = null;
	private UploadFile uploadFile = null;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public void setFiles(Files files){
		this.files = files;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setIds(Long[] ids){
		this.ids = ids;
	}
	
	public void setUploadFile(UploadFile file){
		this.uploadFile = file;
	}

	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {
		try {
			PageMod<Files> pageMod = service.getPage(pageNum, numPerPage);

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, pageMod.getAllCount() - 1, 8);
			SearchForm searchForm = new SearchForm("files/list", "");
			ListPage listPage = new ListPage("files", searchForm, pageMod.getPojos(), dpc);
			listPage = DataAssemUtil.assemHead(listPage, pageMod.getPojos(), Props.getMap("FilesConstant"));

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:files/view/list.jsp";
	}
	
	@Path("/new")
	@GET
	@POST
	public String doGet(Map<String, Object> model) {
		try {
			model.put("cates", cateService.getAll());
		} catch (FileCateException e) {
			e.printStackTrace();
		}
		return "jsp:files/view/add.jsp";
		
	}
	
	@Path("/upload")
	@POST
	public String doUpload() {
		String fmt = "<script>parent.upload_callback('%s', %s);</script>";
		try {
			File file = null;
			if (uploadFile != null) {
				file = uploadFile.getTmpFile();
				if (files == null)
					files = new Files();
				
				files.setDisplayName(uploadFile.getFileName());
				files.setIntro(uploadFile.getFileName());
				files.setSize(uploadFile.getSize());
			}
			
			service.create(files, file);
			
			return String.format(fmt, "true", CommonUtil.toJson(files));
		} catch (FilesException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg == null || msg.trim().length() == 0)
				msg = "upload failed -> "+e.toString();
			
			return String.format(fmt, false, "'"+msg+"'");
		}
	}
	
	@Path("/")
	@POST
	public String doPost(){
		try {
			service.update(files);
			return dwz.getSuccessJson("create ok", "xssywj", MVCConfigConstant.BASE_URL+"files/list", "dialog", "文件库").toString();
		} catch (FilesException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}/edit")
	@GET
	@POST
	public String doEdit(Map<String, Object> model) {
		try {
			Files files = service.get(id);
			model.put("pojo", files);
			model.put("cates", cateService.getAll());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:files/view/edit.jsp";
	}
	
	@Path("/{id}")
	@PUT
	public String doPut() {
		try {
			files.setId(id);
			service.update(files);
			return dwz.getSuccessJson("update ok", "xssywj", MVCConfigConstant.BASE_URL+"files/list", "dialog", "文件库").toString();
		} catch (FilesException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}")
	@DELETE
	public String doDelete() {
		try {
			service.remove(id);
			return dwz.getSuccessJson("delete ok", "xssywj", MVCConfigConstant.BASE_URL+"files/list", "dialog", "文件库").toString();
		} catch (FilesException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/batchRemove")
	@DELETE
	public String doBatchDelete() {
		try {
			service.batchRemove(ids);
			return dwz.getSuccessJson("batch delete ok", "xssywj", MVCConfigConstant.BASE_URL+"files/list", "dialog", "文件库").toString();
		} catch (FilesException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
}
