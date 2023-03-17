package fit.wenchao.kotlinplayground.utils;


import java.util.List;

public class LogUtils {
    public static <T> String queryResultMessage(String dataTag, List<T> list) {
        String result = StrUtils.ft("查询 {} 数据成功，结果条数：{}，结果数据：{}", dataTag, list.size(), list);
        return result;
    }
    public static String queryParamsMessage(String dataTag, Object params) {
        String result = StrUtils.ft("请求查询 {} 数据，参数：{}", dataTag, params);
        return result;
    }

    public static <T> String insertSuccessMessage(String dataTag, T data) {
        String result = StrUtils.ft("插入 {} 数据：{} 成功",dataTag, data) ;
        return result;
    }

    public static String createParamsMessage(String dataTag, Object paramList) {
        String result = StrUtils.ft("请求创建 {} ，参数：{}",dataTag, paramList) ;
        return result;
    }


    public static String updateParamsMessage(String dataTag, Object paramList) {
        String result = StrUtils.ft("请求修改 {} ，参数：{}",dataTag, paramList) ;
        return result;
    }

    public static String removeParamsMessage(String dataTag, Object paramList) {
        String result = StrUtils.ft("请求删除 {} ，参数：{}",dataTag, paramList) ;
        return result;
    }

    public static String removeSuccessMessage(String dataTag, Object data) {
        String result = StrUtils.ft("删除 {} 数据：{} 成功",dataTag, data) ;
        return result;
    }

    public static String updateSuccessMessage(String dataTag, Object updated) {
        String result = StrUtils.ft("更新 {} 数据成功：{}",dataTag, updated) ;
        return result;
    }
}
