package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class ResponseDto<T> { // respuesta genérica para cualquier invocación que se haga
    private String code;
    private T response;
    private String errorMessage;

    // public ResponseDto() {}

    /** Utilizar este constructor cuando no hay error.
     * @param response: El objeto de respuesta.
     */
    public ResponseDto(T response) {
        this.code = "200";
        this.response = response;
    }

    /** Utilizar este constructor cuando hay error.
     * Agregar el código de error y el mensaje de error.
     * Procurar que el código de error sea único.
     * @param code: El código de error.
     * @param errorMessage: El mensaje de error.
     */
    public ResponseDto(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", response=" + response +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
