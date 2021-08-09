package tugas.com.security.models;

public class ResponseMessage <E>{
    private E data;
    private String message;

    public ResponseMessage(E data, String message) {
        this.data = data;
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
