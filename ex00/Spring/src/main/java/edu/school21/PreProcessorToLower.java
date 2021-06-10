package edu.school21;

public class PreProcessorToLower implements PreProcessor {

    public String preProcess(String text) {
        return text.toLowerCase();
    }
}