package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Attackable
import com.summerdear.tankgame.business.Blockable
import com.summerdear.tankgame.business.Destoryable
import com.summerdear.tankgame.business.Sufferable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter


/**
 * 砖墙
 */
class Wall(override var x: Int, override var y: Int) : Blockable, Sufferable, Destoryable {


    /**
     * 砖墙的生命值
     */
    override var blood: Int = 3

    /**
     *  宽高
     */
    override var width: Int = Config.block
    override var height: Int = Config.block

    /**
     * 显示
     */
    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }

    /**
     * 受到攻击
     */
    override fun notifySuffer(attack: Attackable): Array<View> {
        blood -= attack.attackPower

        //爆炸声音
        Composer.play("snd/hit.wav")
//        //爆炸效果
//        Painter.drawImage("img/blast_7.png", x, y)

        return arrayOf(Blast(x, y))
    }

    override fun isDestroyed(): Boolean {
        return blood <= 0
    }

}