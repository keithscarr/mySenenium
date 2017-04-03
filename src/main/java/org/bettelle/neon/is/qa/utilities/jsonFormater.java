package org.bettelle.neon.is.qa.utilities;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonFormater {

public static Object readJSON(Object json, String searchKey) throws JSONException {

	String jsonString;
	JSONObject jsonObject = null;
	if (json instanceof String) {
		jsonString = (String) json;
		jsonObject = new JSONObject(jsonString);
	} else if (json instanceof JSONObject) {
		jsonObject = (JSONObject) json;
	}

	//LOGGER.info(Utilities.getCurrentThreadId() + jsonObject.toString(4));
	@SuppressWarnings("unchecked")
	Iterator<String> iterator = jsonObject.keys();
	while (iterator.hasNext()) {
		String key = iterator.next();
		if (searchKey.equals(key)) {
			return jsonObject.get(key);
		}
	}
	return null;
	}
}
