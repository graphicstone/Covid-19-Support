package com.nullbyte.covid_19support.utilities;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ISOCodeUtility {
    public static Map<String, String> countryCodes = new HashMap<>();

    public static String getIsoCode(String mCountryName) {


        countryCodes.put("Afghanistan", "AFG");
        countryCodes.put("Aruba", "ABW");
        countryCodes.put("Albania", "ALB");
        countryCodes.put("Algeria", "DZA");
        countryCodes.put("American Samoa", "ASM");
        countryCodes.put("Andorra", "AND");
        countryCodes.put("Angola", "AGO");
        countryCodes.put("Anguilla", "AIA");
        countryCodes.put("Antarctica", "ATA");
        countryCodes.put("Antigua and Barbuda", "ATG");
        countryCodes.put("Argentina", "ARG");
        countryCodes.put("Armenia", "ARM");
        countryCodes.put("Australia", "AUS");
        countryCodes.put("Austria", "AUT");
        countryCodes.put("Azerbaijan", "AZE");


        countryCodes.put("Bahamas", "BHS");
        countryCodes.put("Bahrain", "BHR");
        countryCodes.put("Bangladesh", "BGD");
        countryCodes.put("Barbados", "BRB");
        countryCodes.put("Belarus", "BLR");
        countryCodes.put("Belgium", "BEL");
        countryCodes.put("Belize", "BLZ");
        countryCodes.put("Benin", "BEN");
        countryCodes.put("Bermuda", "BMU");
        countryCodes.put("Bhutan", "BTN");
        countryCodes.put("Bolivia", "BOL");
        countryCodes.put("Bonaire", "BES");
        countryCodes.put("Bosnia and Herzegovina", "BIH");
        countryCodes.put("Botswana", "BWA");
        countryCodes.put("Bouvet Island", "BVT");
        countryCodes.put("Brazil", "BRA");
        countryCodes.put("British Indian Ocean Territory", "IOT");
        countryCodes.put("Brunei Darussalam", "BRN");
        countryCodes.put("Bulgaria", "BGR");
        countryCodes.put("Burkina Faso", "BFA");
        countryCodes.put("Burundi", "BDI");


        countryCodes.put("Cabo Verde", "CPV");
        countryCodes.put("Cambodia", "KHM");
        countryCodes.put("Cameroon", "CMR");
        countryCodes.put("Canada", "CAN");
        countryCodes.put("Cayman Islands", "CYM");
        countryCodes.put("Central African Republic", "CAF");
        countryCodes.put("Chad", "TCD");
        countryCodes.put("Chile", "CHL");
        countryCodes.put("China", "CHN");
        countryCodes.put("Christmas Island", "CXR");
        countryCodes.put("Cocos (Keeling) Islands (the)", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");
//        countryCodes.put("", "");




















        return countryCodes.get(mCountryName);
    }
}
