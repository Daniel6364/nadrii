package com.yagn.nadrii.web.planner;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Planner;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.planner.PlannerService;

@Controller
@RequestMapping("/planner/*")
public class PlannerController {
	
	///Field
	@Autowired
	@Qualifier("plannerServiceImpl")
	private PlannerService plannerService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public PlannerController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping("addPlannerView")
	public String addPlannerView(User user, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/addPlannerView 접속");
		//session 에 저장된 userId 값을 가져옴
		user = (User)session.getAttribute("loginUser");
		System.out.println("user :: "+ user);
				
		//user가 접속했는지 확인
		if(user == null) {
			System.out.println("로그인한 회원만 사용가능합니다.");
			return "forward:/user/loginView.jsp";
		}

		//일반적으로 접속시 상태를 세팅 --> 업데이트 접속과 구분
		request.setAttribute("status", "normal");
		
		System.out.println("addPlannerView로 이동");
		
		return "forward:../planner/addPlannerView.jsp";
	}
	
	@RequestMapping(value="addPlanner", method=RequestMethod.POST)
	public String addPlanner(@ModelAttribute("planner")Planner planner, User user, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/addPlanner 접속");
		
		user = (User)session.getAttribute("loginUser");
		String plannerMakerId = user.getUserId();
		System.out.println("로그인한 유저 ID : "+plannerMakerId);
		
		planner.setFlag("pl");
		planner.setUser(user);
		
		
		//////////////// 캡쳐 기능 //////////////////
		
		System.out.println("capture controller 접속");
        String binaryData = request.getParameter("imgSrc");
        FileOutputStream stream = null;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");        

		System.out.println("binary file :: " + binaryData);
		if (binaryData == null || binaryData == "") {
			System.out.println("데이터가 null");
			throw new Exception();
		}

		binaryData = binaryData.replaceAll("data:image/png;base64,", "");
		byte[] file = Base64.decodeBase64(binaryData);
		System.out.println("file  :: " + file + " || " + file.length);
		String fileName = UUID.randomUUID().toString();

		stream = new FileOutputStream("C:/Users/bitcamp/git/NadriProject/nadrii/WebContent/resources/images/planner/thumbnail/" + fileName + ".png");
		System.out.println("file이름 : " + fileName);

		stream.write(file);
		stream.close();
		System.out.println("파일 작성 완료");
		mav.addObject("msg", "ok");
		
		/////////////// 캡쳐 기능 ///////////////
		
		planner.setPhoto("../resources/images/planner/thumbnail/" + fileName + ".png");
		
		System.out.println("planner :: "+planner);
		
		plannerService.addPlanner(planner);
		
		System.out.println("PlannerController/addPlanner 접속완료");
		
		return "redirect:/planner/getMyPlannerList";
	}
	
	@RequestMapping(value="getMyPlannerList")
	public String getMyPlannerList(@ModelAttribute("search")Search search, User user,
			HttpServletRequest request, HttpSession session) throws Exception{
		
		System.out.println("PlannerController/getMyPlannerList 접속");
		
		//session 에 저장된 userId 값을 가져옴
		user = (User)session.getAttribute("loginUser");
		System.out.println("user :: "+ user);
		
		//user가 접속했는지 확인
		if(user == null) {
			System.out.println("로그인한 회원만 사용가능합니다.");
			return "forward:/user/loginView.jsp";
		}
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		System.out.println("currentPage :: " + search.getCurrentPage());
		System.out.println("pageSize :: " + pageSize);
		System.out.println("pageUnit ::" + pageUnit);
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		search.setSearchCondition("0");
		System.out.println(search);
		
		String plannerMakerId = user.getUserId();
		System.out.println("UserId :: " + plannerMakerId);
		
		Map<String , Object> map = plannerService.getMyPlannerList(search, plannerMakerId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("Page :: "+resultPage);
		System.out.println("list ::" +map.get("list"));
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		System.out.println("PlannerController/getMyPlannerList 접속완료");
		
		return "forward:/planner/listMyPlanner.jsp";
	}
	
	@RequestMapping(value="getUserPlannerList")
	public String getUserPlannerList(@ModelAttribute("search")Search search, String plannerMakerId, 
			HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/getUserPlannerList 접속");
		
		plannerMakerId = null;
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		System.out.println("currentPage :: " + search.getCurrentPage());
		System.out.println("pageSize :: " + pageSize);
		System.out.println("pageUnit ::" + pageUnit);
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		search.setSearchCondition("0");
		System.out.println(search);
		
		Map<String , Object> map = plannerService.getUserPlannerList(search, plannerMakerId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("Page :: "+resultPage);
		System.out.println("list ::" +map.get("list"));
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		System.out.println("PlannerController/getUserPlannerList 접속완료");
		
		return "forward:/planner/listUserPlanner.jsp";
	}
	
	@RequestMapping(value="getPlanner", method=RequestMethod.GET)
	public String getPlanner(@RequestParam("postNo")int postNo, User user ,HttpServletRequest request, HttpSession session) throws Exception{
		
		//login유저만 접근 가능하게
		if(session.getAttribute("loginUser") == null) {
			System.out.println("로그인한 회원만 사용가능합니다.");
			return "forward:/user/loginView.jsp";
		}
		
		System.out.println("PlannerController/getPlanner 접속");
		System.out.println("postNo :: "+postNo);
		Planner planner = plannerService.getPlanner(postNo);
		
		//뷰카운트 증가
		plannerService.updateViewCount(postNo);
		System.out.println(planner);
		
		//db에서 가져온 x,y좌표를 파싱하여 저장
		String[] lat = planner.getLat().split(",");
		String[] lng = planner.getLng().split(",");
		
		int latLength = lat.length;
		
		System.out.println("lat :: "+lat);
		System.out.println("lng :: "+lng);
		
		user = (User)session.getAttribute("loginUser");
		String userId = user.getUserId();
		
		request.setAttribute("userId", userId);
		request.setAttribute("planner", planner);
		request.setAttribute("lat", lat);
		request.setAttribute("lng", lng);
		request.setAttribute("latLength", latLength);
		
		
		System.out.println("PlannerController/getPlanner 접속완료");
		
		return "forward:/planner/getPlanner.jsp";
	}
	
	@RequestMapping(value="updatePlanner", method=RequestMethod.GET)
	public String updatePlanner(@RequestParam("postNo")int postNo, HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/updatePlanner planner정보 받아오기 접속");
		
		Planner planner = plannerService.getPlanner(postNo);
		System.out.println(planner);
		
		//db에서 가져온 x,y좌표를 파싱하여 저장
		String[] lat = planner.getLat().split(",");
		String[] lng = planner.getLng().split(",");
				
		int latLength = lat.length;
		
		request.setAttribute("planner", planner);
		//업데이트 접속시 상태값 세팅 --> 일반접속과 구분
		request.setAttribute("status", "update");
		//DB정보를 파싱하여 lat, lng값을 배열로 전달
		request.setAttribute("dbLat", lat);
		request.setAttribute("dbLng", lng);
		request.setAttribute("dbLatLength", latLength);
		
		System.out.println("PlannerController/updatePlanner planner정보 받아오기 완료");
		
		return "forward:../planner/addPlannerView.jsp";
	}
	
	@RequestMapping(value="updatePlanner", method=RequestMethod.POST)
	public String updatePlanner(@ModelAttribute("planner")Planner planner,@RequestParam("postNo")int postNo, 
			User user, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/updatePlanner planner정보 수정 접속");
		
		user = (User)session.getAttribute("loginUser");
		String plannerMakerId = user.getUserId();
		System.out.println("로그인한 유저 ID : "+plannerMakerId);

		//////////////// 캡쳐 기능 //////////////////

		System.out.println("capture controller 접속");
		String binaryData = request.getParameter("imgSrc");
		FileOutputStream stream = null;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");

		System.out.println("binary file :: " + binaryData);
		if (binaryData == null || binaryData == "") {
			System.out.println("데이터가 null");
			throw new Exception();
		}
		
		binaryData = binaryData.replaceAll("data:image/png;base64,", "");
		byte[] file = Base64.decodeBase64(binaryData);
		System.out.println("file  :: " + file + " || " + file.length);
		String fileName = UUID.randomUUID().toString();

		stream = new FileOutputStream("C:/Users/bitcamp/git/NadriProject/nadrii/WebContent/resources/images/planner/thumbnail/" + fileName + ".png");
		System.out.println("file이름 : " + fileName);

		stream.write(file);
		stream.close();
		System.out.println("파일 작성 완료");
		mav.addObject("msg", "ok");

		/////////////// 캡쳐 기능 ///////////////
		
		planner.setPostNo(postNo);
		planner.setFlag("pl");
		planner.setUser(user);
		planner.setPhoto("../resources/images/planner/thumbnail/" + fileName + ".png");
		
		System.out.println(planner);
		
		plannerService.updatePlanner(planner);
		
		System.out.println("PlannerController/updatePlanner planner정보 수정 완료");
		
		return "redirect:/planner/getMyPlannerList";
	}
	
	@RequestMapping(value="deletePlanner", method=RequestMethod.GET)
	public String deletePlanner(@RequestParam("postNo")int postNo, HttpServletRequest request) throws Exception{
		
		System.out.println("PlannerController/deletePlanner 접속");
		
		plannerService.deletePlanner(postNo);
		
		System.out.println("PlannerController/deletePlanner 완료");
		
		return "forward:/planner/getMyPlannerList";
	}

}