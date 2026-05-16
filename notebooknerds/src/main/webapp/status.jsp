<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.notebooknerds.entity.Auftrag" %>
<!DOCTYPE html>
<html>
<head>
    <title>Auftragsstatus - Notebooknerds</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
    <h1>🍎 Notebooknerds – Auftragsstatus</h1>
    <div class="inhalt">
        <h2>Ihr Auftragsstatus</h2>

        <%
            Auftrag auftrag = (Auftrag) request.getAttribute("auftrag");
            if (auftrag != null) {
        %>
            <table>
                <tr><th>Feld</th><th>Info</th></tr>
                <tr><td>Auftrag ID</td><td><%= auftrag.getId() %></td></tr>
                <tr><td>Typ</td><td><%= auftrag.getTyp() %></td></tr>
                <tr><td>Baujahr</td><td><%= auftrag.getBaujahr() %></td></tr>
                <tr><td>Zustand</td><td><%= auftrag.getZustand() %></td></tr>
                <tr><td>Angebotspreis</td><td><%= auftrag.getAngebotspreis() %> €</td></tr>
                <tr>
				    <td>Status</td>
				    <td>
				        <%
				            String status = auftrag.getStatus();
				            String cssKlasse = "status-offen";
				            if ("ANGENOMMEN".equals(status)) cssKlasse = "status-angenommen";
				            else if ("ABGELEHNT".equals(status)) cssKlasse = "status-abgelehnt";
				            else if ("GEGENANGEBOT".equals(status)) cssKlasse = "status-gegenangebot";
				            else if ("ABGELEHNT_DURCH_KUNDE".equals(status)) cssKlasse = "status-abgelehnt-kunde";
				        %>
				        <span class="<%= cssKlasse %>"><%= status %></span>
				    </td>
				</tr>
                <tr><td>Kunde</td><td><%= auftrag.getKundeName() %></td></tr>
            </table>

            <% if ("GEGENANGEBOT".equals(auftrag.getStatus())) { %>
                <div class="angebot-box">
                    <h2>💶 Notebooknerds hat ein Gegenangebot gemacht: <%= auftrag.getAngebotspreis() %> €</h2>
                    <p>Möchten Sie dieses Angebot annehmen oder ablehnen?</p>
                    <form method="post" action="auftragAktionServlet">
                        <input type="hidden" name="id" value="<%= auftrag.getId() %>" />
                        <input type="submit" name="aktion" value="Annehmen" />
                        <input type="submit" name="aktion" value="Ablehnen" />
                    </form>
                </div>
            <% } %>

            <br/>
            <a href="index.xhtml">← Zurück zur Startseite</a>

        <%
            } else {
        %>
            <p>Kein Auftrag gefunden.</p>
            <a href="index.xhtml">← Zurück zur Startseite</a>
        <%
            }
        %>
    </div>
</body>
</html>