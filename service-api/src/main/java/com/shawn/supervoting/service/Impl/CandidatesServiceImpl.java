package com.shawn.supervoting.service.Impl;

import com.shawn.supervoting.dto.CandidatesDTO;
import com.shawn.supervoting.dto.requestDTO.RequestCandidatesDTO;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;
import com.shawn.supervoting.dto.responseDTO.PaginatedResponseDTO.PaginatedResponseCandidatesDTO;
import com.shawn.supervoting.dto.responseDTO.ResponseCandidatesDTO;
import com.shawn.supervoting.entity.Candidates;
import com.shawn.supervoting.exception.DuplicateEntryException;
import com.shawn.supervoting.repo.CandidatesRepo;
import com.shawn.supervoting.service.CandidatesService;
import com.shawn.supervoting.util.Mapper.CandidatesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidatesServiceImpl implements CandidatesService {
    private final CandidatesRepo candidatesRepo;
    private final CandidatesMapper candidatesMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(CandidatesServiceImpl.class);
    public CandidatesServiceImpl(CandidatesRepo candidatesRepo, CandidatesMapper candidatesMapper) {
        this.candidatesRepo = candidatesRepo;
        this.candidatesMapper = candidatesMapper;
    }

    @Override
    public CommonResponseDTO save(RequestCandidatesDTO dto) {

        CandidatesDTO candidatesDTO = CandidatesDTO.builder()
                .candidatesId(UUID.randomUUID().toString())
                .description(dto.getDescription())
                .name(dto.getName())
                .nic(dto.getNic())
                .photoUrl(dto.getPhotoUrl())
                .build();

        candidatesRepo.save(candidatesMapper.toCandidates(candidatesDTO));
        return new CommonResponseDTO(200,"Success","Id: "+candidatesDTO.getCandidatesId(),new ArrayList<>());
    }

    @Override
    public PaginatedResponseCandidatesDTO getAllPaginated(String searchText, int page, int size) {
        List<Candidates> candidatesList = candidatesRepo.findByPagination(searchText, PageRequest.of(page,size));
        long count = candidatesRepo.countBySearchText(searchText);

        ArrayList<ResponseCandidatesDTO> arrayList = new ArrayList<>();
            for (Candidates c :candidatesList){
                arrayList.add(new ResponseCandidatesDTO(
                        c.getCandidatesId(),
                        c.getName(),
                        c.getNic(),
                        c.getDescription(),
                        c.getPhotoUrl()
                ));
            }
        return new PaginatedResponseCandidatesDTO(count,arrayList);
    }

    @Override
    public CommonResponseDTO delete(String id) {
        Optional<Candidates> candidates = candidatesRepo.findById(id);
        if(candidates.isEmpty()){
            throw new DuplicateEntryException("Candidates Not Found");
        }
        candidatesRepo.delete(candidates.get());
        return new CommonResponseDTO(204, "Success", "Deleted successfully", new ArrayList<>());
    }

    @Override
    public CommonResponseDTO update(RequestCandidatesDTO dto, String id) {
        Optional<Candidates> candidates = candidatesRepo.findById(id);
        if(candidates.isEmpty()){
            throw new DuplicateEntryException("Candidates Not Found");
        }
        CandidatesDTO candidatesDTO = CandidatesDTO.builder()
                .candidatesId(id)
                .description(dto.getDescription())
                .name(dto.getName())
                .nic(dto.getNic())
                .photoUrl(dto.getPhotoUrl())
                .build();

        Candidates updatedCandidates = candidatesMapper.toCandidates(candidatesDTO);
        candidatesRepo.save(updatedCandidates);
        return new CommonResponseDTO(200, "Success", "Updated successfully", new ArrayList<>());

    }

    @Override
    public ResponseCandidatesDTO getById(String id) {
        Optional<Candidates> candidates = candidatesRepo.findById(id);
        if(candidates.isEmpty()){
            throw new DuplicateEntryException("Candidates Not Found");
        }
        return new ResponseCandidatesDTO(
                candidates.get().getCandidatesId(),
                candidates.get().getName(),
                candidates.get().getNic(),
                candidates.get().getDescription(),
                candidates.get().getPhotoUrl());
    }
}
