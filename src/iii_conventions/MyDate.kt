package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)

    override fun contains(value: MyDate): Boolean = start <= value && value <= endInclusive
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}

class RepeatedTimeIntervals (val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeIntervals(this,number)

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval,1)
operator fun MyDate.plus(timeIntervals: RepeatedTimeIntervals)
        = addTimeIntervals(timeIntervals.timeInterval,timeIntervals.number)




operator fun  MyDate.component2(): Any {
    return month
}
operator fun  MyDate.component3(): Any {
    return dayOfMonth
}






