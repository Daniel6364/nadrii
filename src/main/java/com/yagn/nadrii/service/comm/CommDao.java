package com.yagn.nadrii.service.comm;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Community;

public interface CommDao {
	public int addComm(Community community);
	public Community getComm(int postNo);
	public List<Community> listComm(Search search);
	public void updateComm(Community community);
	public void deleteComm(int postNo);
	public int getTotalCount(Search search);
	public List<String> getHashtags(String keyword);
	public void updateViewCount(Community community);
}
