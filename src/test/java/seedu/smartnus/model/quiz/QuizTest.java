package seedu.smartnus.model.quiz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.TypicalQuestions.ALICE;
import static seedu.smartnus.testutil.TypicalQuestions.BENSON;
import static seedu.smartnus.testutil.TypicalQuestions.CARL;
import static seedu.smartnus.testutil.TypicalQuiz.FIVE_QUESTIONS_QUIZ;
import static seedu.smartnus.testutil.TypicalQuiz.ONE_QUESTION_QUIZ;
import static seedu.smartnus.testutil.TypicalQuiz.SEVEN_QUESTIONS_QUIZ;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;

public class QuizTest {

    @Test
    public void isSameQuizQuestion() {
        // Same object -> Returns true
        assertTrue(FIVE_QUESTIONS_QUIZ.currQuestion().isSameQuestion(ALICE));

        assertTrue(FIVE_QUESTIONS_QUIZ.nextQuestion().isSameQuestion(BENSON));
    }

    @Test
    public void isNavigable() {
        Quiz testQuizObject = SEVEN_QUESTIONS_QUIZ;

        // First question is ALICE -> returns True
        assertTrue(testQuizObject.currQuestion().isSameQuestion(ALICE));

        // Second question is BOB -> returns True
        assertTrue(testQuizObject.nextQuestion().isSameQuestion(BENSON));

        // Third question is CARL -> returns True
        assertTrue(testQuizObject.nextQuestion().isSameQuestion(CARL));

        // Third question is CARL -> returns True
        assertTrue(testQuizObject.currQuestion().isSameQuestion(CARL));

        // Second question is BOB -> returns True
        assertTrue(testQuizObject.prevQuestion().isSameQuestion(BENSON));

        // First question is ALICE -> returns False
        assertFalse(testQuizObject.prevQuestion().isSameQuestion(BENSON));
    }

    @Test
    public void testException() {
        Quiz testQuizObject = ONE_QUESTION_QUIZ;
        // Try to go to the previous question when we are at the first question -> out of bound
        assertThrows(QuizOutOfBoundException.class, () -> testQuizObject.prevQuestion());
        assertThrows(QuizOutOfBoundException.class, () -> testQuizObject.nextQuestion());
    }

    @Test
    public void statisticsTest() {
        Quiz testQuizObject = ONE_QUESTION_QUIZ;
        assertNotEquals(null, testQuizObject.getStatistic());
        assertTrue(testQuizObject.attemptAndCheckAnswer(new Choice("option 2", true)));
        assertFalse(testQuizObject.attemptAndCheckAnswer(new Choice("option 3", false)));
    }

}
