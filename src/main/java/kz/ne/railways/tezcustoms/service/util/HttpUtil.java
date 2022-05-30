package kz.ne.railways.tezcustoms.service.util;

import com.google.gson.Gson;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.FormData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class HttpUtil {
g
    @Value("${services.external.gateway.contractData.url}")
    private String gatewayContractDataUrl;
    @Value("${services.external.gateway.contractDoc.url}")
    private String gatewayContractDocUrl;

    private Gson gson = new Gson();

    public byte[] getContractDoc(String invoiceId) {
        String url = gatewayContractDocUrl + "&invoiceId=" + invoiceId;

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("charset", "UTF-8");
        httpGet.addHeader("Accept-Charset", "UTF-8");

        CloseableHttpClient httpclient = null;
        try {
            // SSLContext ctx = SSLContexts.custom().useProtocol("TLSv1.2").build();
            // httpclient = HttpClients.custom().setSSLSocketFactory(new
            // SSLConnectionSocketFactory(ctx)).build();
            httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {

                if (entity != null) {

                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                    entity.getContent().transferTo(result);
                    return result.toByteArray();
                }

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

    public FormData getContractData(String startSta, String destSta, String expCode, String invoiceNum) {
        String url = gatewayContractDataUrl + "&startSta=" + startSta;
        url += "&destSta=" + destSta;
        // url += "&expCode=" + expCode;
        url += "&invoiceNum=" + invoiceNum;

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
        CloseableHttpClient httpclient = null;
        try {
            // SSLContext ctx = SSLContexts.custom().useProtocol("TLSv1.2").build();
            // httpclient = HttpClients.custom().setSSLSocketFactory(new
            // SSLConnectionSocketFactory(ctx)).build();
            httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {

                String strResponse = null;
                if (entity != null) {
                    strResponse = EntityUtils.toString(entity, "UTF-8");
                    FormData formData = gson.fromJson(strResponse, FormData.class);
                    log.debug("invoiceId: {}", formData.getInvoiceId());
                    return formData;
                }

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
