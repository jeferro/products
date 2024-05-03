package com.jeferro.products.users.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.generated.dtos.AuthRestDTO;
import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import com.jeferro.products.users.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	UsernameRestMapper.class
})
@Profile(RestProfile.NAME)
public abstract class AuthRestMapper extends ToDTOMapper<User, AuthRestDTO> {

	public static final AuthRestMapper INSTANCE = Mappers.getMapper(AuthRestMapper.class);

}
