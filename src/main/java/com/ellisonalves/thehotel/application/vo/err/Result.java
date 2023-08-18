package com.ellisonalves.thehotel.application.vo.err;

public record Result(ResultType resultType, Content content) {

	public static Result unprocessableFailure(String message) {
		return failure(ResultType.UNPROCESSABLE, message);
	}

	public static Result ok(String resourceId, String message) {
		return createResult(ResultType.OK, resourceId, message);
	}

	private static Result failure(ResultType resultType, String message) {
		return createResult(resultType, null, message);
	}

	private static Result createResult(ResultType resultType, String resourceId, String message) {
		return new Result(resultType, new Content(resourceId, message));
	}

	public static record Content(String resourceId, String message) {

	}

	public static enum ResultType {
		UNPROCESSABLE, OK
	}

}
