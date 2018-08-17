package com.summerdear.tankgame.business

import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.model.View


/**
 * 可移动的接口
 */
interface Movable :View{


    /**
     * 可移动的物体运动的方向
     */
    var currentDirection: Direction

    /**
     * 移动的速度
     */
    var speed:Int

    /**
     * 判断移动的物体是否和阻塞物体碰撞
     * @return 碰撞的方向,如果为 null说明没有发生碰撞
     */
    fun willCollision(block: Blockable):Direction?


    /**
     * 通知碰撞
     */
    fun notifyCollision(direction: Direction?,block: Blockable?)
}