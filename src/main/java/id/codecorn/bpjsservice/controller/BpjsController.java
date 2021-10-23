package id.codecorn.bpjsservice.controller;

import id.codecorn.bpjsservice.dto.kamar.ResponseDataKamar;
import id.codecorn.bpjsservice.dto.ketersediaankamar.ResponseDataKetersediaanKamar;
import id.codecorn.bpjsservice.dto.kunjungan.ResponseDataKunjungan;
import id.codecorn.bpjsservice.dto.peserta.ResponseDataPeserta;
import id.codecorn.bpjsservice.dto.poli.ResponseDataPoli;
import id.codecorn.bpjsservice.dto.rujukan.ResponseDataRujukan;
import id.codecorn.bpjsservice.service.BpjsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class BpjsController {

    @Autowired
    private BpjsService bpjsService;

    @GetMapping("nokartu/{nokartu}")
    public ResponseDataPeserta getDataPeserta(@PathVariable("nokartu") String nokartu) {
        return bpjsService.getPesertaByNoKartu(nokartu);
    }

    @GetMapping("nik/{nik}")
    public ResponseDataPeserta getDataPesertaByNik(@PathVariable("nik") String nik) {
        return bpjsService.getPesertaByNik(nik);
    }


    @GetMapping("kamar")
    public ResponseDataKamar getDataKamar() {
        return bpjsService.getListKamar();
    }

    @GetMapping("kamartersedia")
    public ResponseDataKetersediaanKamar getKetersediaanKamar(@RequestParam("kodeppk") String kodeppk, @RequestParam("start") int start, @RequestParam("limit") int limit) {
        return bpjsService.getKamarTersedia(kodeppk, start, limit);
    }

    @GetMapping("poli")
    public ResponseDataPoli getDataPoli(@RequestParam("kode") String kode) {
        return bpjsService.getDataPoli(kode);
    }

    @GetMapping("kunjungan")
    public ResponseDataKunjungan getDataKunjungan(@RequestParam("tanggal") String tanggal, @RequestParam("jenis") String jenis) {
        return bpjsService.getDataKunjungan(tanggal, jenis);
    }

    @GetMapping("rujukan/{nomor}")
    public ResponseDataRujukan getDataRujukanByNo(@PathVariable("nomor") String nomor) {
        return bpjsService.getDataRujukanByNo(nomor);
    }

    @GetMapping("rujukan/nokartu/{nokartu}")
    public ResponseDataRujukan getDataRujukanByNoKartu(@PathVariable("nokartu") String nokartu) {
        return bpjsService.getDataRujukanByNoKartu(nokartu);
    }
}
