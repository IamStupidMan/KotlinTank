package com.summerdear.tankgame.business

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.model.View


/**
 * 可移动的接口
 */
interface Movable : View {


    /**
     * 可移动的物体运动的方向
     */
    var currentDirection: Direction

    /**
     * 移动的速度
     */
    var speed: Int

    /**
     * 判断移动的物体是否和阻塞物体碰撞
     * @return 碰撞的方向,如果为 null说明没有发生碰撞
     */
    /**
     * 检测碰撞
     */
    fun willCollision(block: Blockable): Direction? {

        //
        var x: Int = this.x
        var y: Int = this.y

        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        //防止坦克移动超出屏幕
        if (x < 0) return Direction.LEFT
        if (x > Config.gameWidth - width) return Direction.RIGHT
        if (y < 0) return Direction.UP
        if (y > Config.gameHeight - height) return Direction.DOWN

        val collision = when {
            block.y + block.height <= y -> //障碍物在运动物体上方，没发生碰撞
                false
            y + height <= block.y -> //障碍物在运动物体下方，没发生碰撞
                false
            block.x + block.width <= x -> //障碍物在运动物体左方，没发生碰撞
                false
            else -> x + width > block.x
        }

        return if (collision) currentDirection else null
    }


    /**
     * 通知碰撞
     */
    fun notifyCollision(direction: Direction?, block: Blockable?)
}