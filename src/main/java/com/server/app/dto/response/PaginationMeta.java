package com.server.app.dto.response;

public record PaginationMeta(
        int page,
        int pageSize,
        int pageCount,
        long total
) {
    public void setPage(int number) {
    }

    public void setPageCount(int totalPages) {
    }

    public void setPageSize(int size) {
    }

    public void setTotal(int totalElements) {
    }
}
