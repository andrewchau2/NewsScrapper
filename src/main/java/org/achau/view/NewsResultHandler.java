package org.achau.view;

import org.achau.controller.IndependentJournalController;
import org.achau.controller.OpenNewsController;
import org.achau.model.pojo.NewsItem;
import org.achau.view.exceptions.InvalidOperationException;
import org.achau.view.exceptions.InvalidWebServiceException;

import java.util.List;

/**
 * Handler for the CLI interface.
 * Any requested operations from the user will be handled by this class
 * @author Andrew Chau
 * @version 1.0
 */
public class NewsResultHandler {
    private final IndependentJournalController ijController;
    private final OpenNewsController onController;

    /**
     * Constructor whose sole purpose is to initialize all service controller objects
     */
    public NewsResultHandler(){
        ijController = new IndependentJournalController();
        onController = new OpenNewsController();
    }

    /**
     * Performs webscrapping based on the webService and operation provide.
     * Exceptions are thrown if user input faulty variables
     * @param webService the name of the website being scrapped
     * @param operation the operation for the website being scrapped
     * @return the list of scrapped NewsItems
     * @throws InvalidOperationException occurs when user provides invalid operation for a service
     * @throws InvalidWebServiceException occurs when user provides invalid service
     */
    public List<NewsItem> run(String webService, String operation) throws InvalidOperationException, InvalidWebServiceException {
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
                return switch(operation.toLowerCase()){
                    case "world" -> onController.getRecentNews();
                    default ->
                            throw new InvalidOperationException("Open News: " + operation + " not found for operation column");
                };
            default:
                throw new InvalidWebServiceException(webService + "not found");
        }
    }

    /**
     * Wrapper for the web-scrapper quit operation
     * This method should always be called before exiting the application
     */
    public void cleanUp(){
        ijController.quit();
        onController.quit();
    }
}
