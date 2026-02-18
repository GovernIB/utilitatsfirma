package org.fundaciobit.pluginsib.tipusdocumental.api;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 13:59:07
 */
public class TipusDocumental {

    protected long tipusDocumentalID;

    protected Long parentTipusDocumentalID;

    protected String name;

    protected String description;

    public TipusDocumental() {
        super();
    }

    public TipusDocumental(long tipusDocumentalID, Long parentTipusDocumentalID, String name, String description) {
        super();
        this.tipusDocumentalID = tipusDocumentalID;
        this.parentTipusDocumentalID = parentTipusDocumentalID;
        this.name = name;
        this.description = description;
    }

    public long getTipusDocumentalID() {
        return tipusDocumentalID;
    }

    public void setTipusDocumentalID(long tipusDocumentalID) {
        this.tipusDocumentalID = tipusDocumentalID;
    }

    public Long getParentTipusDocumentalID() {
        return parentTipusDocumentalID;
    }

    public void setParentTipusDocumentalID(Long parentTipusDocumentalID) {
        this.parentTipusDocumentalID = parentTipusDocumentalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
