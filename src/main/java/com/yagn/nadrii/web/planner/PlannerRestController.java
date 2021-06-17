package com.yagn.nadrii.web.planner;

import com.yagn.nadrii.service.planner.PlannerService;

//@RestController
//@RequestMapping("/planner/*")
public class PlannerRestController {
	
	///Field
//	@Autowired
//	@Qualifier("plannerServiceImpl")
	private PlannerService plannerService;
	
	public PlannerRestController(){
		System.out.println(this.getClass());
	}

}