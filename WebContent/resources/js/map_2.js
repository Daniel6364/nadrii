/**
 * 
 */

		var container = document.getElementById('map');
		var options = {
			center: new daum.maps.LatLng(37.494636830447774, 127.02801029569223),
			level: 5
		};
		
		var map = new daum.maps.Map(container, options);
		
		var marker = new daum.maps.Marker({  
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);

		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker.setPosition(latlng);
		});