package com.yagn.nadrii.service.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.common.CommentDao;
import com.yagn.nadrii.service.domain.Comments;

@Repository("commentDaoImpl")
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public void addComment(Comments comment) throws Exception {
		sqlSession.insert("CommentMapper.addComment", comment);
	}

	@Override
	public List<Comments> listCommentByPost(int postNo) throws Exception {
		return sqlSession.selectList("CommentMapper.listCommentByPost", postNo);
	}

	@Override
	public List<Comments> listCommentById(Search search) throws Exception {
		return sqlSession.selectList("CommentMapper.listCommentById", search);
	}

	@Override
	public void updateComment(Comments comment) throws Exception {
		sqlSession.update("CommentMapper.updateComment", comment);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		sqlSession.delete("CommentMapper.deleteComment", commentNo);
	}

	@Override
	public int getTotalCount(int postNo) throws Exception {
		return sqlSession.selectOne("CommentMapper.getTotalCount_postNo", postNo);
	}
	
	@Override
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("CommentMapper.getTotalCount_Id", userId);
	}

	@Override
	public int getCommentNo(Comments comment) throws Exception {
		return sqlSession.selectOne("CommentMapper.getCommentNo", comment);
	}
	
	@Override
	public Comments getComment(int commentNo) throws Exception {
		return sqlSession.selectOne("CommentMapper.getComment", commentNo);
	}
	
	@Override
	public void deleteCommentByPost(int postNo) throws Exception {
		sqlSession.delete("CommentMapper.deleteCommentByPost", postNo);
	}

	@Override
	public String listTitle(int postNo) throws Exception {
		String title = sqlSession.selectOne("CommentMapper.listTitles_post", postNo);
		if(title == null) {
			title = sqlSession.selectOne("CommentMapper.listTitles_groups", postNo);
		}
		return title;
	}
	
}
