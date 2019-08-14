package kata.surfing;

import java.util.List;

public class SurfingApp {

   private final static int WAIKIKI_CODE = 55;
   private final static String GO_AHEAD = "You should go surfing today";
   private final static String ASAP = "Go surfing ASAP";
   private final WeatherApp weatherApp;
   private final NotifierApp notifierApp;
   private final ClockApp clockApp;
   private final GPSApp gpsApp;

   public SurfingApp(WeatherApp weatherApp, NotifierApp notifierApp, ClockApp clockApp, GPSApp gpsApp) {
      this.weatherApp = weatherApp;
      this.notifierApp = notifierApp;
      this.clockApp = clockApp;
      this.gpsApp = gpsApp;
   }

   /**
    * This method is called every minute by some other method outside.
    */
   public void alertOnGoodWeatherConditions() {

      final int minutes = clockApp.getCurrentTime().getMinute();
      if (minutes != 0) {
         return;
      }

      List<City> citiesNearBy = gpsApp.getCitiesNearby();

      citiesNearBy.stream()
         .filter(city -> city.hasBeach())
         .forEach(city -> notifyIfShouldGoSurfing(city));

//      for (City city : citiesNearBy) {
//
//         boolean cityHasBeach = city.hasBeach();
//         if (!cityHasBeach) {
//            continue;
//         }
//
//         notifyIfShouldGoSurfing(city);
//      }
   }

   private void notifyIfShouldGoSurfing(City city) {
      WeatherConditions weatherConditions = weatherApp.getWeatherConditions(city.getCityId());
      final int temperature = weatherConditions.getTemperatureInCelsius();
      final int windSpeed = weatherConditions.getWindSpeedInMetersPerHour();

      if (temperature >= 25 && windSpeed >= 12) {
         notifierApp.notifyAndVibrate(ASAP + " to " + city.getCityName() + "!");
      } else if ((temperature >= 21 && windSpeed >= 12) || (temperature >= 25 && windSpeed >= 9)) {
         notifierApp.notify(GO_AHEAD + " to " + city.getCityName());
      }
   }
}
