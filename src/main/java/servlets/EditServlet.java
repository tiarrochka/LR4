package servlets;

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
import java.util.Map;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private final MainService mainService;

    public EditServlet() {
        this.mainService = new MainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name != null) {
            req.setCharacterEncoding("utf8");
            resp.setCharacterEncoding("utf8");
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
            out.println("<html><body>");
            out.print("<form method='post'>");
            out.print("<input type='hidden' name='oldName' value='" + name + "' />");
            out.print("<p>Введите новое название</p>");
            out.print("<input name='newName' type='text' value='" + name + "' />");
            out.print("<p></p>");
            out.print("<input type='submit' value='Сохранить изменения'>");
            out.print("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldName = req.getParameter("oldName");
        String newName = req.getParameter("newName");

        if (oldName != null && newName != null) {
            mainService.updateName(oldName, newName);
        }

        resp.sendRedirect("test");
    }
}