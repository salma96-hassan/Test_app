package com.Test.app.Test_app.serviceImpl;

import com.Test.app.Test_app.io.entities.Provider;
import com.Test.app.Test_app.io.mapper.ProviderMapper;
import com.Test.app.Test_app.io.repository.ProviderRepository;
import com.Test.app.Test_app.io.reqDto.ProviderReqDto;
import com.Test.app.Test_app.io.respDto.ProviderRespDto;
import com.Test.app.Test_app.service.ProviderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProviderMapper providerMapper;

    @Override
    public ProviderRespDto createProvider(ProviderReqDto providerReqDto) throws Exception {
        try {
            Provider provider = providerMapper.toEntity(providerReqDto);
            providerRepository.save(provider);
            return providerMapper.toResponceDto(provider);
        } catch (Exception e) {
            throw new BadRequestException("Provider not Created");
        }
    }

    @Override
    public ProviderRespDto updateProvider(ProviderReqDto providerReqDto) throws Exception {
        try {
            Optional<Provider> provider = providerRepository.findById(providerReqDto.getId());
            Provider prov = provider.get();
            if (provider.isPresent()) {
                if (providerReqDto.getService() != null) {
                    prov.setService(providerReqDto.getService());
                }
                if (providerReqDto.getAddress() != null) {
                    prov.setAddress(providerReqDto.getAddress());
                }
                if (providerReqDto.getPhone() != null) {
                    prov.setPhone(providerReqDto.getPhone());
                }
                if (providerReqDto.getNote() != null) {
                    prov.setNote(providerReqDto.getNote());
                }
                if (providerReqDto.getName() != null) {
                    prov.setName(providerReqDto.getName());
                }
                Provider providers = providerMapper.toEntity(providerReqDto);
                providerRepository.save(providers);
                return providerMapper.toResponceDto(providers);
            } else {
                throw new BadRequestException("Provider not Found");
            }
        } catch (Exception e) {
            throw new BadRequestException("Provider not Found");
        }
    }

    @Override
    public ProviderRespDto getProvider(Long providerId) throws Exception {
        try {
            Optional<Provider> provider = providerRepository.findById(providerId);
            if (provider.isPresent()) {
                return providerMapper.toResponceDto(provider.get());
            } else {
                throw new BadRequestException("Provider not Found");
            }
        } catch (Exception e) {
            throw new BadRequestException("Provider not Found");
        }
    }

    @Override
    public List<ProviderRespDto> getAllProviders() throws Exception {
        try {
            List<Provider> providers = providerRepository.findAll();
            return providerMapper.toRespList(providers);
        } catch (Exception e) {
            throw new BadRequestException("Provider not Found");
        }
    }

    @Override
    public void deleteProvider(Long providerId) throws Exception {
        try {
            Optional<Provider> provider = providerRepository.findById(providerId);
            if (provider.isPresent()) {
                providerRepository.delete(provider.get());
            } else {
                throw new BadRequestException("Provider not Found");
            }
        } catch (Exception e) {
            throw new BadRequestException("Provider not Found");
        }
    }
}
