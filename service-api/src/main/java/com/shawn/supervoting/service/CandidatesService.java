package com.shawn.supervoting.service;

import com.shawn.supervoting.dto.requestDTO.RequestCandidatesDTO;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;
import com.shawn.supervoting.dto.responseDTO.PaginatedResponseDTO.PaginatedResponseCandidatesDTO;
import com.shawn.supervoting.dto.responseDTO.ResponseCandidatesDTO;

public interface CandidatesService {
    CommonResponseDTO save(RequestCandidatesDTO dto);

    PaginatedResponseCandidatesDTO getAllPaginated(String searchText, int page, int size);

    CommonResponseDTO delete(String id);

    CommonResponseDTO update(RequestCandidatesDTO dto, String id);

    ResponseCandidatesDTO getById(String id);
}
