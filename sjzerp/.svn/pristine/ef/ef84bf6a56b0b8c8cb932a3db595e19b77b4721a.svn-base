package com.qxh.utils;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class IdWorker {
	private final long workerId;
	private final static long twepoch = 1361753741828L;
	private long sequence = 0L;
	private final static long workerIdBits = 4L;
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;

	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	public final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;

	public IdWorker(final long workerId) {
		super();
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public static String createId(int size) {
		String id = UUID.randomUUID().toString();

		id = DEKHash(id) + "";

		int diff = size - id.length();
		String randStr = RandomStringUtils.randomNumeric(size);
		for (int i = 0; i < diff; i++) {
			int randIndex = (int) (Math.random() * randStr.length());
			int index = (int) (Math.random() * id.length());
			id = id.substring(0, index) + randStr.charAt(randIndex)
					+ id.substring(index, id.length());
		}
		return id;
	}
	public static String createId() {
		String id = UUID.randomUUID().toString();

		id = DEKHash(id) + "";

		int diff = 12 - id.length();
		String randStr = RandomStringUtils.randomNumeric(12);
		for (int i = 0; i < diff; i++) {
			int randIndex = (int) (Math.random() * randStr.length());
			int index = (int) (Math.random() * id.length());
			id = id.substring(0, index) + randStr.charAt(randIndex)
					+ id.substring(index, id.length());
		}
		return id;
	}
	private static int DEKHash(String str) {
		int hash = str.length();

		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
		}

		return (hash & 0x7FFFFFFF);
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & this.sequenceMask;
			if (this.sequence == 0) {
				System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(
						String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift))
				| (this.workerId << this.workerIdShift) | (this.sequence);
		// System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
		// + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
		// + workerId + ",sequence:" + sequence);
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		IdWorker worker2 = new IdWorker(2);
		System.out.println(worker2.nextId());

	}

}
