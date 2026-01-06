package in.journal.api.response;

import java.util.List;



public class WeatherResponse {
//	 private Request request;
//	 private Location location;
	 private Current current;
	 
	 public class Current{
		 
		 private int temperature;
		 private List<String> weather_descriptions;
		 private int feelslike;
		 
		 public int getTemperature() {
			 return temperature;
		 }
		 public void setTemperature(int temperature) {
			 this.temperature = temperature;
		 }
		 public List<String> getWeather_descriptions() {
			 return weather_descriptions;
		 }
		 public void setWeather_descriptions(List<String> weather_descriptions) {
			 this.weather_descriptions = weather_descriptions;
		 }
		 public int getFeelslike() {
			 return feelslike;
		 }
		 public void setFeelslike(int feelslike) {
			 this.feelslike = feelslike;
		 }
	
		}

	 public Current getCurrent() {
		 return current;
	 }

	 public void setCurrent(Current current) {
		 this.current = current;
	 }

}

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */



