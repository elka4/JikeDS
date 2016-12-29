package com.jikexueyuan.Java.JSON;

import com.google.gson.JsonObject;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;

/**
 * Created by Student on 12/28/16.
 */
public class ReadJSON {
    public static void main(String[] args) {

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(new FileReader("test.json"));
    }


}
