package exceptions;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScientificPaperAggregatorTest {

   private ArXivRepository arXivRepository = mock(ArXivRepository.class);
   private JSTORRepository jstorRepository = mock(JSTORRepository.class);
   private MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);
   private ScientificPaperAggregator aggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);
   private List<Paper> expectedNewPapers = new ArrayList<>();
   private Paper newArXivPaper;
   private Paper newJSTORPaper;

   @Before
   public void setUp() throws Exception {
      newArXivPaper = new Paper();
      newJSTORPaper = new Paper();
      when(arXivRepository.getNewScientificPapers()).thenReturn(Collections.singletonList(newArXivPaper));
      when(jstorRepository.getNewScientificPapers()).thenReturn(Collections.singletonList(newJSTORPaper));

      expectedNewPapers = Arrays.asList(newArXivPaper, newJSTORPaper);
   }

   @Test
   public void shouldAggregateScientificPapersFromRepositories() throws IOException {

      // given

      // when
      aggregator.updateReaderAppWithNewestPapers();

      // then (Verifies that method is called with argument
      verify(mobileReaderApp).pushContentToScreen(expectedNewPapers);
   }

   @Test
   public void shouldRetryArXiveRepository3TimesMax() throws IOException {
      // given 3 failures
      when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException());

      // when
      aggregator.updateReaderAppWithNewestPapers();

      // then
      verify(mobileReaderApp).pushContentToScreen(Arrays.asList(newJSTORPaper));
   }

   @Test
   public void shouldRetryOnceIfSuccessfulSecondTime() throws IOException {
      // given 3 failures
      when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException()).thenReturn(Arrays.asList(newArXivPaper));

      // when
      aggregator.updateReaderAppWithNewestPapers();

      // then
      verify(mobileReaderApp).pushContentToScreen(expectedNewPapers);


   }

   @Test
   public void shouldRetryJSTORRepositoryMax3Times() throws IOException {
      // given
      when(jstorRepository.getNewScientificPapers()).thenThrow(new RuntimeException());

      // when
      aggregator.updateReaderAppWithNewestPapers();

      // then
      verify(mobileReaderApp).pushContentToScreen(Arrays.asList(newArXivPaper));
   }

   @Test
   public void shouldRetryJSTORRepoOnceIfSuccessfulSecondTime() throws IOException {
      // given
      when(jstorRepository.getNewScientificPapers()).thenThrow(new RuntimeException()).thenReturn(Arrays.asList(newJSTORPaper));

      // when
      aggregator.updateReaderAppWithNewestPapers();

      // then
      verify(mobileReaderApp).pushContentToScreen(expectedNewPapers);
   }

}