/**
 * 
 */



$(document).ready(function() {
	
	
		if(navigator.geolocation) {
		  
			
			navigator.geolocation.getCurrentPosition(
		               
					
					
					
						function(position) {
		                    var lat = position.coords.latitude;
		                    var lon = position.coords.longitude;
		                    

		    
		                    
		                    var location ={
		                    		"lat" : lat,
		                    		"lng" : lon
		                    }
		                    
		                    var jsonData = JSON.stringify(location);

		                    $.ajax({
		                    	//혹여 type:"POST",로 되어 있음// method:"POST"로 바꾸고
		                        method: "POST",
		                        url: "../trip/json/getClientAddress/",
		                        headers:{
		                        	"Content-Type": "application/json",
		                        	"Accept": "application/json"
		                        },
		                        data:jsonData,
		                        dataType: "json",
		                        success: function() {

		                            //alert("난 니가 어딘지 알고 있다 이클립스 콘솔 확인해라");

		                        }
		                    });
		                },
		                function(error) {
		                	/*
		                	var lat = position.coords.latitude;
		                    var lon = position.coords.longitude;

		                    
		                	
		                	 var location ={
			                    		"lat" : lat,
			                    		"lng" : lon
			                    }
			                
		                	 var jsonData = JSON.stringify(location);
		                	 
		                    $.ajax({
		                    	url: "../trip/json/getClientAddress/",
		                        contentType: "application/json",
		                        data:jsonData,
		                        dataType: "json",
		                        success: function() {
		                       
		                        }
		                    });*/  
		                }
		        );
		    }   
		        else {
		        	/*
                    var lat = position.coords.latitude;
                    var lon = position.coords.longitude;

		        	 var location ={
	                    		"lat" : lat,
	                    		"lng" : lon
	                 }
		        	 var jsonData = JSON.stringify(location);    
		           
		            $.ajax({
                    	url: "../trip/json/getClientAddress/",
                        contentType: "application/json",
                        data:jsonData,
                        dataType: "json",
                        success: function() {
                         
                        }
		            }); 
		            */ 
		        }
		    });

		