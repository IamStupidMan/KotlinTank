package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Attackable
import com.summerdear.tankgame.business.Blockable
import com.summerdear.tankgame.business.Sufferable
import org.itheima.kotlin.game.core.Painter
import java.math.BigInteger


/**
 * 钢板
 */
class Steel(override var x: Int, override var y: Int) : Blockable, Sufferable {
    override val blood: Int = Integer.MAX_VALUE

    override fun notifySuffer(attack: Attackable): Array<View>? {
        return null
    }
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