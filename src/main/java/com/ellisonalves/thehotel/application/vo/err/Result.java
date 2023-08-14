package com.ellisonalves.thehotel.application.vo.err;

public record Result(String message) {

    public static Result err(String message) {
        return create(message);
    }

    public static Result ok(String message) {
        return create(message);
    }

    private static Result create(String message) {
        return new Result(message);
    }

}
