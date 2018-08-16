package com.summerdear.tankgame

import com.summerdear.tankgame.enums.Direction
import com.summerdear.tankgame.model.*
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import java.io.File

class GameWindow : Window(title = "坦克大战", icon = "img/logo.jpg", width = Config.gameWidth, height = Config.gameHeight) {

    //地图中所有元素的集合
    private var views = arrayListOf<View>()

    //我方坦克,晚点创建
    private lateinit var tank: Tank

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

        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)


    }

    override fun onDisplay() {
        //绘制地图中的元素
        views.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)
        }
    }

    override fun onRefresh() {
    }
}

fun main(args: Array<String>) {
    Application.launch(GameWindow::class.java)
}