package id.codecorn.bpjsservice.dto.rujukan;

import lombok.Data;

@Data
public class Peserta {
    private Cob cob;
    private HakKelas hakKelas;
    private Informasi informasi;
    private JenisPeserta jenisPeserta;

    private String nama;
    private String nik;
    private String noKartu;
    private String pisa;

    private ProvUmum provUmum;
    private String sex;

    private StatusPeserta statusPeserta;
    private String tglCetakKartu;
    private String tglLahir;
    private String tglTAT;
    private String tglTMT;
    private Umur umur;
}
