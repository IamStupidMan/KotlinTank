package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.enums.Direction
import org.itheima.kotlin.game.core.Painter

class Bullet(val orientation: Direction, override var x: Int, override var y: Int) : View {
    override var width: Int = Config.block
    override var height: Int = Config.block

    override fun draw() {
        var resources = when (orientation) {
            Direction.UP -> "img/shot_top.gif"
            Direction.DOWN -> "img/shot_bottom.gif"
            Direction.LEFT -> "img/shot_left.gif"
            Direction.RIGHT -> "img/shot_right.gif"
        }
        Painter.drawImage(resources, x, y)
    }
}