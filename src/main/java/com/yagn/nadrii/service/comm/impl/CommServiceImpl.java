package com.yagn.nadrii.service.comm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.comm.CommDao;
import com.yagn.nadrii.service.comm.CommOpenAPIDao;
import com.yagn.nadrii.service.comm.CommService;
import com.yagn.nadrii.service.domain.Community;

@Service("commServiceImpl")
public class CommServiceImpl implements CommService {

	@Autowired
	@Qualifier("commDaoImpl")
	private CommDao commDao;
	@Autowired
	@Qualifier("commOpenAPIDaoImpl")
	private CommOpenAPIDao openAPIDao;

	@Override
	public int addComm(Community community) {
		return commDao.addComm(community);
	}

	@Override
	public Community getComm(int postNo) {
		Community community = commDao.getComm(postNo);
		community.setViewCount(community.getViewCount()+1);
		commDao.updateViewCount(community);
		return community;
	}

	@Override
	public Map<String, Object> listComm(Search search) {
		List<Community> list = commDao.listComm(search);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int totalCount = commDao.getTotalCount(search);
		resultMap.put("listComm", list);
		resultMap.put("search", search);
		resultMap.put("totalCount", totalCount);
		return resultMap;
	}

	@Override
	public void updateComm(Community community) {
		commDao.updateComm(community);
	}

	@Override
	public void deleteComm(int postNo) {
		commDao.deleteComm(postNo);
	}

	@Override
	public List<String> listHasgTag(String filePath) throws Exception{
		String cloudVisionResult = openAPIDao.getImageResult(filePath);
		JSONObject translatedResult = openAPIDao.getTranslatedResult(cloudVisionResult);
		String[] temp = translatedResult.get("translatedText").toString().split(",");
		List<String> result = new ArrayList<>();
		for(int i=0; i<temp.length;i++) {
			temp[i] = CommServiceImpl.trim(temp[i]);
			System.out.println(temp[i]);
			System.out.println(result.toString());
			if(result.toString().indexOf(temp[i]) == -1) {
				result.add(CommServiceImpl.trim(temp[i]));
			}
		}
		return result;
	}
	
	public static String trim(String str) {
		String[] temp = str.split(" ");
		str="";
		for(int i=0;i<temp.length;i++) {
				str+=temp[i];
		}
		return str;
	}
	
	@Override
	public List<String> getHashtags(String keyword) throws Exception {
		List<String> tags = commDao.getHashtags(keyword);
		List<String> result = new ArrayList<>();
		for(int i=0;i<tags.size();i++) {
			String[] temp = tags.get(i).split(",");
			for(int n=1;n<temp.length-1;n++) {
				if(result.toString().indexOf(temp[n]) == -1) {
					result.add(temp[n]);
				}
			}
		}
		System.out.println(result);
		return result;
	}

}
