package com.devilsoftware.healthy;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TeacherClass {

    private static final String newLine = System.getProperty("line.separator");
    private static int[] intarray = new int[3];

    private static FileWriter fileWriter;
    private static BufferedReader bufferedReader;

    static String fileName = "train.data";

    public TeacherClass() throws IOException {
        fileWriter = new FileWriter(fileName, true);
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String text = bufferedReader.readLine();

        String[] strArray = text.split(" ");


        for (int i = 0; i<3; i++){
            intarray[i]=Integer.parseInt(strArray[i]);
        }
    }

    public void update() throws IOException {
        fileWriter = new FileWriter(fileName, true);
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String text = bufferedReader.readLine();

        String[] strArray = text.split(" ");


        for (int i = 0; i<3; i++){
            intarray[i]=Integer.parseInt(strArray[i]);
        }
    }

    public void addIllness(float[] map, int id) throws IOException {
        System.out.println("New illness: " + id);
        if(map.length==intarray[1] & id<=intarray[2]){
            Writer output;
            output = new BufferedWriter(new FileWriter(fileName, true));
            String s1 = getStringOfArray(map, false);
            output.append(newLine).append(s1);

            float[] dl = new float[intarray[2]];
            dl[id-1] = 1;

            s1 = getStringOfArray(dl, true);
            output.append(newLine).append(s1);

            intarray[0] ++;

            close();
            output.close();
        } else {
            update();
            if (intarray[1]<map.length & id==(intarray[2]-1)){
                addEmptyFields(map.length, false);
                intarray[1] = map.length;
                close();
                addIllness(map, id);
                System.out.println("Увелечение обьемов симптом");
            } else if(intarray[1]==map.length & id>(intarray[2])){
                addEmptyFields(id, true);
                intarray[2] = id;
                close();
                addIllness(map, id);
                System.out.println("Новый id");
            } else if(intarray[1]<map.length & id>(intarray[2])){
                addEmptyFields(map.length, false);
                intarray[1] = map.length;
                close();
                addEmptyFields(id, true);
                intarray[2] = id;
                close();
                addIllness(map, id);
                System.out.println("Новый id и увелечение обьемов симптом");
            }
            close();

        }
        normalizeFile();
    }

    private String getStringOfArray(float[] map, boolean id) throws IOException {
        update();
        String s1 = "";
        int index = 0;

        int lastNumber;
        if(id){
            lastNumber = intarray[2];
        } else {
            lastNumber = intarray[1];
        }
        for (float f : map){
            String fl = Float.toString(f).replace(".",",");
            if(index!=lastNumber-1) s1 += fl + " "; else s1 += fl;
            index++;
        }
        return s1;
    }

    public int getCountEntries(){
        return intarray[0];
    }

    public int getNumberOfInputNeurons(){
        return intarray[1];
    }

    public int getNumberOfOutputNeurons(){
        return intarray[2];
    }

    public void close() throws IOException {
        RandomAccessFile f = new RandomAccessFile(new File(fileName), "rw");
        f.seek(0);
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<3; i++){
            if(i!=2) s.append(intarray[i]).append(" "); else s.append(intarray[i]);
        }
        f.write((s + newLine).getBytes());
        f.close();
    }

    private void addEmptyFields(int newLength, boolean id) throws IOException {
        update();

        bufferedReader = new BufferedReader(new FileReader(fileName));
        bufferedReader.readLine();

        for(int j = 0; j<intarray[0]*2; j++) {
            String text = bufferedReader.readLine();
            ChangeLineInFile changeFile = new ChangeLineInFile();
            String[] strArray = text.split(" ");
            String[] newStrArray = Arrays.copyOf(strArray, newLength);

            for (int i= 0; i<newStrArray.length; i++){
                if (newStrArray[i]==null){
                    newStrArray[i] = "0,0";
                }
            }

            if (!id){
                if(j % 2 == 0){
                    String s = "";
                    for (int i = 0; i < newStrArray.length; i++) {
                        if (i != newLength-1) s += newStrArray[i] + " "; else s += newStrArray[i];
                    }

                    changeFile.changeALineInATextFile(fileName, s, j+2);

                }
            } else {
                if(j % 2 == 1){
                    String s = "";
                    for (int i = 0; i < newStrArray.length; i++) {
                        if (i != newLength-1) s += newStrArray[i] + " "; else s += newStrArray[i];
                        System.out.println(i);
                    }
                    changeFile.changeALineInATextFile(fileName, s, j+2);
                }
            }
        }
    }

    void normalizeFile() throws FileNotFoundException {

        File file1 = new File(fileName);
        File file2 = new File("buffer.data");

        Scanner file = new Scanner(file1);
        PrintWriter writer = new PrintWriter(file2);

        while (file.hasNext()) {
            String line = file.nextLine();
            if (!line.isEmpty()) {
                writer.write(line);
                writer.write("\n");
            }
        }

        file1.delete();
        file2.renameTo(file1);

        file.close();
        writer.close();

    }

}
