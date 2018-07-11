package com.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main (String[] args) {
        // Set up Logging
        try {
            System.out.println(System.getProperty("user.dir"));
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        DownloadSize downloadSize = new DownloadSize();

        switch (args.length){
            case 0:
                System.out.println("Usage: URL [resource type 1]... ...[resource type n]");
                System.out.println("-h - get help by additional parameters");
                return;
            case 1:
                if (args[0].equals("-h")){
                    paramsMenu();
                    break;
                }

                System.out.println("Web resource size without embedded resources");

                try {
                    System.out.println(downloadSize.getWebSiteSize(new URL(args[0])));
                }catch (MalformedURLException e){
                    log.log(Level.WARNING, "Wrong URL format Exception", e);
                    System.err.println("Wrong URL format");
                }

                return;
            case 2:

                try {
                    String webSiteSize = downloadSize.getWebSiteSize(new URL(args[0]));

                    System.out.println("Web resource size without embedded resources");
                    System.out.println(webSiteSize);
                    ResourceManager resources = new ResourceManager(new URL(args[0]));
                    parseAdditionalParameters(args[1], resources);
                }catch (MalformedURLException e) {
                    log.log(Level.WARNING, "Wrong URL format Exception", e);
                    System.err.println("Wrong URL format");
                }

                break;
            default:
                System.out.println("Wrong parameters!");


//                downloadSize.getWebResourceSize(args);
        }
    }


    /**
     * Params menu display
     *
     * */
    private static void paramsMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("Menu by parameters\n");
        sb.append("-a - Get Web Resource size with all embedded resources\n");
        sb.append("-i - Get Web Resource size with embedded images only\n");
        sb.append("-c - Get Web Resource size with embedded *.css only\n");
        sb.append("-j - Get Web Resource size with embedded *.js only\n");
        sb.append("\nExample - Get Web Resource size with images and js files: \t \n");
        sb.append("\t java -jar DownloadSize-1.0.jar 'http://example.org -ij '\n");
        System.out.println(sb);
    }

    /**
     * Parsing user input and obtaining parameters
     *
     * @param params, resources
     * */
    private static void parseAdditionalParameters(String params, ResourceManager resources){
        if (params.contains("a")){
            System.out.println("Web site size with all embedded resources ");
            System.out.println(resources.getResourcesSizes());
        }

        if (params.contains("i")) {
            System.out.println("Size of all images");
            System.out.println((resources.getImageSizesString()));
        }

        if (params.contains("c")){
            System.out.println("Size of all css");
            System.out.println((resources.getCssSizesString()));
        }

        if (params.contains("j")){
            System.out.println("Size of all js files");
            System.out.println((resources.getJsSizesString()));
        }




    }
}
