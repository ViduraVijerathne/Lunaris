package com.vidura.lunaris.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DefaultResponse {
    private String message;
    private Object data;
    private DefaultResponseType responseType;
}
