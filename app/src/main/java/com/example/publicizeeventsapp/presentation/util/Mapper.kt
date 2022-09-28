package com.example.publicizeeventsapp.presentation.util

interface Mapper<S, T> {
    fun map(source: S): T
}