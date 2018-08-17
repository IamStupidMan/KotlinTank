package com.summerdear.tankgame.model


interface View {

    /**
     * 坐标位置
     */
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