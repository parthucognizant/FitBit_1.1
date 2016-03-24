package com.project.first.database;

import android.provider.BaseColumns;

public class DbContract {
	
	public DbContract(){}
	
	public static abstract class FitBit implements BaseColumns {
        public static final String TABLE_NAME = "Heartrate";
        public static final String COLUMN_NAME_RATE_ID = "rateid";
        public static final String COLUMN_NAME_TRACKER = "tracker";
        public static final String COLUMN_NAME_HEART_RATE = "heartrate";
        public static final String COLUMN_NAME_DATE = "date";

}
	public static abstract class FitBit_One implements BaseColumns {
        public static final String TABLE_NAME = "Activity";
        public static final String COLUMN_NAME_ACTIVITY_ID = "activityid";
        public static final String COLUMN_NAME_ACTIVITY = "activity";
        public static final String COLUMN_NAME_DISTANCE = "distance";
        public static final String COLUMN_NAME_DATE = "date";

}
	public static abstract class FitBit_two implements BaseColumns {
        public static final String TABLE_NAME = "Sleep";
        public static final String COLUMN_NAME_SLEEP_ID = "sleepid";
        public static final String COLUMN_NAME_POSITION = "position";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_DATE = "date";

}
	public static abstract class FitBit_three implements BaseColumns {
        public static final String TABLE_NAME = "Body";
        public static final String COLUMN_NAME_BODY_ID = "bodyid";
        public static final String COLUMN_NAME_BODY_DATE = "bodydate";
        public static final String COLUMN_NAME_BODY_FAT = "fat";
        public static final String COLUMN_NAME_DATE = "date";

}
	public static abstract class FitBit_four implements BaseColumns {
        public static final String TABLE_NAME = "FoodPlan";
        public static final String COLUMN_NAME_FOOD_ID = "foodid";
        public static final String COLUMN_NAME_FOOD_ACTIVITY = "foodactivity";
        public static final String COLUMN_NAME_FOOD_BENCHMARK = "benchmark";
        public static final String COLUMN_NAME_DATE = "date";

}
}