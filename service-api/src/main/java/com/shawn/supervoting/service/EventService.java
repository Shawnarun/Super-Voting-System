package com.shawn.supervoting.service;

import com.shawn.supervoting.dto.requestDTO.RequestEventDTO;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;
import com.shawn.supervoting.dto.responseDTO.PaginatedResponseDTO.PaginatedResponseEventDTO;
import com.shawn.supervoting.dto.responseDTO.ResponseEventDTO;

public interface EventService {
    CommonResponseDTO save(RequestEventDTO dto);

    PaginatedResponseEventDTO getAllPaginated(String searchText, int page, int size);

    CommonResponseDTO delete(String id);

    CommonResponseDTO update(RequestEventDTO dto, String id);

    ResponseEventDTO getById(String id);
}
