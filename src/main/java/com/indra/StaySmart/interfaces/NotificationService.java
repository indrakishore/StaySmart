package com.indra.StaySmart.interfaces;

import com.indra.StaySmart.dto.NotificationDataDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void sendNotification(NotificationDataDto notificationDataDto);

}
