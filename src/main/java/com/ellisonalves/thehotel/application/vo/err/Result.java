package com.ellisonalves.thehotel.application.vo.err;

public record Result(boolean err, String message) {

    public static Result err(String message) {
        return create(false, message);
    }

    public static Result ok(String message) {
        return create(true, message);
    }

    private static Result create(boolean err, String message) {
        return new Result(err, message);
    }

    public boolean isErr() {
        return err;
    }

}
