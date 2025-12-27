package com.qualityanalyzer.ml;

public class TrainModelRunner {

    public static void main(String[] args) {
        System.out.println("Starting ML model training...");

        ModelTrainer trainer = new ModelTrainer();
        trainer.trainAndSaveModel();

        System.out.println("Model training completed successfully.");
    }
}
