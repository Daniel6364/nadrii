package com.yagn.nadrii.service.comm;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Community;

public interface CommService {
	public int addComm(Community community);
	public Community getComm(int postNo);
	public Map<String, Object> listComm(Search search);
	public void updateComm(Community community); 
	public void deleteComm(int postNo);
	public List<String> listHasgTag(String fileName) throws Exception;
	public List<String> getHashtags(String keyword)throws Exception;
}
