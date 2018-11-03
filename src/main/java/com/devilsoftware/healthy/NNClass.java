package com.devilsoftware.healthy;

import com.googlecode.fannj.*;
import sun.misc.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NNClass {




    public static void normalizeFile(File f) {
        File temp = null;
        BufferedReader bufferIn = null;
        BufferedWriter bufferOut = null;

        try {
            if(f.exists()) {
                // Create a new temp file to write to
                temp = new File(f.getAbsolutePath() + ".normalized");
                temp.createNewFile();

                // Get a stream to read from the file un-normalized file
                FileInputStream fileIn = new FileInputStream(f);
                DataInputStream dataIn = new DataInputStream(fileIn);
                bufferIn = new BufferedReader(new InputStreamReader(dataIn));

                // Get a stream to write to the normalized file
                FileOutputStream fileOut = new FileOutputStream(temp);
                DataOutputStream dataOut = new DataOutputStream(fileOut);
                bufferOut = new BufferedWriter(new OutputStreamWriter(dataOut));

                // For each line in the un-normalized file
                String line;
                while ((line = bufferIn.readLine()) != null) {
                    // Write the original line plus the operating-system dependent newline
                    bufferOut.write(line);
                    bufferOut.newLine();
                }

                bufferIn.close();
                bufferOut.close();

                // Remove the original file
                f.delete();

                // And rename the original file to the new one
                temp.renameTo(f);
            } else {
                // If the file doesn't exist...
                System.out.println("Could not find file to open: " + f.getAbsolutePath());
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            // Clean up, temp should never exist

        }
    }

    public void saveNN() throws IOException {

        //Для сборки новой ИНС необходимо создасть список слоев
        List<Layer> layerList = new ArrayList<Layer>();
        TeacherClass teacherClass = new TeacherClass();

        layerList.add(Layer.create(teacherClass.getNumberOfInputNeurons(), ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
        layerList.add(Layer.create(teacherClass.getNumberOfInputNeurons()*teacherClass.getNumberOfOutputNeurons(), ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
        layerList.add(Layer.create(teacherClass.getNumberOfOutputNeurons(), ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
        Fann fann = new Fann(layerList);
        //Создаем тренера и определяем алгоритм обучения
        Trainer trainer = new Trainer(fann);
        trainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_RPROP);
        /* Проведем обучение взяв уроки из файла, с максимальным колличеством
           циклов 100000, показывая отчет каждую 100ю итерацию и добиваемся
        ошибки меньше 0.0001 */
        File file = new File("train.data");

        trainer.train(file.getAbsolutePath(), 100000, 100, 0.0001f);
        fann.save("NN");
    }
}
