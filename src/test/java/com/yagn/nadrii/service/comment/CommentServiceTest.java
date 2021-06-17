//package com.yagn.nadrii.service.comment;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yagn.nadrii.service.common.CommentService;
//import com.yagn.nadrii.service.domain.Comments;
//
//
///*
// *	FileName :  UserServiceTest.java
// * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
// * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
// * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
// * �� @ContextConfiguration : Meta-data location ����
// * �� @Test : �׽�Ʈ ���� �ҽ� ����
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//
////==> Meta-Data �� �پ��ϰ� Wiring ����...
////@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
//@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
//										"classpath:config/context-aspect.xml",
//										"classpath:config/context-mybatis.xml",
//										"classpath:config/context-transaction.xml" })
////@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
//public class CommentServiceTest{
//	
//	@Autowired
//	@Qualifier("commentServiceImpl")
//	private CommentService commentService;
//	private Comments comment;
//
//	//@Test
//	public void testAddUser() throws Exception {
//		comment = new Comments();
//		comment.setPostNo(30003);
//		comment.setText("�����մϴ�~");
//		comment.setUserId("test01");
////		List<Comments> listComment = commentService.addComment(comment);
////		System.out.println(listComment);
//	}
//	
//	@Test
//	public void testGetUser() throws Exception {
////		List<Comments> comm = commentService.listCommentById("test01");
//		System.out.println(comm);
//		
//	}
//	
//	//@Test
//	 public void testUpdateUser() throws Exception{
//		Comments com = new Comments();
//		com.setCommentNo(10011);
//		com.setText("�����մϴ�~!");
//		
//		commentService.updateComment(com);
//		
//		Map<String, Object> comments = commentService.listCommentByPost(30003);
//		System.out.println(comments.get("totalCount")+" , "+comments.get("listComment"));
//		
//	 }
//	 
//	 //==>  �ּ��� Ǯ�� �����ϸ�....
//	 //@Test
//	 public void testGetUserListAll() throws Exception{
//		 Map<String, Object> comments = commentService.listCommentByPost(30003);
//		 
//		 System.out.println(comments.get("totalCount")+" , "+comments.get("listComment"));
//	 }
//	 
//	 //@Test
//	 public void testGetUserListByUserId() throws Exception{
//		 
//	 }
//	 
//	 //@Test
//	 public void testGetUserListByUserName() throws Exception{
//		 
//	 }	 
//
//}