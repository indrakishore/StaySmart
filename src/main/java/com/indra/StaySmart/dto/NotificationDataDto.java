package com.indra.StaySmart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDataDto {

    String userEMail;
    String phoneNumber;
    String subject;
    String text;
}