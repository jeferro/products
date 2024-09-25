package com.jeferro.products.users.users.infrastructure.mongo;

import java.util.Optional;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.users.infrastructure.mongo.daos.UsersMongoDao;
import com.jeferro.products.users.users.infrastructure.mongo.mappers.UsersMongoMapper;
import com.jeferro.shared.auth.domain.models.Username;
import org.springframework.stereotype.Component;

@Component
public class UsersMongoRepository implements UsersRepository {

    private final UsersMongoMapper usersMongoMapper = UsersMongoMapper.INSTANCE;

    private final UsersMongoDao usersMongoDao;

    public UsersMongoRepository(UsersMongoDao usersMongoDao) {
        this.usersMongoDao = usersMongoDao;
    }

    @Override
    public Optional<User> findById(Username username) {
        var usernameDto = usersMongoMapper.toDTO(username);

        return usersMongoDao.findById(usernameDto)
                .map(usersMongoMapper::toDomain);
    }
}
