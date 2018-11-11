package com.islander.islanderjsoup.model.enumtype;

import com.islander.islanderjsoup.common.IslanderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PointResultType {
    SIX_ZERO("6 0"),
    SIX_ONE("6 1"),
    SIX_TWO("6 2"),
    SIX_THREE("6 3"),
    SIX_FOUR("6 4"),
    SEVEN_FIVE("7 5"),
    SEVEN_SIX("7 6"),
    ZERO_SIX("0 6"),
    ONE_SIX("1 6"),
    TWO_SIX("2 6"),
    THREE_SIX("3 6"),
    FOUR_SIX("4 6"),
    FIVE_SEVEN("5 7"),
    SIX_SEVEN("6 7");

    private String value;

    PointResultType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Map<String, PointResultType[]> getPointStatisticsMap() {
        Map<String, PointResultType[]> pointStatisticMap = new HashMap<>();
        pointStatisticMap.put(PointResultType.SIX_FOUR.name(), new PointResultType[]{PointResultType.SIX_FOUR, PointResultType.FOUR_SIX});
        pointStatisticMap.put(PointResultType.SIX_THREE.name(), new PointResultType[]{PointResultType.SIX_THREE, PointResultType.THREE_SIX});
        pointStatisticMap.put(PointResultType.SIX_TWO.name(), new PointResultType[]{PointResultType.SIX_TWO, PointResultType.TWO_SIX});
        pointStatisticMap.put(PointResultType.SIX_ONE.name(), new PointResultType[]{PointResultType.SIX_ONE, PointResultType.ONE_SIX});
        pointStatisticMap.put(PointResultType.SIX_ZERO.name(), new PointResultType[]{PointResultType.SIX_ZERO, PointResultType.ZERO_SIX});
        pointStatisticMap.put(PointResultType.SEVEN_SIX.name(), new PointResultType[]{PointResultType.SEVEN_SIX, PointResultType.SIX_SEVEN});
        pointStatisticMap.put(PointResultType.SEVEN_FIVE.name(), new PointResultType[]{PointResultType.SEVEN_FIVE, PointResultType.FIVE_SEVEN});
        return pointStatisticMap;
    }
}
