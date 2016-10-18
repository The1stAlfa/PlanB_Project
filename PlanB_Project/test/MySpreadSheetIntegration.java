import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class MySpreadSheetIntegration {
  public static void main(String[] args)
      throws AuthenticationException, MalformedURLException, IOException, ServiceException {

    SpreadsheetService service =
        new SpreadsheetService("MySpreadsheetIntegration-v1");

    // TODO: Authorize the service object for a specific user (see other sections)

    // Define the URL to request.  This should never change.
    URL SPREADSHEET_FEED_URL = new URL(
        "https://spreadsheets.google.com/feeds/list/1VxNWD0ptvBPc9L_GBxe-K3Qb_FgLApzf3fA-rUFCjW4/default/public/values");

    // Make a request to the API and get all spreadsheets.
    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
    List<SpreadsheetEntry> spreadsheets = feed.getEntries();

    // Iterate through all of the spreadsheets returned
    for (SpreadsheetEntry spreadsheet : spreadsheets) {
      // Print the title of this spreadsheet to the screen
      System.out.println(spreadsheet.getTitle().getPlainText());
    }
  }

}
