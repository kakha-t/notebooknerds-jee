package com.notebooknerds.service;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton EJB für zentrale Preiskonfiguration.
 * Wird einmalig beim Serverstart initialisiert und
 * hält alle Basispreise für die Preisberechnung.
 */
@Singleton
@Startup
public class PreisKonfiguration {

    // Basispreise je Typ und Schadenstyp
    private Map<String, Integer> basispreise = new HashMap<>();

    @javax.annotation.PostConstruct
    public void init() {
        // MacBook Pro Basispreise
        basispreise.put("MacBook Pro_Sonstige Schaden", 250);
        basispreise.put("MacBook Pro_Flüssigkeitsschaden", 230);
        basispreise.put("MacBook Pro_Displayschaden", 210);

        // MacBook Air Basispreise
        basispreise.put("MacBook Air_Sonstige Schaden", 180);
        basispreise.put("MacBook Air_Flüssigkeitsschaden", 160);
        basispreise.put("MacBook Air_Displayschaden", 140);
    }

    /**
     * Gibt den Basispreis für einen bestimmten Typ und Schadenstyp zurück.
     * Fallback auf 150 falls keine Konfiguration gefunden.
     */
    public int getBasispreis(String typ, String schaden) {
        String key = typ + "_" + schaden;
        return basispreise.getOrDefault(key, 150);
    }
}