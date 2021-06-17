package com.yagn.nadrii.service.trip;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.impl.TripDaoImplgetAddress;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })


public class Triptest {
	
	private Trip trip;
	
	@Autowired
	@Qualifier("tripDaoImpla")
	private TripDao tripDao;
	
	@Autowired
	@Qualifier("tripDaoImpl")
	private TripDao tripDaotour;
	
	@Autowired
	@Qualifier("tripDaoImplAddress")
	private TripDao tripDaoImpl;
	
	//@Test
	public void addTripTest() throws Exception {
		
		trip = new Trip();
		/*trip.setLng("test�浵");
		trip.setLat("test����");
		trip.setAddress("test �ּ���");
		trip.setContentId("123456");
		trip.setContentTypeId("12");
		trip.setCreatedTime("01/01/01");
		trip.setDescription("test������");
		trip.setThumbnailImageFile("�̹��� ����");
		trip.setTripName("test������");
		*/
		
		tripDao.addTrip(trip);
		
		
		
	}
	
	//@Test
	public Trip getTripTest() throws Exception{
		trip =tripDao.getTrip(20004);
		System.out.println(trip);
		return trip;
		
	}
	
	//@Test
	public void listTrip() throws Exception{
		Search search = new Search();
		search.setSearchCondition("1");
		search.setSearchKeyword("%�ּ���%");
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		List list = tripDao.listTrip(search);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		}
		
		
	}
	
	//@Test
	public void updateViewCount() throws Exception{
		trip = this.getTripTest();
		
		if(trip==null) {
			trip = new Trip();
			trip.setLng("test�浵");
			trip.setLat("test����");
			trip.setAddress("test �ּ���");
			trip.setContentId("123456");
			trip.setContentTypeId("12");
			trip.setCreatedTime("01/01/01");
			trip.setDescription("test������");
			trip.setThumbnailImageFile("�̹��� ����");
			trip.setTripName("test������");
			tripDao.addTrip(trip);
			
			
		}else {
		
			tripDao.updateViewCount(trip.getContentId());
			
		}
		
	}
	
	//@Test
	public void getAddress()throws Exception{
		String lat = "37.524496";
		String lng = "128.636357";
		//36.703287, 128.616741
		//���� ���37.535449, 126.955552
		List locate = tripDaoImpl.getClientAddress(lat, lng);
		System.out.println(locate);
		System.out.println(locate.get(1));
		System.out.println(locate.get(2));
		
//		for (int i = 0; i < locate.size(); i++) {
//			boolean flag = true;
//			String temp = (String)locate.get(i);
//			
//			
//			
//		}
//		System.out.println(locate);
//		String placeName =((String)locate.get(5)).trim();
//		System.out.println("���ø�::"+placeName);
//		if(placeName.equals("����Ư����")) {
//			placeName = "����";
//		}
//		
//		String areacode = tripDaotour.getAreaCode(placeName, "");
//		System.out.println(placeName+"�� �����ڵ��  "+areacode);
//		placeName = ((String)locate.get(4)).trim();
//		String localName = tripDaotour.getAreaCode(placeName, areacode);
//		System.out.println(localName+"�� �ʿ���");
//		
	}

	@Test
	public void searchTest()throws Exception {
		List list = tripDaotour.listTourBySearch(1,"군산");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));			
		}
	}
}
