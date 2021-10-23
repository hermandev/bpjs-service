package id.codecorn.bpjsservice.bridging;


import id.codecorn.bpjsservice.dto.kamar.ResponseDataKamar;
import id.codecorn.bpjsservice.dto.ketersediaankamar.ResponseDataKetersediaanKamar;
import id.codecorn.bpjsservice.dto.kunjungan.ResponseDataKunjungan;
import id.codecorn.bpjsservice.dto.peserta.ResponseDataPeserta;
import id.codecorn.bpjsservice.dto.poli.ResponseDataPoli;
import id.codecorn.bpjsservice.dto.rujukan.ResponseDataRujukan;
import id.codecorn.bpjsservice.utils.BpjsApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Paths;
import java.time.LocalDateTime;


@SpringBootTest
public class BpjsApiTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BpjsApi bpjsApi;



    @Test
    public void getDataKunjunganTest() {
        ResponseDataKunjungan data = bpjsApi.dataKunjungan("Monitoring/Kunjungan/Tanggal", "2017-10-01", "R.Inap", HttpMethod.GET, restTemplate);
        System.out.println(data);
    }

    @Test
    public void getDataPesertaTest() {
        LocalDateTime date = LocalDateTime.now();
        ResponseDataPeserta data = bpjsApi.dataPeserta("Peserta/nokartu/", "0001075619147/tglSEP/" + date.toLocalDate(), HttpMethod.GET, restTemplate);
        System.out.println(data);
    }

    @Test
    public void getDataPesertaNikTest() {
        LocalDateTime date = LocalDateTime.now();
        ResponseDataPeserta data = bpjsApi.dataPeserta("Peserta/nik/", "7501010709720004/tglSEP/" + date.toLocalDate(), HttpMethod.GET, restTemplate);
        System.out.println(data);
    }


    @Test
    public void getKamarTest() {
        ResponseDataKamar data = bpjsApi.dataKamar("aplicaresws/rest/ref/kelas" , HttpMethod.GET, restTemplate);
        System.out.println("Get Data Kamar = SUCCESS");
    }


    @Test
    public void getKetersediaanKamarTest() {
        ResponseDataKetersediaanKamar data = bpjsApi.dataKetersediaanKamar("aplicaresws/rest/bed/read/", 1, 1, HttpMethod.GET, restTemplate);
        System.out.println("Get Data Ketersediaan Kamar = SUCCESS");
    }

    @Test
    public void getDataPoliTest() {
        ResponseDataPoli data = bpjsApi.dataPoli("referensi/poli/", "ICU", HttpMethod.GET, restTemplate);
        System.out.println("Get Data Poli = SUCCESS");
    }

    @Test
    public void getDataRujukanByNoTest() {
        ResponseDataRujukan data = bpjsApi.getDataRujukabByNo("Rujukan/", "030107010217Y001465", HttpMethod.GET, restTemplate);
        System.out.println(data);
    }


    @Test
    public void checkDirname() {
        System.out.println(Paths.get("uploads"));
    }


}
