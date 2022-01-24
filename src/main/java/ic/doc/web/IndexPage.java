package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexPage implements Page {

    public void writeTo(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // Header
        writer.println("<html>");
        writer.println("<head><title>Welcome</title></head>");
        writer.println("<body>");

        // Content
        writer.println(
                "<h1>Welcome!!</h1>" +
                        "<p>Enter your query in the box below: " +
                        "<form>" +
                        "<input type=\"text\" name=\"q\" />" +
                        "<br><br><input type=\"submit\">" +
                        "<p>Select output type</p>" +
                        "<input type=\"radio\" id=\"html\" name=\"output_type\" value=\"HTML\" checked=\"checked\">" +
                        "<label for=\"html\">html</label><br>" +
                        "<input type=\"radio\" id=\"markdown\" name=\"output_type\" value=\"Markdown\">" +
                        "<label for=\"markdown\">markdown</label><br>" +
                        "<input type=\"radio\" id=\"pdf\" name=\"output_type\" value=\"Pdf\">" +
                        "<label for=\"pdf\">pdf</label><br>" +
                        "</form>" +
                        "</p>");

        // Footer
        writer.println("</body>");
        writer.println("</html>");
    }
    
}
