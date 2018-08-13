package bpp.arnet.project.databaseotb.Model;

public class BaseResponse {

    private boolean error;
    private String message;

    public BaseResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
