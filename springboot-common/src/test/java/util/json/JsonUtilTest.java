package util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.cnkeep.web.util.entity.JsonEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

/**
 * 描述: 测试JsonUtil
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public class JsonUtilTest {

    @Test
    public void dseriziablizeTest() throws JsonProcessingException {
        JsonEntity.JsonEntityBuilder builder = JsonEntity.builder();
        JsonEntity entity = builder.id("id")
                .date(new Date())
                .aDouble(0.2)
                .localDate(LocalDate.now())
                .localDateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .bigDecimal(new BigDecimal("12.003"))
                .list(Collections.EMPTY_LIST).build();
        String serializeUser = JsonUtil.serialize(entity);
        System.out.println(serializeUser);
    }

}