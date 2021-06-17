package com.yagn.nadrii.service.common;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Comments;

public interface CommentDao {
	public void addComment(Comments comment) throws Exception;
	public List<Comments> listCommentByPost(int postNo)throws Exception;
	public List<Comments> listCommentById(Search search)throws Exception;
	public void updateComment(Comments comment)throws Exception;
	public void deleteComment(int commentNo)throws Exception;
	public int getTotalCount(int postNo)throws Exception;
	public int getTotalCount(String userId)throws Exception;
	public int getCommentNo(Comments comment)throws Exception;
	public Comments getComment(int commentNo)throws Exception;
	public void deleteCommentByPost(int postNo)throws Exception;
	public String listTitle(int postNo)throws Exception;
}