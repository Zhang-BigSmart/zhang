import net.sf.json.util.JSONUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/12/23
 * @history
 */
public class LoginTest {

    private static CloseableHttpClient http_client = null;
    private static HttpPost httpPost = null;
    private static Header cookieHeader= null;

    // 初始化
    public void initHttpClient(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(200);
        httpClientConnectionManager.setDefaultMaxPerRoute(40);
        http_client = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();
    }

    @Test
    public void login() throws Exception {

        String url = "http://120.76.144.118:8080/guns/login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "admin");
        params.put("password", "1111111");

        initHttpClient();
        httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        if (Boolean.parseBoolean( http_client.execute(httpPost, loginResponseHandler()))){
            System.out.println("login success");
        }else{
            System.out.println("login fail");
        }
    }

    @Test
    public void getAssets() throws Exception{

        String url = "http://120.76.144.118:8080/guns/assets/list";
        //String url = "http://localhost:8081/assets/list";
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "car");
        params.put("order", "desc");
        params.put("offset", "0");
        params.put("limit", "14");

        initHttpClient();
        //login();
        String body = null;
        httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", "shiroCookie=f78ee258-531f-4bbc-bd9a-2f858809a356");
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        body = http_client.execute(httpPost, createResponseHandler());
        System.out.println(body);
    }

    private static ResponseHandler<String> loginResponseHandler() {
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                System.out.println("response"+ JSONUtils.valueToString(response));
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                EntityUtils.consume(entity);
                System.out.println(res);
                int status = response.getStatusLine().getStatusCode();
                if (status == 302){
                    // 登录成功
                    Header[] headers = response.getHeaders("Set-Cookie");
                    if (headers.length > 0){
                        for (Header header : headers) {
                            if (header.getValue().contains("shiroCookie")) {
                                cookieHeader = header;
                            }
                        }
                        return "true";
                    }else{
                        return "false";
                    }
                }else{
                    return "false";
                }
            }
        };
        return responseHandler;
    }

    private static ResponseHandler<String> createResponseHandler() {
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                System.out.println("response"+ JSONUtils.valueToString(response));
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    String res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    EntityUtils.consume(entity);
                    return res;
                } else {
                    throw new ClientProtocolException("Unexpected apex response  status: " + status);
                }
            }
        };
        return responseHandler;
    }
}
