package com.Test.app.Test_app.io.mapper;

import com.Test.app.Test_app.io.entities.Provider;
import com.Test.app.Test_app.io.reqDto.ProviderReqDto;
import com.Test.app.Test_app.io.respDto.ProviderRespDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);
    
    Provider toEntity (ProviderReqDto providerReqDto);

    ProviderRespDto toResponceDto(Provider provider);

    List<ProviderRespDto> toRespList(List<Provider> providers);
}
