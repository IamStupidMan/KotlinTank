package com.summerdear.tankgame.business

import com.summerdear.tankgame.model.View

/**
 * 遭受攻击的接口
 */
interface Sufferable : View {

    /**
     * 生命值
     */
    val blood: Int

    /**
     * 通知被攻击者
     */
    fun notifySuffer(attack: Attackable):Array<View>?
}