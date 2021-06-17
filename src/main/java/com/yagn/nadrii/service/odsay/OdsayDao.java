package com.yagn.nadrii.service.odsay;

import java.util.List;

import com.yagn.nadrii.service.domain.odsay.outside.OBJ;

public interface OdsayDao {
	
	public List getPathStationsList(double sx, double sy, double ex, double ey) throws Exception;
	
	public OBJ getOutTerminal(double sx, double sy, double ex, double ey) throws Exception;

}
