package com.nacarseven.desafioconcrete.presentation.common.helpers;

import android.content.Context;
import android.support.annotation.IntDef;

import com.nacarseven.desafioconcrete.R;
import com.nacarseven.desafioconcrete.presentation.RepositoryApplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by nacarseven on 12/10/2017.
 */

public final class DateFormatter {

    public static final int DATE_FORMAT_API_OUTPUT = R.string.date_format_api_output;
    public static final int DATE_FORMAT_DATE = R.string.date_format;

    private Context context;

    public DateFormatter() {
        context = RepositoryApplication.getAppContext();
    }

    public String parseToString(Date date, @DateFormat int dateFormat) {
        return parseToString(date, dateFormat, false);
    }

    public String parseToString(Date date, @DateFormat int dateFormat, boolean isUtc) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(dateFormat), Locale.getDefault());
        if (isUtc) sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public Calendar parseToCalendar(String date, @DateFormat int dateFormat) {
        return parseToCalendar(date, dateFormat, false);
    }

    public Calendar parseToCalendar(String date, @DateFormat int dateFormat, boolean isUtc) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(context.getString(dateFormat), Locale.getDefault());
            if (isUtc) sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return calendar;
    }

    @IntDef({DATE_FORMAT_API_OUTPUT,
            DATE_FORMAT_DATE})
    @Retention(RetentionPolicy.SOURCE)
    @interface DateFormat {
    }
}
