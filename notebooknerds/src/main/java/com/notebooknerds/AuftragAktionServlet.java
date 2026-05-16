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
 * Servlet zur Verarbeitung der Kundenreaktion auf Gegenangebote.
 * Nimmt POST-Anfragen entgegen und aktualisiert den Auftragsstatus
 * auf ANGENOMMEN oder ABGELEHNT_DURCH_KUNDE.
 */
@WebServlet("/auftragAktionServlet")
public class AuftragAktionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private AuftragService auftragService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String aktion = request.getParameter("aktion");
        Auftrag auftrag = auftragService.laden(id);

        if ("Annehmen".equals(aktion)) {
            auftrag.setStatus("ANGENOMMEN");
        } else {
            auftrag.setStatus("ABGELEHNT_DURCH_KUNDE");
        }

        auftragService.aktualisieren(auftrag);
        response.sendRedirect("auftragServlet?id=" + id);
    }
}