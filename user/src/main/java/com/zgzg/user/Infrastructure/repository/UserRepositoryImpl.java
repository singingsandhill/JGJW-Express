package com.zgzg.user.Infrastructure.repository;

import com.zgzg.common.exception.BaseException;
import com.zgzg.common.response.Code;
import com.zgzg.user.domain.model.User;
import com.zgzg.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Transactional
    public Optional<User> save(User user) {
        return Optional.of(jpaUserRepository.save(user));
    }


    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(jpaUserRepository.findByUsername(username).orElseThrow(() -> new BaseException(Code.MEMBER_NOT_EXISTS)));
    }

    public List<User> findAll(){
        return jpaUserRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(jpaUserRepository.findById(id).orElseThrow(() -> new BaseException(Code.MEMBER_NOT_EXISTS)));
    }
}
