		var markers = [];
		var keywordMarkerPosition; //키워드 검색 마커 좌표


		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.50187873437067, 127), // 지도의 중심좌표
		    level: 10 // 지도의 확대 레벨
		};
		
		var map = new daum.maps.Map(mapContainer, mapOption); 
		
		var clickMarker = new daum.maps.Marker({ 
			
		    position: map.getCenter() 
		
		}); 
		
		var latlng = clickMarker.getPosition();
		
		var speInfoWindow = new daum.maps.InfoWindow({
				
				position : new daum.maps.LatLng(latlng.getLat(),latlng.getLng()),
				content :  '<div style="padding:5px;"><a href="#" id="start" onclick="javascript:start()">출발지</a><br>'
					+'<a href="#" id="pass" onclick="javascript:pass()">경유지</a><br>'+'<a href="#" id="end" onclick="javascript:end()">도착지</a></div>'
			});	
			
		var startSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png', // 출발 마커이미지의 주소입니다    
		markerSize = new daum.maps.Size(50, 45), // 출발 마커이미지의 크기입니다 
		markerOption = { 
							    offset: new daum.maps.Point(15, 43) // 출발 마커이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
							};
							
		var startImage = new daum.maps.MarkerImage(startSrc, markerSize, markerOption);
		var startPosition = new daum.maps.LatLng(x,y);	
		
		var startMarker = new daum.maps.Marker({
			map : map,
			position: startPosition,
			image: startImage
		});
		
		var passSrc = [];
		var passImage = [];
		var passPosition = [];
		var passMarker = [];
				
		for(i=1; i<6; i++){
			
			passSrc[i] = 'http://t1.daumcdn.net/localimg/localimages/07/2013/img/green_b_'+i+'.png';
			passImage[i] = new daum.maps.MarkerImage(passSrc[i], markerSize, markerOption);
			passPosition[i] = new daum.maps.LatLng(latlng.getLat(),latlng.getLng());	
			
			passMarker[i] = new daum.maps.Marker({
				
				position: passPosition[i],
				image: passImage[i]
			});
		}
		
		var endSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png'; // 출발 마커이미지의 주소입니다    

		var endImage = new daum.maps.MarkerImage(endSrc, markerSize, markerOption);
		var endPosition = new daum.maps.LatLng(latlng.getLat(),latlng.getLng());	
		
		var endMarker = new daum.maps.Marker({
		
			position: endPosition,
			image: endImage
		});
		
		
		//////////////////////////////////////////////////////
		
		var sort =[];	//sortable 배열선언
		var markerSort;
			
		$( function() {
			
			$("#searchListSubmit").on("click", function(){
				$("#placesList").css("display","block");
			});
			
		    // run the currently selected effect
		    function runEffect() {
		    
		      // Run the effect
		      $( "#placesList" ).toggle( "blind", 300 );
		    };
		 
		    // Set effect from select menu value
		    $( "#button" ).on( "click", function() {
		      runEffect();
		    });
		  } );
		
		
		$( function() {
			$( "#sortable" ).sortable({
				placeholder: "ui-state-highlight"
			});
			$( "#sortable" ).disableSelection();
		} );			
		
		$( "#sortable" ).on( "sortupdate", function( event, ui ) {
			
			if(startMarker.getMap() != null){
				startMarker.setMap(null);
			}
			if(passMarker[1].getMap() != null){
				passMarker[1].setMap(null);
			}
			if(passMarker[2].getMap() != null){
				passMarker[2].setMap(null);
			}
			if(passMarker[3].getMap() != null){
				passMarker[3].setMap(null);
			}
			if(passMarker[4].getMap() != null){
				passMarker[4].setMap(null);
			}
			if(passMarker[5].getMap() != null){
				passMarker[5].setMap(null);
			}
			if(endMarker.getMap() != null){
				endMarker.setMap(null);
			}
			
			markerSort =[];
			markerSort = sort;
			
			var k=1; //.pointer의 자식값의 변화를 위한 초기화값
			for(var i=0; i<7; i++){
				if(markerSort[i] != null){
					markerSort[i]=$($(".pointer :nth-child("+k+")")[0]).attr("value");
					k++;
				}
			} 
			sort = markerSort;
			
			/*******데이터 타입이 달라서 맵에 넣을수가 없어서 전달 받은 value값을 파싱하여 latlng 데이터 타입으로 변환하여 마커 위치 재생성********/
			
			if(sort[0] != null){
				var parseStartPoint = sort[0].replace("(","");
				parseStartPoint = parseStartPoint.replace(")","");
				parseStartPoint = parseStartPoint.split(',');
				
				sort[0] = new daum.maps.LatLng(parseStartPoint[0], parseStartPoint[1]);
				
				startMarker.setPosition(sort[0]);
				startMarker.setMap(map);
			}
			
			
			
			if(sort[1] != null){
				var parsePassPoint1 = sort[1].replace("(","");
				parsePassPoint1 = parsePassPoint1.replace(")","");
				parsePassPoint1 = parsePassPoint1.split(',');
				
				sort[1] = new daum.maps.LatLng(parsePassPoint1[0], parsePassPoint1[1]);
				
				passMarker[1].setPosition(sort[1]);
				passMarker[1].setMap(map);
			}
			
			
			if(sort[2] != null){
				var parsePassPoint2 = sort[2].replace("(","");
				parsePassPoint2 = parsePassPoint2.replace(")","");
				parsePassPoint2 = parsePassPoint2.split(',');
				
				sort[2] = new daum.maps.LatLng(parsePassPoint2[0], parsePassPoint2[1]);

				passMarker[2].setPosition(sort[2]);
				passMarker[2].setMap(map);
			}
			
			
			if(sort[3] != null){
				var parsePassPoint3 = sort[3].replace("(","");
				parsePassPoint3 = parsePassPoint3.replace(")","");
				parsePassPoint3 = parsePassPoint3.split(',');
				
				sort[3] = new daum.maps.LatLng(parsePassPoint3[0], parsePassPoint3[1]);
				
				passMarker[3].setPosition(sort[3]);
				passMarker[3].setMap(map);
			}
			
			
			
			if(sort[4] != null){
				var parsePassPoint4 = sort[4].replace("(","");
				parsePassPoint4 = parsePassPoint4.replace(")","");
				parsePassPoint4 = parsePassPoint4.split(',');
				
				sort[4] = new daum.maps.LatLng(parsePassPoint4[0], parsePassPoint4[1]);
				
				passMarker[4].setPosition(sort[4]);
				passMarker[4].setMap(map);
			}
			
		
			if(sort[5] != null){
				var parsePassPoint5 = sort[5].replace("(","");
				parsePassPoint5 = parsePassPoint5.replace(")","");
				parsePassPoint5 = parsePassPoint5.split(',');
				
				sort[5] = new daum.maps.LatLng(parsePassPoint5[0], parsePassPoint5[1]);
				
				passMarker[5].setPosition(sort[5]);
				passMarker[5].setMap(map);
			}
			
			
			if(sort[6] != null){
				var parseEndPoint = sort[6].replace("(","");
				parseEndPoint = parseEndPoint.replace(")","");
				parseEndPoint = parseEndPoint.split(',');
				
				sort[6] = new daum.maps.LatLng(parseEndPoint[0], parseEndPoint[1]);
				
				endMarker.setPosition(sort[6]);
				endMarker.setMap(map);
			}
			
		} );

		//좌표를 주소로 바꿔주는 geo 호출
		function geo(i){
			$.ajax({
				url : "https://dapi.kakao.com/v2/local/geo/coord2address.json",
				async : false,
				method : "GET",
				headers : {
					"Authorization":"KakaoAK 162ee19a901cbbe89c0c4b261ddecca3"
				},
				data : {
					"x": sort[i].getLng(),
					"y": sort[i].getLat()
				},
				success : function(returnData){
					console.log(returnData.documents[0].address.address_name);
					if(i == 0){
						$("#subPointer").append('<div style="height:31px">출발</div>')
					}else if(i == 1){
						$("#subPointer").append('<div style="height:31px">경유지1</div>')
					}else if(i == 2){
						$("#subPointer").append('<div style="height:31px">경유지2</div>')
					}else if(i == 3){
						$("#subPointer").append('<div style="height:31px">경유지3</div>')
					}else if(i == 4){
						$("#subPointer").append('<div style="height:31px">경유지4</div>')
					}else if(i == 5){
						$("#subPointer").append('<div style="height:31px">경유지5</div>')
					}else if(i == 6){
						$("#subPointer").append('<div style="height:31px">도착</div>')
					}
					
					$(".pointer").append('<li class="ui-state-default" id="'+i+'" value="'+sort[i]+'" style="width:300px">'+returnData.documents[0].address.address_name+'</li>');
				}
			})
		}
		
		//start marker 표시
		function start(){
			
			var latlng;
			
			if(clickMarker.getMap() != null){
				
				latlng = clickMarker.getPosition();
				
				speInfoWindow.close();
				infowindow.close();
				
				clickMarker.setMap(null);
				
			}else if(markers[0].getMap() != null){
				
				latlng = keywordMarkerPosition;
				
				infowindow.close();
				speInfoWindow.close();
				
			}
			
			sort[0] = latlng;			
			
			startMarker.setPosition(sort[0]);
			startMarker.setMap(map);
			
			//sortable 기존에있는거 지우기
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
			
		}
		
		//passMarker 표시
		function pass(){
		
			var latlng;
			
			if(clickMarker.getMap() != null){
				latlng = clickMarker.getPosition();
				
				speInfoWindow.close();
				infowindow.close();
				
				clickMarker.setMap(null);
				
			}else if(markers[0].getMap() != null){
				latlng = keywordMarkerPosition;
				
				infowindow.close();
				speInfoWindow.close();		
			}
			
			speInfoWindow.close();
			clickMarker.setMap(null);
				
			if(passMarker[1].getMap() == null){
				
				sort[1] = latlng;
				
				passMarker[1].setPosition(sort[1]);
				passMarker[1].setMap(map);
				
				if($(".pointer").length > 0){
					$(".pointer").empty();
				}
				//출발,경유,도착 글자 지우기
				if($("#subPointer").length > 0){
					$("#subPointer").empty();
				}
				for(var i=0;i<sort.length;i++){

					if(sort[i] !=null){
						geo(i);
					}
				}
				
			}else if(passMarker[1].getMap() != null && passMarker[2].getMap() == null){
				
				sort[2] = latlng;
				
				passMarker[2].setPosition(sort[2]);
				passMarker[2].setMap(map);
				
				if($(".pointer").length > 0){
					$(".pointer").empty();
				}
				//출발,경유,도착 글자 지우기
				if($("#subPointer").length > 0){
					$("#subPointer").empty();
				}
				for(var i=0;i<sort.length;i++){

					if(sort[i] !=null){
						geo(i);
					}
				}
				
			}else if(passMarker[1].getMap() != null && passMarker[2].getMap() != null && passMarker[3].getMap() == null){
				
				sort[3] = latlng;
				
				passMarker[3].setPosition(sort[3]);
				passMarker[3].setMap(map);
				
				if($(".pointer").length > 0){
					$(".pointer").empty();
				}
				//출발,경유,도착 글자 지우기
				if($("#subPointer").length > 0){
					$("#subPointer").empty();
				}
				for(var i=0;i<sort.length;i++){

					if(sort[i] !=null){
						geo(i);
					}
				}

			}else if(passMarker[1].getMap() != null && passMarker[2].getMap() != null && passMarker[3].getMap() != null && passMarker[4].getMap() == null){
				
				sort[4] = latlng;
				
				passMarker[4].setPosition(sort[4]);
				passMarker[4].setMap(map);
				
				if($(".pointer").length > 0){
					$(".pointer").empty();
				}
				//출발,경유,도착 글자 지우기
				if($("#subPointer").length > 0){
					$("#subPointer").empty();
				}
				for(var i=0;i<sort.length;i++){

					if(sort[i] !=null){
						geo(i);
					}
				}
				
			}else if(passMarker[1].getMap() != null && passMarker[2].getMap() != null && passMarker[3].getMap() != null && passMarker[4].getMap() != null && passMarker[5].getMap() == null){
				
				sort[5] = latlng;
				
				passMarker[5].setPosition(sort[5]);
				passMarker[5].setMap(map);
	
				if($(".pointer").length > 0){
					$(".pointer").empty();
				}
				//출발,경유,도착 글자 지우기
				if($("#subPointer").length > 0){
					$("#subPointer").empty();
				}
				for(var i=0;i<sort.length;i++){

					if(sort[i] !=null){
						geo(i);
					}
				}
			}		
		}
		
		//endMarker표시
		function end(){
		
			var latlng;
			
			if(clickMarker.getMap() != null){
				latlng = clickMarker.getPosition();
				
				speInfoWindow.close();
				infowindow.close();
				
				clickMarker.setMap(null);
				
			}else if(markers[0].getMap() != null){
				latlng = keywordMarkerPosition;
				
				infowindow.close();
				speInfoWindow.close();		
			}
			
			sort[6] = latlng;

			endMarker.setPosition(sort[6]);
			endMarker.setMap(map);
			
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
		}
		
		////////////////////////////////////////////////////////
		
		//passMarker클릭시 지우기
		daum.maps.event.addListener(passMarker[1], 'click', function() {
        
			passMarker[1].setMap(null);
			
			sort[1] = null;
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
      	});
      	daum.maps.event.addListener(passMarker[2], 'click', function() {
         
			passMarker[2].setMap(null);
			
			sort[2] = null;
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
     	 });
      	daum.maps.event.addListener(passMarker[3], 'click', function() {
         
			passMarker[3].setMap(null);
			
			sort[3] = null;
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
     	 });
      	daum.maps.event.addListener(passMarker[4], 'click', function() {
         
			passMarker[4].setMap(null);
			
			sort[4] = null;
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
      	});
		daum.maps.event.addListener(passMarker[5], 'click', function() {
        
			passMarker[5].setMap(null);
			
			sort[5] = null;
			if($(".pointer").length > 0){
				$(".pointer").empty();
			}
			//출발,경유,도착 글자 지우기
			if($("#subPointer").length > 0){
				$("#subPointer").empty();
			}
			for(var i=0;i<sort.length;i++){

				if(sort[i] !=null){
					geo(i);
				}
			}
		});

	
		var iwArray=[];
		var arrayStart =[];
		var arrayEnd = [];
		
		function getInfo(k){
			$.ajax({
				url : "../odsay/json/getInfo",
				method : "GET",
				dataType : "json",
				data : {"sx" : sx, "sy" : sy, "ex" : ex, "ey" : ey},
				headers : {
					"Accept" : "application/json",
					"Content-type" : "application/json"
				},
				success:function(returnData){
					
					var pathStartInfowindow=[];
					var pathEndInfowindow=[];
					
					var pathStartSTN=[];
					var pathEndSTN=[];
										
					var code = returnData.code;
					
					if(code != null){
						
						if(code == 500){
							swal({
								text: "서버 내부 오류",
								icon: "warning"
							});
						}else if(code == -98){
							swal({
								text: "출, 도착지가 700m이내입니다.",
								icon: "warning"
							});
						}else if (code == -99){
							swal({
								text: "검색 결과가 없습니다",
								icon: "warning"
							});
						}
						
					}else{
						
						alert("시내 success");
						callMapObjApiAJAX(returnData.info.mapObj);
									
						var pathSize = new daum.maps.Size(18, 30), // 출발 마커이미지의 크기입니다 
							 pathOption;
						var pathImage = new daum.maps.MarkerImage('http://www.clker.com/cliparts/I/l/L/S/W/9/map-marker-hi.png', pathSize, pathOption);
						
						var iwContent;
						var traffic;
																			
						for( var i=0; i<returnData.subPathList.length ; i++){
							
							if(i==0){
								
								if(returnData.subPathList[i+1].trafficType == 1){
									traffic = '역까지 도보 ';
								}else{
									traffic = '정류장까지 도보 ';
								}
							
								iwContent = returnData.subPathList[i].sectionTime+'분 '+returnData.subPathList[i].distance+'m<br/><br/></div>';
								
								if(k==0){
									var startInfowindow = new daum.maps.InfoWindow({
									    position : startMarker.getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[0]=startInfowindow;
									
									$("#roadStartContent").append('<strong>출발</strong><div>'+iwArray[0].getContent().replace('</br>')+'</div>');
								}
								if(k==1){
									var pass1Infowindow = new daum.maps.InfoWindow({
									    position : passMarker[1].getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[1]=pass1Infowindow;
									
									$("#roadPass1Content").append('<strong>경유1</strong><div>'+iwArray[1].getContent().replace('</br>')+'</div>');
								}
								if(k==2){
									var pass2Infowindow = new daum.maps.InfoWindow({
									    position : passMarker[2].getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[2]=pass2Infowindow;
									
									$("#roadPass2Content").append('<strong>경유2</strong><div>'+iwArray[2].getContent().replace('</br>')+'</div>');
								}
								if(k==3){
									var pass3Infowindow = new daum.maps.InfoWindow({
									    position : passMarker[3].getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[3]=pass3Infowindow;
									$("#roadPass3Content").append('<strong>경유3</strong><div>'+iwArray[3].getContent().replace('</br>')+'</div>');
								}
								if(k==4){
									var pass4Infowindow = new daum.maps.InfoWindow({
									    position : passMarker[4].getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[4]=pass4Infowindow;
									$("#roadPass4Content").append('<strong>경유4</strong><div>'+iwArray[4].getContent().replace('</br>')+'</div>');
								}
								if(k==5){
									var pass5Infowindow = new daum.maps.InfoWindow({
									    position : passMarker[5].getPosition(), 
									    content : '<div style="padding:5px;">'+returnData.subPathList[1].startName+traffic+iwContent
									});
									iwArray[5]=pass5Infowindow;
									$("#roadPass5Content").append('<strong>경유5</strong><div>'+iwArray[5].getContent().replace('</br>')+'</div>');
								}			
								
								///////인포윈도우
								if(iwArray[0] != null){
									
									daum.maps.event.addListener(startMarker, 'mouseover', function() {
										iwArray[0].open(map, startMarker);
									});
									
									daum.maps.event.addListener(startMarker, 'mouseout', function() {
										iwArray[0].close();
									});
									
								}else if(iwArray[1] != null){
									
									daum.maps.event.addListener(passMarker[1], 'mouseover', function() {
										iwArray[1].open(map, passMarker[1]);
									});
									
									daum.maps.event.addListener(passMarker[1], 'mouseout', function() {
										iwArray[1].close();
									});
									
								}else if(iwArray[2] != null){
									
									daum.maps.event.addListener(passMarker[2], 'mouseover', function() {
										iwArray[2].open(map, passMarker[2]);
									});
									
									daum.maps.event.addListener(passMarker[2], 'mouseout', function() {
										iwArray[2].close();
									});

								}else if(iwArray[3] != null){
									
									daum.maps.event.addListener(passMarker[3], 'mouseover', function() {
										iwArray[3].open(map, passMarker[3]);
									});
									
									daum.maps.event.addListener(passMarker[3], 'mouseout', function() {
										iwArray[3].close();
									});
									
								}else if(iwArray[4] != null){
									
									daum.maps.event.addListener(passMarker[4], 'mouseover', function() {
										iwArray[4].open(map, passMarker[4]);
									});
									
									daum.maps.event.addListener(passMarker[4], 'mouseout', function() {
										iwArray[4].close();
									});
									
								}else if(iwArray[5] != null){
									
									daum.maps.event.addListener(passMarker[5], 'mouseover', function() {
										iwArray[5].open(map, passMarker[5]);
									});
									
									daum.maps.event.addListener(passMarker[5], 'mouseout', function() {
										iwArray[5].close();
									});
								}
								
								
							}else if(i%2==1){//도보 제외 지하철, 버스
										
								var pathStartMarker = new daum.maps.Marker({
								   // map: map,
								    position: new daum.maps.LatLng(returnData.subPathList[i].startY, returnData.subPathList[i].startX),
								    image: pathImage
								});
								
								pathStartSTN.push(pathStartMarker);
							
								var pathEndMarker = new daum.maps.Marker({
								  //  map: map,
								    position: new daum.maps.LatLng(returnData.subPathList[i].endY, returnData.subPathList[i].endX),
									image: pathImage
								});
								
								pathEndSTN.push(pathEndMarker);
																									
								var laneName;
								var stationFlag;
								
								if(returnData.subPathList[i].trafficType == 1){
									laneName = '역에서 '+returnData.laneList[Math.floor(i/2)].name+' 승차';
									stationFlag = '역';
								}else{
									laneName = '정류장에서 '+returnData.laneList[Math.floor(i/2)].busNo+'번 버스 승차';
									stationFlag = '정류장';
								}

								var pathIwArray=[];
								var startContent ='<div style="padding:5px;">'
													+returnData.subPathList[i].startName+laneName+'<br/>'
													+returnData.subPathList[i].stationCount+' 정거장 이동 후 '
													+returnData.subPathList[i].endName+stationFlag+' 하차<br/><br/>'
													+'</div>';
								
								pathIwArray[0]=startContent;
								
								var endInfo;
								if(i+2 != returnData.subPathList.length){
									if(returnData.laneList[Math.floor(i/2)+1].busNo != null){
										endInfo = returnData.subPathList[i+2].startName+'정류장';
									}else{
										endInfo = returnData.subPathList[i+2].startName+'역';
									}										
								}else{
									endInfo = '도착지';
								}
								
								var endContent ='<div style="padding:5px;"> '+endInfo+'까지 도보 '
													+returnData.subPathList[i+1].sectionTime+'분 '+returnData.subPathList[i+1].distance+'m<br/><br/></div>';
								
								pathIwArray[1]=endContent;
																
								pathStartInfowindow[Math.floor(i/2)] = new daum.maps.InfoWindow({
								    position : pathStartSTN[Math.floor(i/2)].getPosition(), 
								    content : startContent
								});
								
								pathEndInfowindow[Math.floor(i/2)] = new daum.maps.InfoWindow({
								    position : pathEndSTN[Math.floor(i/2)].getPosition(), 
								    content : endContent
								});
								
								if(k==0){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadStartContent").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}
								if(k==1){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadPass1Content").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}
								if(k==2){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadPass2Content").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}
								if(k==3){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadPass3Content").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}
								if(k==4){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadPass4Content").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}
								if(k==5){
									for(var p=0; p<2;p++){
										if(pathIwArray[p].indexOf("도보 0분") == -1){
											$("#roadPass5Content").append('<div>'+pathIwArray[p].replace('</br>')+'</div>');
										}
									}
								}	
								
								
						
							}
							
																
						}//for문
						
						arrayStart = pathStartSTN;
						arrayEnd = pathEndSTN;
					
						for( var z=0; z<arrayStart.length ; z++){
							
							console.log(":::::   "+arrayStart);
							arrayStart[z].setMap(map);
							kk.push(arrayStart[z]);
							arrayEnd[z].setMap(map);
							qq.push(arrayEnd[z]);
						}		
																
						daum.maps.event.addListener(pathStartSTN[0], 'mouseover', function() {
						    pathStartInfowindow[0].open(map, pathStartSTN[0]);
						});
						
						daum.maps.event.addListener(pathStartSTN[0], 'mouseout', function() {
						    pathStartInfowindow[0].close();
						});
						
						daum.maps.event.addListener(pathEndSTN[0], 'mouseover', function() {
						    pathEndInfowindow[0].open(map, pathEndSTN[0]);
						});
						
						daum.maps.event.addListener(pathEndSTN[0], 'mouseout', function() {
						    pathEndInfowindow[0].close();
						});
						
						if(pathStartSTN.length >= 2){
							
							daum.maps.event.addListener(pathStartSTN[1], 'mouseover', function() {
							    pathStartInfowindow[1].open(map, pathStartSTN[1]);
							});
							
							daum.maps.event.addListener(pathStartSTN[1], 'mouseout', function() {
							    pathStartInfowindow[1].close();
							});
							
							daum.maps.event.addListener(pathEndSTN[1], 'mouseover', function() {
							    pathEndInfowindow[1].open(map, pathEndSTN[1]);
							});
							
							daum.maps.event.addListener(pathEndSTN[1], 'mouseout', function() {
							    pathEndInfowindow[1].close();
							});
						}
						
						
						if(pathStartSTN.length >= 3){
							
							daum.maps.event.addListener(pathStartSTN[2], 'mouseover', function() {
							    pathStartInfowindow[2].open(map, pathStartSTN[2]);
							});
							
							daum.maps.event.addListener(pathStartSTN[2], 'mouseout', function() {
							    pathStartInfowindow[2].close();
							});
							
							daum.maps.event.addListener(pathEndSTN[2], 'mouseover', function() {
							    pathEndInfowindow[2].open(map, pathEndSTN[2]);
							});
							
							daum.maps.event.addListener(pathEndSTN[2], 'mouseout', function() {
							    pathEndInfowindow[2].close();
							});
						}
						
						if(pathStartSTN.length >= 4){
							
							daum.maps.event.addListener(pathStartSTN[3], 'mouseover', function() {
							    pathStartInfowindow[3].open(map, pathStartSTN[3]);
							});
							
							daum.maps.event.addListener(pathStartSTN[3], 'mouseout', function() {
							    pathStartInfowindow[3].close();
							});
							
							daum.maps.event.addListener(pathEndSTN[3], 'mouseover', function() {
							    pathEndInfowindow[3].open(map, pathEndSTN[3]);
							});
							
							daum.maps.event.addListener(pathEndSTN[3], 'mouseout', function() {
							    pathEndInfowindow[3].close();
							});
						}
						
					}//if
					
				}
			});
			
		}// getInfo 끝
		
		var kk=[];
		var qq=[];

		function callMapObjApiAJAX(mabObj){
							
			/****************폴리라인배열 선언 및 초기화****************/
			polylineArray = [];
			boundaryArray = [];

			for(var i=0; i<polylineArray.length; i++){
				polylineArray[i]=null;
			}
			
	//		for(var i=0; i<boundaryArray.length; i++){
	//			boundaryArray[i]=null;
	//		}
						
			var lineArray;
			
			$.ajax({
				url : "../odsay/json/getGraph",
				method : "GET",
				dataType : "json",
				data : {"mapObj": mabObj},
				headers : {
					"Accept" : "application/json",
					"Content-type" : "application/json"
				},
				success:function(returnData){

					var error = returnData.error;
					
					if(error != null){
						
						alert("polyline을 그리던 중 "+error.message);
						
					}else{
																	
						lineArray = null;
						lineArray = new Array();

						for (var k = 0; k < returnData.listY.length; k++) {
							lineArray.push(new daum.maps.LatLng(returnData.listY[k], returnData.listX[k]));
						}

						polyline = new daum.maps.Polyline({
							path : lineArray,
							strokeWeight : 3
						});

						polylineArray.push(polyline);

						if (polyline.getMap() == null) {
							for (var i = 0; i < polylineArray.length; i++) {
								polylineArray[i].setMap(map);
							}
						}
						
						boundary = new daum.maps.LatLngBounds(
							                new daum.maps.LatLng(returnData.boundary.top, returnData.boundary.left),
							                new daum.maps.LatLng(returnData.boundary.bottom, returnData.boundary.right)
							          	);
						
						boundaryArray.push(boundary);
						
						alert("폴리라인 success");
						
					}
				}
			});				
		} //callMapObjApiAJAX 끝
		
		function getOBJ(k, flag){
			$.ajax({
				url : "../odsay/json/getOBJ",
				method : "GET",
				dataType : "json",
				data : {"sx" : sx, "sy" : sy, "ex" : ex, "ey" : ey, "flag" : flag},
				async : false,
				headers : {
					"Accept" : "application/json",
					"Content-type" : "application/json"
				},
				success:function(returnData){
					alert("시외 success 터미널 마커 생성");
					
					var markerSrc = '../resources/images/marker/mint.png', // 출발 마커이미지의 주소입니다    
					markerSize = new daum.maps.Size(30, 45), // 출발 마커이미지의 크기입니다 
					markerOption = { 
										    offset: new daum.maps.Point(15, 15) // 출발 마커이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
										};
										
					var markerImage = new daum.maps.MarkerImage(markerSrc, markerSize, markerOption);
					
					startSTN = new daum.maps.Marker({
					    map: map,
					    position: new daum.maps.LatLng(returnData.sy, returnData.sx),
					    image: markerImage
					});
					endSTN = new daum.maps.Marker({
					    map: map,
					    position: new daum.maps.LatLng(returnData.ey, returnData.ex),
					    image: markerImage
					});
					
					startSTN.setZIndex(10);
					endSTN.setZIndex(10);
					
					var outStartInfowindow = new daum.maps.InfoWindow({
					    map: map, 
					    position : startSTN.getPosition(), 
					    content : '<div style="padding:5px;"><span style="font-weight:bold;">'+returnData.startSTN+'</span></br>'
					    			+'예상 소요 시간 : '+returnData.time+'분</br>'
					    			+'요금 : '+returnData.payment+'원</br></div>',
					    removable : true
					});
					
					var outEndInfowindow = new daum.maps.InfoWindow({
					    map: map, 
					    position : endSTN.getPosition(), 
					    content : '<div style="padding:5px;"><span style="font-weight:bold;">'+returnData.endSTN+'</span></br></div>',
					    removable : true
					});
					
					outStartInfowindow.open(map, startSTN); 
					outEndInfowindow.open(map, endSTN); 
							                    
					realMarkerArray.insert(k+1, startSTN.getPosition());
					realMarkerArray.insert(k+2, endSTN.getPosition());
					
					function line2(){
						STNpolyline = new daum.maps.Polyline({
						    path: [
						    	startSTN.getPosition(), endSTN.getPosition()
						    ],
						    strokeWeight: 2,
						    strokeColor: '#FF00FF',
						    strokeOpacity: 0.8,
						    strokeStyle: 'dashed'
						});	
						
						STNpolyline.setMap(map);
						
					}
					line2();
					
				}//success
			});
		}// getOBJ 끝
		
		////////////////////////////////////

		var polyline;
		var STNpolyline;
		var startSTN;
		var endSTN;
		var polylineArray;
		var boundaryArray;
		
		var sx;
		var sy;
		var ex;
		var ey;
	
		function search1(flag){
				
			deleteRoadContent();		//우측 경로탐색 정보 지우기
			
			if(startMarker.getMap() == null){
				alert("출발지를 지정해주세요!");
				return;
			}
			
			if(endMarker.getMap() == null){
				alert("도착지를 지정해주세요!");
				return;
			}
			
			if(STNpolyline != null ){
				deleteExSearch();
			}
			if(STNpolyline != null || polylineArray != null){
				deleteInSearch();
			}
			deletePathMarker();
			
			tempMarkerArray=[];
			realMarkerArray=[];
			
			tempMarkerArray.push(startMarker.getPosition());
			realMarkerArray.push(startMarker.getPosition());
			if(passMarker[1].getMap() != null){
				tempMarkerArray.push(passMarker[1].getPosition());
				realMarkerArray.push(passMarker[1].getPosition());
			}
			if(passMarker[2].getMap() != null){
				tempMarkerArray.push(passMarker[2].getPosition());
				realMarkerArray.push(passMarker[2].getPosition());
			}
			if(passMarker[3].getMap() != null){
				tempMarkerArray.push(passMarker[3].getPosition());
				realMarkerArray.push(passMarker[3].getPosition());
			}
			if(passMarker[4].getMap() != null){
				tempMarkerArray.push(passMarker[4].getPosition());
				realMarkerArray.push(passMarker[4].getPosition());
			}
			if(passMarker[5].getMap() != null){
				tempMarkerArray.push(passMarker[5].getPosition());
				realMarkerArray.push(passMarker[5].getPosition());
			}
			
			tempMarkerArray.push(endMarker.getPosition());
			realMarkerArray.push(endMarker.getPosition());
			
			for(k=0;k<tempMarkerArray.length;k++){
				if(k==0){
					first = tempMarkerArray[k];
				}else{
					first=temp;
				}
				last = tempMarkerArray[k+1];
				temp = last;
				if(k+1 == tempMarkerArray.length){
					break;
				}
							
				sx = first.getLng();
				sy = first.getLat();
				ex = last.getLng();
				ey = last.getLat();
				
				getOBJ(k, flag);
				
			}
			
			for(k=0;k<realMarkerArray.length;k++){
				if(k==0){
					first = realMarkerArray[k];
				}else{
					first=temp;
				}
				last = realMarkerArray[k+1];
				temp = last;
				if(k+1 == realMarkerArray.length){
					break;
				}
				
				sx = first.getLng();
				sy = first.getLat();
				ex = last.getLng();
				ey = last.getLat();

				getInfo(k);
				
			}//for문 끝
			
		} // search끝

		
		function showBoundary(i){
			console.log("boundaryArray["+i+"] 보여주는중");
			map.setBounds(boundaryArray[i]);
		}
	