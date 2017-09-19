package com.fugaltech.aqm.service.util;

import com.fugaltech.aqm.model.Aircraft;
import com.google.gson.Gson;

public class AircraftJsonUtilities {

    private static Gson gson = new Gson();

    public static Aircraft jsonToAircraft(String json) {
        try {
            if (json == null) throw new NullPointerException("Json string is empty in jsonToAircraft utility");
            return gson.fromJson(json, Aircraft.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String aircraftToJson(Aircraft aircraft) {
        try {
            if (aircraft == null) throw new NullPointerException("Aircraft is missing in AircraftToJson utility");
            return gson.toJson(aircraft);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
