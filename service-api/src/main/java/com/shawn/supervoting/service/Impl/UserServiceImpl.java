package com.shawn.supervoting.service.Impl;

import com.shawn.supervoting.api.UserController;
import com.shawn.supervoting.dto.UserDTO;
import com.shawn.supervoting.dto.requestDTO.RequestUserDto;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;
import com.shawn.supervoting.entity.User;
import com.shawn.supervoting.entity.UserRole;
import com.shawn.supervoting.entity.UserRoleHasUser;
import com.shawn.supervoting.entity.UserRoleHasUserKey;
import com.shawn.supervoting.exception.DuplicateEntryException;
import com.shawn.supervoting.exception.EntryNotFoundException;
import com.shawn.supervoting.repo.UserRepo;
import com.shawn.supervoting.repo.UserRoleHasUserRepo;
import com.shawn.supervoting.repo.UserRoleRepo;
import com.shawn.supervoting.service.UserService;
import com.shawn.supervoting.util.Mapper.UserMapper;
import com.shawn.supervoting.util.Mapper.UserRoleHasUserMapper;
import com.shawn.supervoting.util.Mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    private final UserRoleHasUserMapper userRoleHasUserMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserRoleHasUserRepo userRoleHasUserRepo, UserMapper userMapper, UserRoleMapper userRoleMapper, UserRoleHasUserMapper userRoleHasUserMapper) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.userRoleHasUserMapper = userRoleHasUserMapper;
    }

    @Override
    public CommonResponseDTO save(RequestUserDto dto) {
        LOGGER.info("User Create Request received : Business layer ");
        Optional<User> user = userRepo.findByEmail(dto.getEmail());
        if(user.isPresent()){
            throw new DuplicateEntryException("User Already Exist");
        }

        UserDTO userDTO = UserDTO.builder()
                .userId(UUID.randomUUID().toString())
                .email(dto.getEmail())
                .otp(0)
                .displayName(dto.getDisplayName())
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .password(dto.getPassword())
                .build();


        Optional<UserRole> selectedRole = userRoleRepo.findUserRoleByRoleName("USER");
        if (selectedRole.isEmpty()) {
            throw new EntryNotFoundException("Role not found");
        }

        userRepo.save(userMapper.toUser(userDTO));
        UserRoleHasUserKey userRoleHasUserKey = new UserRoleHasUserKey(userDTO.getUserId(), selectedRole.get().getRoleId());
        UserRoleHasUser userRoleHasUser = UserRoleHasUser.builder()
                .idUserRoleHasUserKey(userRoleHasUserKey)
                .user(userMapper.toUser(userDTO))
                .userRole(selectedRole.get())
                .build();
        userRoleHasUserRepo.save(userRoleHasUser);
        return new CommonResponseDTO(200,"Success","ID: "+userDTO.getUserId(),new ArrayList<>());
    }

    @Override
    public void initializeAdmin() {
        Optional<User> byEmail = userRepo.findByEmail("admin@gmail.com");
        if (byEmail.isEmpty()) {
            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .email("admin@gmail.com")
                    .otp(0)
                    .displayName("Abc Xyz")
                    .isEnabled(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonExpired(true)
                    .password("1234")
                    .build();

            Optional<UserRole> selectedRole = userRoleRepo.findUserRoleByRoleName("ADMIN");
            if (selectedRole.isEmpty()) {
                throw new EntryNotFoundException("Role not found");
            }
            userRepo.save(user);
            UserRoleHasUserKey userRoleHasUserKey = new UserRoleHasUserKey(user.getUserId(), selectedRole.get().getRoleId());
            UserRoleHasUser userRoleHasUser = UserRoleHasUser.builder()
                    .idUserRoleHasUserKey(userRoleHasUserKey)
                    .user(user)
                    .userRole(selectedRole.get())
                    .build();
            userRoleHasUserRepo.save(userRoleHasUser);
        }
    }
}