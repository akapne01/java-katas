package kata.surfing;

import kata.cocktailbar.Clock;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SurfingAppTest {

   @Test
   public void shouldAlertOnGoodWeatherConditions() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);

      // Given -> weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(21, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(goodWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notify("You should go surfing today");
   }

   @Test
   public void shouldAlertOnDifferentGoodWeatherConditions() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);

      // Given -> weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(25, 9);
      when(weatherApp.getWeatherConditions(55)).thenReturn(goodWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notify("You should go surfing today");
   }

   @Test
   public void shouldNotNotifyWhenBadWeatherConditions() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);

      // Given -> weather conditions are good
      WeatherConditions goodWeatherConditions = new WeatherConditions(1, 9);
      when(weatherApp.getWeatherConditions(55)).thenReturn(goodWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verifyZeroInteractions(notifierApp);
   }

   @Test
   public void shouldNotifyAndVibrateWhenGreatWeatherConditions() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);

      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notifyAndVibrate("Go surfing ASAP!");
   }

   @Test
   public void shouldNotifyOnFullHourWhenGoodWeather() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);


      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);
      when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 00)));

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verify(notifierApp).notifyAndVibrate("Go surfing ASAP!");
   }

   @Test
   public void shouldNotNotifyIfNotFullHourWhenGoodWeather() {
      WeatherApp weatherApp = mock(WeatherApp.class);
      NotifierApp notifierApp = mock(NotifierApp.class);
      ClockApp clockApp = mock(ClockApp.class);
      SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);


      // Given -> weather conditions are good
      WeatherConditions greatWeatherConditions = new WeatherConditions(25, 12);
      when(weatherApp.getWeatherConditions(55)).thenReturn(greatWeatherConditions);
      when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 15)));

      // When -> check weather conditions
      surfingApp.alertOnGoodWeatherConditions();

      // Then -> notification received
      verifyZeroInteractions(notifierApp);
   }

}