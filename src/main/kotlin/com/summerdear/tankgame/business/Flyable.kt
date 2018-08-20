package com.summerdear.tankgame.business

import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.model.View

interface Flyable : View {

    //飞行的速度
    val speed: Int

    //当前飞行的方向
    val currentDirection: Direction

    //飞行的方法
    fun fly()
}