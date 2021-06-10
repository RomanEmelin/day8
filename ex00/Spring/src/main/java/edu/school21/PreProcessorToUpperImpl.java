package edu.school21;

public class PreProcessorToUpperImpl implements PreProcessor {

    public String preProcess(String text) {
        return text.toUpperCase();
    }
}
