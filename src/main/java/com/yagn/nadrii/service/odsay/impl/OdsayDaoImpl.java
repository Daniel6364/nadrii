package com.yagn.nadrii.service.odsay.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.service.domain.odsay.inside.Stations;
import com.yagn.nadrii.service.domain.odsay.outside.OBJ;
import com.yagn.nadrii.service.odsay.OdsayDao;

@Repository("odsayDaoImpl")
public class OdsayDaoImpl implements OdsayDao {

	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public OdsayDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public List getPathStationsList(double sx, double sy, double ex, double ey) throws Exception {
		
		HttpClient httpclient = new DefaultHttpClient();
		
		String url = "https://api.odsay.com/api/searchPubTransPath?apiKey=0ObaGjz7q8kLrzbsVutNT0qpRKpduNy7cnS9HDogmsk&lang=0";
		if(sx != 0 && sy != 0 && ex != 0 && ey != 0) {
			url+="&SX="+sx+"&SY="+sy+"&EX="+ex+"&EY="+ey;
		}
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
		
		HttpResponse res = httpclient.execute(httpGet);
		
		System.out.println(res);
		System.out.println();
		
		HttpEntity httpEntity = res.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		List list = new ArrayList();
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		JSONObject result = (JSONObject)jsonobj.get("result");
		JSONArray pathArray = (JSONArray)result.get("path");
		JSONObject path = (JSONObject)pathArray.get(0);
		JSONArray subPathArray = (JSONArray)path.get("subPath");
		for (int i = 0; i < subPathArray.size(); i++) {
			JSONObject subPath = (JSONObject)subPathArray.get(i);
	//		System.out.println("[6] subPath["+i+"] : "+subPath);
		}
		
		JSONObject subPath = (JSONObject)subPathArray.get(1);
		JSONObject passStopList = (JSONObject)subPath.get("passStopList");	
		JSONArray stationsArray = (JSONArray)passStopList.get("stations");
		for (int i = 0; i < stationsArray.size(); i++) {
			JSONObject stations = (JSONObject)stationsArray.get(i);
			System.out.println("[6] stations["+i+"] : "+stations);
			ObjectMapper objectMapper = new ObjectMapper();
			Stations odsayStations= new Stations();
			odsayStations = objectMapper.readValue(stations.toJSONString(), Stations.class);
			System.out.println("///////////");
			System.out.println(odsayStations);
			list.add(odsayStations);
		}

		return list;
	}
	
	public OBJ getOutTerminal(double sx, double sy, double ex, double ey) throws Exception {
		
		
		return null;
	}
	
}
