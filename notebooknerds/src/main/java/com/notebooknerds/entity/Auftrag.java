package com.notebooknerds.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * JPA Entity-Klasse für einen MacBook Ankauf-Auftrag.
 * Bildet die Datenbanktabelle "auftrag" ab und enthält
 * alle relevanten Informationen zu Gerät, Kunde und Status.
 */
@Entity
@Table(name = "auftrag")
public class Auftrag implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modell")
    private String modell;

    @Column(name = "baujahr")
    private Integer baujahr;

    @Column(name = "typ")
    private String typ;

    @Column(name = "zoll")
    private String zoll;

    @Column(name = "zustand")
    private String zustand;

    @Column(name = "schaden")
    private String schaden;

    @Column(name = "tastatur_deutsch")
    private Boolean tastaturDeutsch;

    @Column(name = "netzteil")
    private Boolean netzteil;

    @Column(name = "angebotspreis")
    private BigDecimal angebotspreis;

    @Column(name = "status")
    private String status;

    @Column(name = "kunde_name")
    private String kundeName;

    @Column(name = "kunde_email")
    private String kundeEmail;

    @Column(name = "erstellt_am")
    @Temporal(TemporalType.TIMESTAMP)
    private Date erstelltAm;

    public Auftrag() {
        this.status = "OFFEN";
        this.erstelltAm = new java.util.Date();
    }

    public Long getId() { return id; }
    public String getModell() { return modell; }
    public void setModell(String modell) { this.modell = modell; }
    public Integer getBaujahr() { return baujahr; }
    public void setBaujahr(Integer baujahr) { this.baujahr = baujahr; }
    public String getTyp() { return typ; }
    public void setTyp(String typ) { this.typ = typ; }
    public String getZoll() { return zoll; }
    public void setZoll(String zoll) { this.zoll = zoll; }
    public String getZustand() { return zustand; }
    public void setZustand(String zustand) { this.zustand = zustand; }
    public String getSchaden() { return schaden; }
    public void setSchaden(String schaden) { this.schaden = schaden; }
    public Boolean getTastaturDeutsch() { return tastaturDeutsch; }
    public void setTastaturDeutsch(Boolean tastaturDeutsch) { this.tastaturDeutsch = tastaturDeutsch; }
    public Boolean getNetzteil() { return netzteil; }
    public void setNetzteil(Boolean netzteil) { this.netzteil = netzteil; }
    public BigDecimal getAngebotspreis() { return angebotspreis; }
    public void setAngebotspreis(BigDecimal angebotspreis) { this.angebotspreis = angebotspreis; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getKundeName() { return kundeName; }
    public void setKundeName(String kundeName) { this.kundeName = kundeName; }
    public String getKundeEmail() { return kundeEmail; }
    public void setKundeEmail(String kundeEmail) { this.kundeEmail = kundeEmail; }
    public Date getErstelltAm() { return erstelltAm; }
    public void setErstelltAm(Date erstelltAm) { this.erstelltAm = erstelltAm; }
}