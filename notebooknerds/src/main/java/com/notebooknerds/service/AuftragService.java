package com.notebooknerds.service;

import com.notebooknerds.entity.Auftrag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;

/**
 * Stateless EJB für die Geschäftslogik der Auftragsverwaltung.
 * Kapselt alle Datenbankoperationen via JPA EntityManager
 * sowie die zentrale Preisberechnungslogik.
 */
@Stateless
public class AuftragService {

    @PersistenceContext(unitName = "notebooknerds")
    private EntityManager em;
    
    @EJB
    private PreisKonfiguration preisKonfiguration;

    public void speichern(Auftrag auftrag) {
        em.persist(auftrag);
    }

    public Auftrag laden(Long id) {
        return em.find(Auftrag.class, id);
    }

    public List<Auftrag> alleAuftraege() {
        return em.createQuery("SELECT a FROM Auftrag a ORDER BY a.id DESC", Auftrag.class)
                 .getResultList();
    }

    public void aktualisieren(Auftrag auftrag) {
        em.merge(auftrag);
    }

    /**
     * Berechnet den Ankaufspreis anhand von Typ, Baujahr, Zoll,
     * Zustand, Schadenstyp, Tastatur und Netzteil.
     * Basispreise werden aus der Singleton PreisKonfiguration geladen.
     * Ergebnis wird auf 5 Euro gerundet.
     */
    public BigDecimal preisBerechnen(Auftrag auftrag) {
        String typ = auftrag.getTyp();
        int baujahr = auftrag.getBaujahr() != null ? auftrag.getBaujahr() : 2019;
        String schaden = auftrag.getSchaden() != null ? auftrag.getSchaden() : "Sonstige Schaden";
        String zoll = auftrag.getZoll() != null ? auftrag.getZoll() : "13";
        String zustand = auftrag.getZustand() != null ? auftrag.getZustand() : "schlecht";

        // Basispreis je nach Schadenstyp und Typ
	    // Basispreis aus Singleton-Konfiguration laden
	        double basispreis = preisKonfiguration.getBasispreis(typ, schaden);

        // Baujahr: pro Jahr älter 10 Euro weniger
        int jahreAlt = 2025 - baujahr;
        basispreis -= jahreAlt * 10.0;

        // Zoll-Faktor
        switch (zoll) {
            case "16": basispreis *= 1.0;  break;
            case "15": basispreis *= 0.92; break;
            case "14": basispreis *= 0.85; break;
            case "13": basispreis *= 0.78; break;
            default:   basispreis *= 0.78; break;
        }

        // Zustand
        switch (zustand) {
            case "gut":        basispreis *= 1.0;  break;
            case "akzeptabel": basispreis *= 0.85; break;
            case "schlecht":   basispreis *= 0.65; break;
            default:           basispreis *= 0.65; break;
        }

        // Deutsche Tastatur
        if (auftrag.getTastaturDeutsch() == null || !auftrag.getTastaturDeutsch()) {
            basispreis -= 10.0;
        }

        // Kein Netzteil
        if (auftrag.getNetzteil() == null || !auftrag.getNetzteil()) {
            basispreis -= 15.0;
        }

        // Minimum 10 Euro
        if (basispreis < 10.0) basispreis = 10.0;

        // Auf nächste 5 runden
        int gerundet = (int) (Math.round(basispreis / 5.0) * 5);

        return new BigDecimal(gerundet);
    }
}