package com.moodanalyzer;
import com.moodanalyzerexception.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestMoodAnalyzer {
    private MoodAnalyzer moodAnalyzer;

    @Test
    public void givenMessageInConstructor_WhenContainsAnyMood_ShouldReturnHappy() {
        moodAnalyzer = new MoodAnalyzer("I am in Happy Mood");
        try {
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMessageInConstructor_WhenContainsSad_ShouldReturnSad() {
        moodAnalyzer = new MoodAnalyzer("I am in Sad Mood");
        try {
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("Sad", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenMessageInConstructor_WhenNull_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer(null);
        try {
            moodAnalyzer.analyzeMood();
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_NULL, e.type);
        }
    }

    @Test
    public void givenMessageInConstructor_WhenEmpty_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer("");
        try {
            moodAnalyzer.analyzeMood();

        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_EMPTY, e.type);
        }
    }

    //Reflection
    @Test
    public void givenMessageInConstructorUsingReflection_WhenContainsAnyMood_ShouldReturnHappy() {
        Constructor<?> constructor = null;
        try {
            constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            Object object = constructor.newInstance("I am in a Happy Mood");
            MoodAnalyzer moodAnalyzer = (MoodAnalyzer) object;
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("Happy", mood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyzerClassUsingDefaultConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            Assert.assertEquals(new MoodAnalyzer(), reflectionMoodObject);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyzerClassUsingParameterizedConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObject = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            Assert.assertEquals(new MoodAnalyzer("I am in Happy Mood"), reflectionMoodObject);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer", String.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.type);
        }
    }

    @Test
    public void givenClassName_WhenProperWithImproperConstructor_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzerFactory", int.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }


}
