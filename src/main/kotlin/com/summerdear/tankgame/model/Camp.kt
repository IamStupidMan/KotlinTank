package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Attackable
import com.summerdear.tankgame.business.Blockable
import com.summerdear.tankgame.business.Destoryable
import com.summerdear.tankgame.business.Sufferable
import org.itheima.kotlin.game.core.Painter

/**
 * 大本营
 */
class Camp(override var x: Int, override var y: Int) : Blockable, Sufferable, Destoryable {
    /**
     * 血量
     */
    override var blood: Int = 3

    /**
     * 接受攻击
     */
    override fun notifySuffer(attack: Attackable): Array<View>? {
        blood -= attack.attackPower

        var x = x - 32
        var y = y - 32
        return arrayOf(Blast(x, y)
                , Blast(x + 32, y)
                , Blast(x + Config.block, y)
                , Blast(x + Config.block + 32, y)
                , Blast(x + Config.block * 2, y)
                , Blast(x, y + 32)
                , Blast(x, y + Config.block)
                , Blast(x, y + Config.block + 32)
                , Blast(x + Config.block * 2, y + 32)
                , Blast(x + Config.block * 2, y + Config.block)
                , Blast(x + Config.block * 2, y + Config.block + 32)
        )
    }

    override var width: Int = Config.block * 2
    override var height: Int = Config.block + 32

    override fun draw() {
        //绘制大本营的老鹰
        Painter.drawImage("img/camp.gif", x + 32, y + 32)

        //绘制外围的砖块
        Painter.drawImage("img/steel_small.gif", x, y)
        Painter.drawImage("img/steel_small.gif", x + 32, y)
        Painter.drawImage("img/steel_small.gif", x + 64, y)
        Painter.drawImage("img/steel_small.gif", x + 96, y)
        Painter.drawImage("img/steel_small.gif", x, y + 32)
        Painter.drawImage("img/steel_small.gif", x, y + 64)
        Painter.drawImage("img/steel_small.gif", x + 96, y + 32)
        Painter.drawImage("img/steel_small.gif", x + 96, y + 64)
    }

    /**
     * 销毁的展示
     */
    override fun showDestroy(): Array<View> {
//        return arrayOf(Blast(x - 32, y - 32)
//                , Blast(x, y - 32)
//                , Blast(x + 32, y - 32)
//                , Blast(x - 32, y)
//                , Blast(x, y)
//                , Blast(x + 32, y)
//                , Blast(x - 32, y + 32)
//                , Blast(x, y + 32)
//                , Blast(x + 32, y + 32))

        return arrayOf(Blast(x - 32, y - 32)
                , Blast(x, y - 32)
                , Blast(x + 32, y - 32)
                , Blast(x - 32, y)
                , Blast(x, y)
                , Blast(x + 32, y)
                , Blast(x - 32, y + 32)
                , Blast(x, y + 32)
                , Blast(x + 32, y + 32))

////        return arrayOf(Blast(x, y)
////                , Blast(x + 32, y)
////                , Blast(x + Config.block, y)
////                , Blast(x + Config.block + 32, y)
////                , Blast(x + Config.block * 2, y)
////                , Blast(x, y + 32)
////                , Blast(x, y + Config.block)
////                , Blast(x, y + Config.block + 32)
////                , Blast(x + Config.block * 2, y + 32)
////                , Blast(x + Config.block * 2, y + Config.block)
////                , Blast(x + Config.block * 2, y + Config.block + 32)
//        )
    }

    /**
     * 大本营被销毁了
     */
    override fun isDestroyed(): Boolean {
        return blood <= 0
    }


}