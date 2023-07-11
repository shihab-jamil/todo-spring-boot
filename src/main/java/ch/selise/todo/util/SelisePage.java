package ch.selise.todo.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhidoy
ch.selise.todo.util
 * @package ch.selise.todo.util
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelisePage {
    protected int page = 0;
    protected int perPage = 10;
    protected List<String> sort = List.of("id");
    protected Sort.Direction direction = Sort.Direction.DESC;
    private String filter = null;

    public SelisePage(int page, int perPage) {
        this.page = page;
        this.perPage = perPage;
    }

    @JsonSetter
    public void setDirection(String direction) {
        if (direction == null)
            return;
        this.direction = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @JsonGetter
    public String getDirection() {
        return direction.toString().toLowerCase();
    }

    public String getFilter() {
        if (filter == null || filter.isBlank())
            return null;
        return filter;
    }

    @JsonIgnore
    public Pageable getPageable() {
        List<Sort.Order> orders = new ArrayList<>();
        for (String s : getSort()) {
            s = s.trim();
            orders.add(new Sort.Order(direction, s));
        }
        return PageRequest.of(getPage(), getPerPage(), Sort.by(orders));
    }

    @JsonIgnore
    public <T> SelisePageResponse<T> getResponse(List<T> data, Page<?> page) {
        SelisePageResponse<T> response = new SelisePageResponse<>(this);
        return response.setResponse(data, page);
    }
}
