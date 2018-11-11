package com.islander.islanderjsoup.common;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IslanderUtils {


    public List<Integer> stringToIntList(String input) {
        return Arrays.stream(input.split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
