package id.codecorn.bpjsservice.service;

import id.codecorn.bpjsservice.dto.kamar.ResponseDataKamar;
import id.codecorn.bpjsservice.dto.ketersediaankamar.ResponseDataKetersediaanKamar;
import id.codecorn.bpjsservice.dto.kunjungan.ResponseDataKunjungan;
import id.codecorn.bpjsservice.dto.peserta.ResponseDataPeserta;
import id.codecorn.bpjsservice.dto.poli.ResponseDataPoli;
import id.codecorn.bpjsservice.dto.rujukan.ResponseDataRujukan;
import id.codecorn.bpjsservice.utils.BpjsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BpjsService extends BpjsApi {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BpjsApi bpjsApi;

    public ResponseDataPeserta getPesertaByNoKartu(String nokartu) {
        LocalDateTime date = LocalDateTime.now();
        return bpjsApi.dataPeserta("Peserta/nokartu/", nokartu + "/tglSEP/" + date.toLocalDate(), HttpMethod.GET, restTemplate);
    }

    public ResponseDataPeserta getPesertaByNik(String nik) {
        LocalDateTime date = LocalDateTime.now();
        return bpjsApi.dataPesertaByNik("Peserta/nik/", nik + "/tglSEP/" + date.toLocalDate(), HttpMethod.GET, restTemplate);
    }

    public ResponseDataKamar getListKamar() {
        return bpjsApi.dataKamar("aplicaresws/rest/ref/kelas" , HttpMethod.GET, restTemplate);
    }

    public ResponseDataKetersediaanKamar getKamarTersedia(String kodeppk, int start, int limit) {
        return bpjsApi.dataKetersediaanKamar("aplicaresws/rest/bed/read/", start, limit, HttpMethod.GET, restTemplate);
    }

    public ResponseDataPoli getDataPoli(String kode) {
        return bpjsApi.dataPoli("referensi/poli/", kode, HttpMethod.GET, restTemplate);
    }

    public ResponseDataKunjungan getDataKunjungan(String tanggal, String jenis) {
        return bpjsApi.dataKunjungan("Monitoring/Kunjungan/Tanggal/", tanggal, jenis, HttpMethod.GET, restTemplate);
    }

    public ResponseDataRujukan getDataRujukanByNo(String nomor) {
        return bpjsApi.getDataRujukabByNo("Rujukan/", nomor, HttpMethod.GET, restTemplate);
    }

    public ResponseDataRujukan getDataRujukanByNoKartu(String nokartu) {
        return bpjsApi.getDataRujukabByNo("Rujukan/Peserta/", nokartu, HttpMethod.GET, restTemplate);
    }
}
