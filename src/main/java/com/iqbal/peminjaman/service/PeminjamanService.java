/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iqbal.peminjaman.service;

import com.iqbal.peminjaman.VO.Nasabah;
import com.iqbal.peminjaman.VO.ResponseTemplateVO;
import com.iqbal.peminjaman.entity.Peminjaman;
import com.iqbal.peminjaman.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author iqbal
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Peminjaman savePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }
    
    public ResponseTemplateVO getPeminjaman(Long peminjamanId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Peminjaman peminjaman = 
                peminjamanRepository.findByPeminjamanId(peminjamanId);
        Nasabah nasabah = restTemplate.getForObject("http://localhost:9001/nasabah/" + peminjaman.getNasabahId(), Nasabah.class);
        vo.setPeminjaman(peminjaman);
        vo.setNasabah(nasabah);
        return vo;
    }

    public Peminjaman findPeminjamanById(Long peminjamanId){
        return peminjamanRepository.findByPeminjamanId(peminjamanId);
    }
}
