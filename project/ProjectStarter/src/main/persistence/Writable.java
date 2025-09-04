package persistence;

import org.json.JSONObject;

// This code was based off of the JSONSERIALIZATIONDEMO
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
