package id.codecorn.bpjsservice.dto.rujukan;

import lombok.Data;

@Data
public class Rujukan {
    private Diagnosa diagnosa;
    private String keluhan;
    private String noKunjungan;
    private Pelayanan pelayanan;
    private Peserta peserta;
    private PoliRujukan poliRujukan;
    private ProvPerujuk provPerujuk;
    private String tglKunjungan;
}
