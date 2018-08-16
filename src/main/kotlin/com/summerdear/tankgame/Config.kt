package com.summerdear.tankgame

object Config {

    /**
     * 小格子宽高，因为小格子是正方形的，所以每一个小格子的宽高都是64
     */
    val block = 64
    /**
     * 窗体的宽，有13个小格子
     */
    val gameWidth = block * 13
    /**
     * 窗体的高，有13个小格子
     */
    val gameHeight = block * 13
}