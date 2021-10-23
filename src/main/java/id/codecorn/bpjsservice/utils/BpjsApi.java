package id.codecorn.bpjsservice.utils;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import id.codecorn.bpjsservice.dto.kamar.ResponseDataKamar;
import id.codecorn.bpjsservice.dto.ketersediaankamar.ResponseDataKetersediaanKamar;
import id.codecorn.bpjsservice.dto.kunjungan.ResponseDataKunjungan;
import id.codecorn.bpjsservice.dto.peserta.ResponseDataPeserta;
import id.codecorn.bpjsservice.dto.poli.ResponseDataPoli;
import id.codecorn.bpjsservice.dto.rujukan.ResponseDataRujukan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class BpjsApi {
    // CONS ID PRODUCTION
    String kodePpk = "KODE_PK";
    String constId = "CONST_ID";
    String secretKeys = "SECRET_KEYS";
    String baseUrl = "https://new-api.bpjs-kesehatan.go.id:8080/";
    String baseUrlAplicare = "https://new-api.bpjs-kesehatan.go.id/";
    String serviceName = "new-vclaim-rest/";
    // CONS ID DEVELOPMENT
//    String constId = "22850";
//    String secretKeys = "5uG9686834";
//    private final String baseUrl = "https://dvlp.bpjs-kesehatan.go.id/";
//    private final String serviceName = "vclaim-rest-1.1/";
    public String timeStamp;
    public HttpEntity<String> httpEntity;



    public BpjsApi() {
        refresh();
    }


    public ResponseDataKunjungan dataKunjungan(String url, String tglSep, String jenisPelayanan, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrl + serviceName + url + "/" + tglSep + "/JnsPelayanan/" + jenisPelayanan, method, httpEntity, ResponseDataKunjungan.class).getBody();
    }

    public ResponseDataPeserta dataPeserta(String url, String nokartu, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrl + serviceName + url + nokartu, method, httpEntity, ResponseDataPeserta.class).getBody();
    }

    public ResponseDataPeserta dataPesertaByNik(String url, String nik, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrl + serviceName + url + nik, method, httpEntity, ResponseDataPeserta.class).getBody();
    }

    public ResponseDataKamar dataKamar(String url, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrlAplicare + url, method, httpEntity, ResponseDataKamar.class).getBody();
    }

    public ResponseDataKetersediaanKamar dataKetersediaanKamar(String url, int start, int limit , HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrlAplicare + url + kodePpk + "/" + start + "/" + limit, method, httpEntity, ResponseDataKetersediaanKamar.class).getBody();
    }

    public ResponseDataPoli dataPoli(String url, String kode, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrl + serviceName + url + kode, method ,httpEntity, ResponseDataPoli.class).getBody();
    }

    public ResponseDataRujukan getDataRujukabByNo(String url, String nomor, HttpMethod method, RestTemplate restTemplate) {
        refresh();
        return restTemplate.exchange(baseUrl + serviceName + url + nomor, method, httpEntity, ResponseDataRujukan.class).getBody();
    }




    protected void setHeader() throws GeneralSecurityException, UnsupportedEncodingException {
        String generateHmacSHA256Signature = generateHmacSHA256Signature(constId, secretKeys, timeStamp);
        String urlEncodedSign = URLEncoder.encode(generateHmacSHA256Signature, "UTF-8");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-cons-id", constId);
        headers.set("X-timestamp", timeStamp);
        headers.set("X-signature", generateHmacSHA256Signature);
        httpEntity = new HttpEntity<String>(headers);
    }


    protected void  setTimeStamp() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        LocalDateTime localDateTime = LocalDateTime.now();
        long timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        timeStamp = Long.toString(timeInSeconds);
    }

    public static String generateHmacSHA256Signature(String data, String key, String time) throws GeneralSecurityException {
        byte[] hmacData = null;
        String dateAndTime = data + "&" + time;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hmacData = mac.doFinal(dateAndTime.getBytes("UTF-8"));
            return new Base64Encoder().encode(hmacData);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }


    private void refresh() {
        try {
            this.setTimeStamp();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            this.setHeader();
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
