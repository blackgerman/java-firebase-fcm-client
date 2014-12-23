/*
 * Copyright 2016-2017 Tom Misawa, riversun.org@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
 * Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package org.riversun.fcm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * Notification data for pushing to the individual devices via Firebase cloud
 * messaging service<br>
 * <br>
 * Partially wrapped as follows.
 * https://firebase.google.com/docs/cloud-messaging/http-server-ref
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class DeviceMessage {

	private final Map<String, Object> mDataMap = new LinkedHashMap<String, Object>();
	private final List<String> mRegistrationIdList = new ArrayList<String>();

	/**
	 * Pub String value to the payload
	 * 
	 * @param key
	 * @param value
	 */
	public void putStringData(String key, String value) {
		putData(key, value);
	}

	/**
	 * Put boolean value to the payload
	 * 
	 * @param key
	 * @param value
	 */
	public void putBooleanData(String key, boolean value) {
		putData(key, value);
	}

	/**
	 * Put Object
	 * 
	 * @param key
	 * @param value
	 */
	public void putData(String key, Object value) {
		mDataMap.put(key, value);
	}

	/**
	 * Add specified registrationId
	 * 
	 * @param registrationId
	 */
	public void addRegistrationId(String registrationId) {
		mRegistrationIdList.add(registrationId);
	}

	/**
	 * Remove specified registrationId
	 * 
	 * @param registrationId
	 */
	public void removeRegistrationId(String registrationId) {
		if (mRegistrationIdList.contains(registrationId)) {
			mRegistrationIdList.remove(registrationId);
		}

	}

	/**
	 * Set registrationIds in the specified list
	 * 
	 * @param list
	 */
	public void setRegistrationIdList(List<String> list) {
		mRegistrationIdList.clear();
		mRegistrationIdList.addAll(list);
	}

	/**
	 * Remove all registered registrationIds
	 */
	public void clearRegistrationIds() {
		mRegistrationIdList.clear();
	}

	/**
	 * Returns JSONObject
	 * 
	 * @return
	 */
	public JSONObject toJsonObject() {

		final JSONObject json = new JSONObject();
		final String[] registrationIds = mRegistrationIdList.toArray(new String[] {});

		// for multicast
		json.accumulate("registration_ids", registrationIds);

		// payload
		json.accumulate("data", mDataMap);

		return json;
	}

	/**
	 * Returns JSON text
	 * 
	 * @return
	 */
	public String toJson() {
		return toJsonObject().toString();
	}
}