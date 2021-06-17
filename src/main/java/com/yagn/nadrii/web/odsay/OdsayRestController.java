package com.yagn.nadrii.web.odsay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.domain.odsay.outside.OBJ;
import com.yagn.nadrii.service.odsay.OdsayService;

@RestController
@RequestMapping("/odsay/*")
public class OdsayRestController {

	@Autowired
	@Qualifier("odsayServiceImpl")
	private OdsayService odsayService;

	public OdsayRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/getOBJ")
	public OBJ getOBJ(@RequestParam("sx") double sx, @RequestParam("sy") double sy, @RequestParam("ex") double ex, @RequestParam("ey") double ey, @RequestParam("flag") int flag) throws Exception {
			
		System.out.println("getOBJ");
		
		return odsayService.getOBJ(sx, sy, ex, ey, flag);
	}
	
	@RequestMapping(value="json/getInfo")
	public Map getInfo(@RequestParam("sx") double sx, @RequestParam("sy") double sy, @RequestParam("ex") double ex, @RequestParam("ey") double ey) throws Exception {
			
		System.out.println("getInfo");
		
		return odsayService.getInfo(sx, sy, ex, ey);
	}
	
	@RequestMapping(value="json/getGraph")
	public Map getGraph(String mapObj) throws Exception{
		
		System.out.println("getGraph");
		
		return odsayService.getGraph(mapObj);
	}
}
