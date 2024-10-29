package com.indra.StaySmart.dto.request;


import com.indra.StaySmart.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    private UUID roomId;
    private String roomName;
    private RoomType roomType;
    private String amenities;
    private Date createdAt;
    private Date updatedAt;
}
