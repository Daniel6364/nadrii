package com.yagn.nadrii.service.like.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Like;
import com.yagn.nadrii.service.like.LikeDao;
import com.yagn.nadrii.service.like.LikeService;;

@Service("likeServiceImpl")
public class LikeServiceImpl implements LikeService{
	
	///Field
	@Autowired
	@Qualifier("likeDaoImpl")
	private LikeDao likeDao;
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}
	
	///Constructor
	public LikeServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addLike(Like like) throws Exception {
		likeDao.addLike(like);
	}

	public void deleteLike(Like like) throws Exception {
		likeDao.deleteLike(like);
	}
	
	public Map<String , Object > listLikeById(Search search) throws Exception {
		
		List<Like> list= likeDao.listLikeById(search);
		int totalCount = likeDao.getTotalLikeById(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println(map);
		
		return map;
	}
	
	public Map<String , Object > listLikeByPost(Like like) throws Exception {
		
		List<Like> list= likeDao.listLikeByPost(like);
		int totalCount = likeDao.getTotalLikeByPost(like);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println(map);
		
		return map;
	}
}