package com.yagn.nadrii.service.comm.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.protobuf.ByteString;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.LocationInfo;
import com.google.cloud.vision.v1.WebDetection;
import com.google.cloud.vision.v1.WebDetection.WebEntity;
import com.google.cloud.vision.v1.WebDetection.WebImage;
import com.google.cloud.vision.v1.WebDetection.WebPage;
import com.yagn.nadrii.service.comm.CommOpenAPIDao;
        
@Repository("commOpenAPIDaoImpl")
public class CommOpenAPIDaoImpl implements CommOpenAPIDao {
	public String getImageResult(String filePath) throws Exception {
		
		String result = "";
		
		/// Call the LANDMARAKS
		String landmark = CommOpenAPIDaoImpl.getDetectLandmarks(filePath, System.out);
			if(landmark != null && !landmark.equals("")) {
			result+=landmark+",";
		}
		
		/// Call the WEB_DETECTION
		List<String> webValues = CommOpenAPIDaoImpl.getDetectWebDetections(filePath, System.out);
		if(webValues.size()>0) {
			for(int i=0;i<webValues.size();i++) {
				result+=webValues.get(i)+",";
			}
		}
		
		/// Call the LABELS
		List<String> labels = CommOpenAPIDaoImpl.getDetectLabels(filePath, System.out);
		if(labels.size()>0) {
			for(int i=0;i<labels.size();i++) {
				if(i==labels.size()-1) {
					result+=labels.get(i);
				}else {
					result+=labels.get(i)+",";
				}
			}
		}
		return result;
	}	

	@Override
	public JSONObject getTranslatedResult(String result) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url="https://openapi.naver.com/v1/papago/n2mt";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("X-Naver-Client-Id","pNNiwKQrytok28PNE1Ao");
		httpPost.setHeader("X-Naver-Client-Secret", "VF9Pi9_Bcc");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Accept", "application/json");
		
		String request = "source=en&target=ko&text="+result;
		
		HttpEntity httpEntity = new ByteArrayEntity(request.getBytes());
		httpPost.setEntity(httpEntity);
		HttpResponse response = httpClient.execute(httpPost);
		httpEntity = response.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		JSONObject object = (JSONObject)JSONValue.parse(br);
		JSONObject message = (JSONObject)object.get("message");
		
		return (JSONObject)message.get("result");
	}
	
	/// WEB_DETECTION
	private static List<String> getDetectWebDetections(String filePath, PrintStream out) {
		
		try {

			out.println("\n========== [WEB_DETECTION START] ==========");

			List<AnnotateImageRequest> requests = new ArrayList<>();
			List<String> values = new ArrayList<>();
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.WEB_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
				List<AnnotateImageResponse> responses = response.getResponsesList();

				for (AnnotateImageResponse res : responses) {
					if (res.hasError()) {
						out.printf("Error: %s\n", res.getError().getMessage());
						return null;
					}

					// Search the web for usages of the image. You could use these signals later
					// for user input moderation or linking external references.
					// For a full list of available annotations, see http://g.co/cloud/vision/docs
					WebDetection annotation = res.getWebDetection();
//					System.out.println("Entity:Id:Score");
//					System.out.println("===============");
					for (WebEntity entity : annotation.getWebEntitiesList()) {
//						System.out.println(	entity.getDescription() + " : " + entity.getEntityId() + " : " + entity.getScore());
						
						///*
//						System.out.println("///////////////////////////");
						String entityValue = entity.getDescription();
						values.add(entityValue);
//						System.out.println("///////////////////////////");
						//*/
					}
				}
				return values;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/// LANDMARKS
	private static String getDetectLandmarks(String filePath, PrintStream out) {
		
		try {

			System.out.println("\n========== [LANDMARKS_DETECTION START] ==========");
			
			String landmark = "";
			List<AnnotateImageRequest> requests = new ArrayList<>();
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LANDMARK_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
				List<AnnotateImageResponse> responses = response.getResponsesList();

				for (AnnotateImageResponse res : responses) {
					if (res.hasError()) {
						// out.printf("Error: %s\n", res.getError().getMessage());
						System.out.println("Error: " + res.getError().getMessage());
						return null;
					}
					
					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
						LocationInfo info = annotation.getLocationsList().listIterator().next();
						// out.printf("Landmark: %s\n %s\n", annotation.getDescription(),
						// info.getLatLng());
						System.out.println("Landmark: " + annotation.getDescription());
						landmark=annotation.getDescription();
						System.out.println(info.getLatLng());
					}
				}
				return landmark;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	/// LABELS
	private static List<String> getDetectLabels(String filePath, PrintStream out) {

		try {

			System.out.println("\n========== [LABELS_DETECTION START] ==========");

			List<AnnotateImageRequest> requests = new ArrayList<>();
			List<String> values = new ArrayList<>();
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
				List<AnnotateImageResponse> responses = response.getResponsesList();

				for (AnnotateImageResponse res : responses) {
					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());
						return null;
					}

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
//						annotation.getAllFields().forEach((k, v) -> System.out.printf("%s : %s\n", k, v.toString()));
						System.out.println(annotation.getDescription());
						values.add(annotation.getDescription());
					}
				}
				return values;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
