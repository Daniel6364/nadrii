package com.yagn.nadrii.web.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.group.GroupService;

@RestController
@RequestMapping("/group/*")
public class GroupRestController {
	
	///Field
	@Autowired
	@Qualifier("groupServiceImpl")
	private GroupService groupService;
		
	public GroupRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@RequestMapping(value="json/getThumbNail",  method=RequestMethod.POST)
	public String getThumbNail(@RequestParam String placeName) throws Exception {
		System.out.println();
		System.out.println("@url@"+groupService.getThumbNail(placeName));
		System.out.println();
		return groupService.getThumbNail(placeName);		
	}
	
}