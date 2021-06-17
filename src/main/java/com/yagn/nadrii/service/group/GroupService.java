package com.yagn.nadrii.service.group;

import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Group;
  
public interface GroupService {
	
	public int addGroup(Group group) throws Exception;
	
	public Group getGroup(int groupNo) throws Exception;
	 
	public Map<String , Object> listGroup(Search search) throws Exception;
	
	public void updateViewCount(int groupNo) throws Exception;
	
	public void updateGroup(Group group) throws Exception;
	
	public void deleteGroup(Group group) throws Exception;
	
	public String getThumbNail(String placeName) throws Exception;
	
}