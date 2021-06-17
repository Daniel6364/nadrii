package com.yagn.nadrii.service.like;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Like;

public interface LikeDao {

	public void addLike(Like like) throws Exception ;

	public void deleteLike(Like like) throws Exception;
	
	public List<Like> listLikeById(Search search) throws Exception;

	public int getTotalLikeById(Search search) throws Exception;
	
	public List<Like> listLikeByPost(Like like) throws Exception;
	
	public int getTotalLikeByPost(Like like) throws Exception;
}