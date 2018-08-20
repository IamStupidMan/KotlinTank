package com.summerdear.tankgame.business

import com.summerdear.tankgame.model.View

/**
 * 可被销毁的接口
 */
interface Destoryable:View {

    /**
     * 判断是否销毁了
     */
    fun isDestroyed():Boolean
}