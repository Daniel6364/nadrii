package com.yagn.nadrii.web.ticket;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yagn.nadrii.common.OpenApiPage;
import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.ticket.TicketService;

// [행사정보 조회] 
@Controller
@RequestMapping("/ticket/*")
public class TicketRestController {

	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;
	
	/// Constructor
	public TicketRestController() {
			System.out.println(this.getClass());
		}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "json/listTicket", method = RequestMethod.POST)
	public String listTicket(
			@RequestBody JSONObject searchCondition,
			@ModelAttribute("openApiSearch") OpenApiSearch openApiSearch,
			Model model
			) {
		
		System.out.println("\n /ticket/json/listTicket : GET / POST");

		try {
			if (openApiSearch.getPageNo() == 0) {
				openApiSearch.setPageNo(1);
			}
			openApiSearch.setNumOfRows(pageSize);
			
			JSONObject jsonObj = (JSONObject) JSONValue.parse(searchCondition.toJSONString()); 
			String searchConditionVal = (String) jsonObj.get("searchCondition");
			
			System.out.println("[searchConditionVal check]==>"+searchConditionVal);
			
			openApiSearch.setSearchCondition(searchConditionVal);

			Map<String, Object> map = ticketService.getTicketList(openApiSearch);

			System.out.println(map.get("tourTicketList"));

			OpenApiPage resultPage = new OpenApiPage(openApiSearch.getPageNo(),
					((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

			System.out.println("[resultPage]" + resultPage);

			model.addAttribute("tourTicket", map.get("tourTicketList"));
			model.addAttribute("resultPage", resultPage);

		} catch (Exception e) {
			System.out.println(e);
		}
//		return null;
		return "forward:/ticket/listTicket.jsp";
	}
} // end of class
