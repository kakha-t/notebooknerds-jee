package com.notebooknerds.bean;

import com.notebooknerds.entity.Auftrag;
import com.notebooknerds.service.AuftragService;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * CDI Managed Bean (SessionScoped) für den Admin-Bereich.
 * Ermöglicht die Verwaltung aller Aufträge: Annehmen,
 * Ablehnen und Gegenangebot erstellen.
 */
@Named
@SessionScoped
public class AdminBean implements Serializable {
	private static final long serialVersionUID = 1L;

    @Inject
    private AuftragService auftragService;

    private Long ausgewaehlteId;
    private Map<Long, String> gegenangebotPreise = new HashMap<>();
    private Set<Long> bearbeitungsModus = new HashSet<>();

    public List<Auftrag> getAlleAuftraege() {
        return auftragService.alleAuftraege();
    }

    public String annehmen(Long id) {
        Auftrag a = auftragService.laden(id);
        a.setStatus("ANGENOMMEN");
        auftragService.aktualisieren(a);
        bearbeitungsModus.remove(id);
        return "admin?faces-redirect=true";
    }

    public String ablehnen(Long id) {
        Auftrag a = auftragService.laden(id);
        a.setStatus("ABGELEHNT");
        auftragService.aktualisieren(a);
        bearbeitungsModus.remove(id);
        return "admin?faces-redirect=true";
    }

    public Long getAusgewaehlteId() { return ausgewaehlteId; }
    public void setAusgewaehlteId(Long ausgewaehlteId) { 
        this.ausgewaehlteId = ausgewaehlteId; 
    }
    public String gegenangebot(Long id) {
        Auftrag a = auftragService.laden(id);
        String preisStr = gegenangebotPreise.get(id);
        if (preisStr != null && !preisStr.isEmpty()) {
            a.setAngebotspreis(new BigDecimal(preisStr));
        }
        a.setStatus("GEGENANGEBOT");
        auftragService.aktualisieren(a);
        bearbeitungsModus.remove(id);
        return "admin?faces-redirect=true";
    }

    public Map<Long, String> getGegenangebotPreise() { 
        return gegenangebotPreise; 
    }
    public void setGegenangebotPreise(Map<Long, String> gegenangebotPreise) { 
        this.gegenangebotPreise = gegenangebotPreise; 
    }
    
    public boolean isBearbeitungsModus(Long id) {
        return bearbeitungsModus.contains(id);
    }

    public String bearbeitungUmschalten(Long id) {
        if (bearbeitungsModus.contains(id)) {
            bearbeitungsModus.remove(id);
        } else {
            bearbeitungsModus.add(id);
        }
        return null;
    }
    
}