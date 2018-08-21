package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.*
import com.summerdear.tankgame.enums.Direction
import org.itheima.kotlin.game.core.Painter
import java.util.*

/**
 * 敌方坦克
 */
class EnemyTank(override var x: Int, override var y: Int) : Movable, Flyable, Blockable, AutoShot, Sufferable, Destoryable {

    /**
     * 地方坦克的血量
     */
    override var blood: Int = 2

    /**
     *地方坦克被销毁
     */
    override fun isDestroyed(): Boolean {
        return blood <= 0
    }

    /**
     * 被攻击的回调
     */
    override fun notifySuffer(attack: Attackable): Array<View>? {

        //如果子弹是敌方坦克发出来的，不做被袭击的效果
        if (attack.owner is EnemyTank) {
            return null
        }

        blood -= attack.attackPower
        return arrayOf(Blast(x, y))
    }

    //当前的方向
    override var currentDirection: Direction = Direction.UP
    //速度
    override var speed: Int = 8

    //坦克的碰撞方向，默认为null
    private var collisionDirection: Direction? = null

    override var width: Int = Config.block
    override var height: Int = Config.block

    //上次射击的时间
    private var lastShotTimeMills = 0L
    //射击的频率
    private var ShotFrequency = 1000L


    //上次移动的时间
    private var lastMoveTimeMills = 0L
    //移动的频率
    private var MoveFrequency = 50L

    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        this.collisionDirection = direction
    }


    override fun draw() {
        //根据坦克的方向进行绘制
        val resources = when (currentDirection) {
            Direction.UP -> "img/enemy_1_u.gif"
            Direction.DOWN -> "img/enemy_1_d.gif"
            Direction.LEFT -> "img/enemy_1_l.gif"
            Direction.RIGHT -> "img/enemy_1_r.gif"
        }
        Painter.drawImage(resources, x, y)
    }

    /**
     * 自动移动
     */
    override fun fly() {

        val currentMills = System.currentTimeMillis()
        if (currentMills - lastMoveTimeMills < MoveFrequency) return
        lastMoveTimeMills = currentMills


        //如果当前行驶方向和要发生碰撞的方向一致，改变方向
        if (currentDirection == collisionDirection) {
            currentDirection = randomDirection(currentDirection)
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
     *随机一个方向
     */
    fun randomDirection(badDirection: Direction?): Direction {
        val i = Random().nextInt(4)
        var direction = when (i) {
            0 -> Direction.UP
            1 -> Direction.DOWN
            2 -> Direction.LEFT
            3 -> Direction.RIGHT
            else -> Direction.UP

        }

        if (direction == badDirection) {
            return randomDirection(badDirection)
        }

        return direction
    }

    /**
     * 自动射击
     */
    override fun autoShot(): View? {
        val currentMills = System.currentTimeMillis()
        if (currentMills - lastShotTimeMills < ShotFrequency) return null
        lastShotTimeMills = currentMills

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