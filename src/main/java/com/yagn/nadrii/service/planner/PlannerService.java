package com.yagn.nadrii.service.planner;

import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Planner;

public interface PlannerService {

	public void addPlanner(Planner planner) throws Exception;
	
	public Map<String, Object> getMyPlannerList(Search search, String plannerMakerId) throws Exception;
	
	public Map<String, Object> getUserPlannerList(Search search, String plannerMakerId) throws Exception;
	
	public Planner getPlanner(int postNo) throws Exception;
	
	public void updatePlanner(Planner planner) throws Exception;
	
	public void deletePlanner(int postNo) throws Exception;
	
	public void updateViewCount(int postNo) throws Exception;
	
}