package com.main;

import com.main.exceptions.ErrorReadingAddressException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloadSize{

    private HttpURLConnection httpURLConnection;

    private int requestCount;

    private static Logger logger = Logger.getLogger(DownloadSize.class.getName());

    public DownloadSize(){
        this.requestCount = 0;
    }

    /**
     * Obtaining Web resource size bu url address
     *
     * @param url Resource url address
     * @return Size of the resource
     * */
    long getWebResourceSize(URL url) {
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 7.1; Trident/5.0)");
            httpURLConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty( "Accept", "/");
            httpURLConnection.setRequestProperty("Set-Cookie", "__Host-ID=123; Secure; Path=/");
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.connect();
            httpURLConnection.getInputStream();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Exception in obtaining resource size", e);
        }
        finally {
            httpURLConnection.disconnect();
        }

        long size = httpURLConnection.getContentLengthLong();
        requestCount++;
        System.out.println("Request count by operation: " + requestCount);

        if (size < 0) throw new ErrorReadingAddressException("Could not determine resource size: '"+ url.toString() +"'", null, true, false);

        return size;
    }

    /**
     * Casting size to human readable format
     *
     * @param url Resource url address
     * @return resource size as String
     * */
    String getWebSiteSize(URL url){
        long size = getWebResourceSize(url);
        return Utils.getHumanReadableSize(size);
    }
}
