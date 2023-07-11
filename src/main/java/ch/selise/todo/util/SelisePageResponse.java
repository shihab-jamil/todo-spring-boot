package ch.selise.todo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Rhidoy
 * @created 23/05/2023
 * @package ch.selise.todo.util
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SelisePageResponse<T> extends SelisePage {
    //for page data
    private List<T> data;
    private Long totalRecords;
    private int totalPages;

    public SelisePageResponse(SelisePage page) {
        super(page.getPage(),
                page.getPerPage(),
                page.getSort(),
                page.direction,
                page.getFilter()
        );
    }

    public SelisePageResponse<T> setResponse(List<T> data, Page<?> page) {
        setData(data);
        setTotalPages(page.getTotalPages());
        setTotalRecords(page.getTotalElements());
        return this;
    }
}
