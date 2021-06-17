package com.yagn.nadrii.service.group.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Group;
import com.yagn.nadrii.service.group.GroupDao;
import com.yagn.nadrii.service.group.GroupService;;

@Service("groupServiceImpl")
public class GroupServiceImpl implements GroupService{
	
	///Field
	@Autowired
	@Qualifier("groupDaoImpl")
	private GroupDao groupDao;
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	///Constructor
	public GroupServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public int addGroup(Group group) throws Exception {
		return groupDao.addGroup(group);
	}

	public Group getGroup(int groupNo) throws Exception {
		return groupDao.getGroup(groupNo);
	}

	public Map<String , Object > listGroup(Search search) throws Exception {
		
		List<Group> list= groupDao.listGroup(search);
		int totalCount = groupDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void updateViewCount(int groupNo) throws Exception{
		Group group = groupDao.getGroup(groupNo);
		groupDao.updateViewCount(group);
	}

	public void updateGroup(Group group) throws Exception {
		groupDao.updateGroup(group);
	}
	
	public void deleteGroup(Group group) throws Exception {
		groupDao.deleteGroup(group);
	}

	@Override
	public String getThumbNail(String placeName) throws Exception {
		
        String text = URLEncoder.encode(placeName, "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/search/image?sort=sim&filter=medium&query="+text;        

        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", "ZmqsMjB1TiY_F52W19IG");
        con.setRequestProperty("X-Naver-Client-Secret", "D4ngLaXkoJ");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
        
        JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
        JSONArray itemsArray = (JSONArray)jsonobj.get("items");
        JSONObject items = (JSONObject)itemsArray.get(1);
        String link = (String) items.get("link");
  
        return link;
	}
}