package com.summerdear.tankgame

import com.summerdear.tankgame.business.*
import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.model.*
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(title = "坦克大战", icon = "img/logo.jpg", width = Config.gameWidth, height = Config.gameHeight) {

    //地图中所有元素的集合,线程安全的集合
    private var views = CopyOnWriteArrayList<View>()

    //我方坦克,晚点创建
    private lateinit var tank: Tank

    /**
     * 窗体创建的回调
     */
    override fun onCreate() {
        //设计地图
        val file = File(javaClass.getResource("/map/1.map").path)
        val lines = file.readLines()
        var lineNum = 0
        lines.forEach {
            var columnNum = 0
            it.toCharArray().forEach {
                when (it) {
                    '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                    '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                    '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))
                    '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
                }
                columnNum++
            }
            lineNum++
        }

        //先固定坦克的初始位置
        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)


    }

    /**
     * 窗体绘制展示的回调，一直在不断的执行
     */
    override fun onDisplay() {
        //绘制地图中的元素
        views.forEach {
            it.draw()
        }
    }

    /**
     * 接收用户按键操作
     */
    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)
            KeyCode.ENTER -> views.add(tank.shot())
        }
    }

    /**
     * 业务逻辑，耗时的操作，窗体刷新的回调
     */
    override fun onRefresh() {
        //业务逻辑


        //判断运动的物体和阻塞的物体是否发生碰撞
        //1.找到可移动的物体
        views.filter { it is Movable }.forEach moveTag@{ move ->
            //强转
            move as Movable
            //碰撞的方向和障碍物
            var collisionDirection: Direction? = null
            var collisionBlock: Blockable? = null
            //2.找到阻塞的物体,blockTAG@表示为此循环加一个Tag
            views.filter { it is Blockable }.forEach blockTag@{ block ->
                //3.遍历集合，找到是否发生碰撞
                //强转
                block as Blockable
                //获取碰撞的方向
                val direction = move.willCollision(block)

                //direction为空就不走let方法，不为空才走
                direction?.let letTag@{
                    //进来就说明找到了碰撞的物体，跳出当前循环
                    collisionDirection = direction
                    collisionBlock = block
                    return@blockTag
                }

            }

            //找到了move碰撞的block物体
            move.notifyCollision(collisionDirection, collisionBlock)
        }


        //检测有飞行能力的view，让他们自己动起来
        views.filter { it is Flyable }.forEach {
            it as Flyable
            it.fly()
        }

        //检测可销毁的view，移除
        views.filter { it is Destoryable }.forEach {
            if ((it as Destoryable).isDestroyed()) {
                views.remove(it)
            }
        }

        //检测具备攻击能力的view和被攻击的物体是否产生碰撞
        views.filter { it is Attackable }.forEach attackTag@{ attack ->
            views.filter { it is Sufferable }.forEach sufferTag@{ suffer ->
                attack as Attackable
                suffer as Sufferable
                //判断是否发生碰撞
                if (attack.isCollision(suffer)) {

                    //通知攻击者
                    attack.notifyAttack(suffer)

                    //通知被攻击者
                    val view = suffer.notifySuffer(attack)
                    view?.let {
                        //显示爆炸的效果
                        views.addAll(view)
                    }

                    return@sufferTag
                }
            }

        }

    }
}

fun main(args: Array<String>) {
    Application.launch(GameWindow::class.java)
}