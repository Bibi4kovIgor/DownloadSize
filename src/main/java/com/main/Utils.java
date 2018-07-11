package com.main;

class Utils {

    /**
     * Formatting size in human-readable
     *
     * @param bytes resource size in bytes
     * @return size in human-readable format
     *
     * */
    static String getHumanReadableSize(long bytes){
        if (bytes < 1024) return bytes + " B";
        int z = (63 - Long.numberOfLeadingZeros(bytes)) / 10;
        return String.format("%.1f %sB", (double)bytes / (1L << (z*10)), " KMGTPE".charAt(z));
    }
}
