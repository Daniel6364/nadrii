package com.yagn.nadrii.service.odsay;

import java.util.Map;

import com.yagn.nadrii.service.domain.odsay.inside.Info;
import com.yagn.nadrii.service.domain.odsay.outside.OBJ;

public interface OdsayService {

	public OBJ getOBJ(double sx, double sy, double ex, double ey, int flag) throws Exception;
	
	public Map getInfo(double sx, double sy, double ex, double ey) throws Exception;

	public Map getGraph(String mapObj) throws Exception;
}
