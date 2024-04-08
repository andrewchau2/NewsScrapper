import org.achau.logger.FileLogger;
import org.achau.model.pojo.NewsItem;
import org.achau.view.ExportNewsResultHandler;
import org.achau.view.exceptions.InvalidOperationException;
import org.achau.view.exceptions.InvalidWebServiceException;
import org.achau.view.NewsResultHandler;

import java.util.List;
import java.util.logging.Level;
public class Driver {

    private static final String logHeader = "Main: ";


    /*
    Main class:
    Initializes the NewsResultHandler and passes in the user arguments from CLI.
    Result are then converted into JSON output specified in the ExportNewsResultHandler class.
     */
    public static void main(String[] args) {

        NewsResultHandler handler = new NewsResultHandler();
        if(args.length < 2) {
            FileLogger.logger.log(Level.WARNING, logHeader + "Invalid sets of arguments");
            handler.cleanUp();
            return;
        }
        try {
            for (int i = 0; i < args.length - 1; i += 2) {
                String name = args[i].equals("ij") ? "Independent_Journal" : "Open_News";
                String operation = args[i + 1];
                    FileLogger.logger.log(Level.INFO, logHeader + "Starting " + name + " with operation " + operation + " scrap");
                    List<NewsItem> newsList = handler.run(args[i], args[i + 1]);
                    ExportNewsResultHandler.exportNewsResults(newsList, name, operation);
            }
        }catch(InvalidWebServiceException | InvalidOperationException e){
            FileLogger.logger.log(Level.SEVERE, logHeader + "Failed to scrap data -" +  e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e1){
            FileLogger.logger.log(Level.SEVERE, logHeader + "Failed Unknown Exception - " + e1.getMessage());
            e1.printStackTrace();
        }
        finally{
            handler.cleanUp();
            FileLogger.logger.log(Level.INFO, logHeader + "Cleanup successful");
        }

        FileLogger.logger.log(Level.INFO, logHeader + "All services finished running");
    }
}