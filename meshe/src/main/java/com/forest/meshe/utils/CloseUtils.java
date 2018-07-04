package com.forest.meshe.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by forest on 2018/5/8 0008.
 */

public class CloseUtils {

    public static void closeQuietly(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
