package ic.doc.web;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HTMLResultPage implements Page {

    private final String query;
    private final String answer;

    public HTMLResultPage(String query, String answer) {
        this.query = query;
        this.answer = answer;
    }

    public void writeTo(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // Header
        writer.println("<html>");
        writer.println("<head><title>" + query + "</title></head>");
        writer.println("<body>");

        // Content
        if (answer == null || answer.isEmpty()) {
            writer.println("<h1>Sorry</h1>");
            writer.print("<p>Sorry, we didn't understand <em>" + query + "</em></p>");
        } else {
            writer.println("<h1>" + query + "</h1>");
            writer.println("<p>" + answer.replace("\n", "<br>") + "</p>");
        }

        writer.println("<p><a href=\"/\">Back to Search Page</a></p>");

        // Footer
        writer.println("</body>");
        writer.println("</html>");
    }

    public void serveFile(HttpServletResponse resp) throws IOException {
      resp.setContentType("text/plain");
      resp.setHeader("Content-disposition", "attachment; filename=result.md");
      File tempFile = new File("result.md");
      tempFile.createNewFile();
      FileWriter writer = new FileWriter(tempFile.getPath());
      if (answer == null || answer.isEmpty()) {
        writer.write("Sorry,\nwe didn't understand " + query);
      } else {
        writer.write(query + "\n" + answer);
      }
      writer.close();
      InputStream inputStream = new FileInputStream(tempFile);
      tempFile.delete();
      OutputStream outputStream = resp.getOutputStream();
      inputStream.transferTo(outputStream);
      inputStream.close();
      outputStream.close();
    }

    public void servePdf(HttpServletResponse resp) throws IOException {
      resp.setContentType("application/pdf");
      resp.setHeader("Content-disposition", "attachment; filename=result.pdf");
      File tempFile = new File("result.md");
      tempFile.createNewFile();
      FileWriter writer = new FileWriter(tempFile.getPath());
      if (answer == null || answer.isEmpty()) {
        writer.write("Sorry,\nwe didn't understand " + query);
      } else {
        writer.write(query + "\n" + answer);
      }
      writer.close();
      Process process = new ProcessBuilder("pandoc", "-s", "-r", "man", "-t", "latex" ,"result.md", "-o", "result.pdf").start();
      int exitCode;
      try {
        exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      tempFile.delete();
      File pdf = new File("result.pdf");
      InputStream inputStream = new FileInputStream(pdf);
      pdf.delete();
      OutputStream outputStream = resp.getOutputStream();
      inputStream.transferTo(outputStream);
      inputStream.close();
      outputStream.close();
    }
}
