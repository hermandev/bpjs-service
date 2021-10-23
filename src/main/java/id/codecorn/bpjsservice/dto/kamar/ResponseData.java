package id.codecorn.bpjsservice.dto.kamar;

import lombok.Data;

import java.util.List;

@Data
public class ResponseData {
    private List<ListKamar> list;
}
