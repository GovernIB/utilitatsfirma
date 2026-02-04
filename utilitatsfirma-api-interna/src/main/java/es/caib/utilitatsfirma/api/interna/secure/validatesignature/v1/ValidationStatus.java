package es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1;


/**
 * 
 * @author anadal
 *
 */
public class ValidationStatus {



    protected int status = ValidationStatusConstants.SIGNATURE_ERROR.value;

    protected String errorMsg;

    protected String errorException;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    //@XmlJavaTypeAdapter(ThrowableAdapter.class)
    public String getErrorException() {
        return errorException;
    }

    public void setErrorException(String errorException) {
        this.errorException = errorException;
    }

    

}
