package com.kizxm.whatariot;

public class Constants {
    ///..!!!ONLY ONE BUILD CAN BE ACTIVE AT ANYTIME!!!..///

//    ///..SORTING BUILD (all champions by attribute)..///
//    public static final String PANDA_KEY = BuildConfig.PANDA_KEY;
//    public static final String PANDA_BASE_URL = "https://api.pandascore.co//lol/champions";
//    public static final String PANDA_CHAMP_QUERY = "sort";
//    ///....///

    ///..FILTERING BUILD (single champion by name..///
    public static final String PANDA_KEY = BuildConfig.PANDA_KEY;
    public static final String PANDA_BASE_URL = "https://api.pandascore.co//lol/champions";
    public static final String PANDA_CHAMP_QUERY = "filter[name]";
    ///....///


    public static final String PREFERENCES_CHAMPION_KEY = "champion";

    public static final String FIREBASE_CHILD_SEARCHED_CHAMPION = "searchedChampion";
    public static final String FIREBASE_CHILD_CHAMPIONS = "champions";
    public static final String FIREBASE_QUERY_INDEX = "index";


    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_CHAMPIONS = "champions";

}
