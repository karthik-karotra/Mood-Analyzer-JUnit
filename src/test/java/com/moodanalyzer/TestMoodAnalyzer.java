package com.moodanalyzer;

import com.moodanalyzerexception.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

public class TestMoodAnalyzer {
    private MoodAnalyzer moodAnalyzer;

    @Test
    public void givenHappyMoodShouldReturnHappy() {
        moodAnalyzer = new MoodAnalyzer("I am in Happy Mood");
        try {
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenSadMoodShouldReturnSad() {
        moodAnalyzer = new MoodAnalyzer("I am in Sad Mood");
        try {
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("Sad", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void givenNullMoodShouldReturnCustomMessage() {
        moodAnalyzer = new MoodAnalyzer(null);
        try {
            moodAnalyzer.analyzeMood();

        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_NULL, e.type);
        }

    }

    @Test
    public void givenEmptyMoodShouldReturnCustomMessage() {
        moodAnalyzer = new MoodAnalyzer("");
        try {
            moodAnalyzer.analyzeMood();

        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_EMPTY, e.type);
        }

    }
}
