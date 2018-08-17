package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import org.itheima.kotlin.game.core.Painter


/**
 * 草坪
 */
class Grass(override var x: Int, override var y: Int) : View {
    /**
     * 位置
     */
//    override var x: Int = 200
//    override var y: Int = 200
    /**
     *  宽高
     */
    override var width: Int = Config.block
    override var height: Int = Config.block

    /**
     * 显示
     */
    override fun draw() {
        Painter.drawImage("img/grass.gif", x, y)
    }
}