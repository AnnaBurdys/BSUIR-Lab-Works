import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String angle = request.getParameter("angle");
        String angleType = request.getParameter("angle-type");

        double angle_int = Double.parseDouble(angle);
        angle_int = (angleType.equals("Radians")) ? angle_int : (angle_int * Math.PI / 180);

        String[] mathFunctions = request.getParameterValues("math-function");
        try {
            writer.println("<p> Angle: " + angle + "</p>");
            writer.println("<p> Angle type: " + angleType + "</p>");
            writer.println("<h4>Math functions</h4>");

            for (String mathFunction : mathFunctions) {

                switch (mathFunction) {
                    case "sin": {
                        String result = String.format("sin(%s) = %.3f", angle, Math.sin(angle_int));
                        writer.println("<li>" + result + "</li>");
                        break;
                    }
                    case "cos": {
                        String result = String.format("cos(%s) = %.3f", angle, Math.cos(angle_int));
                        writer.println("<li>" + result + "</li>");
                        break;
                    }
                    case "tg": {
                        String result = String.format("tg(%s) = %.3f", angle, Math.tan(angle_int));
                        writer.println("<li>" + result + "</li>");
                        break;
                    }
                    case "ctg": {
                        String result = String.format("ctg(%s) = %.3f", angle, 1 / Math.tan(angle_int));
                        writer.println("<li>" + result + "</li>");
                        break;
                    }
                }
            }

            writer.println("<table bgcolor =\"" + request.getParameter("color") + "\"> <caption>" + request.getParameter("title") + "</caption>");
            int rows = Integer.parseInt(request.getParameter("rows"));
            int columns = Integer.parseInt(request.getParameter("columns"));

            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    writer.println("<td>1</td>");
                }
                writer.println("<tr>2</tr>");
            }

            writer.println("</table>");


        } finally {
            writer.close();
        }
    }
}