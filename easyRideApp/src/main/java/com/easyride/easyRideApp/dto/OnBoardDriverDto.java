package com.easyride.easyRideApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardDriverDto {
    private Long userId;
    private String vechileId;
}
