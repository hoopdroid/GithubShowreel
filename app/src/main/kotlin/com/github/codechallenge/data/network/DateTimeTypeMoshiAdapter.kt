package com.github.codechallenge.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat

internal class DateTimeTypeMoshiAdapter : JsonAdapter<DateTime>() {

    @ToJson
    override fun toJson(writer: JsonWriter, date: DateTime?) {
        writer.value(ISODateTimeFormat.dateTimeNoMillis().print(date))
    }

    @FromJson
    override fun fromJson(reader: JsonReader): DateTime? =
        DateTime.parse(reader.nextString()).withZone(DateTimeZone.getDefault())
}