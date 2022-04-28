package kz.ne.railways.tezcustoms.service.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.core.util.Json;
import kz.ne.railways.tezcustoms.service.model.Contract;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
public class HttpUtil {

    @Value("${services.external.asudkr.authenticationUrl}")
    private String asudkrUrl;
    @Value("${services.external.asudkr.username}")
    private String asudkr_username;
    @Value("${services.external.asudkr.password}")
    private String asudkr_password;

    private Gson gson = new Gson();

    public Contract getContract() throws UnsupportedEncodingException {
        String token = getToken();

        log.debug("Util: getContract");
        return null;
    }

    public String getToken() throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(asudkrUrl);
        httpPost.addHeader("Content-Type", "application/json");

        JsonObject jsonObj = new JsonObject();
        jsonObj.add("username", gson.toJsonTree(asudkr_username));
        jsonObj.add("password", gson.toJsonTree(asudkr_password));

        httpPost.setEntity(new StringEntity(jsonObj.toString()));
        CloseableHttpClient httpclient = null;
        try {
//            SSLContext ctx = SSLContexts.custom().useProtocol("TLSv1.2").build();
//            httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(ctx)).build();
            httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {

                String strResponse = null;
                if (entity != null) {
                        strResponse = EntityUtils.toString(entity);
                        JsonObject obj = gson.fromJson(strResponse, JsonObject.class);
                        log.debug("Token: {}", obj.get("token"));
                }

//                return gson.fromJson(strResponse, Contract.class);
            }
        } catch (RuntimeException e) {
            log.error("RuntimeException in checkNaturalPersonFromStatApi: ", e);
        } catch (Exception e) {
            log.error("Exception in checkNaturalPersonFromStatApi: ", e);
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    log.error("IOException during closing in checkNaturalPersonFromStatApi: ", e);
                }
            }

        }

        return null;
    }

}
