# BPJS SERVICE


## Settings

ubah nilai pada file [```./src/main/java/id/codecorn/bpjsservice/utils/BpjsApi.java```](https://github.com/hermandev/bpjs-service/blob/main/src/main/java/id/codecorn/bpjsservice/utils/BpjsApi.java)

```java
    String kodePpk = "KODE_PK";
    String constId = "CONST_ID";
    String secretKeys = "SECRET_KEYS";
```

service berjalan pada port ```8301```


## Web Service
#### BASE_URL = ```http://BASE_URL:8301/api/v1``` 

* Get Peserta By nomor kartu = ```/nokartu/{nokartu}```
* Get Peserta By NIK = ```/nik/{nik}```
* Get Data Kamar = ```/kamar```
* Get Data Kamar tersedia = ```/kamartersedia```
* Get Data Poli = ```/poli```
* Get Data Kunjungan = ```/kunjungan```
* Get Data Rujukan By Nomor = ```/rujukan/{nomor}```
* Get Data Rujukan By Nomor Kartu bpjs = ```/rujukan/nokartu/{nokartu}```

