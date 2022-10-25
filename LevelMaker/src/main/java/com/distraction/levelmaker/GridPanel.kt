package com.distraction.levelmaker

import com.distraction.levelmaker.Tile.TileObject.*
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel
import javax.swing.SwingUtilities

class TileObjectData(val tileObject: Tile.TileObject, val row: Int, val col: Int, var row2: Int = -1, var col2: Int = -1)

class Tile(var row: Int, var col: Int, val rect: Rectangle = Rectangle(), tile: Boolean = false, active: Boolean = false, val objects: ArrayList<TileObject> = arrayListOf()) {
    enum class TileObject {
        GRAY,
        RIGHT,
        UP,
        DOWN,
        LEFT,
        JUMP,
        ICE,
        BLOCK,
        BUBBLE,
        FINISH,
        TELEPORT;

        fun isArrow() = this == RIGHT || this == UP || this == DOWN || this == LEFT
    }

    var tele: TileObjectData? = null
    var hasTele = false

    var tile = tile
        set(value) {
            if (!value) {
                active = false
                objects.clear()
            }
            field = value
        }

    var active = active
        set(value) {
            if (tile) {
                field = value
            }
        }

    private val inactiveColor = Color(0, 40, 0)
    private val activeColor = Color(0, 220, 0)
    private val arrowColor = Color(40, 100, 250)
    private val jumpColor = Color(250, 250, 40)
    private val iceColor = Color(176, 255, 241)
    private val teleportColor = Color(50, 150, 255)
    private val stroke3 = BasicStroke(3f)
    private val finishColor = Color(230, 50, 50)

    fun tileObject(tileObject: TileObject) {
        if (!tile) {
            return
        }
        if (objects.contains(tileObject)) {
            objects.remove(tileObject)
            return
        }

        if (tileObject != BLOCK) objects.removeAll { it == BLOCK }

        when {
            tileObject.isArrow() -> objects.removeAll {
                it.isArrow() || it == TELEPORT
            }
            tileObject == JUMP -> objects.removeAll {
                it == TELEPORT
            }
            tileObject == TELEPORT -> objects.removeAll {
                it.isArrow() || it == JUMP
            }
            tileObject == BLOCK -> objects.clear()
        }
        val index = if (tileObject.isArrow()) objects.size else 0
        objects.add(index, tileObject)
        hasTele = false
    }

    fun removeTeleport() {
        tele = null
        objects.removeAll {
            it == TELEPORT
        }
    }

    fun teleportObject(row: Int, col: Int) {
        if (!tile) {
            return
        }
        tele?.let {
            it.row2 = row
            it.col2 = col
        } ?: run {
            tele = TileObjectData(TELEPORT, row, col)
        }
    }

    fun draw(g: Graphics2D) {
        g.translate(rect.x, rect.y)
        if (tile) {
            g.color = if (active) activeColor else inactiveColor
            g.fillRect(0, 0, 32, 32)
        } else {
            g.color = Color.GRAY
            g.fillRect(0, 0, 32, 32)
        }
        val c = g.color
        val s = g.stroke
        g.stroke = stroke3
        for (tileObject in objects) {
            when (tileObject) {
                GRAY -> {
                    g.color = Color.DARK_GRAY
                    g.fillRect(0, 0, 32, 32)
                }
                LEFT -> {
                    g.color = arrowColor
                    g.drawLine(rect.width / 4, rect.height / 2, 3 * rect.width / 4, 3 * rect.height / 4)
                    g.drawLine(rect.width / 4, rect.height / 2, 3 * rect.width / 4, rect.height / 4)
                }
                UP -> {
                    g.color = arrowColor
                    g.drawLine(rect.width / 2, rect.height / 4, rect.width / 4, 3 * rect.height / 4)
                    g.drawLine(rect.width / 2, rect.height / 4, 3 * rect.width / 4, 3 * rect.height / 4)
                }
                RIGHT -> {
                    g.color = arrowColor
                    g.drawLine(3 * rect.width / 4, rect.height / 2, rect.width / 4, 3 * rect.height / 4)
                    g.drawLine(3 * rect.width / 4, rect.height / 2, rect.width / 4, rect.height / 4)
                }
                DOWN -> {
                    g.color = arrowColor
                    g.drawLine(rect.width / 2, 3 * rect.height / 4, rect.width / 4, rect.height / 4)
                    g.drawLine(rect.width / 2, 3 * rect.height / 4, 3 * rect.width / 4, rect.height / 4)
                }
                JUMP -> {
                    g.color = jumpColor
                    g.drawRect(rect.width / 4, rect.height / 4, rect.width / 2, rect.height / 2)
                }
                ICE -> {
                    g.color = iceColor
                    g.fillRect(rect.width / 4, rect.height / 4, rect.width / 2, rect.height / 2)
                }
                BLOCK -> {
                    g.color = finishColor
                    g.drawLine(rect.width / 4, rect.height / 4, 3 * rect.width / 4, 3 * rect.height / 4)
                    g.drawLine(rect.width / 4, 3 * rect.height / 4, 3 * rect.width / 4, rect.height / 4)
                }
                BUBBLE -> {
                    g.color = iceColor
                    g.drawOval(rect.width / 4, rect.height / 4, rect.width / 2, rect.height / 2)
                }
                FINISH -> {
                    g.color = finishColor
                    g.fillRect(rect.width / 8, rect.height / 8, rect.width / 4, rect.height / 4)
                }
                else -> {
                }
            }
        }
        g.color = c
        g.stroke = s
        g.translate(-rect.x, -rect.y)
    }

    fun drawTeleports(g: Graphics2D) {
        g.translate(rect.x, rect.y)
        val c = g.color
        val s = g.stroke
        g.stroke = stroke3
        for (tileObject in objects) {
            when (tileObject) {
                TELEPORT -> {
                    g.color = teleportColor
                    g.drawOval(rect.width / 4, rect.height / 4, rect.width / 2, rect.height / 2)
                    tele?.let {
                        if (it.row2 >= 0 && it.col2 >= 0) {
                            val xdiff = (it.col2 - it.col) * rect.width
                            val ydiff = (it.row2 - it.row) * rect.height
                            g.drawOval(
                                    rect.width / 4 + xdiff,
                                    rect.height / 4 + ydiff,
                                    rect.width / 2,
                                    rect.height / 2)
                            g.drawLine(rect.width / 2, rect.height / 2, rect.width / 2 + xdiff, rect.height / 2 + ydiff)
                        }
                    }
                }
                else -> {
                }
            }
        }
        g.color = c
        g.stroke = s
        g.translate(-rect.x, -rect.y)
    }
}

data class Vector2(var x: Int = 0, var y: Int = 0)

fun JPanel.paintImmediately() = paintImmediately(0, 0, width, height)

class GridPanel : JPanel() {
    var clickType = TilePanel.ClickType.TILE
        set(value) {
            if (value != TilePanel.ClickType.TELEPORT) {
                gridTele?.let {
                    val index = tileToIndex(it.row, it.col)
                    tiles[index].removeTeleport()
                    val index2 = tileToIndex(it.row2, it.col2)
                    if (index2 > 0) {
                        tiles[index2].hasTele = false
                    }
                }
                gridTele = null
                paintImmediately()
            }

            when (value) {
                TilePanel.ClickType.CLEAR -> {
                    for (tile in tiles) {
                        tile.tile = false
                    }
                    moveCount = -1
                    paintImmediately()
                }
                TilePanel.ClickType.DEACTIVATE -> {
                    for (tile in tiles) {
                        tile.active = false
                    }
                    moveCount = -1
                    paintImmediately()
                }
                else -> {
                    field = value
                }
            }
        }

    private val size = 32
    private val numRows = 24
    private val numCols = 24
    private val totalTiles = numRows * numCols
    val tiles = Array(totalTiles) {
        val (row, col) = indexToTile(it)
        Tile(row, col).apply {
            val (x, y) = tileToPosition(row, col)
            rect.setBounds(x, y, size, size)
        }
    }
    var moveCount = -1

    var gridTele: TileObjectData? = null

    init {
        preferredSize = Dimension(size * numCols, size * numRows)
        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent?) {
                e?.let {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        moveCount--
                        paintImmediately()
                        return
                    }
                    val (row, col) = positionToTile(e.x, e.y)
                    val index = tileToIndex(row, col)
                    with(tiles[index]) {
                        when (clickType) {
                            TilePanel.ClickType.TILE -> tile = !tile
                            TilePanel.ClickType.ACTIVE -> {
                                if (tile) {
                                    active = !active
                                    if (!e.isControlDown) {
                                        moveCount++
                                    }
                                }

                            }
                            TilePanel.ClickType.GRAY -> tileObject(GRAY)
                            TilePanel.ClickType.RIGHT -> tileObject(RIGHT)
                            TilePanel.ClickType.UP -> tileObject(UP)
                            TilePanel.ClickType.DOWN -> tileObject(DOWN)
                            TilePanel.ClickType.LEFT -> tileObject(LEFT)
                            TilePanel.ClickType.JUMP -> tileObject(JUMP)
                            TilePanel.ClickType.ICE -> tileObject(ICE)
                            TilePanel.ClickType.BLOCK -> tileObject(BLOCK)
                            TilePanel.ClickType.BUBBLE -> tileObject(BUBBLE)
                            TilePanel.ClickType.FINISH -> tileObject(FINISH)
                            TilePanel.ClickType.TELEPORT -> {
                                gridTele?.let {
                                    if (tile) {
                                        val index2 = tileToIndex(it.row, it.col)
                                        tiles[index2].teleportObject(row, col)
                                        hasTele = true
                                        gridTele = null
                                    }
                                } ?: run {
                                    if (tile) {
                                        gridTele = TileObjectData(TELEPORT, row, col)
                                        tileObject(TELEPORT)
                                        teleportObject(row, col)
                                    }
                                }
                            }
                            else -> {
                            }
                        }
                    }
                    paintImmediately()
                }
            }
        })
        isFocusable = true
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        for (tile in tiles) {
            tile.draw(g2)
        }
        for (tile in tiles) {
            tile.drawTeleports(g2)
        }
        val c = g2.color
        g2.color = Color.BLACK
        for (i in 0..size) {
            g2.drawLine(0, i * size, size * numCols, i * size)
            g2.drawLine(i * size, 0, i * size, size * numRows)
        }
        g2.color = Color.WHITE
        g2.fillRect(0, 0, 100, 20)
        g2.color = Color.BLACK
        g2.drawString("move count: $moveCount", 5, 15)
        g2.color = c
    }

    fun positionToTile(x: Int, y: Int) = Vector2(y / size, x / size)
    fun tileToPosition(row: Int, col: Int) = Vector2(col * size, row * size)
    fun indexToTile(index: Int) = Vector2(index / numCols, index % numCols)
    fun tileToIndex(row: Int, col: Int) = row * numCols + col

    /**
     * format:
    MapData(
    4, // numRows
    4, // numCols
    new int[] { // map
    0, 0, 0, 0,
    0, 0, 0, 0,
    0, 0, 0, 0,
    0, 0, 0, 0
    },
    19, // goal
    Arrays.asList(TilePoint(3, 3)), // player positions
    Arrays.asList( // objects
    ArrowData(1, 0, Direction.RIGHT),
    ArrowData(2, 3, Direction.LEFT),
    IceData(1, 1),
    IceData(2, 2)
    )
    )
     *
     */
    fun createLevelText(active: Boolean = false): String {
        val sb = StringBuilder()

        var minRow = numRows
        var minCol = numCols
        var maxRow = 0
        var maxCol = 0
        for (index in 0 until totalTiles) {
            if (tiles[index].tile) {
                val (row, col) = indexToTile(index)
                if (row < minRow) {
                    minRow = row
                }
                if (col < minCol) {
                    minCol = col
                }
                if (row > maxRow) {
                    maxRow = row
                }
                if (col > maxCol) {
                    maxCol = col
                }
            }
        }

        val numRows = maxRow - minRow + 1
        val numCols = maxCol - minCol + 1
        val grid = ArrayList<Char>(numRows * numCols)
        val objects = ArrayList<TileObjectData>()

        for (row in minRow..maxRow) {
            for (col in minCol..maxCol) {
                val index = tileToIndex(row, col)
                val tile = tiles[index]
                val arow = row - minRow
                val acol = col - minCol
                grid.add(
                        if (tile.tile) {
                            if (tile.objects.find { it == BLOCK } != null) {
                                'b'
                            } else if (tile.objects.find { it == GRAY } != null) {
                                'g'
                            } else {
                                if (active && tile.active) '1' else '0'
                            }
                        } else 'e'
                )
                for (tileObject in tile.objects) {
                    if (tileObject == TELEPORT) {
                        tile.tele?.let {
                            val arow2 = it.row2 - minRow
                            val acol2 = it.col2 - minCol
                            objects.add(TileObjectData(tileObject, arow, acol, arow2, acol2))
                        }
                    } else {
                        objects.add(TileObjectData(tileObject, arow, acol))
                    }
                }
            }
        }
        objects.sortWith(Comparator { t1, t2 ->
            if (t1.tileObject.isArrow()) -1 else 1
        })

        sb.append("new MapData(\n")
        sb.append("\t$numRows, $numCols,\n")
        sb.append("\tnew int[] {\n")
        sb.append("${grid.joinToString(", ", "\n", numCols)}\n")
        sb.append("\t},\n")
        sb.append("\t0,\n")
        sb.append("\tArrays.asList(new TilePoint(0, 0)),\n")
        if (objects.size > 0) {
            sb.append("\tArrays.asList(\n")
            sb.append("\t\t${objects.filterNot { it.tileObject == GRAY || it.tileObject == BLOCK }.joinToString(",\n", transform = {
                when (it.tileObject) {
                    RIGHT -> "new ArrowData(${it.row}, ${it.col}, Direction.RIGHT)"
                    UP -> "new ArrowData(${it.row}, ${it.col}, Direction.UP)"
                    DOWN -> "new ArrowData(${it.row}, ${it.col}, Direction.DOWN)"
                    LEFT -> "new ArrowData(${it.row}, ${it.col}, Direction.LEFT)"
                    JUMP -> "new SuperJumpData(${it.row}, ${it.col})"
                    ICE -> "new IceData(${it.row}, ${it.col})"
                    BUBBLE -> "new BubbleData(${it.row}, ${it.col})"
                    FINISH -> "new FinishTileData(${it.row}, ${it.col})"
                    TELEPORT -> {
                        "new TeleportData(${it.row}, ${it.col}, ${it.row2}, ${it.col2})," + "\n" +
                                "new TeleportData(${it.row2}, ${it.col2}, ${it.row}, ${it.col})"
                    }
                    else -> {""}
                }
            })}\n")
            sb.append("\t),\n")
        } else {
            sb.append("\tnew ArrayList<>(),\n")
        }
        // paths
        sb.append("\tnew ArrayList<>(),\n")
        // start bubble
        sb.append("\tfalse\n")
        sb.append("\t)")
        return sb.toString()
    }
}

fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ", separator2: CharSequence = "", separator2interval: Int = 0, prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
    return joinTo(StringBuilder(), separator, separator2, separator2interval, prefix, postfix, limit, truncated, transform).toString()
}

fun <T, A : Appendable> Iterable<T>.joinTo(buffer: A, separator: CharSequence = ", ", separator2: CharSequence = "", separator2interval: Int = 0, prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): A {
    buffer.append(prefix)
    var count = 0
    var count2 = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (++count2 > separator2interval) {
            count2 = 1
            buffer.append(separator2)
        }
        if (limit < 0 || count <= limit) {
            buffer.appendElement(element, transform)
        } else break
    }
    if (limit in 0 until count) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun <T> Appendable.appendElement(element: T, transform: ((T) -> CharSequence)?) {
    when {
        transform != null -> transform(element).let { if (it.isNotEmpty()) append(it) }
        element is CharSequence? -> append(element)
        element is Char -> append(element)
        else -> append(element.toString())
    }
}
