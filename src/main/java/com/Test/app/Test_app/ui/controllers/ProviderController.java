package com.Test.app.Test_app.ui.controllers;

import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import com.Test.app.Test_app.io.reqDto.ProviderReqDto;
import com.Test.app.Test_app.io.respDto.InvoiceRespDto;
import com.Test.app.Test_app.io.respDto.ProviderRespDto;
import com.Test.app.Test_app.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    ProviderService providerService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProvider(@RequestBody ProviderReqDto providerReqDto)throws Exception  {
        ProviderRespDto providerRespDto = providerService.createProvider(providerReqDto);
        return new ResponseEntity<>(providerRespDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProvider(@RequestBody ProviderReqDto providerReqDto)throws Exception  {
        ProviderRespDto providerRespDto = providerService.updateProvider(providerReqDto);
        return new ResponseEntity<>(providerRespDto, HttpStatus.OK);
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long providerId)throws Exception  {
        providerService.deleteProvider(providerId);
        return new ResponseEntity<>("Deleted Successfully ", HttpStatus.OK);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<?> getProvider(@PathVariable Long providerId)throws Exception  {
        ProviderRespDto providerRespDto=providerService.getProvider(providerId);
        return new ResponseEntity<>(providerRespDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProviders()throws Exception  {
        List<ProviderRespDto> providerRespDtos=providerService.getAllProviders();
        return new ResponseEntity<>(providerRespDtos, HttpStatus.OK);
    }
}
