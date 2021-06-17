package com.yagn.nadrii.service.planner;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Planner;

public interface PlannerDao {

	public void addPlanner(Planner planner) throws Exception;
	
	public List<Planner> getMyPlannerList(Search search, String plannerMakerId) throws Exception;
	
	public List<Planner> getUserPlannerList(Search search, String plannerMakerId) throws Exception;
	
	public Planner getPlanner(int postNo) throws Exception;
	
	public void updatePlanner(Planner planner) throws Exception;
	
	public int getTotalCount(Search search, String plannerMakerId) throws Exception;
	
	public void deletePlanner(int postNo) throws Exception;
	
	public void updateViewCount(int postNo) throws Exception;
	
}