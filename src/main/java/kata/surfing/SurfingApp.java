package kata.surfing;

import kata.amazon.Notifier;

public class SurfingApp {

   private final static int WAIKIKI_CODE = 55;
   private final static String GO_AHEAD = "You should go surfing today";
   private final static String ASAP = "Go surfing ASAP!";
   private final WeatherApp weatherApp;
   private final NotifierApp notifierApp;
   private final ClockApp clockApp;

   public SurfingApp(WeatherApp weatherApp, NotifierApp notifierApp, ClockApp clockApp) {
      this.weatherApp = weatherApp;
      this.notifierApp = notifierApp;
      this.clockApp = clockApp;
   }

   public void alertOnGoodWeatherConditions() {
      WeatherConditions weatherConditions = weatherApp.getWeatherConditions(WAIKIKI_CODE);
      final int temperature = weatherConditions.getTemperatureInCelsius();
      final int windSpeed = weatherConditions.getWindSpeedInMetersPerHour();
      if (temperature >= 25 && windSpeed >= 12) {
         notifierApp.notifyAndVibrate(ASAP);
      } else if ((temperature >= 21 && windSpeed >= 12) || (temperature >= 25 && windSpeed >= 9)) {
         notifierApp.notify(GO_AHEAD);
      }
   }
}
