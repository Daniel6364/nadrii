package com.yagn.nadrii.service.join.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;
import com.yagn.nadrii.service.join.JoinDao;
import com.yagn.nadrii.service.join.JoinService;;


//==> ȸ������ ���� ����
@Service("joinServiceImpl")
public class JoinServiceImpl implements JoinService{
	
	///Field
	@Autowired
	@Qualifier("joinDaoImpl")
	private JoinDao joinDao;
	public void setJoinDao(JoinDao joinDao) {
		this.joinDao = joinDao;
	}
	
	///Constructor
	public JoinServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addJoin(Join join) throws Exception {
		joinDao.addJoin(join);
	}
	
	public String getGroupMaster(int groupNo) throws Exception{
		return joinDao.getGroupMaster(groupNo);
	}
	
	public Map<String, Object> getJoinMemberList(Search search) throws Exception {
		
		List<Join> list= joinDao.getJoinMemberList(search);
		int totalCount = joinDao.getTotalMemberCount(search); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println(map);
		
		return map;
	}

	public Map<String , Object > getJoinList(Search search) throws Exception {
		List<Join> list= joinDao.getJoinList(search);
		int totalCount = joinDao.getTotalJoinCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println(map);
		
		return map;
	}

	public void deleteJoin(Join join) throws Exception {
		joinDao.deleteJoin(join);
	}
}