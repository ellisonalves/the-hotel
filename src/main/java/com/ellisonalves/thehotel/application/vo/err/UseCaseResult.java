package com.ellisonalves.thehotel.application.vo.err;

public record UseCaseResult(ResultType resultType, Content content) {

    public static UseCaseResult ok(String resourceId, String message) {
        return createResult(ResultType.ACCEPTED, resourceId, message);
    }

    public static UseCaseResult dataValidationError(String message) {
        return failure(message);
    }

    private static UseCaseResult failure(String message) {
        return createResult(ResultType.DATA_VALIDATION_ERROR, null, message);
    }

    private static UseCaseResult createResult(ResultType resultType, String resourceId, String message) {
        return new UseCaseResult(resultType, new Content(resourceId, message));
    }

    public record Content(String resourceId, String message) {

    }

    public enum ResultType {
        DATA_VALIDATION_ERROR,
        ACCEPTED
    }

}
