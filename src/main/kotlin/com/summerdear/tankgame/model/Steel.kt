package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Blockable
import org.itheima.kotlin.game.core.Painter


/**
 * 钢板
 */
class Steel(override var x: Int, override var y: Int) : Blockable {
    /**
     * 位置
     */
//    override var x: Int = 300
//    override var y: Int = 300
    /**
     *  宽高
     */
    override var width: Int = Config.block
    override var height: Int = Config.block

    /**
     * 显示
     */
    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}