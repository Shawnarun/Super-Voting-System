package com.shawn.supervoting.service.Impl;

import com.shawn.supervoting.dto.EventDTO;
import com.shawn.supervoting.dto.requestDTO.RequestEventDTO;
import com.shawn.supervoting.dto.responseDTO.CommonResponseDTO;
import com.shawn.supervoting.dto.responseDTO.PaginatedResponseDTO.PaginatedResponseEventDTO;
import com.shawn.supervoting.dto.responseDTO.ResponseEventDTO;
import com.shawn.supervoting.entity.Event;
import com.shawn.supervoting.exception.DuplicateEntryException;
import com.shawn.supervoting.repo.EventRepo;
import com.shawn.supervoting.service.EventService;
import com.shawn.supervoting.util.Mapper.EventMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepo eventRepo, EventMapper eventMapper) {
        this.eventRepo = eventRepo;
        this.eventMapper = eventMapper;
    }

    @Override
    public CommonResponseDTO save(RequestEventDTO dto) {
        EventDTO eventDTO = EventDTO.builder()
                .eventId(UUID.randomUUID().toString())
                .description(dto.getDescription())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .build();

        eventRepo.save(eventMapper.toEvent(eventDTO));
        return new CommonResponseDTO(200,"Success","Id: "+eventDTO.getEventId(),new ArrayList<>());

    }

    @Override
    public PaginatedResponseEventDTO getAllPaginated(String searchText, int page, int size) {
        List<Event> events = eventRepo.findByPagination(searchText, PageRequest.of(page,size));
        long count = eventRepo.countBySearchText(searchText);

        ArrayList<ResponseEventDTO> arrayList = new ArrayList<>();
        for (Event e :events){
            arrayList.add(new ResponseEventDTO(
                 e.getEventId(),
                    e.getName(),
                    e.getDescription(),
                    e.getStartDate(),
                    e.getEndDate()
            ));
        }
        return new PaginatedResponseEventDTO(count,arrayList);
    }

    @Override
    public CommonResponseDTO delete(String id) {
        Optional<Event> event = eventRepo.findById(id);
        if(event.isEmpty()){
            throw new DuplicateEntryException("Event Not Exist");
        }
        eventRepo.delete(event.get());
        return new CommonResponseDTO(204, "Success", "Deleted successfully", new ArrayList<>());

    }

    @Override
    public CommonResponseDTO update(RequestEventDTO dto, String id) {
        Optional<Event> event = eventRepo.findById(id);
        if(event.isEmpty()){
            throw new DuplicateEntryException("Event Not Exist");
        }
        EventDTO eventDTO = EventDTO.builder()
                .eventId(id)
                .description(dto.getDescription())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .name(dto.getName())
                .build();

        Event updatedEvent = eventMapper.toEvent(eventDTO);
        eventRepo.save(updatedEvent);
        return new CommonResponseDTO(200, "Success", "Updated successfully", new ArrayList<>());

    }

    @Override
    public ResponseEventDTO getById(String id) {
        Optional<Event> event = eventRepo.findById(id);
        if(event.isEmpty()){
            throw new DuplicateEntryException("Event Not Exist");
        }
        return new ResponseEventDTO(
                event.get().getEventId(),
                event.get().getName(),
                event.get().getDescription(),
                event.get().getStartDate(),
                event.get().getEndDate()
        );
    }
}
