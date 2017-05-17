package com.bk.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bk.api.bean.User;
import com.bk.entities.UserEntity;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target="id", source="source.uniquieId")
	UserEntity beantoEntity(User source);
	
	@Mapping(target="uniquieId", source="destination.id")
    User entityToBean(UserEntity destination);
}
