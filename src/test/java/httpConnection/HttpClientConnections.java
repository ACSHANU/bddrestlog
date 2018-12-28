package httpConnection;

import Utils.RestEvidence;
import Utils.Utilities;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Duvvuri on 23/12/2018.
 */
public class HttpClientConnections {


    static final Logger logger = LogManager.getLogger(HttpClientConnections.class.getName());

    static HttpHost proxy;
    //public static Map<String,String> queryStringParams= new HashMap<String, String>();


    static void setProxy(String hostname, String username, String password) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(proxy),
                new UsernamePasswordCredentials(username, password));
        BasicScheme basicAuth = new BasicScheme();


// Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();

    }


    public static List<Header> setDefaultHeaders() {

        ArrayList<Header> al = new ArrayList<Header>();
        al.add(new BasicHeader(
                HttpHeaders.CONTENT_TYPE, "application/json"));
       /* al.add(new BasicHeader(
                HttpHeaders.AUTHORIZATION, "Basic aGFudW1hZEBnbWFpbC5jb206aGFub2EzMzM="));*/


        Header header[] = new Header[al.size()];
        header = al.toArray(header);

        return al;

    }

    public static void main(String[] args) {
        for (Header hdr : setDefaultHeaders()) {
            System.out.println(hdr.getName() + hdr.getValue());

        }

    }

    public static CloseableHttpClient buildHttpClient() {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultHeaders(setDefaultHeaders())
                .build();
        return httpclient;

    }

    private static List<NameValuePair> getPostParameterPairs(Map<String, String> postParams) {
        List<NameValuePair> result = new ArrayList<NameValuePair>(postParams.size());
        for (String key : postParams.keySet()) {
            result.add(new BasicNameValuePair(key, postParams.get(key)));
        }
        return result;
    }

    public static URI buildURI(String path) throws URISyntaxException {
        return buildURI(path, null);
    }

    public static URI buildURI(String path, Map<String, String> queryStringParams) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme(Utilities.envProps.getScheme())
                .setHost(Utilities.envProps.getHostnme())
                .setPath(path);

/*.setParameter("q", "httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "")*/
        if (queryStringParams != null)
            uriBuilder.setParameters(getPostParameterPairs(queryStringParams));

logger.info("uri :" + uriBuilder.toString());
        return uriBuilder.build();
    }

    public static String getHTTPResponse(final Object obj) throws java.io.IOException {
        String responseBody = "";
        CloseableHttpClient httpclient = buildHttpClient();
        try {
    /*       final HttpGet httpget = new HttpGet(uri);
           System.out.println("Executing request " + httpget.getRequestLine());
    */       // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    return HandleResponse(response, obj);

                }

            };

            if (obj instanceof HttpGet)
                responseBody = httpclient.execute((HttpGet) obj, responseHandler);
            else
                responseBody = httpclient.execute((HttpPost) obj, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
        return responseBody;
    }


   /*public static void addParameter(String key,String value)
   {

       queryStringParams.put(key,value);
   }*/

    public static String HandleResponse(final HttpResponse response, Object obj) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        StringBuilder str = new StringBuilder();
        StringBuilder strReq = new StringBuilder();
        HttpGet httpget = null;
        HttpPost httppost = null;
        Header[] headers;
        String url;
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            for (Header header : response.getAllHeaders()) {
                logger.info(header.getName() + ":" + header.getValue());
                str.append(header.getName() + ":" + header.getValue());
            }

            if (obj instanceof HttpGet) {
                httpget = (HttpGet) obj;
                headers = httpget.getAllHeaders();
                url = httpget.getURI().toURL().toString();
            } else {
                httppost = (HttpPost) obj;
                headers = httppost.getAllHeaders();
                url = httppost.getURI().toURL().toString();
            }


            for (Header header : headers) {
                logger.info(header.getName() + ":" + header.getValue());
                strReq.append(header.getName() + ":" + header.getValue());
            }

            if (entity == null) {
                return null;
            } else {
                String res = EntityUtils.toString(entity);
                RestEvidence oEvi = new RestEvidence();

                oEvi.setUrl(httpget.getURI().toURL().toString());
                oEvi.setStatusCode(Integer.toString(status));
                oEvi.setResHeaders(str.toString());
                oEvi.setReqHeaders(strReq.toString());
                oEvi.setResponse(res);
                oEvi.setRequest("");
                Utilities.writeHTML(oEvi);

                return res;
            }
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }

    HttpGet createHTTPGetConnection(String username, String password) {

        HttpClient client = HttpClients.createDefault();

        String url = "";
        HttpGet hget = new HttpGet(url);
        String authHeader = Base64.encodeBase64String(url.getBytes());
        hget.addHeader("authorozation", "");


        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("Username", "Password"));
        HttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();

        return null;
    }
}
