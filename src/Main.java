package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> directory = new ArrayList<>(Arrays.asList(
                "src/",
                "src/main",
                "src/test",
                "res/",
                "res/drawables",
                "res/vectors",
                "res/icons",
                "savegames/", "temp/"));

        ArrayList<String> file = new ArrayList<>(Arrays.asList(
                "C://Programming//JAVA нетология//Main//HWFiles//src//main//Main.java",
                "C://Programming//JAVA нетология//Main//HWFiles//src//main//Utils.java",
                "C://Programming//JAVA нетология//Main//HWFiles//temp//temp.txt"));

        for (int i = 0; i < directory.size() && i < file.size(); i++) {
            createDirectory(directory.get(i));
            createFile(file.get(i));
        }

        //Задание 2
        GameProgress gameProgress = new GameProgress(95, 10, 5, 300.42);
        savegame("C://Programming//JAVA нетология//Main//HWFiles//savegames//save3.dat", gameProgress);
        zipFiles("C://Programming//JAVA нетология//Main//HWFiles//savegames//GameProgress.zip", "C://Programming//JAVA нетология//Main//HWFiles//savegames//save3.dat");
        deleteFile("C://Programming//JAVA нетология//Main//HWFiles//savegames//save3.dat");
    }
    public static void createDirectory(String url) {
        File file = new File(url);
        file.mkdir();
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        String text = sb.toString();
        try {
            FileWriter writer = new FileWriter("temp/temp.txt", false);
            writer.write(text);
            writer.append("\nВсе файлы были созданы успешно!");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createFile(String url) {
        File file = new File(url);
        try {
            file.createNewFile();
            StringBuilder sb = new StringBuilder();
            sb.append(file);
            String text = sb.toString();
            FileWriter writer = new FileWriter("temp/temp.txt", false);
            writer.write(text);
            writer.append("\nВсе файлы были созданы успешно!");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Задание 2
    public static void savegame(String url, GameProgress gameProgress) {
        try(FileOutputStream fos = new FileOutputStream(url);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String url, String file) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(url));
            FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry("packed_game.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteFile(String url) {
        File save = new File(url);
        save.delete();
    }
}
