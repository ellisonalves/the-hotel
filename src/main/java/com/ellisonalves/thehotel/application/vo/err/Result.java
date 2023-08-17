package com.ellisonalves.thehotel.application.vo.err;

public record Result(Content content) {

	public static Result inputError(String message) {
		return err(400, message);
	}

	public static Result err(Integer statusCode, String message) {
		return createResult(null, statusCode, message);
	}

	public static Result created(String id, String message) {
		return createResult(id, 200, message);
	}

	private static Result createResult(String id, Integer statusCode, String message) {
		return new Result(new Content(id, statusCode, message));
	}

	public static record Content(String id, Integer statusCode, String message) {

	}

}
