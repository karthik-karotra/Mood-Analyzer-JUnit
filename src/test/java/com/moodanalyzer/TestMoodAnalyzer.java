package com.moodanalyzer;
import com.moodanalyzerexception.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Constructor;

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
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.ENTERED_NULL, e.type);
        }
    }

    @Test
    public void givenMessageInConstructor_WhenEmpty_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer("");
        try {
            moodAnalyzer.analyzeMood();

        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.ENTERED_EMPTY, e.type);
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

    //Comparing two objects are equal or not using default constructors
    @Test
    public void givenMoodAnalyzerClassUsingDefaultConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            Assert.assertEquals(new MoodAnalyzer(), reflectionMoodObject);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Comparing two objects are equal or not using parameterized constructors
    @Test
    public void givenMoodAnalyzerClassUsingParameterizedConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObject = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            Assert.assertEquals(new MoodAnalyzer("I am in Happy Mood"), reflectionMoodObject);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //No such class exception using reflection
    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer", String.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.type);
        }
    }

    //No such method exception using reflection
    @Test
    public void givenClassName_WhenProperWithImproperConstructor_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzerFactory", int.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //Invoking Methods and comparing message
    @Test
    public void givenProperMethodName_WhenInvoked_ShouldReturnHappy() {
        try {

            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            String mood = MoodAnalyzerFactory.invokeMethod(moodObject, "analyzeMood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //Invoking Methods : If method name not found then throw no such method exception
    @Test
    public void givenMethodName_WhenNotProper_ShouldThrowMoodAnalyzerException() {
        try {

            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            MoodAnalyzerFactory.invokeMethod(moodObject,"analyzeMood1");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //Set field(variable) value at runtime using reflection
    @Test
    public void givenFieldNameAndItsValue_WhenProper_ShouldReturnValue() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            String mood = MoodAnalyzerFactory.setFieldValue(moodObject, "Happy", "mood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //When field value not present throw exception
    @Test
    public void givenFieldNameAndItsValue_WhenFieldNotFound_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            String mood = MoodAnalyzerFactory.setFieldValue(moodObject, "Happy", "mood");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_FIELD, e.type);
        }
    }

    //Set null value to field dynamically using reflection it throws exception
    @Test
    public void givenFieldNameAndNullValue_ShouldThrowMoodAnalyzerExceptionTest3() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            String mood = MoodAnalyzerFactory.setFieldValue(moodObject, null, "mood");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.METHOD_INVOCATION_ISSUE, e.type);
        }
    }

}
