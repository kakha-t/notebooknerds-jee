package com.notebooknerds;

import com.notebooknerds.entity.Auftrag;
import com.notebooknerds.service.AuftragService;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet zur Anzeige des Auftragsstatus für Kunden.
 * Lädt den Auftrag anhand der ID aus der Datenbank
 * und leitet zur JSP-Statusseite weiter.
 */
@WebServlet("/auftragServlet")
public class AuftragServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private AuftragService auftragService;

    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Auftrag auftrag = auftragService.laden(id);
            request.setAttribute("auftrag", auftrag);
            request.getRequestDispatcher("/status.jsp")
                   .forward(request, response);
        } else {
            response.sendRedirect("ankauf.xhtml");
        }
    }
}