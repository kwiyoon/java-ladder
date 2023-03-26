package ladder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ladder.NaturalNumber.createNaturalNumber;
import static ladder.Position.createPosition;
import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    static LineManager lineManager;

    @BeforeEach
    void init(){
        NaturalNumber height = createNaturalNumber(5);
        NaturalNumber numberOfPerson = createNaturalNumber(4);
        lineManager = new LineManager(height, numberOfPerson);
    }

    @Test
    @DisplayName("checkDirection : 하향 중에 line 만난 경우")
    void When_LastMoveIsDownward_Expect_ReturnStateAfterMove(){
        lineManager.drawLine(createPosition(1), createPosition(2));
        assertEquals(1, lineManager.nextDirection(1, 2, 0));
        assertEquals(-1, lineManager.nextDirection(1, 3, 0));
    }

    @Test
    @DisplayName("checkDirection : 하향 중 & 좌우에 line이 존재")
    void When_LastMoveIsDownwardAndLineOnBothSide_Expect_ReturnStateAfterMove(){
        lineManager.drawLine(createPosition(1), createPosition(2));
        lineManager.drawLine(createPosition(1), createPosition(1));
        assertEquals(1, lineManager.nextDirection(1, 2, 0));
    }

    @Test
    @DisplayName("checkDirection : 좌향 중")
    void When_LastMoveIsLeftward_Expect_ReturnStateAfterMove(){
        lineManager.drawLine(createPosition(1), createPosition(2));
        assertEquals(0, lineManager.nextDirection(1, 2, -1));
        assertEquals(-1, lineManager.nextDirection(1, 3, -1));
    }

    @Test
    @DisplayName("checkDirection : 우향 중")
    void When_LastMoveIsRightward_Expect_ReturnStateAfterMove(){
        lineManager.drawLine(createPosition(1), createPosition(2));
        assertEquals(1, lineManager.nextDirection(1, 2, 1));
        assertEquals(0, lineManager.nextDirection(1, 3, 1));
    }


    @Test
    @DisplayName("drawLine : row 값 유효성")
    void When_InvalidRowValueInDrawLine_Expect_ThrowException(){
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(-3), createPosition(3)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(0), createPosition(3)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(6), createPosition(3)));
    }

    @Test
    @DisplayName("drawLine : col 값 유효성")
    void When_InvalidColValueInDrawLine_Expect_ThrowException(){
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(4), createPosition(-1)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(4), createPosition(0)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(4), createPosition(4)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.drawLine(createPosition(4), createPosition(7)));
    }

    @Test
    @DisplayName("validateStartPoint : 시작점 유효성")
    void When_InvalidStartPointInRun_Expect_ThrowException(){
        assertThrows(IllegalArgumentException.class, ()-> lineManager.validateStartPoint(createPosition(-1)));
        assertThrows(IllegalArgumentException.class, ()-> lineManager.validateStartPoint(createPosition(7)));
    }

}