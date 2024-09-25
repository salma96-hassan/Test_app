package com.Test.app.Test_app.service;


import com.Test.app.Test_app.io.reqDto.ProviderReqDto;
import com.Test.app.Test_app.io.respDto.ProviderRespDto;

import java.util.List;

public interface ProviderService {
    public ProviderRespDto createProvider(ProviderReqDto providerReqDto) throws Exception;

    public ProviderRespDto updateProvider(ProviderReqDto providerReqDto) throws Exception;

    public ProviderRespDto getProvider(Long providerId) throws Exception;

    public List<ProviderRespDto> getAllProviders() throws Exception;

    public void deleteProvider(Long providerId) throws Exception;

}
