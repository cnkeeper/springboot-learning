package com.github.cnkeep.web.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.cnkeep.web.util.date.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 依赖于Jackson的Json格式化工具
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/8/8
 */
public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String TIME_PATTERN = "HH:mm:ss";

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME_PATTERN)));

//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(javaTimeModule);
    }
    /**
     * @description 序列化
     * @param value
     * @return String
     * @throws JsonProcessingException
     */
    public static String serialize(Object value) throws JsonProcessingException{
        return mapper.writeValueAsString(value);
    }

    /**
     * @description 序列化
     * @param value
     * @return byte[]
     * @throws JsonProcessingException
     */
    public static byte[] serializeToByte(Object value) throws JsonProcessingException{
        return mapper.writeValueAsBytes(value);
    }

    /**
     * @description 反序列化
     * @param content
     * @param valueType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T deserialize(String content,Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{
        return mapper.readValue(content, valueType);
    }

    /**
     * @description 反序列化
     * @param content
     * @param valueType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T deserializeFromByte(byte[] content,Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{
        return mapper.readValue(content, valueType);
    }
}
