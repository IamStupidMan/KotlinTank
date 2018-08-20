package com.summerdear.tankgame.model

import com.summerdear.tankgame.Config
import com.summerdear.tankgame.business.Attackable
import com.summerdear.tankgame.business.Destoryable
import com.summerdear.tankgame.business.Flyable
import com.summerdear.tankgame.business.Sufferable
import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.ext.checkCollision
import org.itheima.kotlin.game.core.Painter

/**
 * 此处Bullet的构造函数，接收一个两个参数，一个是Direction类型的，名字叫direction
 * 另一个是一个函数，名字叫create，这个函数返回一个Pair对象，里面包含两个Int类型的整数
 */
class Bullet(override val currentDirection: Direction, create: (width: Int, height: Int) -> Pair<Int, Int>) : Flyable, Destoryable, Attackable {
    /**
     * 子弹的攻击力
     */
    override val attackPower: Int = 1

    override val speed: Int = 8

    //子弹的坐标位置
    override var x: Int = 0
    override var y: Int = 0

    //子弹的宽和高
    override var width: Int = 0
    override var height: Int = 0

    private var isDestroyed = false

    //子弹的资源文件地址
    private val imagePath = when (currentDirection) {
        Direction.UP -> "img/shot_top.gif"
        Direction.DOWN -> "img/shot_bottom.gif"
        Direction.LEFT -> "img/shot_left.gif"
        Direction.RIGHT -> "img/shot_right.gif"
    }

    //Kotlin自带的函数，做一些初始化的操作
    init {

        //先根据资源文件的尺寸，计算子弹的宽高
        val size = Painter.size(imagePath)
        width = size[0]
        height = size[1]

        //再计算子弹的坐标
        //相当于调用传进来的create方法，并拿到此函数（create函数）的返回值
        val pair = create.invoke(width, height)
        //把计算好的子弹的坐标赋值给子弹
        x = pair.first
        y = pair.second
    }

    /**
     * 子弹的绘制
     */
    override fun draw() {
        Painter.drawImage(imagePath, x, y)
    }

    /**
     * 子弹飞行的方法
     */
    override fun fly() {
        //根据当前飞行的方向
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    /**
     * 判断是否销毁了
     */
    override fun isDestroyed(): Boolean {

        if (isDestroyed) return true

        //子弹在射出屏幕 需要被销毁
        if (x < -width) return true
        if (x > Config.gameWidth) return true
        if (y < -height) return true
        if (y > Config.gameHeight) return true
        return false
    }


    override fun isCollision(sufferable: Sufferable): Boolean {
        return checkCollision(sufferable)
    }

    override fun notifyAttack(sufferable: Sufferable) {
        //销毁子弹
        isDestroyed = true
    }

}