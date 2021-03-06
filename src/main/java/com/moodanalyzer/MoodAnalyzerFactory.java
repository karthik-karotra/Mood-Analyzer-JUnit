package com.moodanalyzer;
import com.moodanalyzerexception.MoodAnalyzerException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class MoodAnalyzerFactory {

    //Return object of Default Constructor
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

    //Return object of Parameterized Constructor
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

    //Return constructor of passed class name and constructor name
    public static Constructor<?> getConstructor(String className, Class constructor) {
        try {
            Class<?> moodAnalyserClass = Class.forName(className);
            return moodAnalyserClass.getConstructor(constructor);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.getMessage());

        } catch (NoSuchMethodException e) {

            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        }
    }

    //Invoking method of passed class name and passed method name and returning message
    public static String invokeMethod(Object obj,String methodName ) {
        try {
            return  (String) obj.getClass().getDeclaredMethod(methodName).invoke(obj);
        }
         catch (NoSuchMethodException e) {
             throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setFieldValue(MoodAnalyzer obj, String message, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, message);
            return (String) obj.getClass().getDeclaredMethod("analyzeMood").invoke(obj);
        } catch (NoSuchFieldException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_FIELD, e.getMessage());
        } catch (IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.METHOD_INVOCATION_ISSUE, e.getMessage());
        }
        return "message";
    }
}
