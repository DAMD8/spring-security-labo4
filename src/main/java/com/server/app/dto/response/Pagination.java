package com.server.app.dto.response;

import java.util.List;

public record Pagination<T>(
        List<T> data,
        PaginationMeta pagination
) {
    public void setData(List<T> content) {
    }

    public void setPagination(PaginationMeta meta) {
    }
}