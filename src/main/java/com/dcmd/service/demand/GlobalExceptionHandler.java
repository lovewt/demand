package com.dcmd.service.demand;

import com.dcmd.common.core.utils.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yy
 * @JDK version used:       	1.8
 * @Modified By:            	<修改人中文名或拼音缩写>
 * @Modified Date:          	<修改日期，格式:YYYY年MM月DD日>
 * @Why & What is modified: 	统一异常处理类
 * @create 2018-05-11 16:30
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        StackTraceElement[] stackTrace = e.getStackTrace();
        attributes.put("data", "哎呀，出异常了");
        String exceptionInfo = e.toString();
        if (e instanceof ErrorInfo) {
            ErrorInfo customException = (ErrorInfo) e;
            attributes.put("code", 500);
            attributes.put("message", exceptionInfo.substring(42));
        } else {
            attributes.put("code", "500");
            if (toEmpty(exceptionInfo) && Arrays.asList(exceptionInfo.split(":")).contains("org.springframework.jdbc.BadSqlGrammarException")) {
                attributes.put("message", "SQL出错了,原因是: " + exceptionInfo.split(":")[exceptionInfo.split(":").length - 1]);
                attributes.put("errorClassName", stackTrace[0].getFileName());
                attributes.put("errorMethodName", stackTrace[0].getMethodName());
                attributes.put("errorNumber", stackTrace[0].getLineNumber());
            } else {
                attributes.put("message", exceptionInfo);
                attributes.put("errorClassName", stackTrace[0].getFileName());
                attributes.put("errorMethodName", stackTrace[0].getMethodName());
                attributes.put("errorLineNumber", stackTrace[0].getLineNumber());
            }
        }
        return attributes;
    }

    /**
     * @Comments: 是否为空(字符串|集合|数组:!=null or size>0, 数字 > 0, boolean:true)
     * @param: 不定参数(不定类型)
     * @return: boolean true不为空，false为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean toEmpty(Object... obj) {
        if (obj != null) {
            for (Object o : obj) {
                if (o != null) {
                    if (o instanceof String && !o.equals("")) {//传值为字符串    不能为""
                        continue;
                    } else if (o instanceof Number) {//传值为数字  && ((Number) o).doubleValue() > 0
                        continue;
                    } else if (o instanceof Boolean && (Boolean) o) {//传值为 boolean 不能为false
                        continue;
                    } else if (o instanceof Collection && !((Collection) o).isEmpty()) {//传值为Collection 长度 > 0
                        continue;
                    } else if (o instanceof Map && !((Map) o).isEmpty()) {//传值为map 长度 > 0
                        continue;
                    } else if (o instanceof Object[] && ((Object[]) o).length > 0) {//传值为数组 长度 > 0
                        continue;
                    } else {//否则  为没有值
                        return false;
                    }
                } else {//否则  为没有值
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

