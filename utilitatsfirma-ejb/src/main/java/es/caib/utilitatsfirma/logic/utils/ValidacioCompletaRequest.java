package es.caib.utilitatsfirma.logic.utils;

import es.caib.utilitatsfirma.logic.datasource.IDataSource;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidacioCompletaRequest {

    protected boolean validarFitxerFirma;

    protected boolean checkCanviatDocFirmat;

    protected boolean comprovarNifFirma;

    protected IDataSource originalData;

    protected IDataSource signatureData;

    protected IDataSource adaptedData;

    protected IDataSource documentDetachedData;

    protected int signTypeID;

    protected int signMode;

    protected String languageUI;

    protected int numFirmaPortaFIB;

    protected int numFirmesOriginals;

    /*
      protected String nifEsperat;
    
      protected String cifEsperat;
    
      protected int posTaulaDeFirmes;
    */
    public ValidacioCompletaRequest(boolean validarFitxerFirma, boolean checkCanviatDocFirmat,
            boolean comprovarNifFirma, IDataSource originalData, IDataSource adaptedData, IDataSource signatureData,
            IDataSource documentDetachedData, int signTypeID, int signMode, String languageUI, int numFirmaPortaFIB,
            int numFirmesOriginals
            //, String nifEsperat, int posTaulaDeFirmes
    ) {

        this.validarFitxerFirma = validarFitxerFirma;
        this.checkCanviatDocFirmat = checkCanviatDocFirmat;
        this.comprovarNifFirma = comprovarNifFirma;
        this.originalData = originalData;
        this.adaptedData = adaptedData;
        this.signatureData = signatureData;
        this.documentDetachedData = documentDetachedData;
        this.signTypeID = signTypeID;
        this.signMode = signMode;
        this.languageUI = languageUI;
        this.numFirmaPortaFIB = numFirmaPortaFIB;
        /*
        this.numFirmesOriginals = numFirmesOriginals;
        this.nifEsperat = nifEsperat;
        this.posTaulaDeFirmes = posTaulaDeFirmes;
        */
    }

    public ValidacioCompletaRequest(String entitatID, boolean validarFitxerFirma, boolean checkCanviatDocFirmat,
            boolean comprovarNifFirma, IDataSource originalData, IDataSource adaptedData, IDataSource signatureData,
            IDataSource documentDetachedData, int signTypeID, int signMode, String languageUI, int numFirmaPortaFIB,
            int numFirmesOriginals, String nifEsperat, String cifEsperat, int posTaulaDeFirmes) {
        this.validarFitxerFirma = validarFitxerFirma;
        this.checkCanviatDocFirmat = checkCanviatDocFirmat;
        this.comprovarNifFirma = comprovarNifFirma;
        this.originalData = originalData;
        this.adaptedData = adaptedData;
        this.signatureData = signatureData;
        this.documentDetachedData = documentDetachedData;
        this.signTypeID = signTypeID;
        this.signMode = signMode;
        this.languageUI = languageUI;
        this.numFirmaPortaFIB = numFirmaPortaFIB;
        this.numFirmesOriginals = numFirmesOriginals;
        /*
        this.nifEsperat = nifEsperat;
        this.cifEsperat = cifEsperat;
        this.posTaulaDeFirmes = posTaulaDeFirmes;
        */
    }

    public int getNumFirmesOriginals() {
        return numFirmesOriginals;
    }

    public void setNumFirmesOriginals(int numFirmesOriginals) {
        this.numFirmesOriginals = numFirmesOriginals;
    }

    public boolean isValidarFitxerFirma() {
        return validarFitxerFirma;
    }

    public void setValidarFitxerFirma(boolean validarFitxerFirma) {
        this.validarFitxerFirma = validarFitxerFirma;
    }

    public int getSignTypeID() {
        return signTypeID;
    }

    public void setSignTypeID(int signTypeID) {
        this.signTypeID = signTypeID;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

    public boolean isCheckCanviatDocFirmat() {
        return checkCanviatDocFirmat;
    }

    public void setCheckCanviatDocFirmat(boolean checkCanviatDocFirmat) {
        this.checkCanviatDocFirmat = checkCanviatDocFirmat;
    }

    public IDataSource getOriginalData() {
        return originalData;
    }

    public void setOriginalData(IDataSource originalData) {
        this.originalData = originalData;
    }

    public IDataSource getSignatureData() {
        return signatureData;
    }

    public void setSignatureData(IDataSource signatureData) {
        this.signatureData = signatureData;
    }

    public IDataSource getDocumentDetachedData() {
        return documentDetachedData;
    }

    public void setDocumentDetachedData(IDataSource documentDetachedData) {
        this.documentDetachedData = documentDetachedData;
    }

    public int getNumFirmaPortaFIB() {
        return numFirmaPortaFIB;
    }

    public void setNumFirmaPortaFIB(int numFirmaPortaFIB) {
        this.numFirmaPortaFIB = numFirmaPortaFIB;
    }

    public boolean isComprovarNifFirma() {
        return comprovarNifFirma;
    }

    public void setComprovarNifFirma(boolean comprovarNifFirma) {
        this.comprovarNifFirma = comprovarNifFirma;
    }

    /*
      public String getNifEsperat() {
    return nifEsperat;
      }
    
      public void setNifEsperat(String nifEsperat) {
    this.nifEsperat = nifEsperat;
      }
    
      public String getCifEsperat() {
    return cifEsperat;
      }
    
      public void setCifEsperat(String cifEsperat) {
    this.cifEsperat = cifEsperat;
      }
    */
    public int getSignMode() {
        return signMode;
    }

    public void setSignMode(int signMode) {
        this.signMode = signMode;
    }

    public IDataSource getAdaptedData() {
        return adaptedData;
    }

    public void setAdaptedData(IDataSource adaptedData) {
        this.adaptedData = adaptedData;
    }
    /*
      public int getPosTaulaDeFirmes() {
    return posTaulaDeFirmes;
      }
    
      public void setPosTaulaDeFirmes(int posTaulaDeFirmes) {
    this.posTaulaDeFirmes = posTaulaDeFirmes;
      }
    */
}
