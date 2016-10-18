

import java.io.IOException;

import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;
import java.io.BufferedReader;
import java.util.List;
import java.util.Scanner;

public class PrintSpreadsheet {
    private static SpreadsheetService service;
    public static void main(String[] args) {
        service = new SpreadsheetService("Sheet1");
        try {
            String sheetUrl =
                "https://spreadsheets.google.com/feeds/list/1VxNWD0ptvBPc9L_GBxe-K3Qb_FgLApzf3fA-rUFCjW4/default/public/values";

            // Use this String as url
            URL url = new URL(sheetUrl);

            // Get Feed of Spreadsheet url
            ListFeed lf = service.getFeed(url, ListFeed.class);

            //Iterate over feed to get cell value
            for (ListEntry le : lf.getEntries()) {
                CustomElementCollection cec = le.getCustomElements();
                //Pass column name to access it's cell values
                String val = cec.getValue("Concepto");
                System.out.println(val);
                String val2 = cec.getValue("Definición");
                System.out.println(val2);
            }
            System.out.println(lf.getEntries().size());
            listAllWorksheets(new URL("https://spreadsheets.google.com/feeds/worksheets/1Xkd22LiN9unvv7GYOqsv3XwvjVMbFsi-EZASg4hxF9E/public/full"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    
    private static void listAllWorksheets(URL sheet) throws IOException, ServiceException {
    WorksheetFeed worksheetFeed = service.getFeed(sheet,WorksheetFeed.class);
    for (WorksheetEntry worksheet : worksheetFeed.getEntries()) {
      String title = worksheet.getTitle().getPlainText();
      int rowCount = worksheet.getRowCount();
      int colCount = worksheet.getColCount();
      System.out.println("\t" + title + " - rows:" + rowCount + " cols: "
          + colCount);
    }
  }
      
    private int getIndexFromUser(List entries, String type)
      throws IOException {
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < entries.size(); i++) {
          BaseEntry entry = (BaseEntry) entries.get(i);
          System.out.println("\t(" + (i + 1) + ") "
              + entry.getTitle().getPlainText());
        }
        int index = -1;
        while (true) {
            System.out.println("Enter the number of the spreadsheet to load: ");
            String userInput = console.nextLine();
            try {
                index = Integer.parseInt(userInput);
                if (index < 1 || index > entries.size()) {
                    throw new NumberFormatException();
                }
                break;}
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for your selection.");
            }
        }
        return index - 1;
  }
}

