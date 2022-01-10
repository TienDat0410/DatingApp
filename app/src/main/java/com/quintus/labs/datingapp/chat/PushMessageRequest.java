package com.quintus.labs.datingapp.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushMessageRequest {
    private String targetId;
    private String message;


}
