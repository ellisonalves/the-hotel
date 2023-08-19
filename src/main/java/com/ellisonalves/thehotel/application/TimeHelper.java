package com.ellisonalves.thehotel.application;

import java.time.Clock;
import java.time.Instant;

public class TimeHelper {

	public Clock clock() {
		return Clock.systemUTC();
	}

	public Instant now() {
		return Instant.now(clock());
	}

}
