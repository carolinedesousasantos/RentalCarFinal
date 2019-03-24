package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class JsonUtil {

    public static void jsonGenerator(HttpServletResponse response, Object objectToConvert) throws JsonProcessingException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(objectToConvert);
        out.print(jsonString);
        out.flush();
    }
}
