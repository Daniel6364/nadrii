package com.yagn.nadrii.service.join;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;

public interface JoinDao {

	public void addJoin(Join join) throws Exception ;

	public List<Join> getJoinMemberList(Search search) throws Exception ;
	
	public int getTotalMemberCount(Search search) throws Exception;
	
	public String getGroupMaster(int groupNo) throws Exception;

	public List<Join> getJoinList(Search search) throws Exception ;

	public int getTotalJoinCount(Search search) throws Exception;
	
	public void deleteJoin(Join join) throws Exception;
	
}