package com.koubei.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLDecoder;

public class com_HttpClientHelper {
    private static String httpPost(String url, String jsonParam) throws Exception {
        //1.获得一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        if (null != jsonParam) {
            //解决中文乱码问题
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            method.setEntity(entity);
        }
        HttpResponse result = httpClient.execute(method);
        //url = URLDecoder.decode(url, "UTF-8");
        if (result.getStatusLine().getStatusCode() == 200) {
            String str = EntityUtils.toString(result.getEntity());
            return str;
        }
        return null;
    }

    private static String httpGet(String url) throws Exception {
        //1.获得一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        url = URLDecoder.decode(url, "UTF-8");
        //发送get请求
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        /**请求发送成功，并得到响应**/
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
            /**把json字符串转换成json对象**/
            return strResult;
        } else {
            return null;
        }
    }
}
