package com.zgzg.user.application.service;

import com.zgzg.common.exception.BaseException;
import com.zgzg.common.response.Code;
import com.zgzg.common.security.CustomUserDetails;
import com.zgzg.user.application.dto.UserResponseDTO;
import com.zgzg.user.domain.model.User;
import com.zgzg.user.domain.repository.UserRepository;
import com.zgzg.user.presentation.request.UpdateUserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> readAll(){
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        UserResponseDTO userResponseDTO = null;
        for(User user : users){
            userResponseDTO = toUserResponseDTO(user);
            userResponseDTOS.add(userResponseDTO);
            userResponseDTO=null;
        }
        return userResponseDTOS;
    }

    public UserResponseDTO readOne(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BaseException(Code.MEMBER_NOT_EXISTS));
        UserResponseDTO userResponseDTO = toUserResponseDTO(user);
        return userResponseDTO;
    }

    public UserResponseDTO readMyOne(CustomUserDetails customUserDetails){
        User user = userRepository.findById(customUserDetails.getId()).orElseThrow(()->new BaseException(Code.MEMBER_NOT_EXISTS));
        UserResponseDTO userResponseDTO = toUserResponseDTO(user);
        return userResponseDTO;
    }

    @Transactional
    public void updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO){
        User user = userRepository.findById(id).orElseThrow(()->new BaseException(Code.MEMBER_NOT_EXISTS));
        user.setUsername(updateUserRequestDTO.getUsername());
        user.setPassword(updateUserRequestDTO.getPassword());
        user.setNickname(updateUserRequestDTO.getNickname());
        user.setSlackUsername(updateUserRequestDTO.getSlackUsername());
        user.setRole(updateUserRequestDTO.getRole());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id, CustomUserDetails customUserDetails){
        User user = userRepository.findById(id).orElseThrow(()->new BaseException(Code.MEMBER_NOT_EXISTS));

        //이미 삭제된 회원은 회원 삭제 불가
        if(user.getDeletedBy()!=null){
            throw new BaseException(Code.MEMBER_ALREADY_DELETE);
        }

        user.softDelete(customUserDetails.getUsername());
        userRepository.save(user);
    }

    public UserResponseDTO toUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .slackUsername(user.getSlackUsername())
                .role(user.getRole())
                .build();
    }

}
