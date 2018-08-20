package com.summerdear.tankgame.ext

import com.summerdear.tankgame.model.View

/**
 * View的扩展方法，假设View源码，不能编辑，可以通过扩展来对原有的类进行扩展
 */
fun View.checkCollision(view: View): Boolean {
    return checkCollision(x, y, width, height, view.x, view.y, view.width, view.height)
}