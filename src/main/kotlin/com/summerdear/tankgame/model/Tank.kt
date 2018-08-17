package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Blockable
import com.summerdear.tankgame.business.Movable
import com.summerdear.tankgame.enums.Direction
import org.itheima.kotlin.game.core.Painter

/**
 * 我方的坦克
 */
class Tank(override var x: Int, override var y: Int) : Movable {

    override var width: Int = Config.block
    override var height: Int = Config.block

    //坦克的方向
    override var currentDirection: Direction = Direction.UP

    //坦克的速度
    override var speed: Int = 8

    //坦克的碰撞方向，默认为null
    var collisionDirection: Direction? = null

    /**
     * 我方坦克的绘制
     */
    override fun draw() {
        //根据坦克的方向进行绘制
        var resources = when (currentDirection) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
        }
        Painter.drawImage(resources, x, y)
    }


    /**
     * 我方坦克的移动
     */
    fun move(direction: Direction) {

        //如果碰撞的方向和要移动的方向一样，则表示在移动方向上遇到了障碍物，走不了
        if (collisionDirection == direction) {
            return
        }


        //如果要移动的方向和当前的方向不一致，先把坦克的方向改变，而不去执行移动操作
        if (direction != currentDirection) {
            currentDirection = direction
            return
        }


        //改变坦克的的坐标，从而使坦克移动起来
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        //防止坦克移动超出屏幕
        if (x < 0) x = 0
        if (x > Config.gameWidth - width) x = Config.gameWidth - width
        if (y < 0) y = 0
        if (y > Config.gameHeight - height) y = Config.gameHeight - height

    }

    /**
     * 检测碰撞
     */
    override fun willCollision(block: Blockable): Direction? {

        //
        var x: Int = this.x
        var y: Int = this.y

        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        val collision = when {
            block.y + block.height <= y -> //障碍物在运动物体上方，没发生碰撞
                false
            y + height <= block.y -> //障碍物在运动物体下方，没发生碰撞
                false
            block.x + block.width <= x -> //障碍物在运动物体左方，没发生碰撞
                false
            else -> x + width > block.x
        }

        return if (collision) currentDirection else null
    }


    /**
     * 接收到了碰撞信息
     */
    override fun notifyCollision(direction: Direction?, block: Blockable?) {

        collisionDirection = direction
    }

    fun shot(): Bullet {

        //计算子弹发射精确的xy
        when(currentDirection){
//            Direction.UP->
//                Direction.DOWN->
//            Direction.LEFT->
//            Direction.RIGHT->

        }
        return Bullet(currentDirection, x, y)
    }


}