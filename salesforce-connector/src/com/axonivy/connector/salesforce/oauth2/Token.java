package com.axonivy.connector.salesforce.oauth2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.util.date.Now;

public class Token {
	private final Instant created;
	private final Map<String, Object> values;

	Token(Map<String, Object> values) {
		this.values = values;
		this.created = Now.asInstant();
	}
	
	public Instant getCreated() {
		return created;
	}
	
	public Object value(String name) {
		return values.get(name);
	}

	public boolean hasAccessToken() {
		return StringUtils.isNotBlank(accessToken());
	}

	public String accessToken() {
		return (String) values.get("access_token");
	}

	public boolean hasRefreshToken() {
		return StringUtils.isNotBlank(refreshToken());
	}

	public String refreshToken() {
		return (String) values.get("refresh_token");
	}

	public boolean isExpired() {
		var expiresAt = getCreated().plus(expiresIn(), ChronoUnit.SECONDS);
		return Instant.now().isAfter(expiresAt);
	}
	/**
	 * Some services dont wait till expiry, if token not used for e.g. 50 % of expiry time, expiry is reached...
	 * Use this method to expiry token sooner, specify how many percent sooner
	 * @param percent how many percent of expiry, if eg.50 that means - half of expiry date already reached 
	 * @return true if expired according to percentage
	 */
	public boolean isXPercentOfExpiry(int percent) {
		var expiresAt = getCreated().plus(expiresIn()/100*percent, ChronoUnit.SECONDS);
		return Instant.now().isAfter(expiresAt);
	}

	private int expiresIn() {
		var expiresIn = values.get("expires_in");
		if (expiresIn == null) {
			return 15*60;//unknown expiry,use 15 min default
		}
		if (expiresIn instanceof String str) {
			// some services returns this value as string
			return Integer.parseInt(str);
		}
		var integer = (Integer) expiresIn;
		return integer.intValue();
	}

	@Override
	public String toString() {
		return "Token [created=" + getCreated() + " expired=" + isExpired() + " values=" + values + "]";
	}

	
}
