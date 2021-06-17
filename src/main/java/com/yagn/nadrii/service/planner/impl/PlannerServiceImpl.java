package com.yagn.nadrii.service.planner.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Planner;
import com.yagn.nadrii.service.planner.PlannerDao;
import com.yagn.nadrii.service.planner.PlannerService;

@Service("plannerServiceImpl")
public class PlannerServiceImpl implements PlannerService{
	
	///Field
	@Autowired
	@Qualifier("plannerDaoImpl")
	private PlannerDao plannerDao;
	public void setPlannerDao(PlannerDao plannerDao) {
		this.plannerDao = plannerDao;
	}
	
	///Constructor
	public PlannerServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	@Override
	public void addPlanner(Planner planner) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerService/addPlanner 접속");
		plannerDao.addPlanner(planner);
		System.out.println("PlannerService/addPlanner 수행완료");
	}

	@Override
	public Map<String, Object> getMyPlannerList(Search search, String plannerMakerId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerService/getMyPlannerList 접속");
		
		List<Planner> list = plannerDao.getMyPlannerList(search, plannerMakerId);
		Map<String, Object> map = new HashMap<String, Object>();
		int totalCount = plannerDao.getTotalCount(search, plannerMakerId);
		
		map.put("list", list);
		map.put("search", search);
		map.put("totalCount", totalCount);
		
		System.out.println("PlannerService/getMyPlannerList 수행완료");
		
		return map;
	}

	@Override
	public Map<String, Object> getUserPlannerList(Search search, String plannerMakerId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerService/getUserPlannerList 접속");
		
		List<Planner> list = plannerDao.getUserPlannerList(search, plannerMakerId);
		Map<String, Object> map = new HashMap<String, Object>();
		int totalCount = plannerDao.getTotalCount(search, plannerMakerId);
		
		map.put("list", list);
		map.put("search", search);
		map.put("totalCount", totalCount);
		
		System.out.println("PlannerService/getUserPlannerList 수행완료");
		
		return map;
	}

	@Override
	public Planner getPlanner(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerService/getPlanner 접속");
		
		return plannerDao.getPlanner(postNo);
	}
	

	@Override
	public void updatePlanner(Planner planner) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("plannerService/updatePlanner 접속");
		
		plannerDao.updatePlanner(planner);
		
		System.out.println("plannerService/updatePlanner 완료");
	}

	@Override
	public void deletePlanner(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PlannerService/deletePlanner 접속");
		
		plannerDao.deletePlanner(postNo);
		
		System.out.println("PlannerService/deletePlanner 완료");
	}

	@Override
	public void updateViewCount(int postNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("plannnerService/updateViewCount 접속");
		
		plannerDao.updateViewCount(postNo);
		
		System.out.println("plannerService/updateViewCount 완료");
		
	}
	
}
