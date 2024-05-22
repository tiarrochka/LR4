package servlets;

import services.TelephoneEntry;
import services.MainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/test")
public class MainServlet extends HttpServlet {

    private final MainService mainService;

    public MainServlet() {
        this.mainService = new MainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
        out.println("<html><body>");
        out.println("<h1>Телефонная книга</h1>");
        out.print("<table border=\"1px\">");
        out.print("<tr>");
        out.print("<th>Название</th>");
        out.print("<th>Действия</th>");
        out.print("</tr>");
        try {
            List<TelephoneEntry> entries = mainService.getNames();
            for (TelephoneEntry entry : entries) {
                out.print("<tr>");
                out.print("<td>" + entry.getName() + "</td>");
                out.print("<td>");
                out.print("<form action='edit' method='get' style='display:inline;'>");
                out.print("<input type='hidden' name='oldName' value='" + entry.getName() + "'>");
                out.print("<input type='submit' value='Редактировать'>");
                out.print("</form>");
                out.print("<form action='delete' method='post' style='display:inline;'>");
                out.print("<input type='hidden' name='name' value='" + entry.getName() + "'>");
                out.print("<input type='submit' value='Удалить'>");
                out.print("</form>");
                out.print("</td>");
                out.print("</tr>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        out.print("</table>");
        out.print("<form method='post'>");
        out.print("<p>Введите название</p>");
        out.print("<input name='name' type='text' />");
        out.print("<p></p>");
        out.print("<input type='submit' value='Отправить'>");
        out.print("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        mainService.addName(name);
        resp.sendRedirect("test");
    }
}
