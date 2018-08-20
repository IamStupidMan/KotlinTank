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


    /**
     * 检测碰撞
     */
    fun checkCollision(x1: Int, y1: Int, width1: Int, height1: Int,
                       x2: Int, y2: Int, width2: Int, height2: Int): Boolean {
        return when {
            y2 + height2 <= y1 -> //障碍物在运动物体上方，没发生碰撞
                false
            y1 + height1 <= y2 -> //障碍物在运动物体下方，没发生碰撞
                false
            x2 + width2 <= x1 -> //障碍物在运动物体左方，没发生碰撞
                false
            else -> x1 + width1 > x2
        }
    }
}