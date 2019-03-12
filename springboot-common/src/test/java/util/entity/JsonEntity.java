package util.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/27
 */
@Data
@ToString(exclude = {"id"})
@Builder
public class JsonEntity {
    private String id;
    @JsonProperty("property")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    @DateTimeFormat(pattern = "yyyy:MM:dd HH-mm:ss")
    private LocalDateTime localDateTime;
    private LocalDateTime createTime;

    private Integer integer;
    private Double aDouble;
    private BigDecimal bigDecimal;
    private List<JsonEntity> list;
}
