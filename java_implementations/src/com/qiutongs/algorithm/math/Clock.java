package com.qiutongs.algorithm.math;

public class Clock {

	private static double HOUR_HAND_DEGREE_PER_MINUTE = 0.5;
	private static double MINUTE_HAND_DEGREE_PER_MINUTE = 6.0;

	public static double intersectionAngle(double tickMinute) {
		int ticketMinuteInt = (int) tickMinute;
		double ticketMinuteFrac = tickMinute - ticketMinuteInt;

		double hourHandDegree = (ticketMinuteInt % 720 + ticketMinuteFrac) * HOUR_HAND_DEGREE_PER_MINUTE;
		double minuteHandDegree = (ticketMinuteInt % 60 + ticketMinuteFrac) * MINUTE_HAND_DEGREE_PER_MINUTE;

		return Math.abs(hourHandDegree - minuteHandDegree);
	}

	// 6t = 360 * n + 0.5t
	public static double superposition(int n) {
		n = n % 12;
		return 360 * n / 5.5;
	}

	public static void main(String[] args) {
		System.out.println(Clock.intersectionAngle(30));

		System.out.println(Clock.intersectionAngle(60));

		System.out.println(Clock.superposition(1));

		// When they first time meet...
		System.out.println(Clock.intersectionAngle(65.454545454545));
	}
}
