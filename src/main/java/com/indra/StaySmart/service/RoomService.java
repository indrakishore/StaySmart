package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) {
        return new RoomResponseDto();
    }
}
