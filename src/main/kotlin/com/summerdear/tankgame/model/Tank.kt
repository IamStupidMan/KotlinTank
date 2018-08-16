package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.enums.Direction
import org.itheima.kotlin.game.core.Painter

/**
 * 我方的坦克
 */
class Tank(override var x: Int, override var y: Int) : View {
    override var width: Int = Config.block
    override var height: Int = Config.block

    var currentDirection: Direction = Direction.UP

    /**
     * 我方坦克的绘制
     */
    override fun draw() {
        //根据坦克的方向进行绘制
        var resources = when (currentDirection) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
        }
        Painter.drawImage(resources, x, y)
    }


    /**
     * 我方坦克的移动
     */
    fun move(direction: Direction){
        //先把当前坦克的方向改变
        currentDirection = direction

    }
}