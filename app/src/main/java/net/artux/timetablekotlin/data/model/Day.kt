package net.artux.timetablekotlin.data.model

import net.artux.timetablekotlin.data.model.OccupationItem

data class Day(var number: Int, var list: MutableList<OccupationItem>)