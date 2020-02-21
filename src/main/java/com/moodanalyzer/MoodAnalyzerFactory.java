package com.moodanalyzer;

import com.moodanalyzerexception.MoodAnalyzerException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class MoodAnalyzerFactory {

    //Default Constructor
    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?> moodAnalyzerConstructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            Object moodAnalyzerObject = moodAnalyzerConstructor.newInstance();
            return (MoodAnalyzer) moodAnalyzerObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //Parameterized Constructor
    public static MoodAnalyzer createMoodAnalyzer(String mood) {
        Constructor<?> moodAnalyzerConstructor =null;
        try {
            moodAnalyzerConstructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            Object moodAnalyzerObject = moodAnalyzerConstructor.newInstance(mood);
            return (MoodAnalyzer) moodAnalyzerObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Constructor<?> getConstructor(String className, Class constructor) throws MoodAnalyzerException {
        try {
            Class<?> moodAnalyserClass = Class.forName(className);
            return moodAnalyserClass.getConstructor(constructor);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.getMessage());

        } catch (NoSuchMethodException e) {

            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        }
    }
}
