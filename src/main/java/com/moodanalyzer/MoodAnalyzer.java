package com.moodanalyzer;

import com.moodanalyzerexception.MoodAnalyzerException;

public class MoodAnalyzer {
    private String mood;
    public MoodAnalyzer() {
    }
    public MoodAnalyzer(String mood) {
        this.mood = mood;
    }

    public String analyzeMood() {
        try {
            if(mood.contains("Sad")) {
                return "Sad";
            }
            else if(mood.isEmpty()) {
                throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.IS_EMPTY,"String entered is empty.Please enter valid string");
            }
                return "Happy";
        }
        catch(NullPointerException ex) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.IS_NULL,"Entered string is null.Please enter valid string");
        }

    }
}
