package com.server.app.dto.permission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissionDto {
    private String title;
    private String method;
    private String path;
}