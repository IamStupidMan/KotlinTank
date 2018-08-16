package com.summerdear.tankgame.model


interface View {

    var x: Int
    var y: Int
    /**
     * 宽高
     */
    var width: Int
    var height: Int
    /**
     * 显示
     */
    fun draw()
}