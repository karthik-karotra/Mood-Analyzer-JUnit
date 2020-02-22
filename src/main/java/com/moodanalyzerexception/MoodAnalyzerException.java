package com.moodanalyzerexception;

public class MoodAnalyzerException extends RuntimeException{


    public enum ExceptionType {
        ENTERED_EMPTY, NO_SUCH_CLASS, NO_SUCH_METHOD, NO_SUCH_FIELD, METHOD_INVOCATION_ISSUE, ENTERED_NULL
    }

    public final ExceptionType type;

    public MoodAnalyzerException(ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
