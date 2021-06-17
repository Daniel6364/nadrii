package com.yagn.nadrii.service.odsay.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yagn.nadrii.service.domain.odsay.GraphPos;
import com.yagn.nadrii.service.domain.odsay.inside.Info;
import com.yagn.nadrii.service.domain.odsay.inside.Lane;
import com.yagn.nadrii.service.domain.odsay.inside.SubPath;
import com.yagn.nadrii.service.domain.odsay.outside.OBJ;
import com.yagn.nadrii.service.odsay.OdsayDao;
import com.yagn.nadrii.service.odsay.OdsayService;

@Service("odsayServiceImpl")
public class OdsayServiceImpl implements OdsayService{

	@Autowired
	@Qualifier("odsayDaoImpl")
	private OdsayDao odsayDao;
	public void setOdsayDao(OdsayDao odsayDao) {
		this.odsayDao = odsayDao;
	}

	public OdsayServiceImpl() {
		System.out.println(this.getClass());
	}
	
	public Map getGraph(String mapObj) throws Exception {

		Map map = new HashMap();
		
		HttpClient httpclient = new DefaultHttpClient();
		
		String trimMapObj = mapObj.trim();
		
		System.out.println("mapObj :: "+trimMapObj);
		String url = "https://api.odsay.com/v1/api/loadLane?apiKey=0ObaGjz7q8kLrzbsVutNT0qpRKpduNy7cnS9HDogmsk&mapObject=0:0@"+trimMapObj;
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

		HttpResponse res = httpclient.execute(httpGet);

		System.out.println(res);
		System.out.println();

		HttpEntity httpEntity = res.getEntity();

		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		List listX = new ArrayList();
		List listY = new ArrayList();

		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		JSONArray errorArray = (JSONArray)jsonobj.get("error");
		
		if( errorArray != null) {
			
			System.out.println("@@@error@@@");
			System.out.println(errorArray);
			JSONObject error = (JSONObject)errorArray.get(0);
			System.out.println(error);
			map.put("error", error);
						
		}else {
			
			JSONObject result = (JSONObject)jsonobj.get("result");
			JSONObject boundary = (JSONObject)result.get("boundary");
			
			map.put("boundary", boundary);
			
			JSONArray laneArray = (JSONArray)result.get("lane");
			for (int i = 0; i < laneArray.size(); i++) {
				JSONObject lane = (JSONObject)laneArray.get(i);
				JSONArray sectionArray = (JSONArray)lane.get("section");
				for(int j = 0 ; j<sectionArray.size() ; j++) {
					JSONObject section = (JSONObject)sectionArray.get(j);
					JSONArray graphPosArray = (JSONArray)section.get("graphPos");
					for(int k=0 ; k <graphPosArray.size(); k++){ 
						JSONObject graphPos = (JSONObject)graphPosArray.get(k);
				
						ObjectMapper objectMapper = new ObjectMapper();
						GraphPos graphPos2= new GraphPos();
						graphPos2 = objectMapper.readValue(graphPos.toJSONString(), GraphPos.class);
						
						listX.add(graphPos2.getX());
						listY.add(graphPos2.getY());
					}
				}
			}
			
			map.put("listX", listX);
			map.put("listY", listY);
			
		}
		
		return map;
	}

	public Map getInfo(double sx, double sy, double ex, double ey){
		
		System.out.println("@ sx : "+sx);
		System.out.println("@ sy : "+sy);
		System.out.println("@ ex : "+ex);
		System.out.println("@ ey : "+ey);
		
		HttpClient httpclient = new DefaultHttpClient();
		
		String url = "https://api.odsay.com/api/searchPubTransPath?apiKey=0ObaGjz7q8kLrzbsVutNT0qpRKpduNy7cnS9HDogmsk&lang=0";
		if(sx != 0 && sy != 0 && ex != 0 && ey != 0) {
			url+="&SX="+sx+"&SY="+sy+"&EX="+ex+"&EY="+ey;
		}
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
		
		Map map = new HashMap();
		
		try{
			HttpResponse res = httpclient.execute(httpGet);
			
			System.out.println(res);
			System.out.println();
			
			HttpEntity httpEntity = res.getEntity();

			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			List subPathList = new ArrayList();
			List laneList = new ArrayList();

			
			
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			JSONObject errorMessage = (JSONObject)jsonobj.get("error");
			if( errorMessage != null) {
				System.out.println("@@@error@@@");
				System.out.println(errorMessage);
				Object code = errorMessage.get("code");
				System.out.println(code);
				map.put("code", code);
				
			}else {
			
				JSONObject result = (JSONObject)jsonobj.get("result");
				JSONArray pathArray = (JSONArray)result.get("path");
				JSONObject path = (JSONObject) pathArray.get(0);
			//	System.out.println("[4] path : " + path);
				
				///////////////
				JSONObject info = (JSONObject) path.get("info");
				System.out.println("[5] info : " + info);
				ObjectMapper objectMapper = new ObjectMapper();
				Info odsayinfo = new Info();
				odsayinfo = objectMapper.readValue(info.toJSONString(), Info.class);
		
				map.put("info", odsayinfo);
				
				///////////////
				JSONArray subPathArray = (JSONArray)path.get("subPath");
				for (int i = 0; i < subPathArray.size(); i++) {
					JSONObject subPath = (JSONObject)subPathArray.get(i);
					System.out.println("[6] subPath["+i+"] : "+subPath);

					ObjectMapper objectMapper2 = new ObjectMapper();
					SubPath odsaysubPath = new SubPath();
					odsaysubPath = objectMapper2.readValue(subPath.toJSONString(), SubPath.class);
					subPathList.add(subPath);
					
					
					JSONArray laneArray = (JSONArray)subPath.get("lane");	
					if(laneArray != null) {
						System.out.println("[7] laneArray : "+laneArray);		
						JSONObject lane = (JSONObject) laneArray.get(0);
						System.out.println("[8] lane : " + lane);

						ObjectMapper objectMapper3 = new ObjectMapper();
						Lane odsayLane = new Lane();
						odsayLane = objectMapper3.readValue(lane.toJSONString(), Lane.class);
						laneList.add(lane);
					}
					System.out.println();
				}//for 종료
				map.put("subPathList", subPathList);	
				map.put("laneList", laneList);	
				
			}
			
			System.out.println();
			System.out.println("@@@map@@@ "+map);
			
			
		}catch(Exception e){
			System.out.println("시외구간");
		}
		return map;
	}
		
	public OBJ getOBJ(double sx, double sy, double ex, double ey, int flag) throws Exception {
		
		System.out.println("@ sx : "+sx);
		System.out.println("@ sy : "+sy);
		System.out.println("@ ex : "+ex);
		System.out.println("@ ey : "+ey);
		
		String trans="";
		
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

		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		JSONObject result = (JSONObject)jsonobj.get("result");
		
		if(flag == 1) {
			if((JSONObject)result.get("outBusRequest") != null){
				JSONObject transRequest = (JSONObject)result.get("outBusRequest");
				JSONArray OBJArray = (JSONArray)transRequest.get("OBJ");
				if(OBJArray == null) {
					System.out.println("시외버스가 없어서 고속버스로 탐색");
					trans = "exBusRequest";
				}else {
					trans="outBusRequest";
				}
			}
		}else if(flag == 2) {
			if((JSONObject)result.get("exBusRequest") != null){
				JSONObject transRequest = (JSONObject)result.get("exBusRequest");
				JSONArray OBJArray = (JSONArray)transRequest.get("OBJ");
				if(OBJArray == null) {
					System.out.println("고속버스가없어서 기차로 탐색");
					trans = "trainRequest";
				}else {
					trans = "exBusRequest";
				}
			}
		}else if(flag == 3) {
			trans = "trainRequest";
		}
		
		JSONObject transRequest = (JSONObject)result.get(trans);
		JSONArray OBJArray = (JSONArray)transRequest.get("OBJ");
//		for (int i = 0; i < OBJArray.size(); i++) {
//			JSONObject Object = (JSONObject)OBJArray.get(i);
//			System.out.println("[4] Object["+i+"] : "+Object);

		JSONObject Object = (JSONObject)OBJArray.get(0);
		System.out.println("[4] Object["+0+"] : "+Object);
		OBJ obj = new OBJ();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(Object.toString());
		String startSTN = element.getAsJsonObject().get("startSTN").getAsString();
		String startID = element.getAsJsonObject().get("startID").getAsString();
		String SX = element.getAsJsonObject().get("SX").getAsString();
		String SY = element.getAsJsonObject().get("SY").getAsString();
		String endSTN = element.getAsJsonObject().get("endSTN").getAsString();
		String endID = element.getAsJsonObject().get("endID").getAsString();
		String EX = element.getAsJsonObject().get("EX").getAsString();
		String EY = element.getAsJsonObject().get("EY").getAsString();
		String time = element.getAsJsonObject().get("time").getAsString();
		String payment = element.getAsJsonObject().get("payment").getAsString();
		String mapOBJ = element.getAsJsonObject().get("mapOBJ").getAsString();

		obj.setStartSTN(startSTN);
		obj.setStartID(Integer.parseInt(startID));
		obj.setSX(Double.parseDouble(SX));
		obj.setSY(Double.parseDouble(SY));
		obj.setEndSTN(endSTN);
		obj.setEndID(Integer.parseInt(endID));
		obj.setEX(Double.parseDouble(EX));
		obj.setEY(Double.parseDouble(EY));
		obj.setTime(Integer.parseInt(time));
		obj.setPayment(Integer.parseInt(payment));
		obj.setMapOBJ(mapOBJ);

		// trainRequest
		if (element.getAsJsonObject().get("trainType") != null) {
			String trainType = element.getAsJsonObject().get("trainType").getAsString();
			obj.setTrainType(trainType);
		}
		if (element.getAsJsonObject().get("trainCode") != null) {
			String trainCode = element.getAsJsonObject().get("trainCode").getAsString();
			obj.setTrainCode(trainCode);
		}

		return obj;
	}
		
}