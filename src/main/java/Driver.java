import org.achau.model.pojo.NewsItem;
import org.achau.view.ExportNewsResultHandler;
import org.achau.view.InvalidOperationException;
import org.achau.view.InvalidWebServiceException;
import org.achau.view.NewsResultHandler;

import java.util.List;

public class Driver {

    public static void main(String[] args) {

        NewsResultHandler handler = new NewsResultHandler();
        if(args.length <= 2) {
            handler.cleanUp();
            return;
        }

        for(int i = 0; i < args.length - 1; i+=2){
            System.out.println(args[i] + " " + args[i+1]);
            try {
                List<NewsItem> newsList = handler.run(args[i], args[i + 1]);
                String name = args[i].equals("ij") ? "Independent_Journal" : "Open_News";
                String operation = args[i+1];
                ExportNewsResultHandler.exportNewsResults(newsList,name,operation);

            }catch(InvalidWebServiceException | InvalidOperationException e1){
                e1.printStackTrace();
            }
        }

        handler.cleanUp();
    }
}