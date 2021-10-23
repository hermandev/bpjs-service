package id.codecorn.bpjsservice.model;

import lombok.Data;

@Data
public class WebResponse<T> {
    private Integer code;
    private String status;
    private T data;

    public WebResponse() {
    }

    public WebResponse(Integer code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
}
