package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase8

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion

@Entity(tableName = "androidversions")
data class AndroidVersionEntity(@PrimaryKey val apiLevel: Int, val name: String)

// converts an entity to a list of Ui models
fun List<AndroidVersionEntity>.mapToUiModelList() = map {
    AndroidVersion(it.apiLevel, it.name)
}

// converts a list of Ui models to an entity
fun AndroidVersion.mapToEntity() = AndroidVersionEntity(this.apiLevel, this.name)