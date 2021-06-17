package com.yagn.nadrii.service.like;

import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Like;
  
public interface LikeService {
	
	public void addLike(Like like) throws Exception;
	 
	public void deleteLike(Like like) throws Exception;
	
	public Map<String , Object > listLikeById(Search search) throws Exception;
	
	public Map<String , Object > listLikeByPost(Like like) throws Exception;
}