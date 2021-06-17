package com.yagn.nadrii.web.comm;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.comm.CommService;
import com.yagn.nadrii.service.domain.Community;

@RestController
@RequestMapping("/comm/*")
public class CommRestController {

	@Autowired
	@Qualifier("commServiceImpl")
	private CommService commService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("uploadImage")
	public JSONObject uploadImage(HttpServletRequest request) {
		String rootPath = request.getSession().getServletContext().getRealPath("/");  
	    String uploadPath = rootPath+"resources\\images\\uploadedImages\\";
		String fileName = "";
		
		int size = 10 * 1024 * 1024;
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			String file = (String) files.nextElement();
			fileName = multi.getFilesystemName(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		uploadPath += fileName;
		JSONObject jobj = new JSONObject();	
		String filePath = "../resources/images/uploadedImages/"+fileName;
		jobj.put("url", uploadPath);
		jobj.put("relativeUrl", filePath);
		return jobj;
	}

	@RequestMapping("listHashTag")
	public List<String> listHashTag(@RequestParam String filePath, HttpServletRequest request) throws Exception {
		List<String> hashTags = commService.listHasgTag(filePath);

		return hashTags;
		
	}
	
	@RequestMapping("getHashtags")
	public List<String> getHashTags(@RequestParam String keyword) throws Exception{
		return commService.getHashtags(keyword);
	}
	
	@RequestMapping("listComm/json")
	public Map<String, Object> listComm(@ModelAttribute("search") Search search,
							HttpServletRequest request) {
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		System.out.println(search);
		System.out.println("pagesize : "+pageSize);
		System.out.println("pageUnit :: "+pageUnit);
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		Map<String , Object> map=commService.listComm(search);
		List<Community> list = (List<Community>)map.get("listComm");
		for(int i=0;i<list.size();i++) {
			String[] temps = list.get(i).getHashtag().split(",");
			String temp ="";
			for(int j=1;j<temps.length;j++) {
				if(i == 1) {
					temp = "#"+temps[j];
				}else {
					temp += " #"+temps[j];
				}
			}
			list.get(i).setHashtag(temp);
		}
		map.put("resultSize", ((List)map.get("listComm")).size());
		map.put("search", search);
		return map;
	}
}
