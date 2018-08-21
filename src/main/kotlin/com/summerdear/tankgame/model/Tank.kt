package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.*
import com.summerdear.tankgame.enums.Direction
import org.itheima.kotlin.game.core.Painter

/**
 * 我方的坦克
 */
class Tank(override var x: Int, override var y: Int) : Movable, Blockable, Sufferable,Destoryable {

    override var width: Int = Config.block
    override var height: Int = Config.block

    //坦克的方向
    override var currentDirection: Direction = Direction.UP

    //坦克的速度
    override var speed: Int = 8

    //坦克的碰撞方向，默认为null
    private var collisionDirection: Direction? = null

    /**
     * 我方坦克的血量
     */
    override var blood: Int = 100

    /**
     * 遭受攻击
     */
    override fun notifySuffer(attack: Attackable): Array<View>? {
        blood -= attack.attackPower
        return arrayOf(Blast(x, y))
    }

    /**
     * 我方坦克被销毁
     */
    override fun isDestroyed(): Boolean {
        return false
    }

    /**
     * 我方坦克的绘制
     */
    override fun draw() {
        //根据坦克的方向进行绘制
        val resources = when (currentDirection) {
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
     * 接收到了碰撞信息
     */
    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        collisionDirection = direction
    }

    /**
     * 发射子弹的方法
     */
    fun shot(): Bullet {

        return Bullet(currentDirection, this) { bulletWidth, bulletHeight ->
            var bulletX = 0
            var bulletY = 0

            when (currentDirection) {
                Direction.UP -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y + width - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = x - bulletWidth / 2
                    bulletY = y + (height - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = x + width - bulletWidth / 2
                    bulletY = y + (height - bulletHeight) / 2
                }
            }

            Pair(bulletX, bulletY)
        }


    }


}