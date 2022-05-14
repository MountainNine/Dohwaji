package com.mtnine.dohwaji.view.listener

import com.mtnine.dohwaji.model.Post

interface OnPostEditListener {
    fun onPostEditStart()
    fun onPostEditEnd(post: Post)
    fun onPostDelete(post: Post)
}