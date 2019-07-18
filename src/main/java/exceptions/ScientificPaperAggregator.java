package exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScientificPaperAggregator {

   private final ArXivRepository arXivRepository;
   private final JSTORRepository jstorRepository;
   private final MobileReaderApp mobileReaderApp;

   public ScientificPaperAggregator(ArXivRepository arXivRepository, JSTORRepository jstorRepository, MobileReaderApp mobileReaderApp) {
      this.arXivRepository = arXivRepository;
      this.jstorRepository = jstorRepository;
      this.mobileReaderApp = mobileReaderApp;
   }

   public void updateReaderAppWithNewestPapers() throws IOException {
      List<Paper> newPapers = new ArrayList<>();
      int n = 0;
      while (n < 3) {
         try {
            newPapers.addAll(arXivRepository.getNewScientificPapers());
            break;
         } catch (IOException e) {
            n++;
         }
      }

      n = 0;
      while (n < 3) {

         try {
            newPapers.addAll(jstorRepository.getNewScientificPapers());
            break;
         } catch (RuntimeException e) {
            n++;
         }
      }
      mobileReaderApp.pushContentToScreen(newPapers);
   }
}
