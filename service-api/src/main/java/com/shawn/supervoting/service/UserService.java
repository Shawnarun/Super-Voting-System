package com.shawn.supervoting.service;

import com.shawn.supervoting.dto.requestDTO.RequestUserDto;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;

public interface UserService {
    CommonResponseDTO save(RequestUserDto dto);
    public void initializeAdmin();
}
