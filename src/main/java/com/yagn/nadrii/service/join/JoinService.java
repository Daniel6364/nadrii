package com.yagn.nadrii.service.join;

import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;
  
public interface JoinService {
	
	public void addJoin(Join join) throws Exception;
	
	public Map<String, Object> getJoinMemberList(Search search) throws Exception;
	
	public String getGroupMaster(int groupNo) throws Exception;
	 
	public Map<String , Object> getJoinList(Search search) throws Exception;
	
	public void deleteJoin(Join join) throws Exception;
}