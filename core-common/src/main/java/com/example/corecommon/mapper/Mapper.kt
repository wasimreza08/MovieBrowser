package com.example.corecommon.mapper

interface Mapper<FROM,TO> {
    fun map(from:FROM):TO
}
