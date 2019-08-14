package kata.surfing;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SurfingAppTest {

   private static final String GOOD_WEATHER_WAIKIKI_MESSAGE = "You should go surfing today to Waikiki";
   private static final String AMAZING_WEATHER_WAIKIKI_MESSAGE = "Go surfing ASAP to Waikiki!";
   private final ClockApp clockApp = mock(ClockApp.class);
   private final WeatherApp weatherApp = mock(WeatherApp.class);
   private final NotifierApp notifierApp = mock(NotifierApp.class);
   private final GPSApp gpsApp = mock(GPSApp.class);
   private final SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp, gpsApp);

   @Before
   public void setUp() throws Exception {
      when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 00)));
      List<City> citiesNearBy = new ArrayList<>();
      citiesNearBy.add(new City(55, "Waikiki", true));
      when(gpsApp.getCitiesNearby()).thenReturn(citiesNearBy);
   }

   @Test
   public void shouldAlertOnGoodWeatherConditions() {

      // Given -> weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(21, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(goodWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notify(GOOD_WEATHER_WAIKIKI_MESSAGE);
   }

   @Test
   public void shouldAlertOnDifferentGoodWeatherConditions() {

      // Given -> weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(25, 9);
      when(weatherApp.getWeatherConditions(55)).thenReturn(goodWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notify(GOOD_WEATHER_WAIKIKI_MESSAGE);
   }

   @Test
   public void shouldNotNotifyWhenBadWeatherConditions() {

      // Given -> weather conditions are good
      WeatherConditions badWeatherConditions = new WeatherConditions(1, 9);
      when(weatherApp.getWeatherConditions(55)).thenReturn(badWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verifyZeroInteractions(notifierApp);
   }

   @Test
   public void shouldNotifyAndVibrateWhenGreatWeatherConditions() {

      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notifyAndVibrate(AMAZING_WEATHER_WAIKIKI_MESSAGE);
   }

   @Test
   public void shouldNotifyOnFullHourWhenGoodWeather() {

      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);
      when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 00)));

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notifyAndVibrate(AMAZING_WEATHER_WAIKIKI_MESSAGE);
   }

   @Test
   public void shouldNotNotifyIfNotFullHourWhenGoodWeather() {

      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);
      when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 15)));

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verifyZeroInteractions(notifierApp);
   }

   @Test
   public void shouldNotifyWhenNearbyCitiesHasGoodWeatherAndBeach() {
      // Given -> Has a beach and weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(25, 9);
      when(weatherApp.getWeatherConditions(9)).thenReturn(goodWeatherConditions);
      List<City> citiesNearBy = new ArrayList<>();
      citiesNearBy.add(new City(9, "Riga", true));
      when(gpsApp.getCitiesNearby()).thenReturn(citiesNearBy);

      // When -> When check weather conditions in nearby cities
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received with city name
      verify(notifierApp).notify("You should go surfing today to Riga");
   }
}