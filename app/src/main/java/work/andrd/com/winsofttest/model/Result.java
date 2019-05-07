package work.andrd.com.winsofttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Result {


    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("error")
    private boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
