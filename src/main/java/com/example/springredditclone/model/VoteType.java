package com.example.springredditclone.model;

import java.util.Arrays;
import com.example.springredditclone.exceptions.SpringRedditException;

public enum VoteType extends Object{
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;
    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringRedditException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }

}
