package com.rmuti.guidemap.backend.mapper;

import com.rmuti.guidemap.backend.models.authModels.MAuthResponse;
import com.rmuti.guidemap.backend.table.UserData;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // @Mapping(source = "UserData.promotionCode", target = "code")
    // AuthResponse modelsToDtos(UserData uData);

    // @InheritInverseConfiguration
    MAuthResponse toRAuthResponse(UserData uData);


}
