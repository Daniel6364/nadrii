package com.yagn.nadrii.service.planner.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Planner;
import com.yagn.nadrii.service.planner.PlannerDao;

@Repository
public class PlannerDaoImpl implements PlannerDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PlannerDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	@Override
	public void addPlanner(Planner planner) throws Exception {
		System.out.println("PlannerDao/addPlanner 접속");
		sqlSession.insert("PlannerMapper.addPlanner", planner);
		System.out.println("PlannerDao/addPlanner 수행완료");
	}
	
	@Override
	public List<Planner> getMyPlannerList(Search	search, String plannerMakerId) throws Exception{
		System.out.println("PlannerDao/getMyPlannerList 접속");
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("search", search);
		map.put("plannerMakerId", plannerMakerId);
		
		return sqlSession.selectList("PlannerMapper.listPlanner", map);
	}

	@Override
	public List<Planner> getUserPlannerList(Search search, String plannerMakerId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerDao/getUserPlannerList 접속");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("plannerMakerId", plannerMakerId);
		
		return sqlSession.selectList("PlannerMapper.listPlanner", map);
	}

	@Override
	public Planner getPlanner(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerDao/getPlanner 접속");
		return sqlSession.selectOne("PlannerMapper.getPlanner", postNo);
	}
	
	

	@Override
	public void updatePlanner(Planner planner) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("plannerDao/updatePlanner 접속");
		
		sqlSession.update("PlannerMapper.updatePlanner", planner);
		
		System.out.println("plannerDao/updatePlanner 완료");
	}

	@Override
	public int getTotalCount(Search search, String plannerMakerId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerDao/getTotalCount 접속");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("plannerMakerId", plannerMakerId);
		
		return sqlSession.selectOne("PlannerMapper.getTotalCount", map);
	}

	@Override
	public void deletePlanner(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerDao/deletePlanner 접속");
		
		sqlSession.delete("PlannerMapper.deletePlanner", postNo);
	}

	@Override
	public void updateViewCount(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("plannnerDao/updateViewCount 접속");
		
		sqlSession.update("PlannerMapper.updateViewCount", postNo);
	}
	
}