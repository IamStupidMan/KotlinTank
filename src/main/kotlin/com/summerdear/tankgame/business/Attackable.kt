package com.summerdear.tankgame.business

import com.summerdear.tankgame.model.View

/**
 * 具备攻击的能力
 */
interface Attackable : View {
    /**
     * 攻击力
     */
    val attackPower: Int

    /**
     * 检测是否碰撞
     */
    fun isCollision(sufferable: Sufferable): Boolean

    /**
     * 通知攻击者
     */
    fun notifyAttack(sufferable: Sufferable)
}