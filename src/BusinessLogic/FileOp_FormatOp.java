package BusinessLogic;

import Entities.UserLoanApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileOp_FormatOp {

    static void FileWriterOp(String filename, String entry){
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(entry);
        } catch (IOException e) {
            System.err.println("Failed to write to " + filename + ": " + e.getMessage());
        }
    }

    static String Formator(UserLoanApplication app){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s | %s | %.2f | %.2f | %s | %.2f | %s | %s%n",
                app.getTimestamp().format(fmt),            // %s
                app.getLoan().getLoanName(),               // %s
                app.getLoan().getCategory(),               // %s
                app.getLoan().getInterestRate(),           // %.2f
                app.getLoan().getMaxAmount(),              // %.2f
                app.getUser().getName(),                   // %s
                app.getAmountRequested(),                  // %.2f
                app.getLoan().toString(),                  // %s
                app.getStatus().name());                   // %s
    }

}