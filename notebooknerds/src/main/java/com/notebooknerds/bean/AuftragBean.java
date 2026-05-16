package com.notebooknerds.bean;

import com.notebooknerds.entity.Auftrag;
import com.notebooknerds.service.AuftragService;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CDI Managed Bean (SessionScoped) für den Kunden-Workflow.
 * Steuert die JSF-Seiten für Preisberechnung, Auftragserfassung
 * und Weiterleitung zur Bestätigungsseite.
 */
@Named
@SessionScoped
public class AuftragBean implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private AuftragService auftragService;

    private Auftrag auftrag = new Auftrag();
    private boolean preisBerechnet = false;
    private Long letzteAuftragId;
    private String sucheId;

    public String preisBerechnen() {
        auftrag.setAngebotspreis(auftragService.preisBerechnen(auftrag));
        preisBerechnet = true;
        return null;
    }

    public String auftragSenden() {
        auftragService.speichern(auftrag);
        letzteAuftragId = auftrag.getId();
        auftrag = new Auftrag();
        preisBerechnet = false;
        return "bestaetigung?faces-redirect=true";
    }

    public Auftrag getAuftrag() { return auftrag; }
    public void setAuftrag(Auftrag auftrag) { this.auftrag = auftrag; }
    public boolean isPreisBerechnet() { return preisBerechnet; }
    public Long getLetzteAuftragId() { return letzteAuftragId; }
    public String getSucheId() { return sucheId; }
    public void setSucheId(String sucheId) { this.sucheId = sucheId; }

    
    public String gegenangebotAnnehmen(Long id) {
        Auftrag a = auftragService.laden(id);
        a.setStatus("ANGENOMMEN");
        auftragService.aktualisieren(a);
        return null;
    }

    public String gegenangebotAblehnen(Long id) {
        Auftrag a = auftragService.laden(id);
        a.setStatus("ABGELEHNT_DURCH_KUNDE");
        auftragService.aktualisieren(a);
        return null;
    }
    
    public void statusPruefen() throws java.io.IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)
            ctx.getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest)
            ctx.getExternalContext().getRequest();
        String contextPath = request.getContextPath();
        response.sendRedirect(
            contextPath + "/auftragServlet?id=" + sucheId
        );
        ctx.responseComplete();
    }
    
}