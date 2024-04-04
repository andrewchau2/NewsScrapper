package org.achau.view;

import org.achau.controller.IndependentJournalController;
import org.achau.controller.OpenNewsController;
import org.achau.model.pojo.NewsItem;

import java.util.List;

public class NewsResultHandler {
    private final IndependentJournalController ijController;
    private final OpenNewsController onController;

    public NewsResultHandler(){
        ijController = new IndependentJournalController();
        onController = new OpenNewsController();
    }

    public List<NewsItem> run(String webService, String operation) throws InvalidOperationException, InvalidWebServiceException{
        switch(webService.toLowerCase()){
            case "ij":
                return switch (operation.toLowerCase()) {
                    case "us" -> ijController.getRecentUSNews();
                    case "world" -> ijController.getRecentWorldNews();
                    case "politics" -> ijController.getRecentPoliticsNews();
                    default ->
                            throw new InvalidOperationException("Independent Journal: " + operation + " not found for operation column");
                };
            case "on":
                return onController.getRecentNews();


            default:
                throw new InvalidWebServiceException(webService + "not found");
        }
    }

    public void cleanUp(){
        ijController.quit();
        onController.quit();
    }



}
