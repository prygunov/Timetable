package net.artux.timetablekotlin.data.main

import net.artux.timetablekotlin.data.model.Task

data class Day(var number: Int, var list: MutableList<Task>)