package com.yagn.nadrii.service.comm;

import java.io.PrintStream;

import org.json.simple.JSONObject;

public interface CommOpenAPIDao {
	public String getImageResult(String filePath) throws Exception;
	public JSONObject getTranslatedResult(String result) throws Exception;
}
