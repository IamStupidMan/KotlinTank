package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Destoryable
import org.itheima.kotlin.game.core.Painter


/**
 * 爆炸物
 */
class Blast(override var x: Int, override var y: Int) : Destoryable {

    override var width: Int = Config.block
    override var height: Int = Config.block

    private val imagesPaths = arrayListOf<String>()

    private var index: Int = 0

    init {
        (1..32).forEach {
            imagesPaths.add("img/blast_${it}.png")
        }
    }

    override fun draw() {
        val i = index % imagesPaths.size
        Painter.drawImage(imagesPaths[i], x, y)
        index++
    }

    override fun isDestroyed(): Boolean {
        return index >= imagesPaths.size
    }
}