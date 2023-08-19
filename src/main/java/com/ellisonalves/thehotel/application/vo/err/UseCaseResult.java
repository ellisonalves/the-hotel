package com.ellisonalves.thehotel.application.vo.err;

public record UseCaseResult(ResultType resultType, Content content) {

	public static UseCaseResult unprocessableFailure(String message) {
		return failure(ResultType.UNPROCESSABLE, message);
	}

	public static UseCaseResult ok(String resourceId, String message) {
		return createResult(ResultType.OK, resourceId, message);
	}

	private static UseCaseResult failure(ResultType resultType, String message) {
		return createResult(resultType, null, message);
	}

	private static UseCaseResult createResult(ResultType resultType, String resourceId, String message) {
		return new UseCaseResult(resultType, new Content(resourceId, message));
	}

	public static record Content(String resourceId, String message) {

	}

	public static enum ResultType {
		UNPROCESSABLE, OK
	}

}
