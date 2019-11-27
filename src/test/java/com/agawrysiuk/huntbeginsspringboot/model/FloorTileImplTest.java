package com.agawrysiuk.huntbeginsspringboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.agawrysiuk.huntbeginsspringboot.model.Exit.*;

class FloorTileImplTest {

    @ParameterizedTest
    @MethodSource("provideArraysForRotate")
    void rotate_NewArrayIsCorrect(int[] setExitsArray, Exit[] afterRotateArray) {
        FloorTile floorTile = new FloorTileImpl(1, "test");
        floorTile.setExits(setExitsArray);
        floorTile.rotate();
        Assertions.assertArrayEquals(afterRotateArray,floorTile.getExits()
        );
    }

    private static Stream<Arguments> provideArraysForRotate() {
        return Stream.of(
                Arguments.of(new int[]{1, 0, 1, 1}, new Exit[]{TOP_EXIT, RIGHT_EXIT, null, LEFT_EXIT}),
                Arguments.of(new int[]{1, 1, 1, 1}, new Exit[]{TOP_EXIT, RIGHT_EXIT, BOT_EXIT, LEFT_EXIT}),
                Arguments.of(new int[]{0, 0, 0, 0}, new Exit[]{null, null, null, null}),
                Arguments.of(new int[]{0, 1, 0, 1}, new Exit[]{TOP_EXIT, null, BOT_EXIT, null}),
                Arguments.of(new int[]{0, 0, 0, 1}, new Exit[]{TOP_EXIT, null, null, null}),
                Arguments.of(new int[]{1, 0, 1, 1}, new Exit[]{TOP_EXIT, RIGHT_EXIT, null, LEFT_EXIT})
        );
    }
}