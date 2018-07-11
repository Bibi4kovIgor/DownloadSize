package com.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParseManager {

    private Document document;


    ParseManager(String url) {
        loadDoc(url);
    }

    /**
     * Getting urls of the all of the images at Web resource
     *
     * @return List of images URL
     * */
    List<String> getImageUrlList(){
        List<String> imagesList = new ArrayList<>();

        Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for (Element image: images){
            imagesList.add(image.attr("src"));
        }

        return imagesList;
    }

    /**
     * Getting urls of the all of the css-files at Web resource
     *
     * @return List of css-files URL
     * */
    List<String> getCssUrlList(){
        List<String> cssList = new ArrayList<>();

        Elements cssElements = document.select("link[rel=stylesheet]");
        for (Element cssEl: cssElements){
            cssList.add(cssEl.attr("href"));
        }

        return cssList;
    }

    /**
     * Getting urls of the all of the js-files at Web resource
     *
     * @return List of js-files URL
     * */
    List<String> getJsUrlList(){
        List<String> jsList = new ArrayList<>();

        Elements jsElements = document.getElementsByTag("script");
        for (Element jsEl: jsElements){
            jsList.add(jsEl.attr("src"));
        }

        return jsList;
    }


    /**
     * Load site as document by URL
     *
     * @param url Resource url as String
     *
     */
    private void loadDoc(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
