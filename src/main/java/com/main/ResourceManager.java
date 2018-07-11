package com.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class ResourceManager
 *
 * */
class ResourceManager {
    private static Logger logger = Logger.getLogger(ResourceManager.class.getName());
    private DownloadSize downloadSize = new DownloadSize();
    private ParseManager parseManager;
    private URL url;

    ResourceManager(URL url){
        this.parseManager = new ParseManager(url.toString());
        this.url = url;
    }

    /**
     * Forming total size of the embedded resources
     *
     * @return Resources summarize size
     * */
    String getResourcesSizes() {
        return Utils.getHumanReadableSize(downloadSize.getWebResourceSize(url) + getImagesSize() + getJsSize() + getCssSize());
    }

    /**
     * Getting total images size by Website
     *
     * @return images summarize sizes
     * */
    private long getImagesSize() {
        List<String> imagesUrls = parseManager.getImageUrlList();
        long imagesSize = 0;

        for (String imageUrl: imagesUrls){
            try {
                long resSize = downloadSize.getWebResourceSize(new URL(imageUrl));
                System.out.printf("Resource: %35s Size: %7s\n",imageUrl, Utils.getHumanReadableSize(resSize));
                imagesSize += resSize;
            } catch (MalformedURLException e) {
                System.err.printf("Error with URL: %10s\n ", imageUrl);
                logger.log(Level.WARNING, "Wrong URL Exception", e);
            }
        }

        return imagesSize;
    }

    /**
     * Getting total css-files size by Website
     *
     * @return css-files summarize sizes
     * */
    private long getCssSize() {
        List<String> cssHref = parseManager.getCssUrlList();
        long cssSize = 0;

        for (String cssUrl: cssHref) {
            try {
                long resSize = downloadSize.getWebResourceSize(new URL(cssUrl));
                System.out.printf("Resource: %35s Size: %7s\n", cssUrl, Utils.getHumanReadableSize(resSize));
                cssSize += resSize;
            }catch (MalformedURLException e){
                System.err.printf("Error with URL: %10s\n ", cssUrl);
                logger.log(Level.WARNING, "Wrong URL Exception", e);
            }
        }
        return cssSize;
    }

    /**
     * Getting total js-files size by Website
     *
     * @return js-files summarize sizes
     * */
    private long getJsSize() {
        List<String> jsHref = parseManager.getJsUrlList();
        long jsSize = 0;

        for (String jsUrl: jsHref){
            try {
                long resSize = downloadSize.getWebResourceSize(new URL(jsUrl));
                System.out.printf("Resource: %35s Size: %7s\n",jsUrl, Utils.getHumanReadableSize(resSize));
                jsSize += resSize;
            } catch (MalformedURLException e) {
                System.err.printf("Error with URL: %10s\n ", jsUrl);
                logger.log(Level.WARNING, "Wrong URL Exception", e);
            }

        }
        return jsSize;
    }

    /**
     * Getting images sizes in human-readable format
     *
     * @return size as String
     * */
    String getImageSizesString() {
        long imageSizes = getImagesSize();
        System.out.print("Summarized images size\t");
        return Utils.getHumanReadableSize(imageSizes);
    }

    /**
     * Getting js sizes in human-readable format
     *
     * @return size as String
     * */
    String getJsSizesString() {
        long jsSizes = getJsSize();
        System.out.print("Summarized js size\t");
        return Utils.getHumanReadableSize(jsSizes);
    }

    /**
     * Getting css sizes in human-readable format
     *
     * @return size as String
     * */
    String getCssSizesString() {
        long cssSizes = getCssSize();
        System.out.print("Summarized css size\t");
        return Utils.getHumanReadableSize(cssSizes);
    }

}
