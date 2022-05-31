package com.mtnine.dohwaji.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var text: String = ""
)