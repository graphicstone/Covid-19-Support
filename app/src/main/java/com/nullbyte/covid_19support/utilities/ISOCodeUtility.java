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
        countryCodes.put("Colombia", "COL");
        countryCodes.put("Comoros", "COM");
        countryCodes.put("Congo", "COD");
        countryCodes.put("Congo (the)", "COG");
        countryCodes.put("Cook Islands", "COK");
        countryCodes.put("Costa Rica", "CRI");
        countryCodes.put("Croatia", "HRV");
        countryCodes.put("Cuba", "CUB");
        countryCodes.put("Curaçao", "CUW");
        countryCodes.put("Cyprus", "CYP");
        countryCodes.put("Czechia", "CZE");
        countryCodes.put("Côte d'Ivoire", "CIV");


        countryCodes.put("Denmark", "DNK");
        countryCodes.put("Djibouti", "DJI");
        countryCodes.put("Dominica", "DMA");
        countryCodes.put("Dominican Republic", "DOM");


        countryCodes.put("Ecuador", "ECU");
        countryCodes.put("Egypt", "EGY");
        countryCodes.put("El Salvador", "SLV");
        countryCodes.put("Equatorial Guinea", "GNQ");
        countryCodes.put("Eritrea", "ERI");
        countryCodes.put("Estonia", "EST");
        countryCodes.put("Eswatini", "SWZ");
        countryCodes.put("Ethiopia", "ETH");


        countryCodes.put("Falkland Islands", "FLK");
        countryCodes.put("Faroe Islands", "FRO");
        countryCodes.put("Fiji", "FJI");
        countryCodes.put("Finland", "FIN");
        countryCodes.put("France", "FRA");
        countryCodes.put("French Guiana", "GUF");
        countryCodes.put("French Polynesia", "PYF");
        countryCodes.put("French Southern Territories", "ATF");


        countryCodes.put("Gabon", "GAB");
        countryCodes.put("Gambia", "GMB");
        countryCodes.put("Georgia", "GEO");
        countryCodes.put("Germany", "DEU");
        countryCodes.put("Ghana", "GHA");
        countryCodes.put("Gibraltar", "GIB");
        countryCodes.put("Greece", "GRC");
        countryCodes.put("Greenland", "GRL");
        countryCodes.put("Grenada", "GRD");
        countryCodes.put("Guadeloupe", "GLP");
        countryCodes.put("Guam", "GUM");
        countryCodes.put("Guatemala", "GTM");
        countryCodes.put("Guernsey", "GGY");
        countryCodes.put("Guinea", "GIN");
        countryCodes.put("Guinea-Bissau", "GNB");
        countryCodes.put("Guyana", "GUY");


        countryCodes.put("Haiti", "HTI");
        countryCodes.put("Heard Island and McDonald Islands", "HMD");
        countryCodes.put("Holy See", "VAT");
        countryCodes.put("Honduras", "HND");
        countryCodes.put("Hong Kong", "HKG");
        countryCodes.put("Hungary", "HUN");


        countryCodes.put("Iceland", "ISL");
        countryCodes.put("India", "IND");
        countryCodes.put("Indonesia", "IDN");
        countryCodes.put("Iran", "IRN");
        countryCodes.put("Iraq", "IRQ");
        countryCodes.put("Ireland", "IRL");
        countryCodes.put("Isle of Man", "IMN");
        countryCodes.put("Israel", "ISR");
        countryCodes.put("Italy", "ITA");


        countryCodes.put("Jamaica", "JAM");
        countryCodes.put("Japan", "JPN");
        countryCodes.put("Jersey", "JEY");
        countryCodes.put("Jordan", "JOR");


        countryCodes.put("Kazakhstan", "KAZ");
        countryCodes.put("Kenya", "KEN");
        countryCodes.put("Kiribati", "KIR");
        countryCodes.put("Korea", "PRK");
        countryCodes.put("S. Korea", "KOR");
        countryCodes.put("Kuwait", "KWT");
        countryCodes.put("Kyrgyzstan", "KGZ");


        countryCodes.put("Lao People's Democratic Republic", "LAO");
        countryCodes.put("Latvia", "LVA");
        countryCodes.put("Lebanon", "LBN");
        countryCodes.put("Lesotho", "LSO");
        countryCodes.put("Liberia", "LBR");
        countryCodes.put("Libya", "LBY");
        countryCodes.put("Liechtenstein", "LIE");
        countryCodes.put("Lithuania", "LTU");
        countryCodes.put("Luxembourg", "LUX");


        countryCodes.put("Macao", "MAC");
        countryCodes.put("Madagascar", "MDG");
        countryCodes.put("Malawi", "MWI");
        countryCodes.put("Malaysia", "MYS");
        countryCodes.put("Maldives", "MDV");
        countryCodes.put("Mali", "MLI");
        countryCodes.put("Malta", "MLT");
        countryCodes.put("Marshall Islands", "MHL");
        countryCodes.put("Martinique", "MTQ");
        countryCodes.put("Mauritania", "MRT");
        countryCodes.put("Mauritius", "MUS");
        countryCodes.put("Mayotte", "MYT");
        countryCodes.put("Mexico", "MEX");
        countryCodes.put("Micronesia", "FSM");
        countryCodes.put("Moldova", "MDA");
        countryCodes.put("Monaco", "MCO");
        countryCodes.put("Mongolia", "MNG");
        countryCodes.put("Montenegro", "MNE");
        countryCodes.put("Montserrat", "MSR");
        countryCodes.put("Morocco", "MAR");
        countryCodes.put("Mozambique", "MOZ");
        countryCodes.put("Myanmar", "MMR");

        countryCodes.put("Namibia", "NAM");
        countryCodes.put("Nauru", "NRU");
        countryCodes.put("Nepal", "NPL");
        countryCodes.put("Netherlands", "NLD");
        countryCodes.put("New Caledonia", "NCL");
        countryCodes.put("New Zealand", "NZL");
        countryCodes.put("Nicaragua", "NIC");
        countryCodes.put("Niger", "NER");
        countryCodes.put("Nigeria", "NGA");
        countryCodes.put("Niue", "NIU");
        countryCodes.put("Norfolk Island", "NFK");
        countryCodes.put("Northern Mariana Islands", "MNP");
        countryCodes.put("Norway", "NOR");

        countryCodes.put("Oman", "OMN");

        countryCodes.put("Pakistan", "PAK");
        countryCodes.put("Palau", "PLW");
        countryCodes.put("Palestine", "PSE");
        countryCodes.put("Panama", "PAN");
        countryCodes.put("Papua New Guinea", "PNG");
        countryCodes.put("Paraguay", "PRY");
        countryCodes.put("Peru", "PER");
        countryCodes.put("Philippines", "PHL");
        countryCodes.put("Pitcairn", "PCN");
        countryCodes.put("Poland", "POL");
        countryCodes.put("Portugal", "PRT");
        countryCodes.put("Puerto Rico", "PRI");


        countryCodes.put("Qatar", "QAT");

        countryCodes.put("Republic of North Macedonia", "MKD");
        countryCodes.put("Romania", "ROU");
        countryCodes.put("Russia", "RUS");
        countryCodes.put("Rwanda", "RWA");
        countryCodes.put("Réunion", "REU");


        countryCodes.put("Saint Barthélemy", "BLM");
        countryCodes.put("Saint Helena", "SHN");
        countryCodes.put("Saint Kitts and Nevis", "KNA");
        countryCodes.put("Saint Lucia", "LCA");
        countryCodes.put("Saint Martin", "MAF");
        countryCodes.put("Saint Pierre and Miquelon", "SPM");
        countryCodes.put("Saint Vincent and the Grenadines", "VCT");
        countryCodes.put("Samoa", "WSM");
        countryCodes.put("San Marino", "SMR");
        countryCodes.put("Sao Tome and Principe", "STP");
        countryCodes.put("Saudi Arabia", "SAU");
        countryCodes.put("Senegal", "SEN");
        countryCodes.put("Serbia", "SRB");
        countryCodes.put("Seychelles", "SYC");
        countryCodes.put("Sierra Leone", "SLE");
        countryCodes.put("Singapore", "SGP");
        countryCodes.put("Sint Maarten", "SXM");
        countryCodes.put("Slovakia", "SVK");
        countryCodes.put("Slovenia", "SVN");
        countryCodes.put("Solomon Islands", "SLB");
        countryCodes.put("Somalia", "SOM");
        countryCodes.put("South Africa", "ZAF");
        countryCodes.put("South Georgia and the South Sandwich Islands", "SGS");
        countryCodes.put("South Sudan", "SSD");
        countryCodes.put("Spain", "ESP");
        countryCodes.put("Sri Lanka", "LKA");
        countryCodes.put("Sudan", "SDN");
        countryCodes.put("Suriname", "SUR");
        countryCodes.put("Svalbard and Jan Mayen", "SJM");
        countryCodes.put("Sweden", "SWE");
        countryCodes.put("Switzerland", "CHE");
        countryCodes.put("Syrian Arab Republic", "SYR");


        countryCodes.put("Taiwan", "TWN");
        countryCodes.put("Tajikistan", "TJK");
        countryCodes.put("Tanzania", "TZA");
        countryCodes.put("Thailand", "THA");
        countryCodes.put("Timor-Leste", "TLS");
        countryCodes.put("Togo", "TGO");
        countryCodes.put("Tokelau", "TKL");
        countryCodes.put("Tonga", "TON");
        countryCodes.put("Trinidad and Tobago", "TTO");
        countryCodes.put("Tunisia", "TUN");
        countryCodes.put("Turkey", "TUR");
        countryCodes.put("Turkmenistan", "TKM");
        countryCodes.put("Turks and Caicos Islands", "TCA");
        countryCodes.put("Tuvalu", "TUV");


        countryCodes.put("Uganda", "UGA");
        countryCodes.put("Ukraine", "UKR");
        countryCodes.put("United Arab Emirates", "ARE");
        countryCodes.put("UK", "GBR");
        countryCodes.put("United States Minor Outlying Islands", "UMI");
        countryCodes.put("USA", "USA");
        countryCodes.put("Uruguay", "URY");
        countryCodes.put("Uzbekistan", "UZB");


        countryCodes.put("Vanuatu", "VUT");
        countryCodes.put("Venezuela", "VEN");
        countryCodes.put("Viet Nam", "VNM");
        countryCodes.put("Virgin Islands", "VGB");
        countryCodes.put("Virgin Islands(U.S)", "VIR");


        countryCodes.put("Wallis and Futuna", "WLF");
        countryCodes.put("Western Sahara", "ESH");


        countryCodes.put("Yemen", "YEM");


        countryCodes.put("Zambia", "ZMB");
        countryCodes.put("Zimbabwe", "ZWE");


        countryCodes.put("Åland Islands", "ALA");

        if(countryCodes.get(mCountryName)!=null)
            return countryCodes.get(mCountryName);
        else
            return "NA";
    }
}
