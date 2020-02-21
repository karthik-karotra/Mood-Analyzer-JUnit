package com.moodanalyzerexception;

public class MoodAnalyzerException extends RuntimeException{


    public enum ExceptionType {
        IS_EMPTY, NO_SUCH_CLASS, NO_SUCH_METHOD, IS_NULL
    }

    public final ExceptionType type;

    public MoodAnalyzerException(ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
