package com.yagn.nadrii.web.ticket;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yagn.nadrii.common.OpenApiPage;
import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.SearchFestival;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.TourTicket;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.ticket.TicketService;

// [행사정보 조회] 
@Controller
@RequestMapping("/ticket/*")
public class TicketController {

	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;
	
	/// Constructor
	public TicketController() {
			System.out.println(this.getClass());
		}
	
	/// Page process
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "listTicket")
	public String listTicket(
			@ModelAttribute("openApiSearch") OpenApiSearch openApiSearch,
			@RequestParam(value="searchCondition", required=false, defaultValue="") String searchCondition,
			Map<String, Object>	map
			) {
		
		System.out.println("\n /ticket/listTicket : GET / POST");
		System.out.println("\n[openApiSearch domain check] ==> " + openApiSearch.toString());
		System.out.println(searchCondition);
		
		OpenApiPage resultPage = new OpenApiPage();
		Map<String, Object> returnMap = new HashMap<>();
		
		try {

			if (openApiSearch.getPageNo() == 0) {
				openApiSearch.setPageNo(1);
			}
			openApiSearch.setNumOfRows(pageSize);

			if (openApiSearch.getSearchCondition() == null) {
				openApiSearch.setSearchCondition("B");
			} else {
				openApiSearch.setSearchCondition(searchCondition);
			}
			
			returnMap = ticketService.getTicketList(openApiSearch);
			
			resultPage = new OpenApiPage(openApiSearch.getPageNo(), ((Integer) returnMap.get("totalCount")).intValue(),
					pageUnit, pageSize);

			System.out.println("[resultPage]" + resultPage);

		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("search", openApiSearch);
		map.put("resultPage", resultPage);
		map.put("tourTicket", returnMap.get("tourTicketList"));
		
		return "forward:/ticket/listTicket.jsp";
	}
	
	
	@RequestMapping(value = "getTicket", method = RequestMethod.GET)
	public String getTicket(
		@RequestParam("firstimage") String firstimage,	
		@RequestParam("contentId") int contentId,	
		@RequestParam("contentTypeId") int contentTypeId,
		@RequestParam("title") String encodeTitle,
		HttpSession session,
		Map<String, Object> map
			) {
		
		System.out.println("\n /ticket/getTicket : GET");
		
		DetailIntro detailIntro = new DetailIntro();
		DetailImage detailImage = new DetailImage();
		TourTicket tourTicket = new TourTicket();
		User user = new User();
		
		Map<String, Object> returnMap = new HashMap<>();
		OpenApiSearch openApiSearch = new OpenApiSearch();
		SearchFestival searchFestival = new SearchFestival();
		Ticket ticket = new Ticket();
		
		try {

			String decodeTitle = URLDecoder.decode(encodeTitle, "UTF-8");

			detailIntro = ticketService.getTicket(contentId, contentTypeId);
			detailImage = ticketService.getDetailImage(tourTicket.getContentid(), decodeTitle + " 2018");

			System.out.println("\n\n[entrance Fee check] ==> " + detailIntro.getUsetimefestival());
			
			tourTicket = new TourTicket();
			tourTicket.setFirstimage(firstimage);
			tourTicket.setTitle(decodeTitle);
			tourTicket.setContentid(contentId);
			tourTicket.setContenttypeid(contentTypeId);
			
			user = (User) session.getAttribute("loginUser");
			
			System.out.println("\n\n[1]==> " + detailIntro.toString());
			System.out.println("\n\n[2]==> " + detailImage.toString());
			System.out.println("\n\n[3]==> " + tourTicket.toString());
			System.out.println("\n\n[4]==> " + searchFestival.toString());
			
			openApiSearch.setSearchKeyword(tourTicket.getTitle());
			System.out.println("\n[openApiSearch Domain Check]==>" + openApiSearch.toString());
			returnMap = ticketService.getSearchTicket(openApiSearch);
			
			System.out.println("\n\n[6]==> " + returnMap.get("TicketDomain") );
			
			ticket = (Ticket) returnMap.get("TicketDomain"); 
			ticket.setContentid(contentId);
			System.out.println("\n\n[7]==> " + ticket.toString() );
			
			ticketService.addTicketLog(ticket);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("user", user);
		map.put("detailIntro", detailIntro);
		map.put("detailImage", detailImage);
		map.put("tourTicket", tourTicket);
		
		return "forward:/ticket/getTicket.jsp";
	}
	
	@RequestMapping(value = "addBooking", method = RequestMethod.POST)
	public String addBooking (
			@RequestParam("bookingDate") String bookingDate,
			@ModelAttribute("tourTicket") TourTicket tourTicket,
			@ModelAttribute("detailIntro") DetailIntro detailIntro,
			@ModelAttribute("detailImage") DetailImage detailImage,
			HttpSession session,
			Map<String, Object> map
			) {
		
		System.out.println("\n /ticket/addBooking : POST");
		
		System.out.println("\n[예매일자 확인]==>" + bookingDate);
		System.out.println("\n[tourTicket 확인]==>" + tourTicket.toString());
		System.out.println("\n[detailImage 확인]==>" + detailImage.toString());
		System.out.println("\n[detailIntro 확인]==>" + detailIntro.toString());
		
		String priceInfo = detailIntro.getUsetimefestival();
		
		User user = new User();
		
		Set<String> set = new HashSet<>();
		
		
		
		try {
			
			List<String> priceList = ticketService.getTicketPrice(priceInfo);
			List<String> sendPriceList = new ArrayList<>();
			
			for (int i = 0; i < priceList.size(); i++) {
				System.out.println("[리턴 값 확인]==>"+priceList.get(i));
				set.add(priceList.get(i));		
			}
			
			System.out.println("[set size()]" + set.size());
			System.out.println("[set toString()]" + set.toString());
			
			Object[] obj = set.toArray();
			for (int i = 0; i < obj.length; i++) {
				sendPriceList.add((String) obj[i]);
			}
			System.out.println("[sendPriceList.toString()]" + sendPriceList.toString());
			
			tourTicket.setUsetimefestival(sendPriceList);
			System.out.println("\n[tourTicket 도메인 확인]==>" + tourTicket.getUsetimefestival());
			
			user = (User) session.getAttribute("loginUser");
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("user", user);
		map.put("bookingDate", bookingDate);
		map.put("priceList", tourTicket.getUsetimefestival());
		
		return "forward:/ticket/addBookingView.jsp";
	}
	
	@RequestMapping(value = "getMyTicket", method = RequestMethod.GET)
	public String getMyTicket (
			HttpSession session,
			Map<String, Object> map
			) {
		
		System.out.println("\n /ticket/getMyTicket : GET");
		
		User user = new User();
		System.out.println(session.toString());
		
		try {
			
			user = (User) session.getAttribute("loginUser");
			System.out.println(user.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		

		map.put("user", user);
		
		return "forward:/ticket/getMyTicket.jsp";
	}
			
	
	
} // end of class
