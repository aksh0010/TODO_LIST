package com.example.listviewapplication;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    public static final String FILENAME="filesave.dat";
    public static void writeData(ArrayList<String> list, Context context){


        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }

    }
//    public static ArrayList<String> ReadData(Context context){
//        ArrayList <String> temp1 = new ArrayList<>();
//        try {
//            FileInputStream fis = new FileInputStream(FILENAME);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            temp1 = ((ArrayList<String>)ois.readObject());
//
//            ois.close();
//            fis.close();
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        return temp1;
//
//    }
    public static ArrayList<String> ReadData(Context context){
        ArrayList<String> itemList = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();

            ois.close();
            fis.close();

        } catch (FileNotFoundException e) {
            itemList = new ArrayList<>();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return itemList;
    }

}
