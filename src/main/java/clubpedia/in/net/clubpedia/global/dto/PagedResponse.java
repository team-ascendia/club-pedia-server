package clubpedia.in.net.clubpedia.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PagedResponse<T> {
    private Pagination pagination;
    private List<T> items;

    public PagedResponse(Page<T> pageData) {
        this.pagination = new Pagination(pageData);
        this.items = pageData.getContent();
    }

    @Getter
    @Setter
    public static class Pagination {
        private int page;
        private int size;
        private int totalPages;
        private long totalItems;

        public Pagination(Page<?> pageData) {
            this.page = pageData.getNumber() + 1;
            this.size = pageData.getSize();
            this.totalPages = pageData.getTotalPages();
            this.totalItems = pageData.getTotalElements();
        }
    }
}
