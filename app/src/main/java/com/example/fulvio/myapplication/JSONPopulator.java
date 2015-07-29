package com.example.fulvio.myapplication;

import org.json.JSONObject;

public interface JSONPopulator {
    void populate(JSONObject data);
    JSONObject toJSON();
}
