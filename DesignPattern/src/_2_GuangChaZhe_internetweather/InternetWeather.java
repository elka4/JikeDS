package _2_GuangChaZhe_internetweather;

public class InternetWeather {

	public static void main(String[] args) {
		CurrentConditions mCurrentConditions;
		WeatherData mWeatherData;
		
		mCurrentConditions=new CurrentConditions();
		mWeatherData=new       WeatherData(mCurrentConditions);
		
		mWeatherData.setData(30, 150, 40);
	}

}
